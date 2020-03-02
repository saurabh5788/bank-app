package com.bank.repository.bo;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Embeddable
public class PersonBO implements Serializable {
	@Column(nullable = false, unique = false, insertable = true, updatable = true, length = 100)
	private String firstName;
	@Column(nullable = true, unique = false, insertable = true, updatable = true, length = 100)
	private String lastName;
	@ElementCollection(fetch = FetchType.EAGER)
	@OrderColumn(name = "ORDER_ID")
	@CollectionTable(name = "PERSON_PHONE", joinColumns = { @JoinColumn(name = "PERSON_ID") })
	@Column(name = "PHONE_NO", nullable = false, unique = false, insertable = true, updatable = true, length = 20)
	@NotFound(action=NotFoundAction.EXCEPTION)
	protected Set<PhoneBO> phones = new HashSet<PhoneBO>();
	@Column(name = "GENDER")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Column(name = "DATE_OF_BIRTH", nullable = false, unique = false, insertable = true, updatable = true)
	private Date dateOfBirth;
	@Column(name = "EMAIL", nullable = false, unique = true, insertable = true, updatable = true, length = 100)
	private String email;
	@Column(name = "KYC_ID", nullable = false, unique = true, insertable = true, updatable = true, length = 10)
	private String kycNo;	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Set<PhoneBO> getPhones() {
		return phones;
	}
	public void setPhones(Set<PhoneBO> phones) {
		this.phones = phones;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getKycNo() {
		return kycNo;
	}
	public void setKycNo(String kycNo) {
		this.kycNo = kycNo;
	}
	public static enum Gender {
		Male, Female, NotAvailble
	}
}
