
package com.AlphaDevs.Web.Enums;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

public enum ChequeStatus {
    
    PENDING("Pending"),
    DEPOSITED("Deposited"),
    REALISED("Realised");
    
    private String fieldName;
    private ChequeStatus(String feild){
        fieldName = feild;
    }
    
    public String getChequeStatus(){
        return fieldName;
    }
    

}
