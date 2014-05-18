
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.CashBook;
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

 /*
 * @author Mihindu Gajaba Karunarathne
 * @version 1.0.0
 * @since 2012/06/16
 * @see Alpha Development Team ( www.AlphaDevs.com )
 * 
 */

@Stateless
@LocalBean
public class CashbookController extends AbstractFacade<CashBook>
{
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;
    
    public CashbookController()
    {
        super(CashBook.class);
    }

    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }
    
    public List<CashBook> findReadingByDate(Date relatedDate, Location location) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CashBook> q = cb.createQuery(CashBook.class);
        Root<CashBook> c = q.from(CashBook.class);
        q.select(c);
        q.where(cb.equal(c.get("relatedDate"), relatedDate),cb.equal(c.get("location"), location));
               
        List<CashBook> resultList = getEntityManager().createQuery(q).getResultList();
        if(resultList == null || resultList.isEmpty()){
            return new ArrayList<CashBook>();
        }else{
            return resultList;
        }
    }

}
