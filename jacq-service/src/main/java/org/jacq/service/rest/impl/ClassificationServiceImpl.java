package org.jacq.service.rest.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.jacq.common.model.jpa.TaxClassification;
import org.jacq.common.rest.ClassificationService;
import org.jacq.service.manager.ClassificationManager;

public class ClassificationServiceImpl implements ClassificationService, Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ClassificationManager classificationManager;

	@Override
	public List<TaxClassification> getTopLevelEntries() {
		return classificationManager.getTopLevelEntries();
	}

	@Override
	public String test() {
		return "Hello World";
	}
}
