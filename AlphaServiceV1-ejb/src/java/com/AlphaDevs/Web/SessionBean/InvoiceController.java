
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.Invoice;
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
public class InvoiceController extends AbstractFacade<Invoice>
{
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;
    
    
    public InvoiceController()
    {
        super(Invoice.class);
    }
    
    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<Invoice> findInvoicesByDate(Date relatedDate, Location location) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Invoice> q = cb.createQuery(Invoice.class);
        Root<Invoice> c = q.from(Invoice.class);
        q.select(c);
        q.where(cb.equal(c.get("TrnDate"), relatedDate),cb.equal(c.get("Location"), location));
               
        List<Invoice> resultList = getEntityManager().createQuery(q).getResultList();
        if(resultList == null || resultList.isEmpty()){
            return new ArrayList<Invoice>();
        }else{
            return resultList;
        }
    }
 
    
}
