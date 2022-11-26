package com.utd.nts.reqres.pojo;

public class ManagerStatisticsRes {
	int moneyTransactionCount;
	int tradeTransactionCount;
	double ethCommissionAmount;
	double faitCommissionAmount;

	public ManagerStatisticsRes() {
		super();
	}

	public ManagerStatisticsRes(int moneyTransactionCount, int tradeTransactionCount, double ethCommissionAmount,
			double faitCommissionAmount) {
		super();
		this.moneyTransactionCount = moneyTransactionCount;
		this.tradeTransactionCount = tradeTransactionCount;
		this.ethCommissionAmount = ethCommissionAmount;
		this.faitCommissionAmount = faitCommissionAmount;
	}

	public int getMoneyTransactionCount() {
		return moneyTransactionCount;
	}

	public void setMoneyTransactionCount(int moneyTransactionCount) {
		this.moneyTransactionCount = moneyTransactionCount;
	}

	public int getTradeTransactionCount() {
		return tradeTransactionCount;
	}

	public void setTradeTransactionCount(int tradeTransactionCount) {
		this.tradeTransactionCount = tradeTransactionCount;
	}

	public double getEthCommissionAmount() {
		return ethCommissionAmount;
	}

	public void setEthCommissionAmount(double ethCommissionAmount) {
		this.ethCommissionAmount = ethCommissionAmount;
	}

	public double getFaitCommissionAmount() {
		return faitCommissionAmount;
	}

	public void setFaitCommissionAmount(double faitCommissionAmount) {
		this.faitCommissionAmount = faitCommissionAmount;
	}

	@Override
	public String toString() {
		return "ManagerStatisticsRes [moneyTransactionCount=" + moneyTransactionCount + ", tradeTransactionCount="
				+ tradeTransactionCount + ", ethCommissionAmount=" + ethCommissionAmount + ", faitCommissionAmount="
				+ faitCommissionAmount + "]";
	}

}
