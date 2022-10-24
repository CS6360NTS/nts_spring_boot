package com.utd.nts.common.pojo;

import java.io.Serializable;

/**
 * 
 * @author NXB210086
 *
 */
public class ServerStatusResponsePojo implements Serializable {

	private static final long serialVersionUID = 8612651602529176656L;

	int responseCode;
	boolean isSuccess;

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	String errorMessage;

}
