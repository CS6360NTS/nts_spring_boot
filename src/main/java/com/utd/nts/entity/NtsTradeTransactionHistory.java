package com.utd.nts.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author NXB210086
 *
 */
@Entity
@Table(name = "nts_trade_transaction_history")
public class NtsTradeTransactionHistory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1670341112198187261L;

	@EmbeddedId
	NtsTradeTransactionHistoryPrimaryKey primaryKey;

	@Column(name = "trade_transaction_type")
	String tradeTransactionType;

	@Column(name = "ethereum_value")
	double ethereumValue;

	@Column(name = "commission_paid")
	double commissionPaid;

	@Column(name = "commission_type")
	String commissionType;

	@Column(name = "nft_address")
	String nftAddress;

	@Column(name = "buyer_ethereum_address")
	String buyerEthereumAddress;

	@Column(name = "seller_ethereum_address")
	String sellerEthereumAddress;

	public NtsTradeTransactionHistory(NtsTradeTransactionHistoryPrimaryKey primaryKey, String tradeTransactionType,
			double ethereumValue, double commissionPaid, String commissionType, String nftAddress,
			String buyerEthereumAddress, String sellerEthereumAddress) {
		super();
		this.primaryKey = primaryKey;
		this.tradeTransactionType = tradeTransactionType;
		this.ethereumValue = ethereumValue;
		this.commissionPaid = commissionPaid;
		this.commissionType = commissionType;
		this.nftAddress = nftAddress;
		this.buyerEthereumAddress = buyerEthereumAddress;
		this.sellerEthereumAddress = sellerEthereumAddress;
	}

	public NtsTradeTransactionHistory() {
		super();
	}

	public String getTradeTransactionType() {
		return tradeTransactionType;
	}

	public void setTradeTransactionType(String tradeTransactionType) {
		this.tradeTransactionType = tradeTransactionType;
	}

	public double getEthereumValue() {
		return ethereumValue;
	}

	public void setEthereumValue(double ethereumValue) {
		this.ethereumValue = ethereumValue;
	}

	public double getCommissionPaid() {
		return commissionPaid;
	}

	public void setCommissionPaid(double commissionPaid) {
		this.commissionPaid = commissionPaid;
	}

	public String getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(String commissionType) {
		this.commissionType = commissionType;
	}

	public String getNftAddress() {
		return nftAddress;
	}

	public void setNftAddress(String nftAddress) {
		this.nftAddress = nftAddress;
	}

	public String getBuyerEthereumAddress() {
		return buyerEthereumAddress;
	}

	public void setBuyerEthereumAddress(String buyerEthereumAddress) {
		this.buyerEthereumAddress = buyerEthereumAddress;
	}

	public String getSellerEthereumAddress() {
		return sellerEthereumAddress;
	}

	public void setSellerEthereumAddress(String sellerEthereumAddress) {
		this.sellerEthereumAddress = sellerEthereumAddress;
	}

	public NtsTradeTransactionHistoryPrimaryKey getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(NtsTradeTransactionHistoryPrimaryKey primaryKey) {
		this.primaryKey = primaryKey;
	}

	@Override
	public String toString() {
		return "NtsTradeTransactionHistory [primaryKey=" + primaryKey + ", tradeTransactionType=" + tradeTransactionType
				+ ", ethereumValue=" + ethereumValue + ", commissionPaid=" + commissionPaid + ", commissionType="
				+ commissionType + ", nftAddress=" + nftAddress + ", buyerEthereumAddress=" + buyerEthereumAddress
				+ ", sellerEthereumAddress=" + sellerEthereumAddress + "]";
	}

}
