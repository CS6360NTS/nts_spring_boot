package com.utd.nts.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NtsTradeTransactionHistoryPrimaryKey implements Serializable {

	private static final long serialVersionUID = 1948077807533800068L;

	@Column(name = "transaction_id")
	int transactionId;

	@Column(name = "nft_tocken_id")
	String nftTockenId;

	public NtsTradeTransactionHistoryPrimaryKey() {
		super();
	}

	public NtsTradeTransactionHistoryPrimaryKey(int transactionId, String nftTockenId) {
		super();
		this.transactionId = transactionId;
		this.nftTockenId = nftTockenId;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getNftTockenId() {
		return nftTockenId;
	}

	public void setNftTockenId(String nftTockenId) {
		this.nftTockenId = nftTockenId;
	}

	@Override
	public String toString() {
		return "NtsTradeTransactionHistoryPrimaryKey [transactionId=" + transactionId + ", nftTockenId=" + nftTockenId
				+ "]";
	}

}
