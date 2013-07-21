
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.CashBookBalance;
import com.AlphaDevs.Web.Entities.Location;
import com.AlphaDevs.Web.Enums.BillStatus;
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
public class CashBookBalanceController extends AbstractFacade<CashBookBalance> 
{
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;
    
    public CashBookBalanceController()
    {
        super(CashBookBalance.class);
    }

    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }
    
    public CashBookBalance getCashBookBalanceObject(Location location , BillStatus billStat) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CashBookBalance> q = cb.createQuery(CashBookBalance.class);
        Root<CashBookBalance> c = q.from(CashBookBalance.class);
        q.select(c);
        //ParameterExpression<Product> p = cb.parameter(Product.class);
        q.where(cb.equal(c.get("location"), location)
                ,cb.equal(c.get("billStatus"), billStat));
        //q.where(cb.equal(c.get("supplier"), location)/);
        
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
