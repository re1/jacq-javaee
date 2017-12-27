/*
 * Copyright 2017 fhafner.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jacq.service.manager;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.utils.PdfSplitter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.jacq.common.model.rest.TreeRecordFileResult;
import org.jacq.common.model.jpa.TblTreeRecordFile;
import org.jacq.common.model.jpa.TblTreeRecordFilePage;
import org.jacq.common.model.rest.TreeRecordFilePageResult;
import org.jacq.common.rest.TreeRecordFileService;
import org.jacq.service.JacqConfig;

/**
 *
 * @author fhafner
 */
public class TreeRecordFileManager {

    private static final Logger LOGGER = Logger.getLogger(TreeRecordFileManager.class.getName());

    @Inject
    protected JacqConfig jacqConfig;

    @PersistenceContext(unitName = "jacq-service")
    protected EntityManager em;

    /**
     * @see TreeRecordFileService#search()
     */
    @Transactional
    public List<TreeRecordFileResult> search(Long treeRecordFileId, Date year, String name, String documentNumber, Integer offset, Integer limit) {
        // prepare criteria builder & query
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TblTreeRecordFile> cq = cb.createQuery(TblTreeRecordFile.class);
        Root<TblTreeRecordFile> bo = cq.from(TblTreeRecordFile.class);

        // select result list
        cq.select(bo);

        // apply search criteria
        applySearchCriteria(cb, cq, bo, treeRecordFileId, year, name, documentNumber);

        // convert to typed query and apply offset / limit
        TypedQuery<TblTreeRecordFile> TreeRecordFileSearchQuery = em.createQuery(cq);
        if (offset != null) {
            TreeRecordFileSearchQuery.setFirstResult(offset);
        }
        if (limit != null) {
            TreeRecordFileSearchQuery.setMaxResults(limit);
        }

        // finally fetch the results
        ArrayList<TreeRecordFileResult> results = new ArrayList<>();
        List<TblTreeRecordFile> treeRecordFileResults = TreeRecordFileSearchQuery.getResultList();
        for (TblTreeRecordFile treeRecordFile : treeRecordFileResults) {
            TreeRecordFileResult treeRecordFileResult = new TreeRecordFileResult(treeRecordFile);

            // add botanical object to result list
            results.add(treeRecordFileResult);
        }

        return results;
    }

    /**
     * @see TreeRecordFileService#searchCount()
     */
    @Transactional
    public int searchCount(Long treeRecordFileId, Date year, String name, String documentNumber) {
        // prepare criteria builder & query
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TblTreeRecordFile> bo = cq.from(TblTreeRecordFile.class);

        // count result
        cq.select(cb.count(bo));

        // apply search criteria
        applySearchCriteria(cb, cq, bo, treeRecordFileId, year, name, documentNumber);

        // run query and return count
        return em.createQuery(cq).getSingleResult().intValue();
    }

    /**
     * @param treeRecordFileResult
     * @see TreeRecordFileService
     */
    @Transactional
    public TreeRecordFileResult save(TreeRecordFileResult treeRecordFileResult) throws IOException {
        TblTreeRecordFile tblTreeRecordFile = new TblTreeRecordFile();
        tblTreeRecordFile.setDocumentNumber(treeRecordFileResult.getDocumentNumber());
        tblTreeRecordFile.setName(treeRecordFileResult.getName());
        em.persist(tblTreeRecordFile);
        if (!treeRecordFileResult.getFileContent().equals("")) {
            final Long tblTreeRecordFileid = tblTreeRecordFile.getId();

            // TODO: decode and save to disk, split file, save total number of pages and records for them
            byte[] decodedPdf = Base64.getDecoder().decode(treeRecordFileResult.getFileContent());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decodedPdf);
            PdfDocument pdfDoc = new PdfDocument(new PdfReader(byteArrayInputStream));

            List<PdfDocument> splitDocuments = new PdfSplitter(pdfDoc).splitByPageCount(1);

            int page = 1;
            for (PdfDocument doc : splitDocuments) {
                doc.getWriter().flush();
                ByteArrayOutputStream baos = (ByteArrayOutputStream) doc.getWriter().getOutputStream();
                doc.close();
                byte[] pdfPage = baos.toByteArray();
                TblTreeRecordFilePage tblTreeRecordFilePage = new TblTreeRecordFilePage();
                tblTreeRecordFilePage.setTreeRecordFileId(em.find(TblTreeRecordFile.class, tblTreeRecordFileid));
                tblTreeRecordFilePage.setContent(Base64.getEncoder().encodeToString(pdfPage));
                tblTreeRecordFilePage.setPage(page++);
                em.persist(tblTreeRecordFilePage);
            }
            pdfDoc.close();
        }

        return new TreeRecordFileResult(tblTreeRecordFile);
    }

    /**
     *
     * @param treeRecordFileId
     * @return
     */
    @Transactional
    public TreeRecordFileResult load(Long treeRecordFileId) {
        TblTreeRecordFile tblTreeRecordFile = em.find(TblTreeRecordFile.class, treeRecordFileId);
        if (tblTreeRecordFile != null) {
            return new TreeRecordFileResult(tblTreeRecordFile);
        }

        return null;
    }

    /**
     *
     * @param treeRecordFilePageId
     * @return
     */
    @Transactional
    public TreeRecordFilePageResult loadPage(Long treeRecordFilePageId) {
        TblTreeRecordFilePage tblTreeRecordFilePage = em.find(TblTreeRecordFilePage.class, treeRecordFilePageId);
        if (tblTreeRecordFilePage != null) {
            return new TreeRecordFilePageResult(tblTreeRecordFilePage);
        }

        return null;
    }

    /**
     * Helper function for applying the search criteria for counting / selecting
     *
     * @see OrganisationManager#search(java.lang.Long, java.lang.String,
     * java.lang.String, java.lang.Boolean, java.lang.String, java.lang.Integer,
     * java.lang.Integer)
     * @see OrganisationManager#searchCount(java.lang.Long, java.lang.String,
     * java.lang.String, java.lang.Boolean, java.lang.String)
     *
     * @param cb
     * @param cq
     * @param bo
     */
    protected void applySearchCriteria(CriteriaBuilder cb, CriteriaQuery cq, Root<TblTreeRecordFile> bo, Long treeRecordFileId, Date year, String name, String documentNumber) {
        // helper variable for handling different paths
        Expression<String> path = null;
        // list of predicates to add in where clause
        List<Predicate> predicates = new ArrayList<>();

        if (treeRecordFileId != null) {
            path = bo.get("id");
            predicates.add(cb.equal(path, treeRecordFileId));
        }

        if (year != null) {
            path = bo.get("year");
            predicates.add(cb.equal(path, year));
        }

        if (name != null) {
            path = bo.get("name");
            predicates.add(cb.like(path, name + "%"));
        }

        if (documentNumber != null) {
            path = bo.get("documentNumber");
            predicates.add(cb.like(path, documentNumber + "%"));
        }

        // add all predicates as where clause
        cq.where(predicates.toArray(new Predicate[0]));
    }
}
