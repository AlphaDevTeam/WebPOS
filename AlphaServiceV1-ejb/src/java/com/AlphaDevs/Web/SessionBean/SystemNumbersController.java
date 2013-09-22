
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.Company;
import com.AlphaDevs.Web.Entities.Location;
import com.AlphaDevs.Web.Entities.SystemNumbers;
import com.AlphaDevs.Web.Entities.Systems;
import com.AlphaDevs.Web.Entities.UserX;
import com.AlphaDevs.Web.Enums.Document;
import java.util.List;
import java.util.Map;
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
public class SystemNumbersController extends AbstractFacade<SystemNumbers>{
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;

    public SystemNumbersController(){
        super(SystemNumbers.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return  em;
    }

     public List<SystemNumbers> findSpecific(Company relatedCompany , Location relatedLocation , Document relatedDocument ) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<SystemNumbers> q = cb.createQuery(SystemNumbers.class);
        Root<SystemNumbers> c = q.from(SystemNumbers.class);
        q.select(c);
        //ParameterExpression<Product> p = cb.parameter(Product.class);
       
        q.where(cb.equal(c.get("relatedCompany"), relatedCompany)
                ,cb.equal(c.get("relatedLocation"), relatedLocation)
                ,cb.equal(c.get("document"), relatedDocument));
        
        /*CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Design.class);
        Root<Design> root = cq.from(Design.class);
        cq.select(cq.from(Design.class)).where(cb.equal(root.get("product"), prod));
        */
        return getEntityManager().createQuery(q).getResultList();
        
    }
     
    
}
