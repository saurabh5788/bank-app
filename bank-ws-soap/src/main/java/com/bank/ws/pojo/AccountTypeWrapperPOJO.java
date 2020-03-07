package com.bank.ws.pojo;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "accountTypes")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountTypeWrapperPOJO {
	private Set<AccountTypePOJO> accountTypes;

	public Set<AccountTypePOJO> getAccountTypes() {
		return accountTypes;
	}

	public void setAccountTypes(Set<AccountTypePOJO> accountTypes) {
		this.accountTypes = accountTypes;
	}
}
