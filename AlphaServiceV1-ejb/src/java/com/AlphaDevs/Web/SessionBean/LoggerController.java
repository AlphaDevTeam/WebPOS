
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.Logger;
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
public class LoggerController extends AbstractFacade<Logger>
{
    public LoggerController()
    {
        super(Logger.class);
    }
    
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;
    

    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }

   
    
}
