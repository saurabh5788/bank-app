package com.bank.ws.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "accountType")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountTypePOJO {
	//@XmlElement(name="accountTypeCode")
	@XmlAttribute(name="accountTypeCode")
	private String code;
	@XmlElement(name="accountTypeName")
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
