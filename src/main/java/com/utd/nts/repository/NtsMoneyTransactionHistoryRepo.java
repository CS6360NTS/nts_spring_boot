package com.utd.nts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utd.nts.entity.NtsMoneyTransactionHistory;

public interface NtsMoneyTransactionHistoryRepo extends JpaRepository<NtsMoneyTransactionHistory, Integer> {
	NtsMoneyTransactionHistory findByTransactionId(int transactionId);
}
