package com.tech.cadt.shipping.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.tech.cadt.entity.Shipping;
import com.tech.cadt.repository.ShippingRepository;
import com.techcadt.hystrixdashboard.CreateShippingService;
import com.techcadt.hystrixdashboard.GetShippingStatus;

@RestController
@RequestMapping("/shipping")
@EnableDiscoveryClient
public class ShippingServiceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShippingServiceController.class);
    private static final String signingKey = "signingKey";
	
	
	@Autowired
	ShippingRepository shippingRepository;
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	
	public Shipping createShipping(@RequestBody Shipping shipping,HttpServletResponse httpServletResponse,@RequestHeader HttpHeaders headers,HttpServletRequest httpServletRequest) {
	/*	CreateShippingService createShippingService = new CreateShippingService();
		createShippingService.execute();*/
		System.out.print("inside create");
		List<String> token = headers.get("jwtToken");
		String ipAddressOrder =  httpServletRequest.getRemoteAddr();
		
		Map mapValue=new HashMap();		
		mapValue.put("token", token);		
		mapValue.put("ipAddressOrder", ipAddressOrder);		
	
		RestTemplate restTemplate = new RestTemplate();		 
		String res=restTemplate.postForObject("http://localhost:8222/loginService/isValid", mapValue, String.class);
		
		System.out.print("inside create");
		
		if(res.equals("validToken")){
			
		shipping = shippingRepository.save(shipping);
		}
	    return shipping;
	}
	
	
	@RequestMapping("/getShippingStatus")
	
	public Shipping getShippingStatus(@RequestParam long orderId) {
		
		/*GetShippingStatus getShippingStatus = new GetShippingStatus();
		getShippingStatus.execute();*/
		
		System.out.print("inside get");
		
		Shipping shipping = shippingRepository.findByOrderId(orderId);
	    return shipping;
	}

	
	
		
	}