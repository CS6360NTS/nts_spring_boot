package com.utd.nts.service;

import com.utd.nts.common.pojo.ServerStatusResponsePojo;

public interface NtsMoneyService {

	public abstract ServerStatusResponsePojo addMoneyFromBofa(int clientId, double amount);
}
