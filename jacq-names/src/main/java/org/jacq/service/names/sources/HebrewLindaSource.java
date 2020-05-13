package org.jacq.service.names.sources;

import org.jacq.common.model.jpa.openup.TblSourceHebrewLinda;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class HebrewLindaSource implements CommonNamesSource {

    @PersistenceContext(unitName = "openup")
    protected EntityManager em;

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        // build SQL lookup query for source rows for the given query
        String lookupQuery = "SELECT row FROM TblSourceHebrewLinda row WHERE row.cleanScientificName = '" + query.getScientificName() + "'";
        TypedQuery<TblSourceHebrewLinda> sourceQuery = em.createQuery(lookupQuery, TblSourceHebrewLinda.class);
        // get SQL lookup query results
        List<TblSourceHebrewLinda> sourceQueryResults = sourceQuery.getResultList();

        ArrayList<CommonName> results = new ArrayList<>();
        // create common names for rows in SQL lookup query results
        for (TblSourceHebrewLinda row : sourceQueryResults) {
            CommonName commonName = new CommonName();

            commonName.setName(row.getHebrewSpecies());
            commonName.setTaxon(row.getCleanScientificName());
            commonName.setLanguage("heb");
            // https://github.com/wkollernhm/openup/blob/master/protected/components/Sources/HebrewLinda.php
            commonName.getReferences().add("Hebrew Names");
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
