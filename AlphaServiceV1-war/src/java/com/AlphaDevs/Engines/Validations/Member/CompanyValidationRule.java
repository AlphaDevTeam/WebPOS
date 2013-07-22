/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.Engines.Validations.Member;

import com.AlphaDevs.Engines.Validations.ValidationRule;

/**
 *
 * @author Home
 */
public class CompanyValidationRule implements ValidationRule{

    @Override
    public String isRequred() {
        return "This is Company String";
    }
    
}
