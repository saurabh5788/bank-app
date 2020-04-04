package com.bank.service.dto.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AccountTypeWrapperDTO {
	@XmlElement(name="AccountType")
	private List<AccountTypeDTO> accountTypeList = new ArrayList<AccountTypeDTO>();

	public List<AccountTypeDTO> getAccountTypeList() {
		return accountTypeList;
	}

	public void setAccountTypeList(List<AccountTypeDTO> accountTypeList) {
		this.accountTypeList = accountTypeList;
	}
}
