package org.jacq.service.names.sources;

import org.jacq.common.model.jpa.openup.TblSourceCzechPrague;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class CzechPragueSource implements CommonNamesSource {

    @PersistenceContext(unitName = "openup")
    protected EntityManager em;

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        // build SQL lookup query for source rows for the given query
        String lookupQuery = "SELECT row FROM TblSourceCzechPrague row WHERE row.celeJmeno = :scientificName";
        TypedQuery<TblSourceCzechPrague> sourceQuery =
                em.createQuery(lookupQuery, TblSourceCzechPrague.class)
                        .setParameter("scientificName", query.getScientificName());
        // get SQL lookup query results
        List<TblSourceCzechPrague> sourceQueryResults = sourceQuery.getResultList();

        ArrayList<CommonName> results = new ArrayList<>();
        // create common names for rows in SQL lookup query results
        for (TblSourceCzechPrague row : sourceQueryResults) {
            CommonName commonName = new CommonName();

            commonName.setName(row.getCeskeJmeno());
            commonName.setTaxon(row.getCeleJmeno());
            commonName.setLanguage("ces");

            commonName.getReferences().add("Institute of Botany, Academy of Sciences of Czech Republic - Květena");
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
