package org.jacq.service.names.sources;

import org.jacq.common.model.jpa.openup.TblSourceNewZealandLandcare;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class NewZealandLandcareSource implements CommonNamesSource {

    @PersistenceContext(unitName = "openup")
    protected EntityManager em;

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        // build SQL lookup query for source rows for the given query
        String lookupQuery = "SELECT row FROM TblSourceNewZealandLandcare row WHERE row.nameFull = :scientificName";
        TypedQuery<TblSourceNewZealandLandcare> sourceQuery =
                em.createQuery(lookupQuery, TblSourceNewZealandLandcare.class)
                        .setParameter("scientificName", query.getScientificName());
        ;
        // get SQL lookup query results
        List<TblSourceNewZealandLandcare> sourceQueryResults = sourceQuery.getResultList();

        ArrayList<CommonName> results = new ArrayList<>();
        // create common names for rows in SQL lookup query results
        for (TblSourceNewZealandLandcare row : sourceQueryResults) {
            CommonName commonName = new CommonName();

            commonName.setName(row.getVernacularName());
            commonName.setTaxon(row.getNameFull());
            commonName.setLanguage(row.getLanguageIsoCode());
            commonName.setGeography(row.getGeoRegionName());
            // add reference citation or default reference
            String reference = row.getReferenceGenCitation();
            if (reference == null || reference.isEmpty()) reference = "New Zealand Landcare";
            commonName.getReferences().add(reference);
            // TODO: Query tables with similar scientific names
            commonName.setScore(100L);
            commonName.setMatch(true);
            // add common name to results
            results.add(commonName);
        }

        return results;
    }

    /**
     * @see CommonNamesSource#query(String)
     */
    @Override
    public ArrayList<ScientificName> query(String query) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
