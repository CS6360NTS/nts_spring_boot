package com.utd.nts.reqres.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @author navaneethkumarbuddi
 *
 */
public class NtsNftTradeReq implements Serializable {

	private static final long serialVersionUID = 1L;

	Collection<String> tokenIds = new ArrayList<>();
	int clientId = 0;
	double totalCartValue;
	double commissionValue;
	String commissionType;

	public NtsNftTradeReq() {
		super();
	}

	public NtsNftTradeReq(Collection<String> tokenIds, int clientId, double totalCartValue, double commissionValue,
			String commissionType) {
		super();
		this.tokenIds = tokenIds;
		this.clientId = clientId;
		this.totalCartValue = totalCartValue;
		this.commissionValue = commissionValue;
		this.commissionType = commissionType;
	}

	public Collection<String> getTokenIds() {
		return tokenIds;
	}

	public void setTokenIds(Collection<String> tokenIds) {
		this.tokenIds = tokenIds;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public double getTotalCartValue() {
		return totalCartValue;
	}

	public void setTotalCartValue(double totalCartValue) {
		this.totalCartValue = totalCartValue;
	}

	public double getCommissionValue() {
		return commissionValue;
	}

	public void setCommissionValue(double commissionValue) {
		this.commissionValue = commissionValue;
	}

	public String getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(String commissionType) {
		this.commissionType = commissionType;
	}

	@Override
	public String toString() {
		return "NtsNftTradeReq [tokenIds=" + tokenIds + ", clientId=" + clientId + ", totalCartValue=" + totalCartValue
				+ ", commissionValue=" + commissionValue + ", commissionType=" + commissionType + "]";
	}

}
