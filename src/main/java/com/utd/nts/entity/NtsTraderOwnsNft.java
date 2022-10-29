package com.utd.nts.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author NXB210086
 *
 */
@Entity
@Table(name = "nts_trader_owns_nft")
public class NtsTraderOwnsNft implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 118110590753009845L;

	@Id
	@Column(name = "token_id")
	String tokenId;

	@Column(name = "client_id")
	int clientId;

	public NtsTraderOwnsNft() {
		super();
	}

	public NtsTraderOwnsNft(String tokenId, int clientId) {
		super();
		this.tokenId = tokenId;
		this.clientId = clientId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	@Override
	public String toString() {
		return "NtsTraderOwnsNft [tokenId=" + tokenId + ", clientId=" + clientId + "]";
	}
}
