package com.utd.nts.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author navaneethkumarbuddi
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nts_user")
public class NtsUserEntity {

	@Id
	@GeneratedValue
	@Column(name = "client_id")
	int clientId;

	@Column(name = "first_name")
	String firstName;

	@Column(name = "last_name")
	String lastName;

	@Column(name = "email_id")
	String emailId;

	@Column(name = "phone_number")
	String phoneNumber;

	@Column(name = "cell_phone_number")
	String cellPhoneNumber;

	@Column(name = "user_type")
	char userType = 'T';

	@Column(name = "street_address")
	String streetAddress;

	@Column(name = "city")
	String city;

	@Column(name = "state")
	String state;

	@Column(name = "zip_code")
	String zipCode;

}
