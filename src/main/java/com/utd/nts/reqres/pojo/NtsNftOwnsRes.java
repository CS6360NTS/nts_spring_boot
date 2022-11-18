package com.utd.nts.reqres.pojo;

import java.io.Serializable;
import java.util.Collection;

import com.utd.nts.common.pojo.ServerStatusResponsePojo;
import com.utd.nts.entity.NtsTraderOwnsNft;

/**
 * 
 * @author navaneethkumarbuddi
 *
 */
public class NtsNftOwnsRes implements Serializable {
	private static final long serialVersionUID = 1L;

	Collection<NtsTraderOwnsNft> nfts;
	ServerStatusResponsePojo serverResponse;

	public NtsNftOwnsRes() {
		super();
	}

	public NtsNftOwnsRes(Collection<NtsTraderOwnsNft> nfts, ServerStatusResponsePojo serverResponse) {
		super();
		this.nfts = nfts;
		this.serverResponse = serverResponse;
	}

	public Collection<NtsTraderOwnsNft> getNfts() {
		return nfts;
	}

	public void setNfts(Collection<NtsTraderOwnsNft> nfts) {
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
		return "NtsNftOwnsRes [nfts=" + nfts + ", serverResponse=" + serverResponse + "]";
	}

}
