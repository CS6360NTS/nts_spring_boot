package com.utd.nts.service;

import com.utd.nts.common.pojo.ServerStatusResponsePojo;

public interface NtsMoneyService {

	public abstract ServerStatusResponsePojo addMoneyFromBofa(int clientId, double amount);

	public abstract ServerStatusResponsePojo debitMoneyFormWallet(int clientId, double amount);

	public abstract ServerStatusResponsePojo transferWalletAmountToEth(int clientId, double amount);

	public abstract ServerStatusResponsePojo transferWalletEthAmountToFait(int clientId, double amount);

	public abstract ServerStatusResponsePojo addMoneyFromBofaToEthWallet(int clientId, double amount);

	public abstract ServerStatusResponsePojo debitMoneyForEthmWallet(int clientId, double amount);
}
