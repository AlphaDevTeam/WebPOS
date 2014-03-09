
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.Design;
import com.AlphaDevs.Web.Entities.Items;
import com.AlphaDevs.Web.Entities.Location;
import com.AlphaDevs.Web.Entities.Product;
import com.AlphaDevs.Web.Entities.Units;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
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
public class ItemsController extends AbstractFacade<Items>
{
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;
    
    public ItemsController()
    {
        super(Items.class);
    }

    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }
    
    public List<Items> findLike(String query) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Items> q = cb.createQuery(Items.class);
        Root<Items> c = q.from(Items.class);
        q.select(c);
        //ParameterExpression<Product> p = cb.parameter(Product.class);
        Expression<String> path = c.get("ItemDescription");
        q.where(cb.like(path, "%" + query + "%"));
        
        /*CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Design.class);
        Root<Design> root = cq.from(Design.class);
        cq.select(cq.from(Design.class)).where(cb.equal(root.get("product"), prod));
        */
        return getEntityManager().createQuery(q).getResultList();
        
    }
    
    public List<Items> findItemByUnit(Units units , Location itemLocation) 
    {
        System.out.println("Location Rece: " + itemLocation.getId() + " - findItemByUnit");
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Items> q = cb.createQuery(Items.class);
        Root<Items> c = q.from(Items.class);
        q.select(c);
        q.where(cb.equal(c.get("unitOfMeasurement"), units),cb.equal(c.get("ItemLocation"), itemLocation));
        if(getEntityManager().createQuery(q).getResultList() == null){
            return new ArrayList<Items>();
        }else{
            return getEntityManager().createQuery(q).getResultList();
        }
        
        
    }
    
}
