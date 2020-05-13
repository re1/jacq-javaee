package org.jacq.service.names.sources;

import org.jacq.common.model.jpa.openup.TblSourceCzechJiri;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public class CzechJiriSource implements CommonNamesSource {

    @PersistenceContext(unitName = "openup")
    protected EntityManager em;

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        // add common names from Czech Jiri Bezo1
        String lookupQuery = "SELECT row FROM TblSourceCzechJiriBezo1 row WHERE row.latinName = '" + query.getScientificName() + "'";
        List<TblSourceCzechJiri> sourceQueryResults = new ArrayList<>(em.createQuery(lookupQuery, TblSourceCzechJiri.class).getResultList());
        // add common names from Czech Jiri Roztoci
        lookupQuery = "SELECT row FROM TblSourceCzechJiriRoztoci row WHERE row.latinName = '" + query.getScientificName() + "'";
        sourceQueryResults.addAll(em.createQuery(lookupQuery, TblSourceCzechJiri.class).getResultList());
        // add common names from Czech Jiri Vacnatci
        lookupQuery = "SELECT row FROM TblSourceCzechJiriVacnatci row WHERE row.latinName = '" + query.getScientificName() + "'";
        sourceQueryResults.addAll(em.createQuery(lookupQuery, TblSourceCzechJiri.class).getResultList());

        ArrayList<CommonName> results = new ArrayList<>();
        // create common names for rows in SQL lookup query results
        for (TblSourceCzechJiri row : sourceQueryResults) {
            CommonName commonName = new CommonName();

            commonName.setName(row.getCzechName());
            commonName.setTaxon(row.getLatinName());
            commonName.setLanguage("ces");
            // from https://github.com/wkollernhm/openup/blob/master/protected/components/Sources/CzechJiri.php
            commonName.getReferences().add("Czech names provided by Jiri Kvacek");
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
