
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.Company;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

 /*
 * @author Mihindu Gajaba Karunarathne
 * @version 1.0.0
 * @since 2013/07/23
 * @see Alpha Development Team ( www.AlphaDevs.com )
 * 
 */
@Stateless
@LocalBean
public class CompanyController extends AbstractFacade<Company>{
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;

    public CompanyController(){
        super(Company.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    

}
