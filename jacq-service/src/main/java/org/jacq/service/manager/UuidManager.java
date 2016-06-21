package org.jacq.service.manager;

import java.io.Serializable;
import java.util.UUID;
import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import org.jacq.common.model.jpa.SrvcUuidMinter;
import org.jacq.common.model.jpa.SrvcUuidMinterType;
import org.jacq.common.rest.UuidService;

/**
 *
 * @author wkoller
 */
@ManagedBean
public class UuidManager implements Serializable {

    @PersistenceContext
    protected EntityManager em;

    /**
     * @see UuidService#resolve(java.lang.String)
     */
    public String resolve(String uuid) {
        TypedQuery<SrvcUuidMinter> query = em.createNamedQuery("SrvcUuidMinter.findByUuid", SrvcUuidMinter.class);
        query.setParameter("uuid", uuid);

        SrvcUuidMinter srvcUuidMinter = query.getSingleResult();
        if (srvcUuidMinter != null) {
            return srvcUuidMinter.getUuid();
        }

        throw new NotFoundException("Unable to resolve UUID");
    }

    /**
     * @see UuidService#mint(java.lang.String, int)
     */
    public String mint(String type, int internal_id) {
        // check if an uuid already exists for this entry
        TypedQuery<SrvcUuidMinter> query = em.createNamedQuery("SrvcUuidMinter.findByTypeAndInternalId", SrvcUuidMinter.class);
        query.setParameter("uuidMinterType", "type");
        query.setParameter("internalId", "internal_id");

        SrvcUuidMinter srvcUuidMinter = query.getSingleResult();
        if (srvcUuidMinter != null) {
            return srvcUuidMinter.getUuid();
        }
        else {
            // if not lookup the type and create a new uuid
            TypedQuery<SrvcUuidMinterType> typeQuery = em.createNamedQuery("SrvcUuidMinterType.findByDescription", SrvcUuidMinterType.class);
            typeQuery.setParameter("description", type);
            SrvcUuidMinterType srvcUuidMinterType = typeQuery.getSingleResult();
            if (srvcUuidMinterType == null) {
                throw new NotFoundException("Type '" + type + "' not found");
            }

            // create new uuid entry and save it to the database
            srvcUuidMinter = new SrvcUuidMinter();
            srvcUuidMinter.setUuidMinterTypeId(srvcUuidMinterType);
            srvcUuidMinter.setInternalId(internal_id);
            srvcUuidMinter.setUuid(UUID.randomUUID().toString());
            em.persist(srvcUuidMinter);

            return srvcUuidMinter.getUuid();
        }
    }

}
