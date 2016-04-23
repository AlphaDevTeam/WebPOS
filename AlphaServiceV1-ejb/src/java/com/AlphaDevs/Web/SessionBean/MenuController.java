/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.Menu;
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
public class MenuController extends AbstractFacade<Menu> {
    
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    EntityManager em;
    
    public MenuController() {
        super(Menu.class);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    protected EntityManager getEntityManager() {
        
        return em;
    }
    
    public void persist(Object object) {
        
        em.persist(object);
    }
    
}
