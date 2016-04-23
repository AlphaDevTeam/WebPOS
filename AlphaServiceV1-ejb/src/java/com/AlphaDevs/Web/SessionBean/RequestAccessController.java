package com.AlphaDevs.Web.SessionBean;

import com.AlphaDevs.Web.Entities.AccessRequest;
import com.AlphaDevs.Web.Entities.AccessRequest_;
import com.AlphaDevs.Web.Enums.Status;
import java.util.ArrayList;
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
 * Alpha Development Team ( Pvt ) Ltd www.AlphaDevs.com Info@AlphaDevs.com
 *
 */
@Stateless
@LocalBean
public class RequestAccessController extends AbstractFacade<AccessRequest> {

    @PersistenceContext(unitName = "AlphaServiceV1-ejbPU")
    private EntityManager em;

    public RequestAccessController() {
        super(AccessRequest.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<AccessRequest> getRequests(Status status) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AccessRequest> q = cb.createQuery(AccessRequest.class);
        Root<AccessRequest> c = q.from(AccessRequest.class);
        q.select(c);
        q.where(cb.equal(c.get(AccessRequest_.requestStatus), status));

        List<AccessRequest> resultList = getEntityManager().createQuery(q).getResultList();
        if (resultList == null || resultList.isEmpty()) {
            return new ArrayList<AccessRequest>();
        } else {
            return resultList;
        }
    }

}
