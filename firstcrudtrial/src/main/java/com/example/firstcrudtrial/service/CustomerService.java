package com.example.firstcrudtrial.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.firstcrudtrial.model.CustomerPlan;
import com.example.firstcrudtrial.commonservices.HttpService;
import com.example.firstcrudtrial.commonservices.Helper;
import com.example.firstcrudtrial.constants.*;

@Service
public class CustomerService {

	@Autowired
	HttpService httpService;
	
	@Autowired
	Helper helper;
	
	public CustomerPlan getCustomerPlan(String msisdn)
	{
		if(helper.ValidateMsisdn(msisdn))
		{
			String baseUrl = System.getenv(Constants.MAD_API_V2);
			String url = helper.GetCompleteMadApiUrl(baseUrl, msisdn, Constants.PLANS_URL);
			String xApiKey = System.getenv(Constants.X_API_KEY);
			Map<String, String> headers = Map.of(Constants.X_API_KEY, xApiKey);
			return httpService.GetAsync(url, CustomerPlan.class, headers, null);
		}
		else
		{
			CustomerPlan customerPlan = new CustomerPlan();
			
			return customerPlan;
		}
	}
}
