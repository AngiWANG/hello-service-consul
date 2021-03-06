package com.allinpay.sample.hello.service.controller;

import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() throws Throwable {
		ServiceInstance instance = discoveryClient.getLocalServiceInstance();
		int sleepTime = new Random().nextInt(3000);
		Thread.sleep(sleepTime);
		logger.info("/hello, host:" + instance.getHost() + ", serviceId:" + instance.getServiceId());
		return "Hello, World!";
	}

}
