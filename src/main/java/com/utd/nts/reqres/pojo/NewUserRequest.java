package com.utd.nts.reqres.pojo;

import java.io.Serializable;

import com.utd.nts.entity.NtsUserEntity;
import com.utd.nts.entity.NtsUserManagerEntity;
import com.utd.nts.entity.NtsUserTraderEntity;

/**
 * 
 * @author NXB210086
 *
 */
public class NewUserRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5417773290923549238L;
	NtsUserEntity userInfo;
	NtsUserTraderEntity tradeInfo;
	NtsUserManagerEntity managerInfo;

	public NewUserRequest() {
		super();
	}

	public NewUserRequest(NtsUserEntity userInfo, NtsUserTraderEntity tradeInfo, NtsUserManagerEntity managerInfo) {
		super();
		this.userInfo = userInfo;
		this.tradeInfo = tradeInfo;
		this.managerInfo = managerInfo;
	}

	public NtsUserEntity getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(NtsUserEntity userInfo) {
		this.userInfo = userInfo;
	}

	public NtsUserTraderEntity getTradeInfo() {
		return tradeInfo;
	}

	public void setTradeInfo(NtsUserTraderEntity tradeInfo) {
		this.tradeInfo = tradeInfo;
	}

	public NtsUserManagerEntity getManagerInfo() {
		return managerInfo;
	}

	public void setManagerInfo(NtsUserManagerEntity managerInfo) {
		this.managerInfo = managerInfo;
	}

	@Override
	public String toString() {
		return "NewUserRequest [userInfo=" + userInfo + ", tradeInfo=" + tradeInfo + ", managerInfo=" + managerInfo
				+ "]";
	}
	
	
}
