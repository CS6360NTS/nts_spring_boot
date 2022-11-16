package com.utd.nts.service;

import com.utd.nts.reqres.pojo.NFTRes;
import com.utd.nts.reqres.pojo.NFTsRes;
import com.utd.nts.reqres.pojo.NtsNftOwnsRes;

/**
 * 
 * @author NXB210086
 *
 */
public interface NFTService {

	public abstract NFTsRes getAllNtfs();

	public abstract NFTRes getNftByTokenId(String tokenId);

	public abstract NFTsRes getAllNtsWithContractEthereumAddress(String contractEthereumAddress);

	public abstract NFTsRes createNft(int clientId, String name, double ethPrice, int noOfCopies);

	public abstract NtsNftOwnsRes getAllNftsWithTheClientId(int clientId);

	public abstract NtsNftOwnsRes getAllNftsWithExcludingTheClientId(int clientId);
}
