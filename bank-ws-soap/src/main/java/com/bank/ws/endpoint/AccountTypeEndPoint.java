package com.bank.ws.endpoint;

import java.util.List;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.xpath.XPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import com.bank.repository.bo.AccountTypeCodeBO;
import com.bank.service.impl.CustomerService;
import com.bank.ws.pojo.AccountTypeDetails;

@Endpoint
public class AccountTypeEndPoint {
	private static final String NAMESPACE_URI = "http://bank.com/bn/schemas";
	private XPath startDateExpression;
	private XPath endDateExpression;
	private XPath nameExpression;
	private CustomerService customerService;

	@Autowired
	public AccountTypeEndPoint(CustomerService customerService)
			throws JDOMException {
		this.customerService = customerService;

		Namespace namespace = Namespace.getNamespace("bn", NAMESPACE_URI);

		/*startDateExpression = XPath.newInstance("//hr:StartDate");
		startDateExpression.addNamespace(namespace);

		endDateExpression = XPath.newInstance("//hr:EndDate");
		endDateExpression.addNamespace(namespace);

		nameExpression = XPath
				.newInstance("concat(//hr:FirstName,' ',//hr:LastName)");
		nameExpression.addNamespace(namespace);*/
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "LeaveRequest")
	public AccountTypeDetails handleAccountTypeRequest(@RequestPayload Element leaveRequest)
			throws Exception {
		List<AccountTypeCodeBO> allAccountTypeBOs = customerService.getAllAccountTypes();
		AccountTypeDetails accounTypeDetails = new AccountTypeDetails();
		int accountTypeIndex = 0;
		for(AccountTypeCodeBO accountTypeBO : allAccountTypeBOs){
			AccountTypeDetails.AccountType accountType = new AccountTypeDetails.AccountType();
			accountType.setSno(++accountTypeIndex);
			accountType.setTypeCode(accountTypeBO.getCode());
			accountType.setName(accountTypeBO.getValue());
			accountType.setDescription(accountTypeBO.getDescription());
			
			accounTypeDetails.getAccountType().add(accountType);
		}		
		return accounTypeDetails;
	}
}