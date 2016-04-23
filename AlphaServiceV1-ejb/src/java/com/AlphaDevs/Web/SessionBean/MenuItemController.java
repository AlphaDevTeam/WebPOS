/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.MenuItem;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kumuditha_2
 */
@Stateless
@LocalBean
public class MenuItemController extends AbstractFacade<MenuItem>{
    
    
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    EntityManager em;

    public MenuItemController() {
        super(MenuItem.class);
    }

 
    @Override
    protected EntityManager getEntityManager() {
    return em;
    
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
     public void persist(Object object) {
        
        em.persist(object);
    }
}
