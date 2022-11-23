package com.utd.nts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utd.nts.entity.NtsTradeTransactionHistory;

public interface NtsTradeTransactionHistoryRepo extends JpaRepository<NtsTradeTransactionHistory, Integer> {

}
