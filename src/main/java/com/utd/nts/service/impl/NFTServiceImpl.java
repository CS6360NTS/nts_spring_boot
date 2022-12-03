package com.utd.nts.service.impl;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utd.nts.common.pojo.ServerStatusResponsePojo;
import com.utd.nts.entity.NtsNftEntity;
import com.utd.nts.repository.NftRepo;
import com.utd.nts.reqres.pojo.NFTRes;
import com.utd.nts.reqres.pojo.NFTsRes;
import com.utd.nts.reqres.pojo.NtsTradeUserResponse;
import com.utd.nts.service.EthUsdService;
import com.utd.nts.service.NFTService;
import com.utd.nts.service.UserService;

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

	@Autowired
	private UserService userService;

	@Autowired
	EthUsdService ethUsdService;

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
	public NFTsRes createNft(int clientId, String name, double ethPrice, int noOfCopies) {
		NtsTradeUserResponse userServiceRes = userService.getUserTraderById(clientId);
		NFTsRes res = new NFTsRes();
		if (userServiceRes.getUserInfo() != null) {

			Collection<NtsNftEntity> nfts = new ArrayList<>();
			ServerStatusResponsePojo serverRes = new ServerStatusResponsePojo();
			try {
				UUID uuid = UUID.randomUUID();
				// For now let's limit it's value to 99
				for (int i = 1; i <= Integer.min(noOfCopies, 99); i++) {
					nfts.add(createANftCopy(clientId, uuid.toString(), name, ethPrice, i));
				}
				res.setNfts(nfts);
				serverRes.setErrorMessage("SUCCESS");
			} catch (Exception e) {
				log.error("Exception occured while saving the NFT" + e.getMessage());
				serverRes.setErrorMessage("Error occured at NFTServiceImpl.createNft");
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
		res.setServerResponse(userServiceRes.getServerResponse());
		return res;
	}

	private NtsNftEntity createANftCopy(int clientId, String contractEthereumAddress, String name, double ethPrice,
			int copy) throws Exception {
		NtsNftEntity req = new NtsNftEntity();
		try {

			UUID uuid = UUID.randomUUID();
			req.setTokenId(uuid.toString());
			req.setContractEthereumAddress(contractEthereumAddress);
			req.setName(name);
			req.setDescription("Copy " + copy);
			req.setEthPrice(ethPrice);
			req.setClientId(clientId);
			req.setLastClientId(0);
			Date d = java.sql.Timestamp.valueOf(LocalDateTime.now());
			req.setLastModifiedDate(new java.sql.Date(d.getTime()));
			req.setLastModifiedTime(Time.valueOf(LocalTime.now()));
			req.setOpenForTrade(true);
			req = nftRepo.save(req);

		} catch (Exception e) {
			log.error("Exception occured while saving the NFTs" + e.getMessage());
			throw e;
		}
		return req;

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

	@Override
	public NFTsRes getAllNftsWithTheClientId(int clientId) {
		NFTsRes res = new NFTsRes();
		double usdAamount = ethUsdService.getTheUsdValue();
		Collection<NtsNftEntity> collection = new ArrayList<NtsNftEntity>();
		ServerStatusResponsePojo serverRes = new ServerStatusResponsePojo();
		try {

			res.setNfts(nftRepo.findAllNftsByClientId(clientId));
			for (NtsNftEntity ele : res.getNfts()) {
				double value = ele.getEthPrice();
				ele.setCurrentUSDValue(Math.round(usdAamount * value));
				collection.add(ele);

			}
			res.setNfts(collection);
		} catch (Exception e) {
			log.error("Exception occured while fetching the NFT's form the DB" + e.getMessage());
			serverRes.setErrorMessage("Error occured at NFTServiceImpl.getAllNftsWithTheClientId");
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
	public NFTsRes getAllNftsWithExcludingTheClientId(int clientId) {
		NFTsRes res = new NFTsRes();
		ServerStatusResponsePojo serverRes = new ServerStatusResponsePojo();
		try {
			res.setNfts(nftRepo.findAllNftsByExcludingClientId(clientId));
		} catch (Exception e) {
			log.error("Exception occured while fetching the NFT's form the DB" + e.getMessage());
			serverRes.setErrorMessage("Error occured at NFTServiceImpl.getAllNftsWithTheClientId");
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

}
