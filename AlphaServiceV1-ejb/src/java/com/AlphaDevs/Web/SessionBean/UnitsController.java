
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.Units;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
public class UnitsController extends AbstractFacade<Units> {
    
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;
    
    public UnitsController(){
        super(Units.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public Units findUnitsByCode(String unitCode) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Units> q = cb.createQuery(Units.class);
        Root<Units> c = q.from(Units.class);
        q.select(c);
        q.where(cb.equal(c.get("unitCode"), unitCode));
        
        return getEntityManager().createQuery(q).getSingleResult();
        
    }

}
