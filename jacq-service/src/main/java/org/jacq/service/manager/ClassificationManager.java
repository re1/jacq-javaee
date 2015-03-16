package org.jacq.service.manager;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jacq.common.model.jpa.TaxClassification;

@ManagedBean
public class ClassificationManager implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "jacq-service")
	EntityManager entityManager;

	public List<TaxClassification> getTopLevelEntries() {
		Query query = entityManager.createNamedQuery("TaxClassification.findAllTopLevel", TaxClassification.class);

		List<TaxClassification> resultList = query.getResultList();
		return resultList;
	}
}
