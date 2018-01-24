package com.aarnasolutions.repo;

import com.aarnasolutions.domain.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE LOWER(u.mobileNo) = LOWER(?1)")
	List<User> findByMobileNo(String mobileNo);
	
	@Query("SELECT u FROM User u WHERE LOWER(u.userName) = LOWER(?1)")
	List<User> findByUserName(String userName);
}