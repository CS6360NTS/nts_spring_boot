package com.utd.nts.service.impl;

import java.sql.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.utd.nts.common.pojo.ServerStatusResponsePojo;
import com.utd.nts.entity.NtsMoneyTransactionHistory;
import com.utd.nts.entity.NtsTransactionHistory;
import com.utd.nts.entity.NtsUserTraderEntity;
import com.utd.nts.repository.NtsMoneyTransactionHistoryRepo;
import com.utd.nts.repository.NtsTransactionHistoryRepo;
import com.utd.nts.repository.NtsUserTraderRepository;
import com.utd.nts.service.EthUsdService;
import com.utd.nts.service.NtsMoneyService;

/**
 * 
 * @author navaneethkumarbuddi
 *
 */
@Service
public class NtsMoneyServiceImpl implements NtsMoneyService {

	public static final Logger log = LoggerFactory.getLogger(NFTServiceImpl.class);

	@Autowired
	private NtsTransactionHistoryRepo ntsTransactionHistoryRepo;

	@Autowired
	private NtsMoneyTransactionHistoryRepo ntsMoneyTransactionHistoryRepo;

	@Autowired
	private EthUsdService ethUsdService;

	@Autowired
	private NtsUserTraderRepository ntsUserTraderRepository;

	@Override
	public ServerStatusResponsePojo addMoneyFromBofa(int clientId, double amount) {
		ServerStatusResponsePojo serverRes = new ServerStatusResponsePojo();
		try {
			Optional<NtsUserTraderEntity> tradeInfo = ntsUserTraderRepository.findById(clientId);

			if (!tradeInfo.isEmpty()) {
				NtsUserTraderEntity tradeInfoReq = tradeInfo.get();
				tradeInfoReq.setBalance(tradeInfoReq.getBalance() + amount);
				ntsUserTraderRepository.save(tradeInfoReq);

			} else {
				log.info("No trader info exist");
				serverRes.setErrorMessage("No trader info exist for the given clientId");
				serverRes.setResponseCode(200);
				serverRes.setSuccess(true);
				return serverRes;
			}

			NtsTransactionHistory save_req = new NtsTransactionHistory();
			save_req.setClient_id(clientId);
			save_req.setTransactionType("Money");
			save_req.setTransactionStatus("SUCCESS");
			save_req.setTransaction_date(new Date(new java.util.Date().getTime()));
			save_req = ntsTransactionHistoryRepo.save(save_req);
			System.out.println(new Gson().toJson(save_req));
			NtsMoneyTransactionHistory save_req_2 = new NtsMoneyTransactionHistory();

			save_req_2.setTransactionId(save_req.getTransactionId());
			save_req_2.setAmount(amount);
			save_req_2.setEthUsdValue(ethUsdService.getTheUsdValue());
			save_req_2.setMoneyTransactionType("credit");
			save_req_2.setTransDesc("Credited money from BOFA -> NTS fiat currency wallet");
			long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
			save_req_2.setPaymentAddress("Ammount recived from BOFA Account number: " + number);
			save_req_2 = ntsMoneyTransactionHistoryRepo.save(save_req_2);
			System.out.println(new Gson().toJson(save_req_2));

		} catch (Exception e) {
			log.error("Exception occured while updating the balance" + e.getMessage());
			serverRes.setErrorMessage("Error occured at NtsMoneyServiceImpl.addMoneyFromBofa");
			serverRes.setResponseCode(500);
			serverRes.setSuccess(false);
			return serverRes;
		}
		serverRes.setErrorMessage("SUCCESS");
		serverRes.setResponseCode(200);
		serverRes.setSuccess(true);
		return serverRes;
	}

}
