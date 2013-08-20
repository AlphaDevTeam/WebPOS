
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.PropertyManager;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

@Stateless
@LocalBean
public class PropertyManagerController extends AbstractFacade<PropertyManager>{

    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;

    public PropertyManagerController(){
        super(PropertyManager.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);
    }

    
}
