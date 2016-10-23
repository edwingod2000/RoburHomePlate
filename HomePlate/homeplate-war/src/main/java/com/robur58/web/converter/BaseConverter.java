package com.robur58.web.converter;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.convert.Converter;

public abstract class BaseConverter implements Converter, Serializable {

    /**
     * Maak de foutmelding op basis van een resourcebundle key en replacements.
     * De resourcebundle tekst heeft hetzelfde formaat als de JSF teksten.
     *
     * <ul>
     * <li>0 = nieuwe (foute) waarde
     * <li>1 = voorbeeldwaarde
     * <li>2 = label van component
     * </ul>
     *
     * @param key        Resourcebundle key.
     * @param value      Value van veld (optioneel).
     * @param example    Voorbeeld waarde (optioneel)
     * @param fieldLabel Veldnaam.
     * @return Geformatteerd bericht.
     */
    FacesMessage maakFoutmelding(String bundleName, String key, Object value, Object example, Object fieldLabel) {
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName);

        String ongeldigVeldMelding = formatMessage(bundle.getString(key), value, example, fieldLabel);
        String ongeldigVeldDetail = formatMessage(bundle.getString(key + "_detail"), value, example, fieldLabel);

        // As-is, severity is error bij ongeldig veld.
        return new FacesMessage(FacesMessage.SEVERITY_ERROR, ongeldigVeldMelding, ongeldigVeldDetail);
    }

    /**
     * Bepaal label voor component.
     * Als het label expliciet gezet is, gebruik dan dit label. In andere gevallen het id gebruiken.
     *
     * @param component JSF UIComponent.
     * @return Label van component of het (automatisch gegenereerde) id.
     */
    String getLabel(UIComponent component) {
        if (component == null) {
            // Zonder component kan label niet bepaald worden, maar in de praktijk is component altijd aanwezig.
            return "???label???";
        } else {
            Object label = component.getAttributes().get("label");
            return label != null ? label.toString() : component.getId();
        }
    }


    /**
     * Parameters in message verwerken.
     *
     * @param template   Message template met placeholders voor replacements
     * @param value      Value van veld (optioneel).
     * @param example    Voorbeeld waarde (optioneel)
     * @param fieldLabel Veldnaam.
     * @return Geformatteerd bericht.
     */
    private String formatMessage(String template, Object value, Object example, Object fieldLabel) {
        MessageFormat messageFormat = new MessageFormat(template);
        return messageFormat.format(new Object[]{value, example, fieldLabel});
    }
}
