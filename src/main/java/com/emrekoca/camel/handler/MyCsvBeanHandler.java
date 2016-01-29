package com.emrekoca.camel.handler;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.emrekoca.camel.domain.User;

public class MyCsvBeanHandler {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MyCsvBeanHandler.class);

	public void doHandleCsv(List<User> payloads) {
		for(User payload : payloads ){
			System.out.println(payload);
		}
	}
}
