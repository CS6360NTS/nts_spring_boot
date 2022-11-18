package com.utd.nts.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utd.nts.common.pojo.ServerStatusResponsePojo;
import com.utd.nts.entity.NtsUserEntity;
import com.utd.nts.entity.NtsUserManagerEntity;
import com.utd.nts.entity.NtsUserTraderEntity;
import com.utd.nts.repository.NtsUserManagerRepo;
import com.utd.nts.repository.NtsUserRepository;
import com.utd.nts.repository.NtsUserTraderRepository;
import com.utd.nts.reqres.pojo.NewUserRequest;
import com.utd.nts.reqres.pojo.NtsTradeUserResponse;
import com.utd.nts.reqres.pojo.NtsUserResponse;
import com.utd.nts.service.UserService;

/**
 * 
 * @author NXB210086
 *
 */
@Service
public class UserServiceImpl implements UserService {

	public static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private NtsUserRepository ntsUserRepository;

	@Autowired
	private NtsUserTraderRepository ntsUserTraderRepository;

	@Autowired
	private NtsUserManagerRepo ntsUserManagerRepo;

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
		serverRes.setErrorMessage("SUCCESS");
		serverRes.setResponseCode(200);
		serverRes.setSuccess(true);
		response.setServerResponse(serverRes);
		return response;
	}

	@Override
	public NtsTradeUserResponse getUserTraderById(int clientId) {
		NtsTradeUserResponse response = new NtsTradeUserResponse();
		ServerStatusResponsePojo serverRes = new ServerStatusResponsePojo();
		try {
			Optional<NtsUserEntity> userInfo = ntsUserRepository.findById(clientId);
			// System.out.println(new Gson().toJson(userInfo.get()));
			if (userInfo.isEmpty()) {
				serverRes.setErrorMessage("clientId doesn't exist");
				serverRes.setResponseCode(200);
				serverRes.setSuccess(true);
				response.setServerResponse(serverRes);
				return response;
			}
			response.setUserInfo(userInfo.get());

			// If trader get the trade info else get the manager info
			if (userInfo.get().getUserType() == 'T') {
				Optional<NtsUserTraderEntity> tradeInfo = ntsUserTraderRepository.findById(clientId);
				response.setTradeInfo(tradeInfo.get());
			} else {
				Optional<NtsUserManagerEntity> managerInfo = ntsUserManagerRepo.findById(clientId);
				response.setNtsUserManagerEntity(managerInfo.get());
			}
		} catch (Exception e) {
			log.error("Exception occured while fetching the user/ user trade records form the DB" + e.getMessage());
			response.setTradeInfo(null);
			response.setUserInfo(null);
			serverRes.setErrorMessage("Error occured at UserServiceImpl.getUserTraderById");
			serverRes.setResponseCode(500);
			serverRes.setSuccess(false);
			response.setServerResponse(serverRes);
			return response;
		}
		serverRes.setErrorMessage("SUCCESS");
		serverRes.setResponseCode(200);
		serverRes.setSuccess(true);
		response.setServerResponse(serverRes);
		return response;
	}

	@Override
	public NtsTradeUserResponse addUser(NewUserRequest newUser) {
		NtsTradeUserResponse response = new NtsTradeUserResponse();
		ServerStatusResponsePojo serverRes = new ServerStatusResponsePojo();
		try {
			NtsUserEntity userInfo = ntsUserRepository.save(newUser.getUserInfo());
			response.setUserInfo(userInfo);
			if (userInfo.getUserType() == 'T') {
				NtsUserTraderEntity reqObj = new NtsUserTraderEntity();

				// By default new user is active
				reqObj.setActive(Boolean.TRUE);

				// We are getting the client id that is auto generated
				reqObj.setClientId(userInfo.getClientId());

				// creating uuid and converting it to string
				UUID uuid = UUID.randomUUID();
				reqObj.setEthereumAddress(uuid.toString());

				// By default the new user will be in the silver level
				reqObj.setTraderLevel("silver");

				/**
				 * for now let's say every user we created is initialized with the default
				 * balance of 1000$
				 */
				reqObj.setBalance(1000);
				// System.out.println(new Gson().toJson(reqObj));
				NtsUserTraderEntity newNtsUserTraderEntity = ntsUserTraderRepository.save(reqObj);
				response.setTradeInfo(newNtsUserTraderEntity);
			} else {
				NtsUserManagerEntity managerInfo = new NtsUserManagerEntity();

				// We are getting the client id that is auto generated
				managerInfo.setClientId(userInfo.getClientId());
				// By default new manager is active
				managerInfo.setActive(Boolean.TRUE);
				managerInfo = ntsUserManagerRepo.save(managerInfo);
				response.setNtsUserManagerEntity(managerInfo);
			}
		} catch (Exception e) {
			log.error("Exception occured while creating a new user profile" + e.getMessage());
			response.setTradeInfo(null);
			response.setUserInfo(null);
			serverRes.setErrorMessage("Error occured at UserServiceImpl.addUser");
			serverRes.setResponseCode(500);
			serverRes.setSuccess(false);
			response.setServerResponse(serverRes);
			return response;
		}
		serverRes.setErrorMessage("SUCCESS");
		serverRes.setResponseCode(200);
		serverRes.setSuccess(true);
		response.setServerResponse(serverRes);
		return response;

	}

	@Override
	public NtsTradeUserResponse updateUserInfo(NewUserRequest updateUser) {
		NtsTradeUserResponse response = new NtsTradeUserResponse();
		ServerStatusResponsePojo serverRes = new ServerStatusResponsePojo();
		try {
			Optional<NtsUserEntity> userInfo = ntsUserRepository.findById(updateUser.getUserInfo().getClientId());
			NtsUserEntity resFromDb = userInfo.get();
			NtsUserEntity updateReq = updateUser.getUserInfo();

			// We will update all the details that was passed with the request
			if (updateReq.getFirstName() != null) {
				resFromDb.setFirstName(updateReq.getFirstName());
			}
			if (updateReq.getLastName() != null) {
				resFromDb.setLastName(updateReq.getLastName());
			}
			if (updateReq.getEmailId() != null) {
				resFromDb.setEmailId(updateReq.getEmailId());
			}
			if (updateReq.getPhoneNumber() != null) {
				resFromDb.setPhoneNumber(updateReq.getPhoneNumber());
			}

			if (updateReq.getCellPhoneNumber() != null) {
				resFromDb.setCellPhoneNumber(updateReq.getCellPhoneNumber());
			}
			if (updateReq.getStreetAddress() != null) {
				resFromDb.setStreetAddress(updateReq.getStreetAddress());
			}
			if (updateReq.getCity() != null) {
				resFromDb.setCity(updateReq.getCity());
			}
			if (updateReq.getState() != null) {
				resFromDb.setState(updateReq.getState());
			}
			if (updateReq.getZipCode() != null) {
				resFromDb.setZipCode(updateReq.getZipCode());
			}
			if (updateReq.getPassword() != null) {
				resFromDb.setPassword(updateReq.getPassword());
			}
			resFromDb = ntsUserRepository.save(resFromDb);
			response.setUserInfo(resFromDb);
		} catch (Exception e) {
			log.error("Exception occured while updating the user profile" + e.getMessage());
			response.setTradeInfo(null);
			response.setUserInfo(null);
			serverRes.setErrorMessage("Error occured while updating the user profile");
			serverRes.setResponseCode(500);
			serverRes.setSuccess(false);
			response.setServerResponse(serverRes);
			return response;
		}
		serverRes.setErrorMessage("SUCCESS");
		serverRes.setResponseCode(200);
		serverRes.setSuccess(true);
		response.setServerResponse(serverRes);
		return response;
	}

}
