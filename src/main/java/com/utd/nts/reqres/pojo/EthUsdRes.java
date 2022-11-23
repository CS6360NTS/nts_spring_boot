package com.utd.nts.reqres.pojo;

import java.io.Serializable;

/**
 * 
 * @author navaneethkumarbuddi
 *
 */
public class EthUsdRes implements Serializable {

	private static final long serialVersionUID = 1L;

	double USD;

	public EthUsdRes() {
		super();
	}

	public EthUsdRes(double uSD) {
		super();
		USD = uSD;
	}

	public double getUSD() {
		return USD;
	}

	public void setUSD(double uSD) {
		USD = uSD;
	}

	@Override
	public String toString() {
		return "EthUsdRes [USD=" + USD + "]";
	}

}
