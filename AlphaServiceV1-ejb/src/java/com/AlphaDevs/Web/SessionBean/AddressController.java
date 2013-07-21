
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.Address;
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
public class AddressController extends AbstractFacade<Address>
{
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;
    
    public AddressController()
    {
        super(Address.class);
    }

    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }

    
}
