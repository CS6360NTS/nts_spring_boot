package com.utd.nts.service.impl;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.utd.nts.common.pojo.ServerStatusResponsePojo;
import com.utd.nts.entity.NtsNftEntity;
import com.utd.nts.entity.NtsTradeTransactionHistory;
import com.utd.nts.entity.NtsTransactionHistory;
import com.utd.nts.entity.NtsUserTraderEntity;
import com.utd.nts.repository.NftRepo;
import com.utd.nts.repository.NtsTradeTransactionHistoryRepo;
import com.utd.nts.repository.NtsTransactionHistoryRepo;
import com.utd.nts.repository.NtsUserRepository;
import com.utd.nts.repository.NtsUserTraderRepository;
import com.utd.nts.reqres.pojo.NtsNftTradeReq;
import com.utd.nts.service.NtsNftTradeService;

/**
 * 
 * @author navaneethkumarbuddi
 *
 */
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
	private NtsUserRepository ntsUserRepository;

	@Override
	public ServerStatusResponsePojo validateAndCompleteTheTradeTransaction(NtsNftTradeReq req) {
		ServerStatusResponsePojo serverRes = new ServerStatusResponsePojo();
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
				save_req = ntsTransactionHistoryRepo.save(save_req);

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

	int processTheTransaction(String tokenId, NtsNftTradeReq req, NtsUserTraderEntity buyerTradeUserInfo,
			NtsTransactionHistory save_req) {
		int processedTransCount = 0;
		try {
			Optional<NtsNftEntity> nts_with_tokenId = nftRepo.findById(tokenId);
			Optional<NtsUserTraderEntity> SellerTradeInfo = ntsUserTraderRepository.findById(req.getClientId());
			if (!nts_with_tokenId.isEmpty() && !SellerTradeInfo.isEmpty()) {
				
				//NFT attribute value changes 
				NtsNftEntity current_nft = new NtsNftEntity();
				Date d = java.sql.Timestamp.valueOf(LocalDateTime.now());
				current_nft.setLastModifiedDate(null);
				save_req.setTransactionTime(Time.valueOf(LocalTime.now()));
				
				// TH2 (trader TH table) save request
				NtsTradeTransactionHistory tradeTransactionSaveReq = new NtsTradeTransactionHistory();
				tradeTransactionSaveReq.setTransactionId(save_req.getTransactionId());
				tradeTransactionSaveReq.setTradeTransactionType("Trade");
				tradeTransactionSaveReq.setCommissionPaid(req.getCommissionValue());
				tradeTransactionSaveReq.setCommissionType(req.getCommissionType());
				tradeTransactionSaveReq.setSellerEthereumAddress(SellerTradeInfo.get().getEthereumAddress());
				tradeTransactionSaveReq.setBuyerEthereumAddress(buyerTradeUserInfo.getEthereumAddress());
				tradeTransactionSaveReq.setEthereumValue(nts_with_tokenId.get().getEthPrice());
				tradeTransactionSaveReq.setNftAddress(nts_with_tokenId.get().getContractEthereumAddress());
				tradeTransactionSaveReq.setNftTockenId(nts_with_tokenId.get().getTokenId());
				ntsTradeTransactionHistoryRepo.save(tradeTransactionSaveReq);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}
		return processedTransCount;
	}

}
