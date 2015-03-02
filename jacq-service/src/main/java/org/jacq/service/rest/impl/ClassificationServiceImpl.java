package org.jacq.service.rest.impl;

import java.io.Serializable;

import org.jacq.common.rest.ClassificationService;

public class ClassificationServiceImpl implements ClassificationService, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String test() {
		return "Hello World!";
	}

}
