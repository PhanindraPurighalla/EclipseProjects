package com.aarnasolutions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aarnasolutions.domain.Customer;
import com.aarnasolutions.repo.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer getCustomerById(Long id) {
		return customerRepository.findOne(id);
	}
	
	@Override
	public Customer getCustomerByContactNo(String contactNo) {
		return customerRepository.findByContactNo(contactNo);
	}
	
	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Long id) {
		Customer customer = getCustomerById(id);
		if (customer != null) {
			customerRepository.delete(id);
		}
	}


}
