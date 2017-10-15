package com.aarnasolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aarnasolutions.assemblers.CustomerAssembler;
import com.aarnasolutions.domain.Customer;
import com.aarnasolutions.service.CustomerService;
import com.aarnasolutions.vo.CreateCustomerVO;
import com.aarnasolutions.vo.CustomerVO;
import com.aarnasolutions.vo.UpdateCustomerVO;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerAssembler customerAssembler;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CustomerVO getCustomer(@PathVariable("id") Long id) {
        return customerAssembler.toCustomerVO(customerService.getCustomerById(id));
    }
    
    @RequestMapping(value = "/cNo/{contactNo}", method = RequestMethod.GET)
    public CustomerVO getCustomer(@PathVariable("contactNo") String contactNo) {
        return customerAssembler.toCustomerVO(customerService.getCustomerByContactNo(contactNo));
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public CustomerVO createCustomer(@RequestBody CreateCustomerVO customerVO) {
        //convert to Customer
        Customer customer = customerAssembler.toCustomer(customerVO);
        //save Customer
        Customer savedCustomer = customerService.createCustomer(customer);
        //convert to CustomerVO
        return customerAssembler.toCustomerVO(savedCustomer);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CustomerVO updateCustomer(@RequestBody UpdateCustomerVO updateCustomerVO) {
        //convert to Customer
        Customer customer = customerAssembler.toCustomer(updateCustomerVO);
        //update Customer
        customerService.updateCustomer(customer);
        //convert to CustomerVO
        return customerAssembler.toCustomerVO(customer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
    }
}