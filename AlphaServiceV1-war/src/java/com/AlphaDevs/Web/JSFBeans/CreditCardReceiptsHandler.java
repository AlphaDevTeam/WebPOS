
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.CreditCardReceipts;
import com.AlphaDevs.Web.SessionBean.CreditCardReceiptsController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

@ManagedBean
@SessionScoped
public class CreditCardReceiptsHandler {
    @EJB
    private CreditCardReceiptsController creditCardReceiptsController;
    
    private CreditCardReceipts current; 

    public CreditCardReceiptsHandler() {
        if(current == null){
            current = new CreditCardReceipts();
        }
    }

    public CreditCardReceiptsController getCreditCardReceiptsController() {
        return creditCardReceiptsController;
    }

    public void setCreditCardReceiptsController(CreditCardReceiptsController creditCardReceiptsController) {
        this.creditCardReceiptsController = creditCardReceiptsController;
    }

    public CreditCardReceipts getCurrent() {
        return current;
    }

    public void setCurrent(CreditCardReceipts current) {
        this.current = current;
    }
    
    public List<CreditCardReceipts> getList(){
        return getCreditCardReceiptsController().findAll();
    }

}
