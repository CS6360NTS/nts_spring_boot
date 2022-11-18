package com.utd.nts.reqres.pojo;

import com.utd.nts.common.pojo.ServerStatusResponsePojo;
import com.utd.nts.entity.NtsUserEntity;
import com.utd.nts.entity.NtsUserManagerEntity;
import com.utd.nts.entity.NtsUserTraderEntity;

/**
 * 
 * @author NXB210086
 *
 */

public class NtsTradeUserResponse {
	NtsUserEntity userInfo;
	NtsUserTraderEntity tradeInfo;
	NtsUserManagerEntity managerInfo;

	ServerStatusResponsePojo serverResponse;

	public NtsTradeUserResponse() {
		super();
	}

	public NtsTradeUserResponse(NtsUserEntity userInfo, NtsUserTraderEntity tradeInfo,
			NtsUserManagerEntity ntsUserManagerEntity, ServerStatusResponsePojo serverResponse) {
		super();
		this.userInfo = userInfo;
		this.tradeInfo = tradeInfo;
		this.managerInfo = ntsUserManagerEntity;
		this.serverResponse = serverResponse;
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

	public ServerStatusResponsePojo getServerResponse() {
		return serverResponse;
	}

	public void setServerResponse(ServerStatusResponsePojo serverResponse) {
		this.serverResponse = serverResponse;
	}

	public NtsUserManagerEntity getNtsUserManagerEntity() {
		return managerInfo;
	}

	public void setNtsUserManagerEntity(NtsUserManagerEntity ntsUserManagerEntity) {
		this.managerInfo = ntsUserManagerEntity;
	}

	@Override
	public String toString() {
		return "NtsTradeUserResponse [userInfo=" + userInfo + ", tradeInfo=" + tradeInfo + ", ntsUserManagerEntity="
				+ managerInfo + ", serverResponse=" + serverResponse + "]";
	}

}
