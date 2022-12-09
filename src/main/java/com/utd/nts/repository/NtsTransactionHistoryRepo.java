package com.utd.nts.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.utd.nts.entity.NtsTransactionHistory;

public interface NtsTransactionHistoryRepo extends JpaRepository<NtsTransactionHistory, Integer> {

	@Query(value = "SELECT COUNT(*) FROM nts_db.nts_transaction_history th where th.transaction_date>=?1 and th.transaction_date<=?2 and th.transaction_type =?3", nativeQuery = true)
	int getTradeTransactionCount(Date startDate, Date endDate, String type);

	@Query(value = "SELECT COUNT(*) FROM nts_db.nts_transaction_history th where th.transaction_date>=?1 and th.transaction_date<=?2 and th.transaction_type =?3 and th.transaction_status =?4", nativeQuery = true)
	int getCountOfType(Date startDate, Date endDate, String type, String status);
	
	@Query(value = "SELECT * FROM nts_db.nts_transaction_history th WHERE th.client_id =?1 ORDER by th.transaction_id DESC", nativeQuery = true)
	List<NtsTransactionHistory> findByclientid(int clientId);

}
