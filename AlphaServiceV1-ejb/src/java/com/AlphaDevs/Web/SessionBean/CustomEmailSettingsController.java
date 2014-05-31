
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.Company;
import com.AlphaDevs.Web.Entities.CustomEmailSettings;
import com.AlphaDevs.Web.Entities.Location;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/*
 * @author Mihindu Gajaba Karunarathne
 * @version 1.0.0
 * @since 2012/06/16
 * @see Alpha Development Team ( www.AlphaDevs.com )
 * 
 */

@Stateless
@LocalBean
public class CustomEmailSettingsController extends AbstractFacade<CustomEmailSettings>{
    
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;

    public CustomEmailSettingsController(){
        super(CustomEmailSettings.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);
    }

    
    public List<CustomEmailSettings> findRelatedEmailSettings(Company relatedcompany, Location location) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CustomEmailSettings> q = cb.createQuery(CustomEmailSettings.class);
        Root<CustomEmailSettings> c = q.from(CustomEmailSettings.class);
        q.select(c);
        q.where(cb.equal(c.get("relatedCompany"),relatedcompany ),cb.equal(c.get("relatedLocation"), location));
               
        List<CustomEmailSettings> resultList = getEntityManager().createQuery(q).getResultList();
        if(resultList == null || resultList.isEmpty()){
            return new ArrayList<CustomEmailSettings>();
        }else{
            return resultList;
        }
    }
    
    public List<CustomEmailSettings> findRelatedEmailSettings(Company relatedcompany) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CustomEmailSettings> q = cb.createQuery(CustomEmailSettings.class);
        Root<CustomEmailSettings> c = q.from(CustomEmailSettings.class);
        q.select(c);
        q.where(cb.equal(c.get("relatedCompany"),relatedcompany ));
               
        List<CustomEmailSettings> resultList = getEntityManager().createQuery(q).getResultList();
        if(resultList == null || resultList.isEmpty()){
            return new ArrayList<CustomEmailSettings>();
        }else{
            return resultList;
        }
    }


}
