package org.jacq.service.names.sources;

import org.jacq.common.model.jpa.openup.TblSourceHungarianPeregovits;
import org.jacq.common.model.jpa.openup.TblSourceHungarianPeregovitsLiterature;
import org.jacq.common.model.names.CommonName;
import org.jacq.common.model.names.NameParserResponse;
import org.jacq.common.model.names.ScientificName;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class HungarianPeregovitsSource implements CommonNamesSource {

    @PersistenceContext(unitName = "openup")
    protected EntityManager em;

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        ArrayList<CommonName> results = new ArrayList<>();
        List<TblSourceHungarianPeregovits> sourceQueryResults = new ArrayList<>();

        // search for both genus and species if they are set for this query
        String genus = query.getGenus();
        String species = query.getSpecies();
        if ((genus != null && !genus.isEmpty() && species != null && !species.isEmpty())) {
            // build SQL lookup query for source rows for the given query
            String lookupQuery = "SELECT row FROM TblSourceHungarianPeregovits row WHERE " +
                    "row.genus = :genus AND row.species = :species";
            TypedQuery<TblSourceHungarianPeregovits> sourceQuery =
                    em.createQuery(lookupQuery, TblSourceHungarianPeregovits.class)
                            .setParameter("genus", genus)
                            .setParameter("species", species);
            sourceQueryResults.addAll(sourceQuery.getResultList());
        } else {
            String uninomial = query.getUninomial();
            if (uninomial != null && !uninomial.isEmpty()) {
                String lookupQuery = "SELECT row FROM TblSourceHungarianPeregovits row WHERE " +
                        "row.species = '' AND ((row.genus = '' AND row.family = :uninomial) OR row.genus = :uninomial)";
                TypedQuery<TblSourceHungarianPeregovits> sourceQuery =
                        em.createQuery(lookupQuery, TblSourceHungarianPeregovits.class)
                                .setParameter("uninomial", uninomial);
                sourceQueryResults.addAll(sourceQuery.getResultList());
            }
        }
        // create common names for rows in SQL lookup query results
        for (TblSourceHungarianPeregovits row : sourceQueryResults) {
            CommonName commonName = new CommonName();

            commonName.setName(row.getHuCommonName());
            commonName.setTaxon(query.getScientificName());
            commonName.setLanguage("hun");
            commonName.setPeriod(row.getPeriod());
            // build references from literature table based on
            // https://github.com/wkollernhm/openup/blob/master/protected/models/sources/SourceHungarianPeregovitsLiterature.php
            TblSourceHungarianPeregovitsLiterature literature = row.getLiterature();
            commonName.getReferences().add(String.format("%s (%s) %s %s %s: %s",
                    literature.getAuthors(), literature.getYear(), literature.getTitle(),
                    literature.getSeriesJournalTitle(), literature.getVolumeNo(), literature.getPages()
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
