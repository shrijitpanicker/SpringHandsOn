package com.example.firstcrudtrial.commonservices;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Helper helper;
	
	public <T> T Get(String url, Class<T> classType, Map<String, String> headers, Map<String, String> parameters)
	{
		HttpHeaders httpHeaders = new HttpHeaders();
		
		if(headers != null) {
			for (Map.Entry<String,String> header : headers.entrySet())
			{
				if(!header.getKey().isBlank() && !header.getValue().isBlank())
				{
					httpHeaders.add(header.getKey(), header.getValue());
				}
			}
		}
		
		String fullUrl = parameters != null ? helper.GetUrlWithParameters(url, parameters) : url; 
		
		if(!httpHeaders.isEmpty())
		{
			HttpEntity<Object> requestEntity = new HttpEntity<>(httpHeaders);
			
			ResponseEntity<T> response = restTemplate.exchange(fullUrl, HttpMethod.GET, requestEntity, classType);
			
			return response.getBody();
		}
		
		else
		{
			ResponseEntity<T> response = restTemplate.getForEntity(fullUrl, classType);
			
			return response.getBody();
		}
		
		
		
	}
}
