
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.CustomerBalance;
import com.AlphaDevs.Web.Entities.CustomerBalance_;
import com.AlphaDevs.Web.Entities.Location;
import com.AlphaDevs.Web.Entities.Supplier;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

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
    
    public List<CustomerBalance> findCustomerBalances(boolean showInactives) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CustomerBalance> q = cb.createQuery(CustomerBalance.class);
        Root<CustomerBalance> c = q.from(CustomerBalance.class);
        Join<CustomerBalance,Supplier> supplier = c.join(CustomerBalance_.supplier); 
        q.select(c);
        if(!showInactives){
            q.where(cb.equal(supplier.get("inactive"), showInactives));
        }
               
        List<CustomerBalance> resultList = getEntityManager().createQuery(q).getResultList();
        if(resultList == null || resultList.isEmpty()){
            return new ArrayList<CustomerBalance>();
        }else{
            return resultList;
        }
    }
    
}
