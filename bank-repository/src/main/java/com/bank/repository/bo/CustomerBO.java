package com.bank.repository.bo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "CUSTOMER")
@NamedQueries({
@NamedQuery(name="Customer.getAllCustomers",
    query="SELECT e FROM CustomerBO e")          
})
public class CustomerBO extends AbstractBO {
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "firstName", column = @Column(name = "C_FIRST_NAME")),
			@AttributeOverride(name = "lastName", column = @Column(name = "C_LAST_NAME")),
			@AttributeOverride(name = "gender", column = @Column(name = "C_GENDER")),
			@AttributeOverride(name = "dateOfBirth", column = @Column(name = "C_DATE_OF_BIRTH")) })
	@NotFound(action = NotFoundAction.EXCEPTION)
	private PersonBO person;
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, orphanRemoval=true)
	@NotFound(action = NotFoundAction.IGNORE)
	@Fetch(FetchMode.SELECT)
	@BatchSize(size = 5)
	private Set<AccountBO> accounts = new HashSet<AccountBO>();
	
	public void addAccount(AccountBO account) {
		this.accounts.add(account);
		account.setCustomer(this);
	}

	public PersonBO getPerson() {
		return person;
	}

	public void setPerson(PersonBO person) {
		this.person = person;
	}

	public Set<AccountBO> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<AccountBO> accounts) {
		this.accounts = accounts;
	}

}
