package com.utd.nts.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utd.nts.common.pojo.ServerStatusResponsePojo;
import com.utd.nts.entity.NtsMoneyTransactionHistory;
import com.utd.nts.entity.NtsNftEntity;
import com.utd.nts.entity.NtsTradeTransactionHistory;
import com.utd.nts.entity.NtsTransactionHistory;
import com.utd.nts.repository.NftRepo;
import com.utd.nts.repository.NtsMoneyTransactionHistoryRepo;
import com.utd.nts.repository.NtsTradeTransactionHistoryRepo;
import com.utd.nts.reqres.pojo.ManagerStatisticsRes;
import com.utd.nts.reqres.pojo.NFTRes;
import com.utd.nts.reqres.pojo.NFTsRes;
import com.utd.nts.reqres.pojo.NewUserRequest;
import com.utd.nts.reqres.pojo.NtsNftTradeReq;
import com.utd.nts.reqres.pojo.NtsTradeUserResponse;
import com.utd.nts.reqres.pojo.NtsUserResponse;
import com.utd.nts.service.EthUsdService;
import com.utd.nts.service.NFTService;
import com.utd.nts.service.NtsMoneyService;
import com.utd.nts.service.NtsNftTradeService;
import com.utd.nts.service.TransactionHistoryService;
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

	@Autowired
	NtsNftTradeService ntsNftTradeService;

	@Autowired
	TransactionHistoryService transactionHistoryService;

	@Autowired
	NtsTradeTransactionHistoryRepo ntsTradeTransactionHistoryRepo;

	@Autowired
	NtsMoneyTransactionHistoryRepo ntsMoneyTransactionHistoryRepo;

	@Autowired
	EthUsdService ethUsdService;

	@Autowired
	NftRepo nftRepo;

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

	@GetMapping("/v1/nft")
	public NFTRes getNftByTokenId(@RequestParam String tokenId) {
		return nFTService.getNftByTokenId(tokenId);
	}

	@GetMapping("/v2/nft")
	public NFTsRes getAllNftsByContractEthereumAddress(@RequestParam String contractEthereumAddress) {
		return nFTService.getAllNtsWithContractEthereumAddress(contractEthereumAddress);
	}

	@GetMapping("/create/nfts")
	public NFTsRes createNfts(@RequestParam int clientId, @RequestParam String name, @RequestParam double ethPrice,
			@RequestParam int noOfCopies) {
		return nFTService.createNft(clientId, name, ethPrice, noOfCopies);
	}

	@GetMapping("/get/nft")
	public NFTsRes getAllNftsWithTheClientId(@RequestParam int clientId) {
		return nFTService.getAllNftsWithTheClientId(clientId);
	}

	@PostMapping(path = "/updateNft", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean updateNft(@RequestBody NtsNftEntity req) {
		try {
			
			nftRepo.save(req);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@GetMapping("/get/trade/nft")
	public NFTsRes getAllNftsExcludingClientId(@RequestParam int clientId) {
		return nFTService.getAllNftsWithExcludingTheClientId(clientId);
	}

	/**
	 * Money Transaction API's
	 */
	@GetMapping("/addMoneyFromBofa")
	public ServerStatusResponsePojo addMoneyFromBofa(@RequestParam int clientId, @RequestParam double amount) {
		return ntsMoneyService.addMoneyFromBofa(clientId, amount);
	}

	@GetMapping("/debitMoneyFromWallet")
	public ServerStatusResponsePojo debitMoneyFormWallet(@RequestParam int clientId, @RequestParam double amount) {
		return ntsMoneyService.debitMoneyFormWallet(clientId, amount);

	}

	@GetMapping("/transferWalletAmountToEth")
	public ServerStatusResponsePojo transferWalletAmountToEth(@RequestParam int clientId, @RequestParam double amount) {
		return ntsMoneyService.transferWalletAmountToEth(clientId, amount);
	}

	@GetMapping("/transferWalletEthAmountToFait")
	public ServerStatusResponsePojo transferWalletEthAmountToFait(@RequestParam int clientId,
			@RequestParam double amount) {
		return ntsMoneyService.transferWalletEthAmountToFait(clientId, amount);
	}

	@GetMapping("/addMoneyFromBofaToEthWallet")
	public ServerStatusResponsePojo addMoneyFromBofaToEthWallet(@RequestParam int clientId,
			@RequestParam double amount) {
		return ntsMoneyService.addMoneyFromBofaToEthWallet(clientId, amount);
	}

	@GetMapping("/debitMoneyForEthmWallet")
	public ServerStatusResponsePojo debitMoneyFormEthWallet(@RequestParam int clientId, @RequestParam double amount) {
		return ntsMoneyService.debitMoneyForEthmWallet(clientId, amount);
	}

	/**
	 * Trade Transaction API's
	 */
	@PostMapping(path = "/performTrade", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ServerStatusResponsePojo performTrade(@RequestBody NtsNftTradeReq req) {
		return ntsNftTradeService.validateAndCompleteTheTradeTransaction(req);
	}

	@GetMapping("getCurrentEthValue")
	public double getCurrentEthValue() {
		return ethUsdService.getTheUsdValue();
	}

	/**
	 * 
	 * Transaction API's
	 */
	@GetMapping("/getAllTransactions")
	List<NtsTransactionHistory> getAllTransactions() {
		return transactionHistoryService.getAll();
	}

	@GetMapping("/getAllTransactionsByClientId")
	List<NtsTransactionHistory> getAllTransactionsByClientId(@RequestParam int clientId) {
		return transactionHistoryService.getAllTransactionsByClientId(clientId);
	}

	@GetMapping("/getAllTradeTransactionsByTransactionId")
	List<NtsTradeTransactionHistory> getMoreDetailsAboutTradeTransaction(@RequestParam int transactionId) {
		return ntsTradeTransactionHistoryRepo.findByTransactionId(transactionId);
	}

	@GetMapping("/getAllMoneyTransactionsByTransactionId")
	NtsMoneyTransactionHistory getMoreDetailsAboutMoneyTransaction(@RequestParam int transactionId) {
		return ntsMoneyTransactionHistoryRepo.findByTransactionId(transactionId);
	}

	@GetMapping("/validateAndCancelTheTransaction")
	ServerStatusResponsePojo validateAndCancelTheTransaction(@RequestParam int transactionId) {
		return ntsNftTradeService.validateAndCancelTheTransaction(transactionId);
	}

	/**
	 * Manager Statistics API's
	 */
	@GetMapping("/getManagerStatistics")
	ManagerStatisticsRes getTheManagerStaistics(@RequestParam Date startDate, @RequestParam Date endDate) {
		return transactionHistoryService.getManagerStatistics(startDate, endDate);
	}
}
