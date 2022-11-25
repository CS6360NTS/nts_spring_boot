package com.utd.nts.service.impl;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utd.nts.common.pojo.ServerStatusResponsePojo;
import com.utd.nts.entity.NtsCommissionEntity;
import com.utd.nts.entity.NtsNftEntity;
import com.utd.nts.entity.NtsTradeTransactionHistory;
import com.utd.nts.entity.NtsTradeTransactionHistoryPrimaryKey;
import com.utd.nts.entity.NtsTransactionHistory;
import com.utd.nts.entity.NtsUserTraderEntity;
import com.utd.nts.repository.NftRepo;
import com.utd.nts.repository.NtsCommissionRepo;
import com.utd.nts.repository.NtsTradeTransactionHistoryRepo;
import com.utd.nts.repository.NtsTransactionHistoryRepo;
import com.utd.nts.repository.NtsUserTraderRepository;
import com.utd.nts.reqres.pojo.NtsNftTradeReq;
import com.utd.nts.service.EthUsdService;
import com.utd.nts.service.NtsNftTradeService;

/**
 * 
 * @author navaneethkumarbuddi
 *
 */
@Service
public class NtsNftTradeServiceImpl implements NtsNftTradeService {

	public static final Logger log = LoggerFactory.getLogger(NtsNftTradeServiceImpl.class);

	@Autowired
	private NtsTransactionHistoryRepo ntsTransactionHistoryRepo;

	@Autowired
	private NtsTradeTransactionHistoryRepo ntsTradeTransactionHistoryRepo;

	@Autowired
	private NtsUserTraderRepository ntsUserTraderRepository;

	@Autowired
	private NftRepo nftRepo;

	@Autowired
	private EthUsdService ethUsdService;

	@Autowired
	private NtsCommissionRepo ntsCommissionRepo;

	@Override
	public ServerStatusResponsePojo validateAndCompleteTheTradeTransaction(NtsNftTradeReq req) {
		ServerStatusResponsePojo serverRes = new ServerStatusResponsePojo();
		// By default we will consider eth
		if (req.getCommissionType() == null)
			req.setCommissionType("eth");

		try {
			if (req.getClientId() != 0) {
				Optional<NtsUserTraderEntity> tradeInfo = ntsUserTraderRepository.findById(req.getClientId());
				if (tradeInfo.isEmpty()) {
					serverRes.setErrorMessage("Buyer clientId doesn't exist");
					serverRes.setResponseCode(200);
					serverRes.setSuccess(true);
					return serverRes;
				}
				NtsUserTraderEntity buyerTradeUserInfo = tradeInfo.get();
				if (req.getTotalCartValue() > buyerTradeUserInfo.getEthBalance()) {
					serverRes.setErrorMessage("Buyer doesn't have sufficient funds to complete this transaction");
					serverRes.setResponseCode(200);
					serverRes.setSuccess(true);
					return serverRes;
				}

				// Transaction history table 1 save request
				NtsTransactionHistory save_req = new NtsTransactionHistory();
				save_req.setClient_id(req.getClientId());
				save_req.setTransactionType("Trade");
				save_req.setTransactionStatus("SUCCESS");
				Date d = java.sql.Timestamp.valueOf(LocalDateTime.now());
				save_req.setTransaction_date(new java.sql.Date(d.getTime()));
				save_req.setTransactionTime(Time.valueOf(LocalTime.now()));
				NtsTransactionHistory save_req_res = ntsTransactionHistoryRepo.save(save_req);

				// process all the tokens, we will validate them, but will proceed only if the
				// user has the sufficient balance
				List<String> tokenIds = (List<String>) req.getTokenIds();
				for (int i = 0; i < tokenIds.size(); i++) {
					buyerTradeUserInfo = processTheTransaction(tokenIds.get(i), req, buyerTradeUserInfo, save_req_res);
				}

			} else {
				serverRes.setErrorMessage("ClientId is required");
				serverRes.setResponseCode(200);
				serverRes.setSuccess(true);
				return serverRes;
			}
		} catch (Exception e) {
			serverRes.setErrorMessage("Exception occured while processing the transaction");
			serverRes.setResponseCode(500);
			serverRes.setSuccess(false);
			return serverRes;
		}
		serverRes.setErrorMessage("SUCCESS");
		serverRes.setResponseCode(200);
		serverRes.setSuccess(true);
		return serverRes;
	}

