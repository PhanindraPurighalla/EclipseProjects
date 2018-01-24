package com.aarnasolutions.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Component
public class User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "user_type_id")
    private String userTypeId;
    
    @Column(name = "last_location_lat")
    private String lastLocationLat;
    
    @Column(name = "last_location_long")
    private String lastLocationLong;
    
    @Column(name = "last_location_locality")
    private String lastLocationLocality;
    
    @Column(name = "last_location_city")
    private String lastLocationCity;
    
    @Column(name = "last_location_country")
    private String lastLocationCountry;
    
    @Column(name = "last_location_time")
    private String lastLocationTime;
    
    @Column(name = "created")
    @CreationTimestamp
    private Date created;

    @Column(name = "updated")
    @UpdateTimestamp
    private Date updated;

	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the updated
	 */
	public Date getUpdated() {
		return updated;
	}

	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}