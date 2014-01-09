
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.BankBranch;
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
public class BankBranchController extends AbstractFacade<BankBranch>{
    
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;

    public BankBranchController(){
        super(BankBranch.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);
    }


}
