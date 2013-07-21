
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.CashBook;
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
public class CashbookController extends AbstractFacade<CashBook>
{
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;
    
    public CashbookController()
    {
        super(CashBook.class);
    }

    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }
    

}
