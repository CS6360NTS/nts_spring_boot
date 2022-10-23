package com.utd.nts.service;

import java.util.Collection;

import com.utd.nts.entity.NtsUserEntity;

/**
 * 
 * @author navaneethkumarbuddi
 *
 */
public interface UserService {

	public abstract Collection<NtsUserEntity> getUsers();

	public abstract NtsUserEntity getUserById(int clientId);

	public abstract void addUser(NtsUserEntity newUser);

}
