
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.CustomerBalance;
import com.AlphaDevs.Web.Entities.Design;
import com.AlphaDevs.Web.Entities.Supplier;

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
public class CustomerBalanceController extends AbstractFacade<CustomerBalance>
{
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;
    
    public CustomerBalanceController()
    {
        super(CustomerBalance.class);
    }

    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }

    
    public double getCustomerBalance(Supplier supllier) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CustomerBalance> q = cb.createQuery(CustomerBalance.class);
        Root<CustomerBalance> c = q.from(CustomerBalance.class);
        q.select(c);
        //ParameterExpression<Product> p = cb.parameter(Product.class);
        q.where(cb.equal(c.get("supplier"), supllier));
        
        if(!getEntityManager().createQuery(q).getResultList().isEmpty())
        {
           return getEntityManager().createQuery(q).getSingleResult().getBalance(); 
        }
        else
        {
            return -1;
        }
        
        
    }
    
    public CustomerBalance getCustomerBalanceObject(Supplier supllier) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CustomerBalance> q = cb.createQuery(CustomerBalance.class);
        Root<CustomerBalance> c = q.from(CustomerBalance.class);
        q.select(c);
        //ParameterExpression<Product> p = cb.parameter(Product.class);
        q.where(cb.equal(c.get("supplier"), supllier));
        
        if(!getEntityManager().createQuery(q).getResultList().isEmpty())
        {
           return getEntityManager().createQuery(q).getSingleResult(); 
        }
        else
        {
            return null;
        }
        
        
    }
    
}
