package com.bank.repository.listener;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractListener {
	private final static Logger LOGGER = LoggerFactory.getLogger(AbstractListener.class.getName());
	@PrePersist
	public void userPrePersist(Object ob) {
		LOGGER.debug("Listening User Pre Persist : " + ob.getClass().getName());
	}

	@PostPersist
	public void userPostPersist(Object ob) {
		LOGGER.debug("Listening User Post Persist : " + ob.getClass().getName());
	}

	@PostLoad
	public void userPostLoad(Object ob) {
		LOGGER.debug("Listening User Post Load : " + ob.getClass().getName());
	}

	@PreUpdate
	public void userPreUpdate(Object ob) {
		LOGGER.debug("Listening User Pre Update : " + ob.getClass().getName());
	}

	@PostUpdate
	public void userPostUpdate(Object ob) {
		LOGGER.debug("Listening User Post Update : " + ob.getClass().getName());
	}

	@PreRemove
	public void userPreRemove(Object ob) {
		LOGGER.debug("Listening User Pre Remove : " + ob.getClass().getName());
	}

	@PostRemove
	public void userPostRemove(Object ob) {
		LOGGER.debug("Listening User Post Remove : " + ob.getClass().getName());
	}
}
