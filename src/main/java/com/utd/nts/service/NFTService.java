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

	public abstract NFTsRes createNft(String name, String desc,int noOfCopies);
}
