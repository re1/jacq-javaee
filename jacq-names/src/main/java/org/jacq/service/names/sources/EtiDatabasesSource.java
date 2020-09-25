package org.jacq.service.names.sources;

import org.jacq.common.model.jpa.openup.TblSourceEtiDatabases;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class EtiDatabasesSource implements CommonNamesSource {

    @PersistenceContext(unitName = "openup")
    protected EntityManager em;

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        // build SQL lookup query for source rows for the given query
        String lookupQuery = "SELECT row FROM TblSourceEtiDatabases row WHERE row.taxon LIKE :scientificName";
        TypedQuery<TblSourceEtiDatabases> sourceQuery =
                em.createQuery(lookupQuery, TblSourceEtiDatabases.class)
                        .setParameter("scientificName", query.getScientificName());
        // get SQL lookup query results
        List<TblSourceEtiDatabases> sourceQueryResults = sourceQuery.getResultList();

        ArrayList<CommonName> results = new ArrayList<>();
        // create common names for rows in SQL lookup query results
        for (TblSourceEtiDatabases row : sourceQueryResults) {
            CommonName commonName = new CommonName();

            commonName.setName(row.getName());
            commonName.setTaxon(row.getTaxon());
            commonName.setLanguage(row.getIso6396());
            commonName.getReferences().add(row.getSource());
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
