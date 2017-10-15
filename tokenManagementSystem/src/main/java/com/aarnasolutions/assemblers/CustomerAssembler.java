package com.aarnasolutions.assemblers;

import com.aarnasolutions.domain.Customer;
import com.aarnasolutions.util.CustomerUtil;
import com.aarnasolutions.vo.CreateCustomerVO;
import com.aarnasolutions.vo.UpdateCustomerVO;
import com.aarnasolutions.vo.CustomerVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerAssembler {
	
	@Autowired
	private Customer customer;
	
	@Autowired
	private CustomerVO customerVO;
    
    /**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}


	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	/**
	 * @return the customerVO
	 */
	public CustomerVO getCustomerVO() {
		return customerVO;
	}


	/**
	 * @param customerVO the customerVO to set
	 */
	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}


	/**
     * CreateCustomerVO to Customer.
     *
     * @param createCustomerVO
     * @return
     */
    public Customer toCustomer(CreateCustomerVO createCustomerVO) {
        customer.setFirstName(createCustomerVO.getFirstName());
        customer.setLastName(createCustomerVO.getLastName());
        customer.setEmailId(createCustomerVO.getEmailId());
        customer.setContactNo(createCustomerVO.getContactNo());
        customer.setDateOfBirth(createCustomerVO.getDateOfBirth());
        return customer;
    }


    /**
     * Customer to CustomerVO.
     *
     * @param customer
     * @return
     */
    public CustomerVO toCustomerVO(Customer customer) {
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
        customer.setId(updateCustomerVO.getCustomerId());
        customer.setFirstName(updateCustomerVO.getFirstName());
        customer.setLastName(updateCustomerVO.getLastName());
        customer.setEmailId(updateCustomerVO.getEmailId());
        customer.setContactNo(updateCustomerVO.getContactNo());
        customer.setDateOfBirth(updateCustomerVO.getDateOfBirth());
        return customer;
    }
}