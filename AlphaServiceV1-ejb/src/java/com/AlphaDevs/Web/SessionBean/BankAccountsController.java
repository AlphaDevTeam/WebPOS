
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.BankAccounts;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
public class BankAccountsController extends AbstractFacade<BankAccounts> {
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;
    
    public BankAccountsController(){
        super(BankAccounts.class);
    }

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    
}
