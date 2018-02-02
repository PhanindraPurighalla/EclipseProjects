package com.aarnasolutions.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserTypeVO {

	@XmlElement
	private Long userTypeId;
	
	@XmlElement
	private String userTypeCode;
	
	@XmlElement
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
