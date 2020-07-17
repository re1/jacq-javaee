package org.jacq.service.names.sources;

import org.jacq.common.model.jpa.openup.TblSourceAzerbaijan;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class AzerbaijanSource implements CommonNamesSource {

    @PersistenceContext(unitName = "openup")
    protected EntityManager em;

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        ArrayList<CommonName> results = new ArrayList<>();

        String genus = query.getGenus();
        String species = query.getSpecies();
        // return empty results list if either genus or species is not set for this query
        if ((genus == null || genus.isEmpty() || species == null || species.isEmpty())) return results;
        // build SQL lookup query for source rows for the given query
        String lookupQuery = "SELECT row FROM TblSourceAzerbaijan row WHERE row.genus = :genus AND row.species = :species";
        TypedQuery<TblSourceAzerbaijan> sourceQuery =
                em.createQuery(lookupQuery, TblSourceAzerbaijan.class)
                        .setParameter("genus", genus)
                        .setParameter("species", species);
        // get SQL lookup query results
        List<TblSourceAzerbaijan> sourceQueryResults = sourceQuery.getResultList();
        // create common names for rows in SQL lookup query results
        for (TblSourceAzerbaijan row : sourceQueryResults) {
            CommonName commonName = new CommonName();
            commonName.setName(row.getAccename());
            commonName.setTaxon(query.getScientificName());
            // all common names from this source are used in Azerbaijan
            commonName.setGeography("Azerbaijan");
            // all common names from this source are azerbaijani
            commonName.setLanguage("aze");
            commonName.getReferences().add("Azerbaijan National Academy of Sciences, Institute of Botany, Azerbaijan");
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
