package com.bank.repository.bo;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.bank.repository.bo.AccountBO.AccountType;

@Entity
@Table(name = "CREDIT_CARD")
public class CreditCardBO extends AccountBO {
	public CreditCardBO() {}
	public CreditCardBO(String last4Digits, BigDecimal totalCreditLimit) {
		this.last4Digits = last4Digits;
		this.totalCreditLimit = totalCreditLimit;
	}
	@Column(name = "LAST_4_DIGITS", nullable = false, unique = false, insertable = true, updatable = false, length = 4)
	@Basic(optional = false)
	private String last4Digits;
	@Column(name = "TOTAL_CREDIT_LIMIT", insertable = true, nullable = true, updatable = true, precision = 10, scale = 2)
	private BigDecimal totalCreditLimit;
	@Column(name = "USED_AMT", insertable = true, nullable = true, updatable = true, precision = 10, scale = 2)
	private BigDecimal usedAmount;
	@Column(name = "BLOCKED", nullable = true, insertable = false, updatable = true)
	private Boolean isBlocked;
	
	public String getLast4Digits() {
		return last4Digits;
	}
	public void setLast4Digits(String last4Digits) {
		this.last4Digits = last4Digits;
	}
	public BigDecimal getTotalCreditLimit() {
		return totalCreditLimit;
	}
	public void setTotalCreditLimit(BigDecimal totalCreditLimit) {
		this.totalCreditLimit = totalCreditLimit;
	}
	public BigDecimal getUsedAmount() {
		return usedAmount;
	}
	public void setUsedAmount(BigDecimal usedAmount) {
		this.usedAmount = usedAmount;
	}		
	public Boolean getIsBlocked() {
		return isBlocked;
	}
	public void setIsBlocked(Boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
	@Override
	@PrePersist
	void onCreate() {
		super.onCreate();
		this.usedAmount = new BigDecimal(0);
		this.accountType = AccountType.CreditCard;
	}
}
