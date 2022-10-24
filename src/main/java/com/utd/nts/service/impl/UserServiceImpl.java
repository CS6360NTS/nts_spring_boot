package com.utd.nts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.utd.nts.common.pojo.ServerStatusResponsePojo;
import com.utd.nts.entity.NtsUserEntity;
import com.utd.nts.repository.NtsUserRepository;
import com.utd.nts.reqres.pojo.NtsUserResponse;
import com.utd.nts.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private NtsUserRepository ntsUserRepository;

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
			// If server error occurs, it will be handled here.
			System.out.println("Error occured at UserServiceImpl.getUsers");
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
	public NtsUserEntity getUserById(int clientId) {

		return ntsUserRepository.findById(clientId).orElse(null);
	}

	@Override
	public void addUser(NtsUserEntity newUser) {
		System.out.println(new Gson().toJson(newUser));

	}

}
