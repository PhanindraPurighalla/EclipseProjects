package com.aarnasolutions.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserVO {

	@XmlElement
	private Long userId;
	
	@XmlElement
	private String userName;
	
	@XmlElement
	private String mobileNo;
	
	@XmlElement
	private String userTypeId;
	
	@XmlElement
	private String lastLocationLat;
	
	@XmlElement
	private String lastLocationLong;
	
	@XmlElement
	private String lastLocationLocality;
	
	@XmlElement
	private String lastLocationCity;
	
	@XmlElement
	private String lastLocationCountry;
	
	@XmlElement
	private String lastLocationTime;
	
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}
	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	/**
	 * @return the userTypeId
	 */
	public String getUserTypeId() {
		return userTypeId;
	}
	/**
	 * @param userTypeId the userTypeId to set
	 */
	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}
	/**
	 * @return the lastLocationLat
	 */
	public String getLastLocationLat() {
		return lastLocationLat;
	}
	/**
	 * @param lastLocationLat the lastLocationLat to set
	 */
	public void setLastLocationLat(String lastLocationLat) {
		this.lastLocationLat = lastLocationLat;
	}
	/**
	 * @return the lastLocationLong
	 */
	public String getLastLocationLong() {
		return lastLocationLong;
	}
	/**
	 * @param lastLocationLong the lastLocationLong to set
	 */
	public void setLastLocationLong(String lastLocationLong) {
		this.lastLocationLong = lastLocationLong;
	}
	/**
	 * @return the lastLocationLocality
	 */
	public String getLastLocationLocality() {
		return lastLocationLocality;
	}
	/**
	 * @param lastLocationLocality the lastLocationLocality to set
	 */
	public void setLastLocationLocality(String lastLocationLocality) {
		this.lastLocationLocality = lastLocationLocality;
	}
	/**
	 * @return the lastLocationCity
	 */
	public String getLastLocationCity() {
		return lastLocationCity;
	}
	/**
	 * @param lastLocationCity the lastLocationCity to set
	 */
	public void setLastLocationCity(String lastLocationCity) {
		this.lastLocationCity = lastLocationCity;
	}
	/**
	 * @return the lastLocationCountry
	 */
	public String getLastLocationCountry() {
		return lastLocationCountry;
	}
	/**
	 * @param lastLocationCountry the lastLocationCountry to set
	 */
	public void setLastLocationCountry(String lastLocationCountry) {
		this.lastLocationCountry = lastLocationCountry;
	}
	/**
	 * @return the lastLocationTime
	 */
	public String getLastLocationTime() {
		return lastLocationTime;
	}
	/**
	 * @param lastLocationTime the lastLocationTime to set
	 */
	public void setLastLocationTime(String lastLocationTime) {
		this.lastLocationTime = lastLocationTime;
	}

	

}
