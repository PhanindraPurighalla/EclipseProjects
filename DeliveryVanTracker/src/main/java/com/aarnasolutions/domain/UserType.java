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
@Table(name = "user_types")
@Component
public class UserType implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_type_code")
    private String userTypeCode;

    @Column(name = "user_type_desc")
    private String userTypeDesc;

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