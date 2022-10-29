package com.utd.nts.reqres.pojo;

import com.utd.nts.common.pojo.ServerStatusResponsePojo;
import com.utd.nts.entity.NtsUserEntity;
import com.utd.nts.entity.NtsUserTraderEntity;

/**
 * 
 * @author NXB210086
 *
 */

public class NtsTradeUserResponse {
	NtsUserEntity userInfo;
	NtsUserTraderEntity tradeInfo;
	ServerStatusResponsePojo serverResponse;

	public NtsTradeUserResponse() {
		super();
	}

	public NtsTradeUserResponse(NtsUserEntity userInfo, NtsUserTraderEntity tradeInfo,
			ServerStatusResponsePojo serverResponse) {
		super();
		this.userInfo = userInfo;
		this.tradeInfo = tradeInfo;
		this.serverResponse = serverResponse;
	}

	@Override
	public String toString() {
		return "NtsTradeUserResponse [userInfo=" + userInfo + ", tradeInfo=" + tradeInfo + ", serverResponse="
				+ serverResponse + "]";
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
}
