package com.utd.nts.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author navaneethkumarbuddi
 *
 */
@Entity
@Table(name = "nts_user")
public class NtsUserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1782270804769395502L;

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

	@Column(name = "nts_user_password")
	String password;

	public NtsUserEntity() {
		super();
	}

	public NtsUserEntity(int clientId, String firstName, String lastName, String emailId, String phoneNumber,
			String cellPhoneNumber, char userType, String streetAddress, String city, String state, String zipCode,
			String password) {
		super();
		this.clientId = clientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.cellPhoneNumber = cellPhoneNumber;
		this.userType = userType;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.password = password;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCellPhoneNumber() {
		return cellPhoneNumber;
	}

	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}

	public char getUserType() {
		return userType;
	}

	public void setUserType(char userType) {
		this.userType = userType;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
