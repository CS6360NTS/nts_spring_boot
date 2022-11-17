package com.utd.nts.service;

import com.utd.nts.common.pojo.ServerStatusResponsePojo;

public interface NtsMoneyService {

	public abstract ServerStatusResponsePojo addMoneyFromBofa(int clientId, double amount);

	ServerStatusResponsePojo debitMoneyFormWallet(int clientId, double amount);

	ServerStatusResponsePojo transferWalletAmountToEth(int clientId, double amount);

	ServerStatusResponsePojo transferWalletEthAmountToFait(int clientId, double amount);

	ServerStatusResponsePojo addMoneyFromBofaToEthWallet(int clientId, double amount);

	ServerStatusResponsePojo debitMoneyForEthmWallet(int clientId, double amount);
}
