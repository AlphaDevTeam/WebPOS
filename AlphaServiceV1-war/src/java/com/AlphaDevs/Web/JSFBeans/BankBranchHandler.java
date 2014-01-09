

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.BankBranch;
import com.AlphaDevs.Web.SessionBean.BankBranchController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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
@RequestScoped
public class BankBranchHandler {
    @EJB
    private BankBranchController bankBranchController;
    
    private BankBranch current;
    
     
    public BankBranchHandler() {
        if(current == null){
            current = new BankBranch();
        }
    }


    public BankBranchController getBankBranchController() {
        return bankBranchController;
    }

    public void setBankBranchController(BankBranchController bankBranchController) {
        this.bankBranchController = bankBranchController;
    }

    public BankBranch getCurrent() {
        return current;
    }

    public void setCurrent(BankBranch current) {
        this.current = current;
    }
    
    public List<BankBranch> getList(){
        return getBankBranchController().findAll();
    }
   
}
