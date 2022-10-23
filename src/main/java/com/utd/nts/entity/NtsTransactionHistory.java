package com.utd.nts.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nts_transaction_history")
public class NtsTransactionHistory {

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
}
