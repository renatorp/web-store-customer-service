package com.example.webstorecustomerservice.service;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.webstorecustomerservice.entity.Customer;
import com.example.webstorecustomerservice.vo.CartRequestVO;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@Service
public class CartService {

	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public ResponseEntity<?> createCustomerCart(Customer customer) {
		
		Application application = eurekaClient.getApplication("web-store-cart-service");
		
		if (CollectionUtils.isEmpty(application.getInstances())) {
			throw new RuntimeException("Não foi possível criar carrinho de compras!");
		}
		
		InstanceInfo info = application.getInstances().iterator().next();
		
		String url = "http://" + info.getIPAddr() + ":" + info.getPort() + "/carts";
		
		CartRequestVO cart = new CartRequestVO(customer.getId());
		
		HttpEntity<CartRequestVO> httpEntity = new HttpEntity<>(cart);
		
		ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, ResponseEntity.class);
		
		return response;
		
	}

}
