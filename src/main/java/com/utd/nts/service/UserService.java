package com.utd.nts.service;

import com.utd.nts.entity.NtsUserEntity;
import com.utd.nts.reqres.pojo.NtsUserResponse;

/**
 * 
 * @author navaneethkumarbuddi
 *
 */
public interface UserService {

	public abstract NtsUserResponse getUsers();

	public abstract NtsUserEntity getUserById(int clientId);

	public abstract void addUser(NtsUserEntity newUser);

}
