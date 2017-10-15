package com.aarnasolutions.assemblers;

import org.springframework.stereotype.Component;

import com.aarnasolutions.domain.Customer;
import com.aarnasolutions.util.CustomerUtil;
import com.aarnasolutions.vo.CreateCustomerVO;
import com.aarnasolutions.vo.CustomerVO;
import com.aarnasolutions.vo.UpdateCustomerVO;

@Component
public class CustomerAssembler {
	
	
	/**
     * CreateCustomerVO to Customer.
     *
     * @param createCustomerVO
     * @return
     */
    public Customer toCustomer(CreateCustomerVO createCustomerVO) {
    		
    		Customer customer = new Customer();
        customer.setFirstName(createCustomerVO.getFirstName());
        customer.setLastName(createCustomerVO.getLastName());
        customer.setEmailId(createCustomerVO.getEmailId());
        customer.setContactNo(createCustomerVO.getContactNo());
        customer.setDateOfBirth(createCustomerVO.getDateOfBirth());
        customer.setPinCode(createCustomerVO.getPinCode());
        return customer;
    }


    /**
     * Customer to CustomerVO.
     *
     * @param customer
     * @return
     */
    public CustomerVO toCustomerVO(Customer customer) {
    	
    		if (customer == null) {
    			return null;
    		}
    		
    		CustomerVO customerVO = new CustomerVO();
        customerVO.setCustomerId(customer.getId());
        customerVO.setFullName(CustomerUtil.convertToFullName(customer.getFirstName(), customer.getLastName()));
        customerVO.setEmailId(customer.getEmailId());
        customerVO.setContactNo(customer.getContactNo());
        customerVO.setDateOfBirth(customer.getDateOfBirth());
        return customerVO;
    }

    /**
     * UpdateCustomerVO to Customer.
     *
     * @param updateCustomerVO
     * @return
     */
    public Customer toCustomer(UpdateCustomerVO updateCustomerVO) {
    	
    		Customer customer = new Customer();
        customer.setId(updateCustomerVO.getCustomerId());
        customer.setFirstName(updateCustomerVO.getFirstName());
        customer.setLastName(updateCustomerVO.getLastName());
        customer.setEmailId(updateCustomerVO.getEmailId());
        customer.setContactNo(updateCustomerVO.getContactNo());
        customer.setDateOfBirth(updateCustomerVO.getDateOfBirth());
        return customer;
    }
}