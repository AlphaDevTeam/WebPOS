
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.Items;
import com.AlphaDevs.Web.Entities.Location;
import com.AlphaDevs.Web.Entities.MeterReading;
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
public class MeterReadingController extends AbstractFacade<MeterReading> {
    
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;

    public MeterReadingController(){
        super(MeterReading.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);
    }

    public double findReadingByItem(Items item, Location location) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MeterReading> q = cb.createQuery(MeterReading.class);
        Root<MeterReading> c = q.from(MeterReading.class);
        q.select(c);
        q.where(cb.equal(c.get("relatedItem"), item),cb.equal(c.get("relatedLocation"), location));
        
        List<MeterReading> resultList = getEntityManager().createQuery(q).getResultList();
        if(resultList == null || resultList.isEmpty()){
            return 0;
        }else{
            if(resultList.get(0) != null){
                return resultList.get(0).getReading();
            }else{
                return 0;
            }
        }
    }

}
