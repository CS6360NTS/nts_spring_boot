package com.utd.nts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utd.nts.common.pojo.ServerStatusResponsePojo;
import com.utd.nts.reqres.pojo.NFTRes;
import com.utd.nts.reqres.pojo.NFTsRes;
import com.utd.nts.reqres.pojo.NewUserRequest;
import com.utd.nts.reqres.pojo.NtsNftOwnsRes;
import com.utd.nts.reqres.pojo.NtsTradeUserResponse;
import com.utd.nts.reqres.pojo.NtsUserResponse;
import com.utd.nts.service.NFTService;
import com.utd.nts.service.NtsMoneyService;
import com.utd.nts.service.UserService;

@RestController
@RequestMapping(value = "/nts")
public class NTSController {

	@Autowired
	UserService userService;

	@Autowired
	NFTService nFTService;

	@Autowired
	NtsMoneyService ntsMoneyService;

	/** User API's **/
	@GetMapping("/demo")
	public String demo() {
		return "NTS server is UP!!!";
	}

	@GetMapping("/users")
	public NtsUserResponse getAllUsers() {
		return userService.getUsers();
	}

	@GetMapping("/user")
	public NtsTradeUserResponse getUserWithId(@RequestParam int clientId) {
		return userService.getUserTraderById(clientId);
	}

	@PostMapping(path = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public NtsTradeUserResponse addUser(@RequestBody NewUserRequest user) {
		System.out.println(user.toString());
		return userService.addUser(user);
	}

	@PostMapping(path = "/updateUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public NtsTradeUserResponse updateUser(@RequestBody NewUserRequest user) {
		return userService.updateUserInfo(user);
	}

	/** NFT API's **/
	@GetMapping("/nfts")
	public NFTsRes getAllNfts() {
		return nFTService.getAllNtfs();
	}

	@GetMapping("/nft")
	public NFTRes getNftByTokenId(@RequestParam String tokenId) {
		return nFTService.getNftByTokenId(tokenId);
	}

	@GetMapping("/v1/nft")
	public NFTsRes getAllNftsByContractEthereumAddress(@RequestParam String contractEthereumAddress) {
		return nFTService.getAllNtsWithContractEthereumAddress(contractEthereumAddress);
	}

	@GetMapping("/create/nfts")
	public NFTsRes createNfts(@RequestParam int clientId, @RequestParam String name, @RequestParam double ethPrice,
			@RequestParam int noOfCopies) {
		return nFTService.createNft(clientId, name, ethPrice, noOfCopies);
	}

	@GetMapping("/get/nft")
	public NtsNftOwnsRes getAllNftsWithTheClientId(@RequestParam int clientId) {
		return nFTService.getAllNftsWithTheClientId(clientId);
	}

	@GetMapping("/get/trade/nft")
	public NtsNftOwnsRes getAllNftsExcludingClientId(@RequestParam int clientId) {
		return nFTService.getAllNftsWithExcludingTheClientId(clientId);
	}

	/**
	 * Money Transaction API's
	 */
	@GetMapping("/addMoneyFromBofa")
	public ServerStatusResponsePojo addMoneyFromBofa(@RequestParam int clientId, @RequestParam double amount) {
		return ntsMoneyService.addMoneyFromBofa(clientId, amount);
	}
}
