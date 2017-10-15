package com.aarnasolutions.repo;

import com.aarnasolutions.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("SELECT c FROM Customer c WHERE LOWER(c.contactNo) = LOWER(?1)")
	Customer findByContactNo(String contactNo);
}