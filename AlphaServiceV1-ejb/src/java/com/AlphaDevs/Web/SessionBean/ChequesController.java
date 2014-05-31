
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.Cheques;
import com.AlphaDevs.Web.Entities.Location;
import java.util.ArrayList;
import java.util.Date;
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
public class ChequesController extends AbstractFacade<Cheques> {
    
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;

    public ChequesController(){
        super(Cheques.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<Cheques> findChequesByDate(Date relatedDate, Location location) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Cheques> q = cb.createQuery(Cheques.class);
        Root<Cheques> c = q.from(Cheques.class);
        q.select(c);
        q.where(cb.equal(c.get("chequeDate"), relatedDate),cb.equal(c.get("relatedLocation"), location));
               
        List<Cheques> resultList = getEntityManager().createQuery(q).getResultList();
        if(resultList == null || resultList.isEmpty()){
            return new ArrayList<Cheques>();
        }else{
            return resultList;
        }
    }

}
