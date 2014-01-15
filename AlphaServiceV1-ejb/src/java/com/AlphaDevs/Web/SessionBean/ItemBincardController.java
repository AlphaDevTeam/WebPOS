
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.ItemBincard;
import com.AlphaDevs.Web.Entities.Items;
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
public class ItemBincardController extends AbstractFacade<ItemBincard>
{
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;
    
    public ItemBincardController()
    {
        super(ItemBincard.class);
    }

    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }

    public List<ItemBincard> findItemByUnit(Items item) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ItemBincard> q = cb.createQuery(ItemBincard.class);
        Root<ItemBincard> c = q.from(ItemBincard.class);
        q.select(c);
        q.where(cb.equal(c.get("item"), item));
        
        return getEntityManager().createQuery(q).getResultList();
        
    }
   
    
    
}
