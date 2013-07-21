
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.Design;
import com.AlphaDevs.Web.Entities.Items;
import com.AlphaDevs.Web.Entities.Product;
import com.AlphaDevs.Web.Entities.Stock;
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
public class StockController extends AbstractFacade<Stock>
{
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;
    
    
    public StockController()
    {
        super(Stock.class);
    }

    
    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }

   public Stock findSpecific(Items items) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Stock> q = cb.createQuery(Stock.class);
        Root<Stock> c = q.from(Stock.class);
        q.select(c);
        //ParameterExpression<Product> p = cb.parameter(Product.class);
        q.where(cb.equal(c.get("SockItem"), items));
        
        /*CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Design.class);
        Root<Design> root = cq.from(Design.class);
        cq.select(cq.from(Design.class)).where(cb.equal(root.get("product"), prod));
        */
        return getEntityManager().createQuery(q).getSingleResult();
        
    }

    
}
