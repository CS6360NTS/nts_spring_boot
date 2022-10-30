package com.utd.nts.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utd.nts.common.pojo.ServerStatusResponsePojo;
import com.utd.nts.entity.NtsNftEntity;
import com.utd.nts.repository.NftRepo;
import com.utd.nts.reqres.pojo.NFTRes;
import com.utd.nts.reqres.pojo.NFTsRes;
import com.utd.nts.service.NFTService;

/**
 * 
 * @author NXB210086
 *
 */
@Service
public class NFTServiceImpl implements NFTService {
	public static final Logger log = LoggerFactory.getLogger(NFTServiceImpl.class);
	@Autowired
	private NftRepo nftRepo;

	@Override
	public NFTsRes getAllNtfs() {
		NFTsRes res = new NFTsRes();
		ServerStatusResponsePojo serverRes = new ServerStatusResponsePojo();
		try {
			res.setNfts(nftRepo.findAll());
		} catch (Exception e) {
			log.error("Exception occured while fetching the NFT's form the DB" + e.getMessage());
			serverRes.setErrorMessage("Error occured at NFTServiceImpl.getAllNtfs");
			serverRes.setResponseCode(500);
			serverRes.setSuccess(false);
			res.setServerResponse(serverRes);
			return res;
		}
		serverRes.setErrorMessage("SUCCESS");
		serverRes.setResponseCode(200);
		serverRes.setSuccess(true);
		res.setServerResponse(serverRes);
		return res;
	}

	@Override
	public NFTRes getNftByTokenId(String tokenId) {
		NFTRes res = new NFTRes();
		ServerStatusResponsePojo serverRes = new ServerStatusResponsePojo();
		try {
			Optional<NtsNftEntity> db_res = nftRepo.findById(tokenId);
			if (db_res.isEmpty()) {
				serverRes.setErrorMessage("No nfts found with the given token id");
				res.setNfts(null);
			} else {
				res.setNfts(db_res.get());
				serverRes.setErrorMessage("SUCCESS");
			}
		} catch (Exception e) {
			log.error("Exception occured while fetching the NFT with tokenId form the DB" + e.getMessage());
			serverRes.setErrorMessage("Error occured at NFTServiceImpl.getNftByTokenId");
			serverRes.setResponseCode(500);
			serverRes.setSuccess(false);
			res.setServerResponse(serverRes);
			return res;
		}
		serverRes.setResponseCode(200);
		serverRes.setSuccess(true);
		res.setServerResponse(serverRes);
		return res;
	}

	@Override
	public NFTsRes createNft(String name, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NFTsRes getAllNtsWithContractEthereumAddress(String contractEthereumAddress) {
		NFTsRes res = new NFTsRes();
		ServerStatusResponsePojo serverRes = new ServerStatusResponsePojo();
		try {
			Collection<NtsNftEntity> db_res = nftRepo.findAllByContractEthereumAddress(contractEthereumAddress);
			res.setNfts(db_res);
			serverRes.setErrorMessage("SUCCESS");
		} catch (Exception e) {
			log.error("Exception occured while fetching the NFTs with contractEthereumAddress form the DB"
					+ e.getMessage());
			serverRes.setErrorMessage("Error occured at NFTServiceImpl.getAllNtsWithContractEthereumAddress");
			serverRes.setResponseCode(500);
			serverRes.setSuccess(false);
			res.setServerResponse(serverRes);
			return res;
		}
		serverRes.setResponseCode(200);
		serverRes.setSuccess(true);
		res.setServerResponse(serverRes);
		return res;
	}

}
