package com.robur58.web.converter;

import java.text.DecimalFormat;
import java.text.ParseException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;

/**
 * Converteert van Number naar String en terug van String naar BigDecimal,
 * volgens het formaat in "currency-format" in basisverzekering.properties.
 */
@FacesConverter("numberConverter")
public class NumberConverter extends BaseConverter {

    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_NUMBER_FORMAT = "#.##";

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String newValue) {
        if (StringUtils.isBlank(newValue)) {
            return null;
        }
        try {
            DecimalFormat format = getDefaultFormatter();
            format.setParseBigDecimal(true);
            return format.parse(newValue.trim());
        } catch (ParseException ex) {
            throw new ConverterException(maakFoutmelding(newValue, component));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (!(value instanceof Number)) {
            throw new ConverterException(maakFoutmelding(value, component));
        }
        return getDefaultFormatter().format(value);
    }

    private DecimalFormat getDefaultFormatter() {
    	return new DecimalFormat(DEFAULT_NUMBER_FORMAT);
    }

    private FacesMessage maakFoutmelding(Object value, UIComponent component) {
        return maakFoutmelding("meldingen","ongeldigNummer", value, null, getLabel(component));
    }
}

