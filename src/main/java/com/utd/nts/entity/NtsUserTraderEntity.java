package com.utd.nts.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nts_user_trader")
public class NtsUserTraderEntity {

	@Id
	@Column(name = "client_id")
	int clientId;

	@Column(name = "is_active")
	boolean isActive;

	@Column(name = "ethereum_address")
	String ethereumAddress;

	@Column(name = "balance")
	double balance;

	@Column(name = "trader_level")
	String traderLevel;

}
