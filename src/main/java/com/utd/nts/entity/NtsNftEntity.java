package com.utd.nts.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

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
@Table(name = "nts_nft")
public class NtsNftEntity implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 472416056324728076L;

	@Id
	@Column(name = "token_id")
	String tokenId;

	@Column(name = "client_id")
	int clientId;

	@Column(name = "contract_ethereum_address")
	String contractEthereumAddress;

	@Column(name = "name")
	String name;

	@Column(name = "description")
	String description;

	@Column(name = "eth_price")
	double ethPrice;

	@Column(name = "last_modified_date")
	Date lastModifiedDate;

	@Column(name = "last_modified_time")
	Time lastModifiedTime;

	@Column(name = "last_client_id")
	int lastClientId;

	@Column(name = "is_open_for_trade")
	boolean isOpenForTrade;

	public NtsNftEntity() {
		super();
	}

	public NtsNftEntity(String tokenId, int clientId, String contractEthereumAddress, String name, String description,
			double ethPrice, Date lastModifiedDate, Time lastModifiedTime, int lastClientId, boolean isOpenForTrade) {
		super();
		this.tokenId = tokenId;
		this.clientId = clientId;
		this.contractEthereumAddress = contractEthereumAddress;
		this.name = name;
		this.description = description;
		this.ethPrice = ethPrice;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedTime = lastModifiedTime;
		this.lastClientId = lastClientId;
		this.isOpenForTrade = isOpenForTrade;
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

	public double getEthPrice() {
		return ethPrice;
	}

	public void setEthPrice(double ethPrice) {
		this.ethPrice = ethPrice;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Time getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Time lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public boolean isOpenForTrade() {
		return isOpenForTrade;
	}

	public void setOpenForTrade(boolean isOpenForTrade) {
		this.isOpenForTrade = isOpenForTrade;
	}

	public int getLastClientId() {
		return lastClientId;
	}

	public void setLastClientId(int lastClientId) {
		this.lastClientId = lastClientId;
	}

	@Override
	public String toString() {
		return "NtsNftEntity [tokenId=" + tokenId + ", clientId=" + clientId + ", contractEthereumAddress="
				+ contractEthereumAddress + ", name=" + name + ", description=" + description + ", ethPrice=" + ethPrice
				+ ", lastModifiedDate=" + lastModifiedDate + ", lastModifiedTime=" + lastModifiedTime
				+ ", lastClientId=" + lastClientId + ", isOpenForTrade=" + isOpenForTrade + "]";
	}

}
