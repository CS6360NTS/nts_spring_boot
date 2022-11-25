package com.utd.nts.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author navaneethkumarbuddi
 *
 */
@Entity
@Table(name = "nts_commission")
public class NtsCommissionEntity implements Serializable {

	private static final long serialVersionUID = 4416291323510795834L;

	@Id
	@Column(name = "row_id")
	int rowId;

	@Column(name = "eth_commision_amount")
	double ethCommisionAmount;

	@Column(name = "fiat_currency_amount")
	double fiatCurrencyAmount;

	public NtsCommissionEntity() {
		super();
	}

	public NtsCommissionEntity(int rowId, double ethCommisionAmount, double fiatCurrencyAmount) {
		super();
		this.rowId = rowId;
		this.ethCommisionAmount = ethCommisionAmount;
		this.fiatCurrencyAmount = fiatCurrencyAmount;
	}

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public double getEthCommisionAmount() {
		return ethCommisionAmount;
	}

	public void setEthCommisionAmount(double ethCommisionAmount) {
		this.ethCommisionAmount = ethCommisionAmount;
	}

	public double getFiatCurrencyAmount() {
		return fiatCurrencyAmount;
	}

	public void setFiatCurrencyAmount(double fiatCurrencyAmount) {
		this.fiatCurrencyAmount = fiatCurrencyAmount;
	}

	@Override
	public String toString() {
		return "NtsCommissionEntity [rowId=" + rowId + ", ethCommisionAmount=" + ethCommisionAmount
				+ ", fiatCurrencyAmount=" + fiatCurrencyAmount + "]";
	}

}
