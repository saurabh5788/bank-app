package com.bank.service.endpointimpl;

import javax.jws.WebService;

import com.bank.service.endpointinterface.HelloService;

@WebService(endpointInterface = "com.bank.service.endpointinterface.HelloService")
public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHelloWorld() {
		return "Hello World!!";
	}

}
