package com.utd.nts.reqres.pojo;

import java.util.Collection;

import com.utd.nts.common.pojo.ServerStatusResponsePojo;
import com.utd.nts.entity.NtsUserEntity;

public class NtsUserResponse {

	Collection<NtsUserEntity> customerList;

	ServerStatusResponsePojo serverResponse;

	public Collection<NtsUserEntity> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(Collection<NtsUserEntity> customerList) {
		this.customerList = customerList;
	}

	public ServerStatusResponsePojo getServerResponse() {
		return serverResponse;
	}

	public void setServerResponse(ServerStatusResponsePojo serverResponse) {
		this.serverResponse = serverResponse;
	}

}
