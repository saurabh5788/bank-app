package com.bank.repository.bo;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "ACCOUNT_TYPE_CD")
@Immutable
@Cacheable // JPA Cacheable Although not mandatory
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "accounttypecode")
public class AccountTypeCodeBO {
	public AccountTypeCodeBO() {
	}

	public AccountTypeCodeBO(String code, String value) {
		this.code = code;
		this.value = value;
	}
	
	@Id
	@Column(name = "CODE", nullable = false, unique = true, insertable = true, updatable = false, length = 2)
	@Basic(optional = false)
	private String code;

	
	@Column(name = "VALUE", nullable = false, unique = true, insertable = true, updatable = false, length = 100)
	@Basic(optional = false)
	private String value;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
