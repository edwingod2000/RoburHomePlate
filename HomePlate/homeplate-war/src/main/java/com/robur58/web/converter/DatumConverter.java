package com.robur58.web.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;

/**
 * JSF Converter voor een datum in het formaat dd-mm-jjjj of ddmmjjjj.
 */
@FacesConverter(value = "datumConverter")
public class DatumConverter extends BaseConverter {

    private static final long serialVersionUID = 1L;
    private static final Pattern DATE_REGEXP = Pattern.compile("^[0123]\\d[01]\\d[^0]\\d{3}$|^[0123]?\\d([-/.])[01]?\\d\\1[^0]\\d{3}$");

    /**
     * Converteer een string naar een datum.
     *
     * @param context FacesContext.
     * @param component UIComponent.
     * @param newValue Datum als string.
     * @return Een datum object, of null.
     *
     * @throws ConverterException als conversie niet lukt.
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String newValue) {

        if (StringUtils.isBlank(newValue)) {
            return null;
        }

        String dateAsString = newValue.trim();
        if (!DATE_REGEXP.matcher(dateAsString).matches()) {
            throw new ConverterException(maakFoutmelding(newValue, component));
        }
        DateFormat[] dateFormats = new DateFormat[]{
                new SimpleDateFormat("dd-MM-yyyy"),
                new SimpleDateFormat("ddMMyyyy")
        };

        for (DateFormat dateFormat : dateFormats) {
            try {
                dateFormat.setLenient(false);
                Date result = dateFormat.parse(dateAsString);
                Calendar cal = new GregorianCalendar();
                cal.setTime(result);

                // Skip.
                if (cal.get(Calendar.YEAR) > 9999) {
                    continue;
                }
                return result;

            } catch (ParseException ex) {
                // ignore parse exception to find another candidate.
            }
        }
        // Dit kan niet optreden.
        throw new ConverterException(maakFoutmelding(newValue, component));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (!(value instanceof Date || value instanceof GregorianCalendar)) {
            throw new ConverterException(maakFoutmelding(value, component));
        }
        SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy");
        if(value instanceof GregorianCalendar){
        	return sdf.format((Date)((GregorianCalendar)value).getTime());
        }
        return sdf.format((Date) value);
    }

    /**
     * Faces melding maken dat bij dit component hoort.
     *
     * @param value     De foute waarde.
     * @param component Component.
     * @return Faces melding.
     */
    private FacesMessage maakFoutmelding(Object value, UIComponent component) {
        return maakFoutmelding("meldingen", "ongeldigeDatum", value, null, getLabel(component));
    }
}
