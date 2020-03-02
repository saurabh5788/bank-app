package com.bank.repository.bo;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "ACCOUNT")
public abstract class AccountBO extends AbstractBO {
	@Column(name = "TYPE", insertable = true, length = 20, updatable = false, nullable = false)
	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	protected AccountType accountType;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinColumn(name = "CUSTOMER_ID", nullable = false, insertable = true, updatable = false)
	@NotFound(action = NotFoundAction.EXCEPTION)
	@Basic(optional = false)
	private CustomerBO customer;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@Basic(optional = false)
	@NotFound(action = NotFoundAction.EXCEPTION)
	@JoinColumn(name = "TYPE_CD", nullable = false, insertable = true, updatable = false, referencedColumnName = "CODE")
	private AccountTypeCodeBO accountTypeCode;

	public CustomerBO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBO customer) {
		this.customer = customer;
		customer.getAccounts().add(this);
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public AccountTypeCodeBO getAccountTypeCode() {
		return accountTypeCode;
	}

	public void setAccountTypeCode(AccountTypeCodeBO accountTypeCode) {
		this.accountTypeCode = accountTypeCode;
	}

	public static enum AccountType {
		Saving, CreditCard, Deposit
	}
}
