
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.AdditionalFields;
import com.AlphaDevs.Web.Entities.Company;
import com.AlphaDevs.Web.Enums.DocType;
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
public class AdditionalFieldsController extends AbstractFacade<AdditionalFields>{
   
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;
    
    public AdditionalFieldsController(){
        super(AdditionalFields.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return getEm();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    
    public List<AdditionalFields> findAll (DocType docType , Company company) 
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AdditionalFields> q = cb.createQuery(AdditionalFields.class);
        Root<AdditionalFields> c = q.from(AdditionalFields.class);
        q.select(c);
        //ParameterExpression<Product> p = cb.parameter(Product.class);
        q.where(cb.equal(c.get("displayOn"), docType)
                ,cb.equal(c.get("relatedCompany"), company));
        //q.where(cb.equal(c.get("supplier"), location)/);
        
        if(!getEntityManager().createQuery(q).getResultList().isEmpty())
        {
           return getEntityManager().createQuery(q).getResultList(); 
        }
        else
        {
            return null;
        }
        
        
    }
    
}
