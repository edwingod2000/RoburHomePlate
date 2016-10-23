package com.robur58.web.controller;

import com.robur58.domein.BsmInfo;
import com.robur58.web.common.ServerPropertiesHelper;
import com.robur58.web.view.HomePlateSessionTO;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;

public class Controller {

    private HomePlateSessionTO homePlateSessionTO;

    private static String separatorString;

    public HomePlateSessionTO getHomePlateSessionTO() {
        HomePlateSessionTO result = (HomePlateSessionTO)getObjectVanSessie(HomePlateSessionTO.HOMEPLATESESSIONTO);

        if (result == null) {
            result = new HomePlateSessionTO();
            setHomePlateSessionTO(homePlateSessionTO);
        }
        return result;
    }

    public void setHomePlateSessionTO(HomePlateSessionTO homePlateSessionTO) {
        setObjectOpSessie(HomePlateSessionTO.HOMEPLATESESSIONTO, homePlateSessionTO);
    }

    public String getSponsorImagePrefix() {
        return ServerPropertiesHelper.getInstance().getSponsorimageprefix();
    }
    
    public SecurityController getSecurityController() {
        SecurityController result = (SecurityController)getObjectVanSessie(SecurityController.SECURITY_CONTROLLER_SESSION);
        return result;
    }
    
    /**
     * Halen van Object op session onder opgegeven sleutel.
     *
     * @param key Opgeven sleutel.
     * @return Object van sessie.
     */
    public Object getObjectVanSessie(String key) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            return session.getAttribute(key);
        }
        return null;
    }

    /**
     * Zetten van Object op session onder opgegeven sleutel.
     *
     * @param key Opgeven sleutel.
     * @param o   Object op te slaan.
     */
    public void setObjectOpSessie(String key, Object o) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ((HttpSession) facesContext.getExternalContext().getSession(true)).setAttribute(key, o);
    }

    /**
     * Verwijderen object van sessie.
     *
     * @param key Sleutel van object op sessie
     */
    public void verwijderObjectVanSessie(String key) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        session.removeAttribute(key);
    }

    /**
     * Hiermee wordt gekeken of de huidige gebruiker een bepaalde rol heeft.
     * Op basis van dit gegeven zouden er bv op schermnivo componenten wel of niet getoond
     * kunnen worden.
     *
     * @param role Rol naam.
     * @return True als user in rol.
     */
    public boolean isUserInRole(String role) {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(role);
    }

    /**
     * Geeft de username van de huidige ingelogde gebruiker terug.
     *
     * @return de username van de gebruiker, of "(ONBEKEND)" als er niet is ingelogd.
     */
    public String getUsername() {
        //Principal userPrincipal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        //return userPrincipal == null ? "(ONBEKEND)" : userPrincipal.toString();
        /*
         * @TODO Als JAAS geconfigureerd is dat bovenste regels weer gebruiken
         */
        return "edwingod2000";
    }

    /**
     * Schonen alle SessionScope controllers. Waarschijnlijk kan ook altijd deze
     * methode aangeroepen worden en is de aparte
     * opschonenControllersVoorContext niet nodig Zorg dat de Controllernamen
     * gesorteerd blijven. Dit maakt het makkelijk zoeken.
     */
    public void opschonenControllers() {
        this.verwijderObjectVanSessie("infoController");
    }

    protected char getSeparatorChar() {
        return UINamingContainer.getSeparatorChar(FacesContext.getCurrentInstance());
    }

    protected String separatorCharAsString() {
        if (Controller.separatorString == null) {
            Controller.separatorString = String.valueOf(getSeparatorChar());
        }
        return Controller.separatorString;
    }

    public void setSessionCookie(String name, String value) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        ((HttpServletResponse)facesContext.getExternalContext().getResponse()).
        addCookie(cookie);
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public String checkForFatalError() {
        FacesContext context = FacesContext.getCurrentInstance();
        // Test method, queue some errors
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "warning", "warning"));
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "info", "info"));
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "fatal", "fatal"));
        FacesMessage.Severity severity = context.getMaximumSeverity();
        String result = "checkForFatalError2";
        if (null != severity) {
            if (severity == FacesMessage.SEVERITY_FATAL) {
                result = "fatalError";
            }
        }
        return result;
    }

    public void addInfoMessage(String summary, String detail) {
        addMessage(FacesMessage.SEVERITY_INFO, summary, detail);
    }

    public void addErrorMessage(String summary, String detail) {
        addMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
    }

    public void addWarningMessage(String summary, String detail) {
        addMessage(FacesMessage.SEVERITY_WARN, summary, detail);
    }

    public void addFatalMessage(String summary, String detail) {
        addMessage(FacesMessage.SEVERITY_FATAL, summary, detail);
    }

    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        getFacesContext().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    /* Filter Info */
    public void filterInfoLijst(List<BsmInfo> infoLijst) {
        for (BsmInfo info : infoLijst) {
            filterInfo(info);
        }
    }

    public void filterInfo(BsmInfo info) {
        String result = "";

        result = filterString(info.getBericht());
        info.setBericht(result);

        result = filterString(info.getBerichtEn());
        info.setBerichtEn(result);
    }

    public String filterString(String bericht) {
        String result = "";

        /* Plaats hieronder de replaces die je wilt uitvoeren */
        result = StringUtils.replace(bericht, "src=\"/pls/", "src=\"http://homeplate.robur58.com/pls/");

        return result;
    }

    public String ophalenFotoString(String foto) {
        String result = ServerPropertiesHelper.getInstance().getFotoService() + foto;
        return result;
    }

    public String getDisplayNaam(String voornaam, String tussenvoegsel, String achternaam) {
        StringBuffer result = new StringBuffer();
        result.append(voornaam);
        result.append(" ");
        if (tussenvoegsel != null) {
            result.append(tussenvoegsel);
            result.append(" ");
        }
        result.append(achternaam);
        
        return result.toString();
    }
    
    public boolean magWijzigen() {
        if (getSecurityController().getGebruiker().getTypeGebruiker() != null && getSecurityController().getGebruiker().getTypeGebruiker().equals("BEH")) {
            return true;
        } else {
            return false;
        }
    }
}
