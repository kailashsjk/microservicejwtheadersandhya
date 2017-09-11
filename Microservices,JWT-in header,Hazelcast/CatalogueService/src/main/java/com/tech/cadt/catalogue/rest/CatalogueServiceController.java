package com.tech.cadt.catalogue.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
//import com.tech.cadt.cache.rest.HazelCastServiceController;


import com.tech.cadt.CatalogueService;
import com.tech.cadt.product.entity.Product;
import com.tech.cadt.product.repository.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/catalogueService")
//@EnableDiscoveryClient

public class CatalogueServiceController extends SpringBootServletInitializer implements Serializable{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogueServiceController.class);
	String customlogger ="ADMS Loger::::";
	
    private static final String signingKey = "signingKey";
	
    @Autowired 
	ProductRepository productRepository;

	@RequestMapping("/create")
	public Product create(@Valid @RequestBody Product product) {
		
	
		try{				
			System.out.println("Product"+product.getCost());
			product = productRepository.save(product);	
		System.out.println("Product"+product);
		
	}catch(Exception e){
		System.out.println("Connection Exception"+e.getMessage());
	}
	    return product;
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	
	public Product update(@Valid @RequestBody Product product,HttpServletResponse httpServletResponse,@RequestHeader HttpHeaders headers,HttpServletRequest httpServletRequest) {
		
		Product products=null;
		
		List<String> token = headers.get("jwtToken");
		String ipAddressOrder =  httpServletRequest.getRemoteAddr();
		
		Map mapValue=new HashMap();		
		mapValue.put("token", token);		
		mapValue.put("ipAddressOrder", ipAddressOrder);		
	
		RestTemplate restTemplate = new RestTemplate();		 
		String res=restTemplate.postForObject("http://localhost:8222/loginService/isValid", mapValue, String.class);
		
		System.out.print("inside create");
		
		if(res.equals("validToken")){
			products= productRepository.findById(product.getId());
		
		products.setAvailableitems(product.getAvailableitems());
		
		products = productRepository.save(products);		
		}
	    return products;
	}
	
	
	@RequestMapping("/read-all")
	public List<Product> readAll() throws InterruptedException {
		
		/*GetAllCatalogueService getAllCatalogueService = new GetAllCatalogueService();
		getAllCatalogueService.execute();*/
		
		System.out.println("inside catalogue: ");
		List<Product> product = productRepository.findAll();	
		System.out.println("inside catalogue: "+product.size());
		 return product;
		
		}
	
	
 
	


}
