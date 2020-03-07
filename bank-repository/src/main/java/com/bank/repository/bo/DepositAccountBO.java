package com.bank.repository.bo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DEPOSIT")
public class DepositAccountBO extends AccountBO {
	public DepositAccountBO(){}
	public DepositAccountBO(BigDecimal balance, BigDecimal rateOfInterest) {
		this.balance = balance;
		this.rateOfInterest = rateOfInterest;
	}
	@Column(name = "BALANCE", insertable = true, nullable = true, updatable = true, precision = 10, scale = 2)
	private BigDecimal balance;
	@Column(name = "RATE_OF_INTEREST", insertable = true, nullable = false, updatable = false, precision = 5, scale = 2)
	private BigDecimal rateOfInterest;
	
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(BigDecimal rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
}
