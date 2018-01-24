package com.aarnasolutions.vo;

import org.springframework.stereotype.Component;

@Component
public class UserTypeVO {

	private Long userTypeId;
	private String userTypeCode;
	private String userTypeDesc;
	/**
	 * @return the userTypeId
	 */
	public Long getUserTypeId() {
		return userTypeId;
	}
	/**
	 * @param userTypeId the userTypeId to set
	 */
	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	/**
	 * @return the userTypeCode
	 */
	public String getUserTypeCode() {
		return userTypeCode;
	}
	/**
	 * @param userTypeCode the userTypeCode to set
	 */
	public void setUserTypeCode(String userTypeCode) {
		this.userTypeCode = userTypeCode;
	}
	/**
	 * @return the userTypeDesc
	 */
	public String getUserTypeDesc() {
		return userTypeDesc;
	}
	/**
	 * @param userTypeDesc the userTypeDesc to set
	 */
	public void setUserTypeDesc(String userTypeDesc) {
		this.userTypeDesc = userTypeDesc;
	}
	
		

}
