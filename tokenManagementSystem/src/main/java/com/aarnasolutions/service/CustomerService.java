package com.aarnasolutions.service;

import com.aarnasolutions.domain.Customer;

public interface CustomerService {

	Customer getCustomerById(Long id);
	
	Customer getCustomerByContactNo(String contactNo);
	
	Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(Long id);
    
}
