package com.utd.nts.reqres.pojo;

import java.util.Collection;

import com.utd.nts.common.pojo.ServerStatusResponsePojo;
import com.utd.nts.entity.NtsNftEntity;

/**
 * 
 * @author NXB210086
 *
 */
public class NFTsRes {
	Collection<NtsNftEntity> nfts;
	ServerStatusResponsePojo serverResponse;

	public NFTsRes() {
		super();
	}

	public NFTsRes(Collection<NtsNftEntity> nfts, ServerStatusResponsePojo serverResponse) {
		super();
		this.nfts = nfts;
		this.serverResponse = serverResponse;
	}

	public Collection<NtsNftEntity> getNfts() {
		return nfts;
	}

	public void setNfts(Collection<NtsNftEntity> nfts) {
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
		return "NFTsRes [nfts=" + nfts + ", serverResponse=" + serverResponse + "]";
	}

}
