package com.utd.nts.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.utd.nts.entity.NtsTransactionHistory;

public interface NtsTransactionHistoryRepo extends JpaRepository<NtsTransactionHistory, Integer> {

	@Query(value = "SELECT COUNT(*) FROM nts_db.nts_transaction_history th where th.transaction_date>=?1 and th.transaction_date<=?2 and th.transaction_type =?3", nativeQuery = true)
	int getTradeTransactionCount(Date startDate, Date endDate, String type);

	List<NtsTransactionHistory> findByclientId(int clientId);

}
