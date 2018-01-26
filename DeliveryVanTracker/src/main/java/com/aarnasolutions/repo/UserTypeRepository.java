package com.aarnasolutions.repo;

import com.aarnasolutions.domain.UserType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {

	@Query("SELECT ut FROM UserType ut WHERE LOWER(ut.userTypeCode) = LOWER(?1)")
	List<UserType> findByUserTypeCode(String userTypeCode);
	
	@Query("SELECT ut FROM UserType ut WHERE LOWER(ut.userTypeDesc) = LOWER(?1)")
	List<UserType> findByUserTypeDesc(String userTypeDesc);
}