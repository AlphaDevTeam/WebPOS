

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.ItemBincard;
import com.AlphaDevs.Web.Entities.Items;
import com.AlphaDevs.Web.SessionBean.ItemBincardController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@RequestScoped
public class ItemBinCardHandler 
{
    @EJB
    private ItemBincardController itemBincardController;
    
    private ItemBincard current;
    private Items selectedItem;

    /** Creates a new instance of ItemBinCardHandler */
    public ItemBinCardHandler() 
    {
        if(current == null)
        {
            current = new ItemBincard();
        }
        selectedItem = new Items();
    }

    public ItemBincardController getItemBincardController() {
        return itemBincardController;
    }

    public void setItemBincardController(ItemBincardController itemBincardController) {
        this.itemBincardController = itemBincardController;
    }

    public Items getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Items selectedItem) {
        this.selectedItem = selectedItem;
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
    
    public List<ItemBincard> getSelectedList(){
        return getItemBincardController().findItemByUnit(getCurrent().getItem());
    }

}
