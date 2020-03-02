package com.bank.repository.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bank.repository.listener.AbstractListener;

@MappedSuperclass
@EntityListeners(AbstractListener.class)
@SelectBeforeUpdate
public abstract class AbstractBO implements Serializable {
	@Transient
	private final static Logger LOGGER = LoggerFactory.getLogger(AbstractBO.class.getName());
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "abstract-seq-generator")
	@SequenceGenerator(name = "abstract-seq-generator", initialValue = 1001, allocationSize = 100)
	/*
	 * @GenericGenerator(name = "abstract-seq-generator", strategy =
	 * "enhanced-sequence", parameters = {
	 * 
	 * @Parameter(name = "sequence_name", value = "abstract_seq"),
	 * 
	 * @Parameter(name = "initial_value", value = "1001"),
	 * 
	 * @Parameter(name = "increment_size", value = "100"),
	 * 
	 * @Parameter(name = "optimizer", value = "pooled-lo") })
	 */

	@Column(name = "ID")
	private Long recordId;
	@Column(name = "CREATION_DATE", nullable = false, insertable = true, updatable = false)
	private Timestamp dateOfCreation;
	@Column(name = "UPDATION_DATE", nullable = true, insertable = false, updatable = true)
	private Timestamp dateOfUpdation;
	@Column(name = "EXPIRED_STATUS", nullable = true)
	private Boolean expiredStatus;
	@Version
	@Column(name = "VERSION")
	private Long version;

	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public Timestamp getDateOfCreation() {
		return dateOfCreation;
	}

	public Timestamp getDateOfUpdation() {
		return dateOfUpdation;
	}

	public Boolean getExpiredStatus() {
		return expiredStatus;
	}

	public void setExpiredStatus(Boolean expiredStatus) {
		this.expiredStatus = expiredStatus;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	@PrePersist
	void onCreate() {
		LOGGER.debug("Assinging Date Of Creation as current Timestamp!!!");
		this.dateOfCreation = new Timestamp((new Date()).getTime());
	}

	@PreUpdate
	void onPersist() {
		LOGGER.debug("Assinging Date Of Updation as current Timestamp!!!");
		this.dateOfUpdation = new Timestamp((new Date()).getTime());
	}
}
