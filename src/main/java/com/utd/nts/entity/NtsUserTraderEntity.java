package com.utd.nts.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nts_user_trader")
public class NtsUserTraderEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9032056842062244289L;

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

	@Override
	public String toString() {
		return "NtsUserTraderEntity [clientId=" + clientId + ", isActive=" + isActive + ", ethereumAddress="
				+ ethereumAddress + ", balance=" + balance + ", traderLevel=" + traderLevel + "]";
	}

	public NtsUserTraderEntity() {
		super();
	}

	public NtsUserTraderEntity(int clientId, boolean isActive, String ethereumAddress, double balance,
			String traderLevel) {
		super();
		this.clientId = clientId;
		this.isActive = isActive;
		this.ethereumAddress = ethereumAddress;
		this.balance = balance;
		this.traderLevel = traderLevel;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getEthereumAddress() {
		return ethereumAddress;
	}

	public void setEthereumAddress(String ethereumAddress) {
		this.ethereumAddress = ethereumAddress;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getTraderLevel() {
		return traderLevel;
	}

	public void setTraderLevel(String traderLevel) {
		this.traderLevel = traderLevel;
	}

}
