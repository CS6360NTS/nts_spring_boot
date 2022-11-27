package com.utd.nts.reqres.pojo;

public class ManagerStatisticsRes {
	int moneyTransactionCount;
	int tradeTransactionCount;
	int tradeTransactionSuccessCount;
	int tradeTransactionCancellCount;
	double ethCommissionAmount;
	double faitCommissionAmount;

	public ManagerStatisticsRes() {
		super();
	}

	public ManagerStatisticsRes(int moneyTransactionCount, int tradeTransactionCount, int tradeTransactionSuccessCount,
			int tradeTransactionCancellCount, double ethCommissionAmount, double faitCommissionAmount) {
		super();
		this.moneyTransactionCount = moneyTransactionCount;
		this.tradeTransactionCount = tradeTransactionCount;
		this.tradeTransactionSuccessCount = tradeTransactionSuccessCount;
		this.tradeTransactionCancellCount = tradeTransactionCancellCount;
		this.ethCommissionAmount = ethCommissionAmount;
		this.faitCommissionAmount = faitCommissionAmount;
	}

	public int getTradeTransactionSuccessCount() {
		return tradeTransactionSuccessCount;
	}

	public void setTradeTransactionSuccessCount(int tradeTransactionSuccessCount) {
		this.tradeTransactionSuccessCount = tradeTransactionSuccessCount;
	}

	public int getTradeTransactionCancellCount() {
		return tradeTransactionCancellCount;
	}

	public void setTradeTransactionCancellCount(int tradeTransactionCancellCount) {
		this.tradeTransactionCancellCount = tradeTransactionCancellCount;
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
				+ tradeTransactionCount + ", tradeTransactionSuccessCount=" + tradeTransactionSuccessCount
				+ ", tradeTransactionCancellCount=" + tradeTransactionCancellCount + ", ethCommissionAmount="
				+ ethCommissionAmount + ", faitCommissionAmount=" + faitCommissionAmount + "]";
	}

}
