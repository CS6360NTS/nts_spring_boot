package com.utd.nts.service;

import com.utd.nts.reqres.pojo.NewUserRequest;
import com.utd.nts.reqres.pojo.NtsTradeUserResponse;
import com.utd.nts.reqres.pojo.NtsUserResponse;

/**
 * 
 * @author navaneethkumarbuddi
 *
 */
public interface UserService {

	public abstract NtsUserResponse getUsers();

	public abstract NtsTradeUserResponse getUserTraderById(int clientId);

	public abstract NtsTradeUserResponse addUser(NewUserRequest newUser);

}
