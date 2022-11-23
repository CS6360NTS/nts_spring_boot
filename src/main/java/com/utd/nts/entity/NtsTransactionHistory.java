package com.utd.nts.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author NXB210086
 *
 */
@Entity
@Table(name = "nts_transaction_history")
public class NtsTransactionHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3585868968307228354L;

	@Id
	@GeneratedValue
	@Column(name = "transaction_id")
	int transactionId;

	@Column(name = "transaction_type")
	String transactionType;

	@Column(name = "client_id")
	int client_id;

	@Column(name = "transaction_status")
	String transactionStatus;

	@Column(name = "transaction_date")
	Date transaction_date;

	@Column(name = "transaction_time")
	Time transactionTime;

	public NtsTransactionHistory() {
		super();
	}

	public NtsTransactionHistory(int transactionId, String transactionType, int client_id, String transactionStatus,
			Date transaction_date, Time transactionTime) {
		super();
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.client_id = client_id;
		this.transactionStatus = transactionStatus;
		this.transaction_date = transaction_date;
		this.transactionTime = transactionTime;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Date getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}

	public Time getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Time transactionTime) {
		this.transactionTime = transactionTime;
	}

	@Override
	public String toString() {
		return "NtsTransactionHistory [transactionId=" + transactionId + ", transactionType=" + transactionType
				+ ", client_id=" + client_id + ", transactionStatus=" + transactionStatus + ", transaction_date="
				+ transaction_date + ", transactionTime=" + transactionTime + "]";
	}

}
