package org.jacq.service.names.sources;

import org.jacq.common.model.jpa.openup.TblSourceUkrainianKobiv;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Source class for Ukrainian Kobiv tables.
 * References and geography are currently not included due to an issue stated here:
 * <a href="https://github.com/re1/jacq-javaee/issues/16">#16</a>
 */
public class UkrainianKobivSource implements CommonNamesSource {

    @PersistenceContext(unitName = "openup")
    protected EntityManager em;

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        // build SQL lookup query for source rows for the given query
        String lookupQuery = "SELECT row FROM TblSourceUkrainianKobiv row WHERE row.lnom = :scientificName OR row.lsecond = :scientificName";
        TypedQuery<TblSourceUkrainianKobiv> sourceQuery =
                em.createQuery(lookupQuery, TblSourceUkrainianKobiv.class)
                        .setParameter("scientificName", query.getScientificName());
        // get SQL lookup query results
        List<TblSourceUkrainianKobiv> sourceQueryResults = sourceQuery.getResultList();

        ArrayList<CommonName> results = new ArrayList<>();
        // create common names for rows in SQL lookup query results
        for (TblSourceUkrainianKobiv row : sourceQueryResults) {
            CommonName commonName = new CommonName();
            // use ukrainian typeset if set
            String name = row.getUtypeset();
            if (name == null || name.isEmpty()) name = row.getUnar();
            commonName.setName(name);
            commonName.setTaxon(row.getLnom());
            commonName.setLanguage("ukr");

            String geosk = row.getGeosk();
            // geosk field can be empty
            if (geosk != null && !geosk.isEmpty()) {
                String[] shorts = geosk.split(" ");
                // only region is relevant for setting the common name geography
                StringBuilder regionLookupQuery = new StringBuilder("SELECT row.region FROM TblSourceUkrainianKobivRegions row WHERE row.shortField = :geosk");
                // Geographic short codes can have multiple items of cyrillic letters.
                // As lists of String cannot be set as query parameter in the given JPA version,
                // multiple comparision parameters are added instead.
                // TODO: find better solution for adding list parameter (Hibernate setParameterList?)
                for (int i = 0; i < shorts.length; i++)
                    regionLookupQuery.append(" OR (row.shortField = :s").append(i).append(") ");
                TypedQuery<String> regionSourceQuery = em.createQuery(regionLookupQuery.toString(), String.class)
                        .setParameter("geosk", geosk);
                for (int i = 0; i < shorts.length; i++) regionSourceQuery.setParameter("s" + i, shorts[i]);

                // add regions from result list
                List<String> regionQueryResults = regionSourceQuery.getResultList();
                commonName.setGeography(String.join(", ", regionQueryResults));
            }
            // only region is relevant for setting the common name geography
            StringBuilder referencesLookupQuery = new StringBuilder("SELECT row.reference FROM TblSourceUkrainianKobivReferences row WHERE row.shortField IN :shorts");
            String[] shorts = row.getLit().split(", ");
            for (int i = 0; i < shorts.length; i++) {
                referencesLookupQuery.append(" OR (row.shortField = :s").append(i).append(")");
            }
            TypedQuery<String> referencesSourceQuery = em.createQuery(referencesLookupQuery.toString(), String.class)
                    .setParameter("shorts", row.getLit());
            for (int i = 0; i < shorts.length; i++) {
                referencesSourceQuery.setParameter("s" + i, shorts[i]);
            }
            // add regions from result list
            List<String> referenceResults = referencesSourceQuery.getResultList();
            commonName.getReferences().addAll(referenceResults);

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
