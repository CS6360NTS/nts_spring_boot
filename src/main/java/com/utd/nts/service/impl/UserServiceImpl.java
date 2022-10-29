package com.utd.nts.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.utd.nts.common.pojo.ServerStatusResponsePojo;
import com.utd.nts.entity.NtsUserEntity;
import com.utd.nts.entity.NtsUserTraderEntity;
import com.utd.nts.repository.NtsUserRepository;
import com.utd.nts.repository.NtsUserTraderRepository;
import com.utd.nts.reqres.pojo.NtsTradeUserResponse;
import com.utd.nts.reqres.pojo.NtsUserResponse;
import com.utd.nts.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	public static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private NtsUserRepository ntsUserRepository;

	@Autowired
	private NtsUserTraderRepository ntsUserTraderRepository;

	@Override
	public NtsUserResponse getUsers() {
		NtsUserResponse response = new NtsUserResponse();
		ServerStatusResponsePojo serverRes = new ServerStatusResponsePojo();
		try {
			response.setCustomerList(ntsUserRepository.findAll());
			serverRes.setResponseCode(200);
			serverRes.setSuccess(true);
			response.setServerResponse(serverRes);
		} catch (Exception e) {
			log.error("Exception occured while fetching the user records form the DB" + e.getMessage());
			response.setCustomerList(null);
			serverRes.setErrorMessage("Error occured at UserServiceImpl.getUsers");
			serverRes.setResponseCode(500);
			serverRes.setSuccess(false);
			response.setServerResponse(serverRes);
			return response;
		}
		return response;
	}

	@Override
	public void addUser(NtsUserEntity newUser) {
		System.out.println(new Gson().toJson(newUser));

	}

	@Override
	public NtsTradeUserResponse getUserTraderById(int clientId) {
		NtsTradeUserResponse response = new NtsTradeUserResponse();
		ServerStatusResponsePojo serverRes = new ServerStatusResponsePojo();
		try {
			Optional<NtsUserEntity> userInfo = ntsUserRepository.findById(clientId);
			// System.out.println(new Gson().toJson(userInfo.get()));
			response.setUserInfo(userInfo.get());
			Optional<NtsUserTraderEntity> tradeInfo = ntsUserTraderRepository.findById(clientId);
			response.setTradeInfo(tradeInfo.get());
			serverRes.setResponseCode(200);
			serverRes.setSuccess(true);
			response.setServerResponse(serverRes);
		} catch (Exception e) {
			log.error("Exception occured while fetching the user/ user trade records form the DB" + e.getMessage());
			response.setTradeInfo(null);
			response.setUserInfo(null);
			serverRes.setErrorMessage("Error occured at UserServiceImpl.getUsers");
			serverRes.setResponseCode(500);
			serverRes.setSuccess(false);
			response.setServerResponse(serverRes);
			return response;
		}
		return response;
	}

}
