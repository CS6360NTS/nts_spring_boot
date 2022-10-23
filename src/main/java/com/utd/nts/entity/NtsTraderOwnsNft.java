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
@Table(name = "nts_trader_owns_nft")
public class NtsTraderOwnsNft {

	@Id
	@Column(name = "token_id")
	String tokenId;

	@Column(name = "client_id")
	int clientId;
}
