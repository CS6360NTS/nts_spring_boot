package com.utd.nts.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.utd.nts.service.EthUsdService;

@Service
public class EthUsdServiceImpl implements EthUsdService {
	public static final Logger log = LoggerFactory.getLogger(EthUsdServiceImpl.class);

	@SuppressWarnings({ "unchecked", "null" })
	@Override
	public double getTheUsdValue() {

		try {
			RestTemplate restTemplate = new RestTemplate();
			String url = "https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=USD";
			Object ethUsdRes = restTemplate.getForEntity(url, Object.class).getBody();
			HashMap<String, Double> usd = new LinkedHashMap<String, Double>();
			usd = (HashMap<String, Double>) ethUsdRes;
			return usd.get("USD");

		} catch (Exception e) {
			log.error("Exception occured while making the API call to the google API" + e.getMessage());
		}
		return 0;
	}

}
