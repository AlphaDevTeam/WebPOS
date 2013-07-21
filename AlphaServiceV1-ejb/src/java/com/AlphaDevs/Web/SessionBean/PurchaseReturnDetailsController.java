
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.PurchaseReturnDetails;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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
public class PurchaseReturnDetailsController extends AbstractFacade<PurchaseReturnDetails>
{
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;

    public PurchaseReturnDetailsController()
    {
        super(PurchaseReturnDetails.class);
    }
    
    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }

    
}
