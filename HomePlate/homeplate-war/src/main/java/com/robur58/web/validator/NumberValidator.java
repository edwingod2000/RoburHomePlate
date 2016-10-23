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
@FacesValidator("nummerValidator")
public class NumberValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        HtmlInputText htmlInputText = (HtmlInputText) component; 

        try {
            Integer nummer = new Integer((String)value);
        } catch (Exception e) {
          FacesMessage facesMessage = new FacesMessage(htmlInputText.getLabel() 
              + ": is geen nummer!"); 
          throw new ValidatorException(facesMessage); 
        }
    }
    
}
