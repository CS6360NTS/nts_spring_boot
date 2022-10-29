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
@Table(name = "nts_user_manager")
public class NtsUserManagerEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7201705635707493232L;

	@Id
	@Column(name = "client_id")
	int clientId;

	@Column(name = "is_active")
	boolean isActive;

	public NtsUserManagerEntity() {
		super();
	}

	public NtsUserManagerEntity(int clientId, boolean isActive) {
		super();
		this.clientId = clientId;
		this.isActive = isActive;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "NtsUserManagerEntity [clientId=" + clientId + ", isActive=" + isActive + "]";
	}

}
