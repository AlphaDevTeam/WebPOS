

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.ItemBincard;
import com.AlphaDevs.Web.SessionBean.ItemBincardController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@SessionScoped
public class ItemBinCardHandler 
{
    @EJB
    private ItemBincardController itemBincardController;
    
    private ItemBincard current;

    /** Creates a new instance of ItemBinCardHandler */
    public ItemBinCardHandler() 
    {
        if(current == null)
        {
            current = new ItemBincard();
        }
    }

    public ItemBincard getCurrent() {
        return current;
    }

    public void setCurrent(ItemBincard current) {
        this.current = current;
    }
    
    public List<ItemBincard> getList()
    {
        return itemBincardController.findAll();
    }

}
