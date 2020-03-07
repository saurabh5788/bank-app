package com.bank.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.repository.bo.AccountBO;
import com.bank.repository.bo.AccountTypeCodeBO;
import com.bank.repository.bo.CustomerBO;
import com.bank.repository.dao.CustomerDao;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class CustomerService {
	private final static Logger LOGGER = LoggerFactory.getLogger(CustomerService.class.getName());
	@Autowired
	private CustomerDao customerDao;
	// @Autowired
	// private LanguageService languageService;

	public CustomerBO persistCustomer(CustomerBO customer) {
		LOGGER.info("Inside persistCustomer!!!");
		LOGGER.debug("No Of Accounts : {}", customer.getAccounts().size());

		for (AccountBO account : customer.getAccounts()) {
			String accountTypeCode = account.getAccountTypeCode().getCode();
			LOGGER.debug("Account Type Code : {}", accountTypeCode);
			AccountTypeCodeBO accountType = customerDao.findAccountType(accountTypeCode);
			if (accountType == null) {
				LOGGER.debug("New Account Type Detected, So needs to be managed and persisted!!!");
				customerDao.persistAccountType(account.getAccountTypeCode());
			} else {
				LOGGER.debug("Existing Account Detected, So already managed!!!");
				account.setAccountTypeCode(accountType);
			}
		}
		customerDao.persistCustomer(customer);
		LOGGER.info("Exit persistCustomer!!!");
		return customer;
	}

	public CustomerBO mergeCustomer(CustomerBO customer) {
		LOGGER.info("Inside mergeCustomer!!!");
		CustomerBO managedCustomer = customerDao.mergeCustomer(customer);
		LOGGER.info("Exit mergeCustomer!!!");
		return managedCustomer;
	}

	@Transactional(readOnly = true, timeout = 5)
	public CustomerBO findCustomer(Long customerId) {
		LOGGER.info("Inside findCustomer!!!");
		CustomerBO customer = customerDao.findCustomer(customerId);
		LOGGER.info(customer.toString());
		LOGGER.info("Exit findCustomer!!!");
		return customer;
	}

	@Transactional(readOnly = true, timeout = 10)
	public List<CustomerBO> getAllCustomersUsingNamedQuery() {
		List<CustomerBO> customerList = customerDao.getAllCustomersUsingNamedQuery();
		LOGGER.info(customerList.toString());
		return customerList;
	}
	
	@Transactional(readOnly = true, timeout = 10)
	public List<AccountTypeCodeBO> getAllAccountTypes() {
		List<AccountTypeCodeBO> customerList = customerDao.getAllAccountTypes();
		LOGGER.info(customerList.toString());
		return customerList;
	}
	
	@Transactional(readOnly = true, timeout = 5)
	public AccountTypeCodeBO getAccountType(String accountTypeCode) {
		AccountTypeCodeBO accountType = customerDao.findAccountType(accountTypeCode);
		LOGGER.info(accountType.toString());
		return accountType;
	}
}
