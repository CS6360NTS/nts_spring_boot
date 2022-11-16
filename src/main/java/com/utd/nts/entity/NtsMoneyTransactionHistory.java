package com.utd.nts.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author navaneethkumarbuddi
 *
 */
@Entity
@Table(name = "nts_money_transaction")
public class NtsMoneyTransactionHistory {

	@Id
	@Column(name = "transaction_id")
	int transactionId;

	@Column(name = "amount")
	double amount;

	@Column(name = "payment_address")
	String paymentAddress;

	@Column(name = "money_transaction_type")
	String moneyTransactionType;

	@Column(name = "eth_usd_value")
	double ethUsdValue;

	@Column(name = "trans_desc")
	String transDesc;

	public NtsMoneyTransactionHistory() {
		super();
	}

	public NtsMoneyTransactionHistory(int transactionId, double amount, String paymentAddress,
			String moneyTransactionType, double ethUsdValue, String transDesc) {
		super();
		this.transactionId = transactionId;
		this.amount = amount;
		this.paymentAddress = paymentAddress;
		this.moneyTransactionType = moneyTransactionType;
		this.ethUsdValue = ethUsdValue;
		this.transDesc = transDesc;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentAddress() {
		return paymentAddress;
	}

	public void setPaymentAddress(String paymentAddress) {
		this.paymentAddress = paymentAddress;
	}

	public String getMoneyTransactionType() {
		return moneyTransactionType;
	}

	public void setMoneyTransactionType(String moneyTransactionType) {
		this.moneyTransactionType = moneyTransactionType;
	}

	public double getEthUsdValue() {
		return ethUsdValue;
	}

	public void setEthUsdValue(double ethUsdValue) {
		this.ethUsdValue = ethUsdValue;
	}

	public String getTransDesc() {
		return transDesc;
	}

	public void setTransDesc(String transDesc) {
		this.transDesc = transDesc;
	}

	@Override
	public String toString() {
		return "NtsMoneyTransactionHistory [transactionId=" + transactionId + ", amount=" + amount + ", paymentAddress="
				+ paymentAddress + ", moneyTransactionType=" + moneyTransactionType + ", ethUsdValue=" + ethUsdValue
				+ ", transDesc=" + transDesc + "]";
	}

}
