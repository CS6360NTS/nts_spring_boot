package com.utd.nts.service;

import com.utd.nts.reqres.pojo.NFTRes;
import com.utd.nts.reqres.pojo.NFTsRes;

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

	public abstract NFTsRes getAllNftsWithTheClientId(int clientId);

	public abstract NFTsRes getAllNftsWithExcludingTheClientId(int clientId);
}
