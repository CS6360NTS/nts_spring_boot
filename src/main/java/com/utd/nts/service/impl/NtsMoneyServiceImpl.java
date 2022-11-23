package com.utd.nts.service.impl;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
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
			Date d = java.sql.Timestamp.valueOf(LocalDateTime.now());
			save_req.setTransaction_date(new java.sql.Date(d.getTime()));
			save_req.setTransactionTime(Time.valueOf(LocalTime.now()));
			save_req = ntsTransactionHistoryRepo.save(save_req);

			System.out.println(new Gson().toJson(save_req));
//			System.out.println(LocalDateTime.now());
//			System.out.println(new Gson().toJson(new Date(new java.util.Date().getTime())));

			NtsMoneyTransactionHistory save_req_2 = new NtsMoneyTransactionHistory();

			save_req_2.setTransactionId(save_req.getTransactionId());
			save_req_2.setAmount(amount);
			save_req_2.setEthUsdValue(ethUsdService.getTheUsdValue());
			save_req_2.setMoneyTransactionType("Credit");
			save_req_2.setTransDesc("Credited money from BOFA -> NTS fiat currency wallet");
			long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
			save_req_2.setPaymentAddress("Ammount recived from BOFA Account number: " + number);
			save_req_2 = ntsMoneyTransactionHistoryRepo.save(save_req_2);
			// System.out.println(new Gson().toJson(save_req_2));

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

	@Override
	public ServerStatusResponsePojo debitMoneyFormWallet(int clientId, double amount) {
		ServerStatusResponsePojo serverRes = new ServerStatusResponsePojo();
		try {
			Optional<NtsUserTraderEntity> tradeInfo = ntsUserTraderRepository.findById(clientId);

			if (!tradeInfo.isEmpty() && tradeInfo.get().getBalance() >= amount) {
				NtsUserTraderEntity tradeInfoReq = tradeInfo.get();
				tradeInfoReq.setBalance(tradeInfoReq.getBalance() - amount);
				NtsUserTraderEntity tradeInfoAfterUpdate = ntsUserTraderRepository.save(tradeInfoReq);
				System.out.println(new Gson().toJson(tradeInfoAfterUpdate));

			} else {
				log.info("No trader info exist or insufficient balance");
				serverRes.setErrorMessage("No trader info exist for the given clientId or insufficient balance");
				serverRes.setResponseCode(200);
				serverRes.setSuccess(true);
				return serverRes;
			}

			NtsTransactionHistory save_req = new NtsTransactionHistory();
			save_req.setClient_id(clientId);
			save_req.setTransactionType("Money");
			save_req.setTransactionStatus("SUCCESS");
			Date d = java.sql.Timestamp.valueOf(LocalDateTime.now());
			save_req.setTransaction_date(new java.sql.Date(d.getTime()));
			save_req.setTransactionTime(Time.valueOf(LocalTime.now()));
			save_req = ntsTransactionHistoryRepo.save(save_req);

			System.out.println(new Gson().toJson(save_req));
//			System.out.println(LocalDateTime.now());
//			System.out.println(new Gson().toJson(new Date(new java.util.Date().getTime())));

			NtsMoneyTransactionHistory save_req_2 = new NtsMoneyTransactionHistory();

			save_req_2.setTransactionId(save_req.getTransactionId());
			save_req_2.setAmount(amount);
			save_req_2.setEthUsdValue(ethUsdService.getTheUsdValue());
			save_req_2.setMoneyTransactionType("Debit");
			save_req_2.setTransDesc("Debit money from NTS fiat currency wallet -> BOFA");
			long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
			save_req_2.setPaymentAddress("Amount: " + amount + "$ credited to BOFA Account number: " + number);
			save_req_2 = ntsMoneyTransactionHistoryRepo.save(save_req_2);
			// System.out.println(new Gson().toJson(save_req_2));

		} catch (Exception e) {
			log.error("Exception occured while updating the balance" + e.getMessage());
			serverRes.setErrorMessage("Error occured at NtsMoneyServiceImpl.debitMoneyFormWallet");
			serverRes.setResponseCode(500);
			serverRes.setSuccess(false);
			return serverRes;
		}
		serverRes.setErrorMessage("SUCCESS");
		serverRes.setResponseCode(200);
		serverRes.setSuccess(true);
		return serverRes;
	}

	@Override
	public ServerStatusResponsePojo transferWalletAmountToEth(int clientId, double amount) {
		ServerStatusResponsePojo serverRes = new ServerStatusResponsePojo();
		try {
			Optional<NtsUserTraderEntity> tradeInfo = ntsUserTraderRepository.findById(clientId);
			double usd_eth_value = ethUsdService.getTheUsdValue();
			if (!tradeInfo.isEmpty() && tradeInfo.get().getBalance() >= amount) {
				NtsUserTraderEntity tradeInfoReq = tradeInfo.get();
				tradeInfoReq.setEthBalance(tradeInfoReq.getEthBalance() + (amount / usd_eth_value));
				tradeInfoReq.setBalance(tradeInfoReq.getBalance() - amount);

				NtsUserTraderEntity tradeInfoAfterUpdate = ntsUserTraderRepository.save(tradeInfoReq);
				System.out.println(new Gson().toJson(tradeInfoAfterUpdate));

			} else {
				log.info("No trader info exist or insufficient balance");
				serverRes.setErrorMessage("No trader info exist for the given clientId or insufficient balance");
				serverRes.setResponseCode(200);
				serverRes.setSuccess(true);
				return serverRes;
			}

			NtsTransactionHistory save_req = new NtsTransactionHistory();
			save_req.setClient_id(clientId);
			save_req.setTransactionType("Money");
			save_req.setTransactionStatus("SUCCESS");
			Date d = java.sql.Timestamp.valueOf(LocalDateTime.now());
			save_req.setTransaction_date(new java.sql.Date(d.getTime()));
			save_req.setTransactionTime(Time.valueOf(LocalTime.now()));
			save_req = ntsTransactionHistoryRepo.save(save_req);

			System.out.println(new Gson().toJson(save_req));
//			System.out.println(LocalDateTime.now());
//			System.out.println(new Gson().toJson(new Date(new java.util.Date().getTime())));

			NtsMoneyTransactionHistory save_req_2 = new NtsMoneyTransactionHistory();

			save_req_2.setTransactionId(save_req.getTransactionId());
			save_req_2.setAmount(amount);
			save_req_2.setEthUsdValue(usd_eth_value);
			save_req_2.setMoneyTransactionType("Transfer");
			save_req_2.setTransDesc("Transfer money from NTS fiat currency wallet -> Eth wallet");
			// long number = (long) Math.floor(Math.random() * 9_000_000_000L) +
			// 1_000_000_000L;
			save_req_2.setPaymentAddress("Amount: " + amount + "$ transfered to Eth wallet corresponding value is "
					+ (amount / usd_eth_value) + " Ξ");
			save_req_2 = ntsMoneyTransactionHistoryRepo.save(save_req_2);
			// System.out.println(new Gson().toJson(save_req_2));

		} catch (Exception e) {
			log.error("Exception occured while transfer" + e.getMessage());
			serverRes.setErrorMessage("Error occured at NtsMoneyServiceImpl.transferWalletAmountToEth");
			serverRes.setResponseCode(500);
			serverRes.setSuccess(false);
			return serverRes;
		}
		serverRes.setErrorMessage("SUCCESS");
		serverRes.setResponseCode(200);
		serverRes.setSuccess(true);
		return serverRes;
	}

	@Override
	public ServerStatusResponsePojo transferWalletEthAmountToFait(int clientId, double amount) {
		ServerStatusResponsePojo serverRes = new ServerStatusResponsePojo();
		try {
			Optional<NtsUserTraderEntity> tradeInfo = ntsUserTraderRepository.findById(clientId);
			double usd_eth_value = ethUsdService.getTheUsdValue();
			if (!tradeInfo.isEmpty() && tradeInfo.get().getEthBalance() >= amount) {
				NtsUserTraderEntity tradeInfoReq = tradeInfo.get();
				tradeInfoReq.setEthBalance(tradeInfoReq.getEthBalance() - amount);
				tradeInfoReq.setBalance(tradeInfoReq.getBalance() + (usd_eth_value * amount));

				NtsUserTraderEntity tradeInfoAfterUpdate = ntsUserTraderRepository.save(tradeInfoReq);
				System.out.println(new Gson().toJson(tradeInfoAfterUpdate));

			} else {
				log.info("No trader info exist or insufficient balance");
				serverRes.setErrorMessage("No trader info exist for the given clientId or insufficient balance");
				serverRes.setResponseCode(200);
				serverRes.setSuccess(true);
				return serverRes;
			}

			NtsTransactionHistory save_req = new NtsTransactionHistory();
			save_req.setClient_id(clientId);
			save_req.setTransactionType("Money");
			save_req.setTransactionStatus("SUCCESS");
			Date d = java.sql.Timestamp.valueOf(LocalDateTime.now());
			save_req.setTransaction_date(new java.sql.Date(d.getTime()));
			save_req.setTransactionTime(Time.valueOf(LocalTime.now()));
			save_req = ntsTransactionHistoryRepo.save(save_req);

			System.out.println(new Gson().toJson(save_req));
//			System.out.println(LocalDateTime.now());
//			System.out.println(new Gson().toJson(new Date(new java.util.Date().getTime())));

			NtsMoneyTransactionHistory save_req_2 = new NtsMoneyTransactionHistory();

			save_req_2.setTransactionId(save_req.getTransactionId());
			save_req_2.setAmount(amount);
			save_req_2.setEthUsdValue(usd_eth_value);
			save_req_2.setMoneyTransactionType("Transfer");
			save_req_2.setTransDesc("Transfer money from NTS Eth wallet ->  fiat currency wallet");
			// long number = (long) Math.floor(Math.random() * 9_000_000_000L) +
			// 1_000_000_000L;
			save_req_2.setPaymentAddress("Amount: " + (usd_eth_value * amount)
					+ "$ transfered to fiat currency wallet from the eth wallet with the EthAddress: "
					+ tradeInfo.get().getEthereumAddress());
			save_req_2 = ntsMoneyTransactionHistoryRepo.save(save_req_2);
			// System.out.println(new Gson().toJson(save_req_2));

		} catch (Exception e) {
			log.error("Exception occured while transfer" + e.getMessage());
			serverRes.setErrorMessage("Error occured at NtsMoneyServiceImpl.transferWalletEthAmountToFait");
			serverRes.setResponseCode(500);
			serverRes.setSuccess(false);
			return serverRes;
		}
		serverRes.setErrorMessage("SUCCESS");
		serverRes.setResponseCode(200);
		serverRes.setSuccess(true);
		return serverRes;
	}

	@Override
	public ServerStatusResponsePojo addMoneyFromBofaToEthWallet(int clientId, double amount) {
		ServerStatusResponsePojo serverRes = new ServerStatusResponsePojo();
		try {
			Optional<NtsUserTraderEntity> tradeInfo = ntsUserTraderRepository.findById(clientId);
			double usd_eth_value = ethUsdService.getTheUsdValue();
			if (!tradeInfo.isEmpty()) {
				NtsUserTraderEntity tradeInfoReq = tradeInfo.get();
				tradeInfoReq.setEthBalance(tradeInfoReq.getEthBalance() + (amount / usd_eth_value));
				NtsUserTraderEntity tradeInfoAfterUpdate = ntsUserTraderRepository.save(tradeInfoReq);
				System.out.println(new Gson().toJson(tradeInfoAfterUpdate));

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
			Date d = java.sql.Timestamp.valueOf(LocalDateTime.now());
			save_req.setTransaction_date(new java.sql.Date(d.getTime()));
			save_req.setTransactionTime(Time.valueOf(LocalTime.now()));
			save_req = ntsTransactionHistoryRepo.save(save_req);

			System.out.println(new Gson().toJson(save_req));
//			System.out.println(LocalDateTime.now());
//			System.out.println(new Gson().toJson(new Date(new java.util.Date().getTime())));

			NtsMoneyTransactionHistory save_req_2 = new NtsMoneyTransactionHistory();

			save_req_2.setTransactionId(save_req.getTransactionId());
			save_req_2.setAmount(amount);
			save_req_2.setEthUsdValue(usd_eth_value);
			save_req_2.setMoneyTransactionType("Credit");
			save_req_2.setTransDesc("Credited money from BOFA -> NTS Eth currency wallet");
			long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
			save_req_2.setPaymentAddress("Amount credited to NTS Eth currency wallet from BOFA account: " + number);
			save_req_2 = ntsMoneyTransactionHistoryRepo.save(save_req_2);
			// System.out.println(new Gson().toJson(save_req_2));

		} catch (Exception e) {
			log.error("Exception occured while credit" + e.getMessage());
			serverRes.setErrorMessage("Error occured at NtsMoneyServiceImpl.addMoneyFromBofaToEthWallet");
			serverRes.setResponseCode(500);
			serverRes.setSuccess(false);
			return serverRes;
		}
		serverRes.setErrorMessage("SUCCESS");
		serverRes.setResponseCode(200);
		serverRes.setSuccess(true);
		return serverRes;
	}

	@Override
	public ServerStatusResponsePojo debitMoneyForEthmWallet(int clientId, double amount) {
		ServerStatusResponsePojo serverRes = new ServerStatusResponsePojo();
		double usd_eth_value = ethUsdService.getTheUsdValue();
		try {
			Optional<NtsUserTraderEntity> tradeInfo = ntsUserTraderRepository.findById(clientId);

			if (!tradeInfo.isEmpty() && tradeInfo.get().getEthBalance() >= amount) {
				NtsUserTraderEntity tradeInfoReq = tradeInfo.get();
				tradeInfoReq.setEthBalance(tradeInfoReq.getEthBalance() - amount);
				NtsUserTraderEntity tradeInfoAfterUpdate = ntsUserTraderRepository.save(tradeInfoReq);
				System.out.println(new Gson().toJson(tradeInfoAfterUpdate));

			} else {
				log.info("No trader info exist or insufficient balance");
				serverRes.setErrorMessage("No trader info exist for the given clientId or insufficient balance");
				serverRes.setResponseCode(200);
				serverRes.setSuccess(true);
				return serverRes;
			}

			NtsTransactionHistory save_req = new NtsTransactionHistory();
			save_req.setClient_id(clientId);
			save_req.setTransactionType("Money");
			save_req.setTransactionStatus("SUCCESS");
			Date d = java.sql.Timestamp.valueOf(LocalDateTime.now());
			save_req.setTransaction_date(new java.sql.Date(d.getTime()));
			save_req.setTransactionTime(Time.valueOf(LocalTime.now()));
			save_req = ntsTransactionHistoryRepo.save(save_req);

			System.out.println(new Gson().toJson(save_req));
			NtsMoneyTransactionHistory save_req_2 = new NtsMoneyTransactionHistory();

			save_req_2.setTransactionId(save_req.getTransactionId());
			save_req_2.setAmount(amount);
			save_req_2.setEthUsdValue(usd_eth_value);
			save_req_2.setMoneyTransactionType("Debit");
			save_req_2.setTransDesc("Debit money from NTS Eth wallet -> BOFA");
			long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
			save_req_2.setPaymentAddress("Amount: " + amount * usd_eth_value + "$ credit to BOFA Account number: "
					+ number + " from the Eth wallet with EthAddress: " + tradeInfo.get().getEthereumAddress());
			save_req_2 = ntsMoneyTransactionHistoryRepo.save(save_req_2);

		} catch (Exception e) {
			log.error("Exception occured while updating the balance" + e.getMessage());
			serverRes.setErrorMessage("Error occured at NtsMoneyServiceImpl.debitMoneyForEthmWallet");
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
