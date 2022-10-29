package com.utd.nts.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nts_nft")
public class NtsNftEntity implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 472416056324728076L;

	@Id
	@Column(name = "token_id")
	String tokenId;

	@Column(name = "contract_ethereum_address")
	String contractEthereumAddress;

	@Column(name = "name")
	String name;

	@Column(name = "description")
	String description;

	public NtsNftEntity() {
		super();
	}

	public NtsNftEntity(String tokenId, String contractEthereumAddress, String name, String description) {
		super();
		this.tokenId = tokenId;
		this.contractEthereumAddress = contractEthereumAddress;
		this.name = name;
		this.description = description;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getContractEthereumAddress() {
		return contractEthereumAddress;
	}

	public void setContractEthereumAddress(String contractEthereumAddress) {
		this.contractEthereumAddress = contractEthereumAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
