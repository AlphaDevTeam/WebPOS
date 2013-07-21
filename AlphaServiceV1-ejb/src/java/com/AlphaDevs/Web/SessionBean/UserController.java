
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.Design;
import com.AlphaDevs.Web.Entities.Product;
import com.AlphaDevs.Web.Entities.UserX;
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
public class UserController extends AbstractFacade<UserX>
{
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;
    
    
    public UserController()
    {
        super(UserX.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<UserX> ValidateUser(UserX user) 
    {
        //System.out.println("Got Here With " + user.getPassWord() + " - " + user.getUserName());
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserX> q = cb.createQuery(UserX.class);
        Root<UserX> c = q.from(UserX.class);
        q.select(c);
        //ParameterExpression<Product> p = cb.parameter(Product.class);
        q.where(cb.equal(c.get("userName"), user.getUserName().trim())
                ,cb.equal(c.get("passWord"), user.getPassWord()));
        
        /*CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Design.class);
        Root<Design> root = cq.from(Design.class);
        cq.select(cq.from(Design.class)).where(cb.equal(root.get("product"), prod));
        */
        return getEntityManager().createQuery(q).getResultList();
        
    }

    public void persist(Object object) {
        em.persist(object);
    }

    
}
