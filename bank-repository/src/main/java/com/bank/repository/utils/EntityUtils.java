package com.bank.repository.utils;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityUtils {
	private final static Logger LOGGER = LoggerFactory.getLogger(EntityUtils.class.getName());

	public static void printEntityDetails(EntityManager manager, Object entity) {
		boolean isEntityManaged = manager.contains(entity);
		if (isEntityManaged) {
			LOGGER.debug("{} Entity is managed!!!", entity.getClass().getName());
		} else {
			LOGGER.debug("{} Entity is not managed!!!", entity.getClass().getName());
		}
	}
}
