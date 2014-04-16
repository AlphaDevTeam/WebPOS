
package com.AlphaDevs.Web.SessionBean;

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
public class CreditCardTeminalsController extends AbstractFacade<CreditCardTeminals> {
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public CreditCardTeminalsController(){
        super(CreditCardTeminals.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<CreditCardTeminals> findTerminalByLocation(Location location) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CreditCardTeminals> q = cb.createQuery(CreditCardTeminals.class);
        Root<CreditCardTeminals> c = q.from(CreditCardTeminals.class);
        q.select(c);
        q.where(cb.equal(c.get("relatedLocation"), location));
               
        List<CreditCardTeminals> resultList = getEntityManager().createQuery(q).getResultList();
        if(resultList == null || resultList.isEmpty()){
            return new ArrayList<CreditCardTeminals>();
        }else{
            return resultList;
        }
    }
    
    
    

    

}
