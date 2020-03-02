package com.bank.mvc.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bank.repository.bo.AccountBO;
import com.bank.repository.bo.AccountTypeCodeBO;
import com.bank.repository.bo.CreditCardBO;
import com.bank.repository.bo.CustomerBO;
import com.bank.repository.bo.DepositAccountBO;
import com.bank.repository.bo.PersonBO;
import com.bank.repository.bo.PersonBO.Gender;
import com.bank.repository.bo.PhoneBO;
import com.bank.repository.bo.SavingAccountBO;
import com.bank.service.impl.CustomerService;

@Controller
@RequestMapping(value = "customer")
public class CustomerController {
	private final static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class.getName());
	private CustomerService customerService;

	@Autowired
	private Set<AccountTypeCodeBO> accountTypeCodes;

	@Autowired
	private CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@RequestMapping(value = "/adddummy", method = RequestMethod.GET)
	public String addDummyCustomer() {

		/*int n = 0;
		if (!CollectionUtils.isEmpty(accountTypeCodes)) {
			for (AccountTypeCodeBO accountTypeCode : accountTypeCodes) {
				try {
					customerService.persistTypeCode(accountTypeCode);
					n++;
				} catch (DataIntegrityViolationException dive) {
					LOGGER.warn("\"{}\" May Present already!!!", accountTypeCode.getCode());
				}
			}
		}
		LOGGER.debug("No of Account Types Added : {}", n);*/

		PersonBO person = new PersonBO();
		Calendar dobCalendar = Calendar.getInstance();
		dobCalendar.set(1988, 7, 5);
		Date dateOfBirth = dobCalendar.getTime();
		person.setDateOfBirth(new java.sql.Date(dateOfBirth.getTime()));		
		person.setFirstName("Saurabh");
		person.setLastName("Singh");
		person.setGender(Gender.Male);
		person.setEmail("saurabh@gmail.com");
		person.setKycNo("DH398000");

		PhoneBO phone1 = new PhoneBO();
		phone1.setAreaCode(120L);
		phone1.setCountryCode(91L);
		phone1.setPhoneNo(4593475834789L);
		phone1.setExtension(345L);

		PhoneBO phone2 = new PhoneBO();
		phone2.setAreaCode(122L);
		phone2.setCountryCode(91L);
		phone2.setPhoneNo(4593443834789L);
		phone2.setExtension(123L);

		person.getPhones().add(phone1);
		person.getPhones().add(phone2);

		CustomerBO customer = new CustomerBO();
		customer.setPerson(person);

		AccountTypeCodeBO ccAccountType = new AccountTypeCodeBO("cc", "Credit Card");
		AccountTypeCodeBO saAccountType = new AccountTypeCodeBO("sa", "Saving Account");
		AccountTypeCodeBO dpAccountType = new AccountTypeCodeBO("dp", "Deposit");

		CreditCardBO creditCard1 = new CreditCardBO("4345", new BigDecimal(100000));
		creditCard1.setAccountTypeCode(ccAccountType);
		creditCard1.setIsBlocked(true);
		AccountBO savingAccount1 = new SavingAccountBO(new BigDecimal(3488));
		savingAccount1.setAccountTypeCode(saAccountType);
		AccountBO depositAccount1 = new DepositAccountBO(new BigDecimal(9800), new BigDecimal(8.1));
		depositAccount1.setAccountTypeCode(dpAccountType);
		AccountBO savingAccount2 = new SavingAccountBO(new BigDecimal(5000));
		savingAccount2.setAccountTypeCode(saAccountType);
		AccountBO creditCard2 = new CreditCardBO("3527", new BigDecimal(100000));
		creditCard2.setAccountTypeCode(ccAccountType);
		AccountBO depositAccount2 = new DepositAccountBO(new BigDecimal(7990), new BigDecimal(8.9));
		depositAccount2.setAccountTypeCode(dpAccountType);

		customer.addAccount(creditCard1);
		customer.addAccount(savingAccount1);
		customer.addAccount(depositAccount1);
		customer.addAccount(savingAccount2);
		customer.addAccount(creditCard2);
		customer.addAccount(depositAccount2);

		customerService.persistCustomer(customer);

		return "customer";
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public String getCustomer(Model model,
			@Min(value = 1L, message = "The Customer ID must be valid.") @PathVariable("id") Long customerId) {
		// Load Customer
		CustomerBO customer = customerService.findCustomer(customerId);
		LOGGER.debug(customer.toString());
		return "customer";
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String getCustomer(Model model) {
		// Load Customer
		List<CustomerBO> customerList = customerService.getAllCustomersUsingNamedQuery();
		return "customer";
	}
}
