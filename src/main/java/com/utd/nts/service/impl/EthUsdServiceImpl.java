package com.utd.nts.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.utd.nts.reqres.pojo.EthUsdRes;
import com.utd.nts.service.EthUsdService;

@Service
public class EthUsdServiceImpl implements EthUsdService {
	public static final Logger log = LoggerFactory.getLogger(EthUsdServiceImpl.class);

	@SuppressWarnings("null")
	@Override
	public double getTheUsdValue() {

		ResponseEntity<EthUsdRes> res = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			String url = "https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=USD";
			res = restTemplate.getForEntity(url, EthUsdRes.class);
		} catch (Exception e) {
			log.error("Exception occured while making the API call to the google API" + e.getMessage());
		}
		return res.getBody().getUSD();
	}

}
