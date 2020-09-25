package org.jacq.service.names.sources;

import org.jacq.common.model.jpa.openup.TblSourceSlovakBratislava;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class SlovakBratislavaSource implements CommonNamesSource {

    @PersistenceContext(unitName = "openup")
    protected EntityManager em;

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        // build SQL lookup query for source rows for the given query
        String lookupQuery = "SELECT row FROM TblSourceSlovakBratislava row WHERE row.fldName LIKE :scientificName";
        TypedQuery<TblSourceSlovakBratislava> sourceQuery =
                em.createQuery(lookupQuery, TblSourceSlovakBratislava.class)
                        .setParameter("scientificName", query.getScientificName());
        // get SQL lookup query results
        List<TblSourceSlovakBratislava> sourceQueryResults = sourceQuery.getResultList();

        ArrayList<CommonName> results = new ArrayList<>();
        // create common names for rows in SQL lookup query results
        for (TblSourceSlovakBratislava row : sourceQueryResults) {
            CommonName commonName = new CommonName();

            commonName.setName(row.getFldNameSkPrefix() + " " + row.getFldNameSk());
            commonName.setTaxon(row.getFldName());
            commonName.setLanguage("slk");
            // https://github.com/wkollernhm/openup/blob/master/protected/components/Sources/SlovakBratislava.php
            commonName.getReferences().add("Slovak Academy of Sciences");
            commonName.setScore(query.getScientificName());
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
