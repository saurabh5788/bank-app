package com.bank.repository.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.repository.bo.AccountTypeCodeBO;
import com.bank.repository.bo.CustomerBO;
import com.bank.repository.utils.EntityUtils;

@Repository // For exception translation
@Transactional(propagation = Propagation.MANDATORY)
public class CustomerDao {
	private final static Logger LOGGER = LoggerFactory.getLogger(CustomerDao.class.getName());
	@PersistenceContext
	private EntityManager manager;

	public void persistCustomer(CustomerBO customer) {
		LOGGER.info("Inside persistCustomer!!!");
		EntityUtils.printEntityDetails(manager, customer);
		manager.persist(customer);
		EntityUtils.printEntityDetails(manager, customer);
		LOGGER.info("Exit persistCustomer!!!");
	}

	public void persistAccountType(AccountTypeCodeBO accountType) {
		LOGGER.info("Inside persistAccountType!!!");
		EntityUtils.printEntityDetails(manager, accountType);
		manager.persist(accountType);
		EntityUtils.printEntityDetails(manager, accountType);
		LOGGER.info("Exit persistAccountType!!!");
	}

	public CustomerBO mergeCustomer(CustomerBO customer) {
		LOGGER.info("Inside mergeCustomer!!!");
		EntityUtils.printEntityDetails(manager, customer);
		CustomerBO managedCustomer = manager.merge(customer);
		EntityUtils.printEntityDetails(manager, customer);
		LOGGER.info("Exit mergeCustomer!!!");
		return managedCustomer;
	}

	public CustomerBO findCustomer(Long customerId) {
		LOGGER.info("Inside findCustomer!!!");
		CustomerBO customer = manager.find(CustomerBO.class, customerId);
		LOGGER.info("Exit findCustomer!!!");
		return customer;
	}

	@Transactional(readOnly = true, timeout = 10)
	public List<CustomerBO> getAllCustomersUsingNamedQuery() {
		return manager.createNamedQuery("Customer.getAllCustomers", CustomerBO.class).getResultList();
	}
	
	@Transactional(readOnly = true, timeout = 1)
	public AccountTypeCodeBO findAccountType(String accountTypeCode) {
		LOGGER.info("Inside findAccountType!!!");
		AccountTypeCodeBO accountType = manager.find(AccountTypeCodeBO.class, accountTypeCode);
		LOGGER.info("Exit findAccountType!!!");
		return accountType;
	}

	@Transactional(readOnly = true, timeout = 10)
	public List<AccountTypeCodeBO> getAllAccountTypes() {
		return manager.createNamedQuery("Account.getAllAccountTypes", AccountTypeCodeBO.class)
				.setHint("org.hibernate.cacheable", true).getResultList();
	}

}
