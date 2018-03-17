/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacq.service.manager;

import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jacq.common.manager.BaseImageServerManager;

/**
 *
 * @author wkoller
 */
@ManagedBean
public class ImageServerManager extends BaseImageServerManager {

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

}
