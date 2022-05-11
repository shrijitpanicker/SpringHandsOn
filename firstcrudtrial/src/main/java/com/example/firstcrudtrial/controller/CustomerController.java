package com.example.firstcrudtrial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstcrudtrial.model.CustomerPlan;
import com.example.firstcrudtrial.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value="/plans/{msisdn}", method=RequestMethod.GET)
    public CustomerPlan readPlans(@PathVariable(value = "msisdn") String msisdn) {
        return customerService.getCustomerPlan(msisdn);
    }
}
