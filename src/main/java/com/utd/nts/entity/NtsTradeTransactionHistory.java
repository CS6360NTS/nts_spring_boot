package com.utd.nts.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	@Id
	@Column(name = "transaction_id")
	int transactionId;

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

	@Column(name = "nft_tocken_id")
	String nftTockenId;

	@Column(name = "buyer_ethereum_address")
	String buyerEthereumAddress;

	@Column(name = "seller_ethereum_address")
	String sellerEthereumAddress;

	public NtsTradeTransactionHistory() {
		super();
	}

	public NtsTradeTransactionHistory(int transactionId, String tradeTransactionType, double ethereumValue,
			double commissionPaid, String commissionType, String nftAddress, String nftTockenId,
			String buyerEthereumAddress, String sellerEthereumAddress) {
		super();
		this.transactionId = transactionId;
		this.tradeTransactionType = tradeTransactionType;
		this.ethereumValue = ethereumValue;
		this.commissionPaid = commissionPaid;
		this.commissionType = commissionType;
		this.nftAddress = nftAddress;
		this.nftTockenId = nftTockenId;
		this.buyerEthereumAddress = buyerEthereumAddress;
		this.sellerEthereumAddress = sellerEthereumAddress;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
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

	public String getNftTockenId() {
		return nftTockenId;
	}

	public void setNftTockenId(String nftTockenId) {
		this.nftTockenId = nftTockenId;
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

	@Override
	public String toString() {
		return "NtsTradeTransactionHistory [transactionId=" + transactionId + ", tradeTransactionType="
				+ tradeTransactionType + ", ethereumValue=" + ethereumValue + ", commissionPaid=" + commissionPaid
				+ ", commissionType=" + commissionType + ", nftAddress=" + nftAddress + ", nftTockenId=" + nftTockenId
				+ ", buyerEthereumAddress=" + buyerEthereumAddress + ", sellerEthereumAddress=" + sellerEthereumAddress
				+ "]";
	}

}
