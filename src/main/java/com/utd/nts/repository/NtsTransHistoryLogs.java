package com.utd.nts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utd.nts.entity.NtsTransactionHistoryLogsEntity;

public interface NtsTransHistoryLogs extends JpaRepository<NtsTransactionHistoryLogsEntity, Integer>{

}
