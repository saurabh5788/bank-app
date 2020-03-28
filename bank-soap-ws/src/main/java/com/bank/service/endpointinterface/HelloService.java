package com.bank.service.endpointinterface;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(name = "Hello", serviceName = "HelloService", targetNamespace = "http://bank.com/helloservice/soap", portName = "HelloPort")
@SOAPBinding(style = Style.DOCUMENT)
public interface HelloService {
	@WebMethod(action = "http://bank.com/helloservice", operationName = "HelloWorldOperation")
	@WebResult(name = "Response", targetNamespace = "http://bank.com/helloservice/soap", partName = "body")
	String sayHelloWorld();
}
