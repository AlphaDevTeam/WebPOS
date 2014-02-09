
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.Location;
import com.AlphaDevs.Web.Entities.Pump;
import java.util.List;
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
public class PumpController extends AbstractFacade<Pump>{
   
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;

    public PumpController(){
        super(Pump.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<Pump> findReadingByPump(Location location) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Pump> q = cb.createQuery(Pump.class);
        Root<Pump> c = q.from(Pump.class);
        q.select(c);
        q.where(cb.equal(c.get("relatedLocation"), location));
        
        List<Pump> resultList = getEntityManager().createQuery(q).getResultList();
        return resultList;
    }


}
