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
@Table(name = "nts_nft")
public class NtsNftEntity {

	@Id
	@Column(name = "token_id")
	String tokenId;

	@Column(name = "contract_ethereum_address")
	String contractEthereumAddress;

	@Column(name = "name")
	String name;

	@Column(name = "description")
	String description;

}
