
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.CreditCardTeminals;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * @author Mihindu Gajaba Karunarathne
 * @version 1.0.0
 * @since 2012/06/16
 * @see Alpha Development Team ( www.AlphaDevs.com )
 * 
 */
@Stateless
@LocalBean
public class CreditCardTeminalsController extends AbstractFacade<CreditCardTeminals> {
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public CreditCardTeminalsController(){
        super(CreditCardTeminals.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    

}
