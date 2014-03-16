
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.CreditCardReceipts;
import com.AlphaDevs.Web.Entities.CreditCardTeminals;
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
public class CreditCardReceiptsController extends AbstractFacade<CreditCardReceipts> {
    
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;

    public CreditCardReceiptsController(){
        super(CreditCardReceipts.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<CreditCardReceipts> findTerminalByLocation(CreditCardTeminals terminal, Location location) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CreditCardReceipts> q = cb.createQuery(CreditCardReceipts.class);
        Root<CreditCardReceipts> c = q.from(CreditCardReceipts.class);
        q.select(c);
        q.where(cb.equal(c.get("relatedTerminal"), terminal),cb.equal(c.get("relatedLocation"), location));
               
        List<CreditCardReceipts> resultList = getEntityManager().createQuery(q).getResultList();
        if(resultList == null || resultList.isEmpty()){
            return new ArrayList<CreditCardReceipts>();
        }else{
            return resultList;
        }
    }


    
}
