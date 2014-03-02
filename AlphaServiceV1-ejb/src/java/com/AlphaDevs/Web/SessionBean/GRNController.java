
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.Design;
import com.AlphaDevs.Web.Entities.GRN;
import com.AlphaDevs.Web.Entities.GRNDetails;
import com.AlphaDevs.Web.Entities.Location;
import com.AlphaDevs.Web.Entities.Product;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
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
public class GRNController extends AbstractFacade<GRN>
{
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;


   
    public GRNController()
    {
        super(GRN.class);
    }
    
    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }

    public List<GRNDetails> findSpecific(GRN grn) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<GRNDetails> q = cb.createQuery(GRNDetails.class);
        Root<GRNDetails> c = q.from(GRNDetails.class);
        q.select(c);
        //ParameterExpression<Product> p = cb.parameter(Product.class);
        q.where(cb.equal(c.get("relatedGRN"), grn));
        
        /*CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Design.class);
        Root<Design> root = cq.from(Design.class);
        cq.select(cq.from(Design.class)).where(cb.equal(root.get("product"), prod));
        */
        return getEntityManager().createQuery(q).getResultList();
        
    }
    
    public List<GRN> findByDateRange(Date date, Location location) 
    {
        
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery cQuery = builder.createQuery();
//        Root<AcsTemplateDateJPA> root = cQuery.from(AcsTemplateDateJPA.class);
//        ParameterExpression<Date> d = builder.parameter(Date.class);
//        cQuery.where(builder.between(d, root.<Date>get("inservicedate"), root.<Date>get("outservicedate"))); 
//        Query query = em.createQuery(cQuery.select(root.get("acstemplateid"))).setParameter(d, planDate, TemporalType.DATE);
//        List results =query.getResultList(); 
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<GRN> q = cb.createQuery(GRN.class);
        Root<GRN> c = q.from(GRN.class);
        q.select(c);
        q.where(cb.equal(c.get("grnDate"), date),cb.equal(c.get("location"), location));
        return getEntityManager().createQuery(q).getResultList();
        
    }
   
    
}
