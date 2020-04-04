package com.bank.service.endpointinterface;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.bind.annotation.XmlElement;

import com.bank.service.dto.response.AccountTypeDTO;
import com.bank.service.dto.response.AccountTypeWrapperDTO;

@WebService(name = "AccountWebService", serviceName = "AccountService", targetNamespace = "http://bank.com/accountservice/soap", portName = "AccountPort")
@SOAPBinding(style = Style.DOCUMENT)
public interface AccountServiceInterface {
	@WebMethod(action = "http://bank.com/accountservice/soap", operationName = "AccountTypeListOperation")
	@WebResult(name = "AccountTypeListResponse", targetNamespace = "http://bank.com/accountservice/soap", partName = "body")
	AccountTypeWrapperDTO getAccountTypeList();

	@WebMethod(action = "http://bank.com/accountservice/soap", operationName = "AccountTypeOperation")
	@WebResult(name = "AccountTypeResponse", targetNamespace = "http://bank.com/accountservice/soap", partName = "body")
	AccountTypeDTO getAccountType(
			@XmlElement(required = true) @WebParam(name = "AccountTypeCode") String accountTypeCode);
}
