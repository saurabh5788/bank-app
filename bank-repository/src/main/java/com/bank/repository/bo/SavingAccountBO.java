package com.bank.repository.bo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "SAVING_ACCOUNT")
public class SavingAccountBO extends AccountBO {
	public SavingAccountBO(){}
	public SavingAccountBO(BigDecimal balance) {
		this.balance = balance;
	}
	@Column(name = "BALANCE", insertable = true, nullable = true, updatable = true, precision = 10, scale = 2)
	private BigDecimal balance;
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	@Override
	@PrePersist
	void onCreate() {
		super.onCreate();
		this.accountType = AccountType.Saving;
	}
}
