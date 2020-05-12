package org.jacq.service.names.sources;

import org.jacq.common.model.jpa.openup.TblSourceAllearterDk;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;
import org.jacq.service.names.sources.CommonNamesSource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class AllearterDKSource implements CommonNamesSource {

    @PersistenceContext(unitName = "openup")
    protected EntityManager em;

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        // build SQL lookup query for source rows for the given query
        String lookupQuery = "SELECT row FROM TblSourceAllearterDk row WHERE row.videnskabeligtNavn = '" + query.getScientificName() + "'";
        TypedQuery<TblSourceAllearterDk> linnaeusProjectsQuery = em.createQuery(lookupQuery, TblSourceAllearterDk.class);
        // get SQL lookup query results
        List<TblSourceAllearterDk> linnaeusProjectsResults = linnaeusProjectsQuery.getResultList();

        ArrayList<CommonName> results = new ArrayList<>();
        // create common names for rows in SQL lookup query results
        for (TblSourceAllearterDk row : linnaeusProjectsResults) {
            CommonName commonName = new CommonName();
            commonName.setName(row.getDanskNavn());
            commonName.setTaxon(row.getVidenskabeligtNavn());
            // all common names from this source are used in Denmark
            commonName.setGeography("Denmark");
            // all common names from this source are danish
            commonName.setLanguage("dan");
            commonName.getReferences().add(String.format(
                    "%s, %s, %s", row.getReferencenavn(), row.getReferencetekst(), row.getReferenceår()
            ));
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
