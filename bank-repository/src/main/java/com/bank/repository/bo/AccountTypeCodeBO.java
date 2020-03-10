package com.bank.repository.bo;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "ACCOUNT_TYPE_CD")
@Immutable
@NamedQueries({ @NamedQuery(name = "Account.getAllAccountTypes", query = "SELECT e FROM AccountTypeCodeBO e") })
@Cacheable
// JPA Cacheable Although not mandatory
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "accounttypecode")
public class AccountTypeCodeBO {
	public AccountTypeCodeBO() {
	}
	
	public AccountTypeCodeBO(String code, String value) {
		this.code = code;
		this.description = this.value = value;	
	}

	public AccountTypeCodeBO(String code, String value, String description) {
		this.code = code;
		this.value = value;
		this.description = description;
	}

	@Id
	@Column(name = "CODE", nullable = false, unique = true, insertable = true, updatable = false, length = 2)
	@Basic(optional = false)
	private String code;

	@Column(name = "VALUE", nullable = false, unique = true, insertable = true, updatable = false, length = 100)
	@Basic(optional = false)
	private String value;

	@Lob
	@Column(name = "DESCRIPTION", nullable = true, unique = true, insertable = true, updatable = true)
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
