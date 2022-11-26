package com.utd.nts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.utd.nts.entity.NtsTradeTransactionHistory;

public interface NtsTradeTransactionHistoryRepo extends JpaRepository<NtsTradeTransactionHistory, Integer> {
	@Query(value = "SELECT * FROM nts_db.nts_trade_transaction_history tth where tth.transaction_id = ?1", nativeQuery = true)
	List<NtsTradeTransactionHistory> findByTransactionId(int transactionId);
}
