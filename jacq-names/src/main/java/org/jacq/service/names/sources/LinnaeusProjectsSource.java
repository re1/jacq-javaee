package org.jacq.service.names.sources;

import org.jacq.common.model.jpa.openup.TblSourceLinnaeusProjects;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LinnaeusProjectsSource implements CommonNamesSource {

    @PersistenceContext(unitName = "openup")
    protected EntityManager em;

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        // build SQL lookup query for source rows for the given query
        String lookupQuery = "SELECT row FROM TblSourceLinnaeusProjects row WHERE row.taxon LIKE :scientificName";
        TypedQuery<TblSourceLinnaeusProjects> sourceQuery =
                em.createQuery(lookupQuery, TblSourceLinnaeusProjects.class)
                        .setParameter("scientificName", query.getScientificName());
        // get SQL lookup query results
        List<TblSourceLinnaeusProjects> sourceQueryResults = sourceQuery.getResultList();

        ArrayList<CommonName> results = new ArrayList<>();
        // create common names for rows in SQL lookup query results
        for (TblSourceLinnaeusProjects row : sourceQueryResults) {
            CommonName commonName = new CommonName();

            commonName.setName(row.getName());
            commonName.setTaxon(row.getTaxon());

            String language = row.getLanguage();
            for (Locale locale : Locale.getAvailableLocales()) {
                if (locale.getDisplayLanguage().equals(language)) {
                    commonName.setLanguage(locale.getISO3Language());
                    break;
                }
            }

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
