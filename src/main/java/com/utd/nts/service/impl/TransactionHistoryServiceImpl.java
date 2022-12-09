package com.utd.nts.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utd.nts.entity.NtsCommissionEntity;
import com.utd.nts.entity.NtsTransactionHistory;
import com.utd.nts.repository.NtsCommissionRepo;
import com.utd.nts.repository.NtsTransactionHistoryRepo;
import com.utd.nts.reqres.pojo.ManagerStatisticsRes;
import com.utd.nts.service.TransactionHistoryService;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {
	@Autowired
	NtsTransactionHistoryRepo ntsTransactionHistoryRepo;

	@Autowired
	private NtsCommissionRepo ntsCommissionRepo;

	@Override
	public List<NtsTransactionHistory> getAll() {
		return ntsTransactionHistoryRepo.findAll();
	}

	@Override
	public ManagerStatisticsRes getManagerStatistics(Date startDate, Date endDate) {
		ManagerStatisticsRes res = new ManagerStatisticsRes();
		Optional<NtsCommissionEntity> ntsCommissionEntity = ntsCommissionRepo.findById(1);
		res.setMoneyTransactionCount(ntsTransactionHistoryRepo.getTradeTransactionCount(startDate, endDate, "Trade"));
		res.setTradeTransactionCount(ntsTransactionHistoryRepo.getTradeTransactionCount(startDate, endDate, "Money"));
		res.setEthCommissionAmount(ntsCommissionEntity.get().getEthCommisionAmount());
		res.setFaitCommissionAmount(ntsCommissionEntity.get().getFiatCurrencyAmount());
		res.setTradeTransactionSuccessCount(
				ntsTransactionHistoryRepo.getCountOfType(startDate, endDate, "Trade", "SUCCESS"));
		res.setTradeTransactionCancellCount(res.getTradeTransactionCount() - res.getTradeTransactionSuccessCount());
		return res;
	}

	@Override
	public List<NtsTransactionHistory> getAllTransactionsByClientId(int clientId) {
		return ntsTransactionHistoryRepo.findByclientid(clientId);
	}

}
