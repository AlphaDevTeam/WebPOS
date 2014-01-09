
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.Bank;
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
public class BankController extends AbstractFacade<Bank>{
    
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;
    
    public BankController(){
        super(Bank.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
   

}
