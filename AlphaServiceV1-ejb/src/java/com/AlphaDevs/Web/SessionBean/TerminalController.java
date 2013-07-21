
package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.Terminal;
import com.AlphaDevs.Web.Entities.UserX;
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
public class TerminalController extends AbstractFacade<Terminal>
{
    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;
    
    public TerminalController()
    {
        super(Terminal.class);
    }

    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }

    public List<Terminal> ValidateTerminal(Terminal terminal) 
    {
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Terminal> q = cb.createQuery(Terminal.class);
        Root<Terminal> c = q.from(Terminal.class);
        q.select(c);
        q.where(cb.equal(c.get("terminalIP"), terminal.getTerminalIP().trim()));
        
        return getEntityManager().createQuery(q).getResultList();
        
    }
    
}
