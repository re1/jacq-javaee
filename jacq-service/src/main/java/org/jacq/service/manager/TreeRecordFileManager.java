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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import org.jacq.common.model.rest.TreeRecordFileResult;
import org.jacq.common.model.jpa.TblTreeRecordFile;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author fhafner
 */
public class TreeRecordFileManager {

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
     * @see OrganisationService#save(org.jacq.common.model.OrganisationResult)
     */
    @Transactional
    public TreeRecordFileResult save(TreeRecordFileResult treeRecordFileResult) {
        TblTreeRecordFile tblTreeRecordFile = new TblTreeRecordFile();

        tblTreeRecordFile.setDocumentNumber(treeRecordFileResult.getDocumentNumber());
        tblTreeRecordFile.setName(treeRecordFileResult.getName());
        em.persist(tblTreeRecordFile);

        return new TreeRecordFileResult(tblTreeRecordFile);
    }

    /**
     *
     * @param is
     * @param formData
     * @return
     * @throws IOException
     */
    /*    @Transactional
    public Response uploadFile(@FormDataParam("upload") InputStream is,
            @FormDataParam("upload") FormDataContentDisposition formData) throws IOException {
        String fileLocation = "c:/work/" + formData.getFileName();
        saveFile(is, fileLocation);
        String result = "Successfully File Uploaded on the path " + fileLocation;
        return Response.status(Status.OK).entity(result).build();

    }
     */
    public void saveFile(InputStream is, String fileLocation) throws IOException {
        OutputStream os = new FileOutputStream(new File(fileLocation));
        byte[] buffer = new byte[256];
        int bytes = 0;
        while ((bytes = is.read(buffer)) != -1) {
            os.write(buffer, 0, bytes);
        }
    }

    /**
     * Helper function for applying the search criteria for counting / selecting
     *
     * @see OrganisationManager#search(java.lang.Long, java.lang.String, java.lang.String, java.lang.Boolean,
     * java.lang.String, java.lang.Integer, java.lang.Integer)
     * @see OrganisationManager#searchCount(java.lang.Long, java.lang.String, java.lang.String, java.lang.Boolean,
     * java.lang.String)
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
