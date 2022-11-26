package com.utd.nts.service;

import java.sql.Date;
import java.util.List;

import com.utd.nts.entity.NtsTransactionHistory;
import com.utd.nts.reqres.pojo.ManagerStatisticsRes;

public interface TransactionHistoryService {

	public abstract List<NtsTransactionHistory> getAll();

	public ManagerStatisticsRes getManagerStatistics(Date startDate, Date endDate);

	public abstract List<NtsTransactionHistory> getAllTransactionsByClientId(int ClientId);

}
