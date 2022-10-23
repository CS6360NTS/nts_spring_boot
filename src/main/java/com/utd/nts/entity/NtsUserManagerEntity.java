package com.utd.nts.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nts_user_manager")
public class NtsUserManagerEntity {

	@Id
	@Column(name = "client_id")
	int clientId;

	@Column(name = "is_active")
	boolean isActive;
}
