package com.aarnasolutions.controller;

import com.aarnasolutions.domain.Customer;
import com.aarnasolutions.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Customer getCustomer(@PathVariable("id") Long id) {
        return customerRepository.findOne(id);
    }
}