package com.robur58.web.controller;

import com.robur58.business.BsmGebruikersFacadeLocal;
import com.robur58.domein.BsmGebruikers;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import nl.profict.platform.web.util.FacesUtils;
import nl.profict.platform.web.util.MessageUtils;
import org.apache.commons.lang.StringUtils;

@ManagedBean(name="securityController")
@SessionScoped
public class SecurityController extends Controller {

    public static final String SECURITY_CONTROLLER_SESSION = "securityController";
    
    @EJB
    private BsmGebruikersFacadeLocal gebruikersFacade;

    private String gebruikersnaam;
    private String wachtwoord;
    private String email;
    private BsmGebruikers gebruiker;
    private boolean wachtwoordVergeten;
    
    public SecurityController() {
    }
    
    @PostConstruct
    public void init() {
        gebruikersnaam = null;
        wachtwoord = null;
        gebruiker = null;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String wachtwoordVergeten() {
        this.wachtwoordVergeten = true;
        return null;
    }
    
    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public BsmGebruikers getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(BsmGebruikers gebruiker) {
        this.gebruiker = gebruiker;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isWachtwoordVergeten() {
        return wachtwoordVergeten;
    }

    public void setWachtwoordVergeten(boolean wachtwoordVergeten) {
        this.wachtwoordVergeten = wachtwoordVergeten;
    }
    
    public String executeLogin() {
        boolean verplichtVeldFout = false;
        
        // Controleer verplichte velden
        // gebruikersnaam, e-mail
        if (StringUtils.isEmpty(gebruikersnaam)) {
            if (getHomePlateSessionTO().isDutch()) {
                MessageUtils.addErrorMessage("Gebruikersnaam is verplicht.");            
            } else {
                MessageUtils.addErrorMessage("Username is required.");   
            }
            verplichtVeldFout = true;
        }
        if (StringUtils.isEmpty(wachtwoord)) {
            if (getHomePlateSessionTO().isDutch()) {
                MessageUtils.addErrorMessage("Wachtwoord is verplicht.");            
            } else {
                MessageUtils.addErrorMessage("Password is required.");  
            }
            verplichtVeldFout = true;
        }
        if(verplichtVeldFout) {
            // Eerst alle verplichte velden laten invullen
            return null;
        }       
        
        getHomePlateSessionTO().setLoginAccordionIndex("0");
        boolean result = false;
        try {
           BsmGebruikers testGebruiker = gebruikersFacade.findByGebruikersId(gebruikersnaam);
            if (testGebruiker.getWachtwoord().equals(wachtwoord)) {
                result = true;
                this.setGebruiker(testGebruiker);
            } else {
                if (getHomePlateSessionTO().isDutch()) {
                    addInfoMessage("Inloggen niet gelukt.", null);
                } else {
                    addInfoMessage("Login failed", null);
                }
            }
        } catch (Exception e) {
            if (getHomePlateSessionTO().isDutch()) {
                addInfoMessage("Inloggen niet gelukt.", null);
            } else {
                addInfoMessage("Login failed", null);
            }
            result = false;
        }
        
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String requestServer = request.getServerName();
        String requestScheme = request.getScheme();
    
        int serverPort = request.getServerPort();
        String url = requestScheme + "://" + requestServer + ":" + Integer.toString(serverPort) + "/pages/homeplate.xhtml";

        FacesUtils.handleNavigationRedirect(url);
        
//        return Navigatie.HOMEPLATE + "?redirect=true";
        return null;
    }
    
    
    public String executeUitloggen() {
        init();
        
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String requestServer = request.getServerName();
        String requestScheme = request.getScheme();
    
        int serverPort = request.getServerPort();
        
        String url = requestScheme + "://" + requestServer + ":" + Integer.toString(serverPort) + "/pages/homeplate.xhtml";

        FacesUtils.handleNavigationRedirect(url);
//        ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)	FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
//
//		nav.performNavigation("/pages/homeplate.xhtml");

        return null;
    }
        
}
