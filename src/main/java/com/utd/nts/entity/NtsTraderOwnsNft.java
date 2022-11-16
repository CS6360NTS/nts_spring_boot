package com.utd.nts.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

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
	@Column(name = "last_modified_date")
	LocalDateTime dateTime;

	@Column(name = "is_open_for_trade")
	boolean isOpenForTrade;

	@Column(name = "name_desc")
	String nameDesc;

	public NtsTraderOwnsNft() {
		super();
	}

	public NtsTraderOwnsNft(String tokenId, int clientId, LocalDateTime dateTime, boolean isOpenForTrade,
			String nameDesc) {
		super();
		this.tokenId = tokenId;
		this.clientId = clientId;
		this.dateTime = dateTime;
		this.isOpenForTrade = isOpenForTrade;
		this.nameDesc = nameDesc;
	}

	public String getNameDesc() {
		return nameDesc;
	}

	public void setNameDesc(String nameDesc) {
		this.nameDesc = nameDesc;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public boolean isOpenForTrade() {
		return isOpenForTrade;
	}

	public void setOpenForTrade(boolean isOpenForTrade) {
		this.isOpenForTrade = isOpenForTrade;
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
		return "NtsTraderOwnsNft [tokenId=" + tokenId + ", clientId=" + clientId + ", dateTime=" + dateTime
				+ ", isOpenForTrade=" + isOpenForTrade + ", nameDesc=" + nameDesc + "]";
	}
}
