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
 * @author navaneethkumarbuddi
 *
 */
@Entity
@Table(name = "nts_transaction_history_logs")
public class NtsTransactionHistoryLogsEntity implements Serializable {
	private static final long serialVersionUID = 5566348602039946356L;

	@Id
	@GeneratedValue
	@Column(name = "log_id")
	int logId;

	@Column(name = "client_id")
	int clientId;

	@Column(name = "transaction_id")
	int transactionId;

	@Column(name = "log_date")
	Date logDate;

	@Column(name = "log_time")
	Time logTime;

	@Column(name = "log_description")
	String logDescription;

	public NtsTransactionHistoryLogsEntity() {
		super();
	}

	public NtsTransactionHistoryLogsEntity(int logId, int clientId, int transactionId, Date logDate, Time logTime,
			String logDescription) {
		super();
		this.logId = logId;
		this.clientId = clientId;
		this.transactionId = transactionId;
		this.logDate = logDate;
		this.logTime = logTime;
		this.logDescription = logDescription;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public Time getLogTime() {
		return logTime;
	}

	public void setLogTime(Time logTime) {
		this.logTime = logTime;
	}

	public String getLogDescription() {
		return logDescription;
	}

	public void setLogDescription(String logDescription) {
		this.logDescription = logDescription;
	}

	@Override
	public String toString() {
		return "NtsTransactionHistoryLogsEntity [logId=" + logId + ", clientId=" + clientId + ", transactionId="
				+ transactionId + ", logDate=" + logDate + ", logTime=" + logTime + ", logDescription=" + logDescription
				+ "]";
	}
}
