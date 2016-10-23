/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author godefrooije
 */
@FacesValidator("emailValidator")
public class EMailValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        org.apache.commons.validator.EmailValidator emailValidator = org.apache.commons.validator.EmailValidator 
            .getInstance(); 
        HtmlInputText htmlInputText = (HtmlInputText) component; 

        if (!emailValidator.isValid((String) value)) 
        { 
          FacesMessage facesMessage = new FacesMessage(htmlInputText.getLabel() 
              + ": E-Mail formaat is niet juist!"); 
          throw new ValidatorException(facesMessage); 
        } 
    }
    
}