	NtsUserTraderEntity processTheTransaction(String tokenId, NtsNftTradeReq req, NtsUserTraderEntity buyerTraderInfo,
			NtsTransactionHistory save_req) throws Exception {
		try {
			Optional<NtsNftEntity> nts_with_tokenId = nftRepo.findById(tokenId);
			Optional<NtsCommissionEntity> ntsCommissionEntity = ntsCommissionRepo.findById(1);
			NtsCommissionEntity ntsCommissionEntitySaveReq = ntsCommissionEntity.get();
			double commisionAmmount = 0;
			if (!nts_with_tokenId.isEmpty()) {
				Optional<NtsUserTraderEntity> sellerTraderInfo = ntsUserTraderRepository
						.findById(nts_with_tokenId.get().getClientId());
				if (sellerTraderInfo.isEmpty()) {
					return null;
				}
				if (buyerTraderInfo.getEthBalance() <= nts_with_tokenId.get().getEthPrice()) {
					return null;
				}
				NtsUserTraderEntity buyerTraderInfoSaveReq = buyerTraderInfo;
				buyerTraderInfoSaveReq
						.setEthBalance(buyerTraderInfoSaveReq.getEthBalance() - nts_with_tokenId.get().getEthPrice());
				NtsUserTraderEntity sellerTraderInfoSaveReq = sellerTraderInfo.get();
				sellerTraderInfoSaveReq
						.setEthBalance(sellerTraderInfoSaveReq.getEthBalance() + nts_with_tokenId.get().getEthPrice());
				// NFT attribute value changes
				NtsNftEntity current_nft = nts_with_tokenId.get();
				current_nft.setClientId(req.getClientId());
				Date d = java.sql.Timestamp.valueOf(LocalDateTime.now());
				current_nft.setLastModifiedDate(new java.sql.Date(d.getTime()));
				current_nft.setLastModifiedTime(Time.valueOf(LocalTime.now()));
				if (buyerTraderInfo.getTraderLevel().toLowerCase() == "silver") {
					commisionAmmount = (req.getCommissionType().toLowerCase().equals("eth"))
							? nts_with_tokenId.get().getEthPrice() * 0.15
							: nts_with_tokenId.get().getEthPrice() * 0.15 * ethUsdService.getTheUsdValue();
				} else {
					commisionAmmount = (req.getCommissionType().toLowerCase().equals("eth"))
							? nts_with_tokenId.get().getEthPrice() * 0.10
							: nts_with_tokenId.get().getEthPrice() * 0.10 * ethUsdService.getTheUsdValue();
				}
				if (req.getCommissionType().toLowerCase().equals("fait")) {
					buyerTraderInfoSaveReq.setEthBalance(buyerTraderInfoSaveReq.getEthBalance() - commisionAmmount);
					ntsCommissionEntitySaveReq.setFiatCurrencyAmount(
							ntsCommissionEntitySaveReq.getFiatCurrencyAmount() + commisionAmmount);

				} else {
					buyerTraderInfoSaveReq.setBalance(buyerTraderInfoSaveReq.getBalance() - commisionAmmount);
					ntsCommissionEntitySaveReq.setEthCommisionAmount(
							ntsCommissionEntitySaveReq.getEthCommisionAmount() + commisionAmmount);
				}
				// TH2 (trader TH table) save request
				NtsTradeTransactionHistory tradeTransactionSaveReq = new NtsTradeTransactionHistory();
				tradeTransactionSaveReq.setPrimaryKey(new NtsTradeTransactionHistoryPrimaryKey(
						save_req.getTransactionId(), nts_with_tokenId.get().getTokenId()));
				tradeTransactionSaveReq.setTradeTransactionType("Trade");
				tradeTransactionSaveReq.setCommissionPaid(commisionAmmount);
				tradeTransactionSaveReq.setCommissionType(req.getCommissionType());
				tradeTransactionSaveReq.setSellerEthereumAddress(sellerTraderInfoSaveReq.getEthereumAddress());
				tradeTransactionSaveReq.setBuyerEthereumAddress(buyerTraderInfo.getEthereumAddress());
				tradeTransactionSaveReq.setEthereumValue(nts_with_tokenId.get().getEthPrice());
				tradeTransactionSaveReq.setNftAddress(nts_with_tokenId.get().getContractEthereumAddress());

				// DB updates
				buyerTraderInfo = ntsUserTraderRepository.save(buyerTraderInfoSaveReq);
				ntsUserTraderRepository.save(sellerTraderInfoSaveReq);
				ntsTradeTransactionHistoryRepo.save(tradeTransactionSaveReq);
				ntsCommissionRepo.save(ntsCommissionEntitySaveReq);
			}
		} catch (Exception e) {
			throw e;
		}
		return buyerTraderInfo;
	}

}
