
package com.AlphaDevs.Helpers.Error;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * @version 1.0.0
 * @since 2013/07/29
 * @see Alpha Development Team ( www.AlphaDevs.com )
 * 
 */
public class ValidationError {
    
    private String errorCode;
    private String errorTitle;
    private String errorDetails;

    public ValidationError(String errorCode, String errorTitle, String errorDetails) {
        this.errorCode = errorCode;
        this.errorTitle = errorTitle;
        this.errorDetails = errorDetails;
    }

    public ValidationError() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }
    
}
