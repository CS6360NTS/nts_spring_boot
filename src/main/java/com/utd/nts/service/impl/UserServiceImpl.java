package com.utd.nts.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.utd.nts.entity.NtsUserEntity;
import com.utd.nts.repository.NtsUserRepository;
import com.utd.nts.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private NtsUserRepository ntsUserRepository;

	@Override
	public Collection<NtsUserEntity> getUsers() {
		return ntsUserRepository.findAll();
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
