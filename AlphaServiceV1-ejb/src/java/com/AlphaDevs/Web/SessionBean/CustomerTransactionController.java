
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.CustomerTransaction;
import com.AlphaDevs.Web.Entities.Supplier;
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
 * @version 1.0.0
 * @since 2012/06/16
 * @see Alpha Development Team ( www.AlphaDevs.com )
 * 
 */

@Stateless
@LocalBean
public class CustomerTransactionController extends AbstractFacade<CustomerTransaction>
{
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;
    

    public CustomerTransactionController()
    {
        super(CustomerTransaction.class);
    }

    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }

    public List<CustomerTransaction> findItemByUnit(Supplier supplier) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CustomerTransaction> q = cb.createQuery(CustomerTransaction.class);
        Root<CustomerTransaction> c = q.from(CustomerTransaction.class);
        q.select(c);
        q.where(cb.equal(c.get("supplier"), supplier));
        
        return getEntityManager().createQuery(q).getResultList();
        
    }
}
