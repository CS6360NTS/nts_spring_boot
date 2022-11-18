package com.utd.nts.reqres.pojo;

import com.utd.nts.common.pojo.ServerStatusResponsePojo;
import com.utd.nts.entity.NtsNftEntity;

/**
 * 
 * @author NXB210086
 *
 */
public class NFTRes {
	NtsNftEntity nfts;
	ServerStatusResponsePojo serverResponse;

	public NFTRes() {
		super();
	}

	public NFTRes(NtsNftEntity nfts, ServerStatusResponsePojo serverResponse) {
		super();
		this.nfts = nfts;
		this.serverResponse = serverResponse;
	}

	public NtsNftEntity getNfts() {
		return nfts;
	}

	public void setNfts(NtsNftEntity nfts) {
		this.nfts = nfts;
	}

	public ServerStatusResponsePojo getServerResponse() {
		return serverResponse;
	}

	public void setServerResponse(ServerStatusResponsePojo serverResponse) {
		this.serverResponse = serverResponse;
	}

	@Override
	public String toString() {
		return "NFTRes [nfts=" + nfts + ", serverResponse=" + serverResponse + "]";
	}
	
	
}
