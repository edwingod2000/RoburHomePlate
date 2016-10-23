package com.robur58.web.controller;

import com.robur58.business.BsmGebruikersFacadeLocal;
import com.robur58.domein.BsmGebruikers;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import nl.profict.platform.web.util.MessageUtils;

/**
 *
 * @author godefrooije
 */
@ManagedBean
@SessionScoped
public class GebruikersController extends Controller implements Serializable {
    
    @ManagedProperty(value="#{securityController}")
    SecurityController securityController;
    
    @EJB
    BsmGebruikersFacadeLocal gebruikersFacade;
    
    private boolean gebruikerWijzigen;
    private boolean actiesWijzigen;
    private boolean wachtwoordWijzigen;
    
    private String wachtwoord;
    private String wachtwoord1;
    private String wachtwoord2;
    
    public GebruikersController() {
    }

    public BsmGebruikers getGebruiker() {
        if (securityController.getGebruiker() != null) {
            return securityController.getGebruiker();
        } else {
            return null;
        }
    }

    public String bewaarGebruiker() {
        try {
            gebruikersFacade.edit(securityController.getGebruiker());
            if (getHomePlateSessionTO().isDutch()) {
                MessageUtils.addSuccessMessage("Opslaan wijzigingen gelukt.");
            } else {
                MessageUtils.addSuccessMessage("Saving changes succeeded.");
            }
            setGebruikerWijzigen(false);
            setActiesWijzigen(false);
            setWachtwoordWijzigen(false);

        } catch(Exception e) {
            if (getHomePlateSessionTO().isDutch()) {
                MessageUtils.addErrorMessage("Opslaan wijzigingen niet gelukt.");
            } else {
                MessageUtils.addErrorMessage("Saving changes not succeeded.");
            }
        }
        
        return null;
    }
    
    public String getWijzigOfBewaarLabel() {
        if (this.gebruikerWijzigen) {
            if (getHomePlateSessionTO().isDutch()) {
               return "Bewaren";
            } else {
                return "Save";
            }
        } else {
            if (getHomePlateSessionTO().isDutch()) {
                return "Gegevens wijzigen";
            } else {
                return "Change information";
            }
        }
    }
    
    public String getWijzigOfBewaarWachtwoordLabel() {
        if (this.wachtwoordWijzigen) {
            if (getHomePlateSessionTO().isDutch()) {
                return "Bewaren";
            } else {
                return "Save";
            }
        } else {
            if (getHomePlateSessionTO().isDutch()) {
                return "Gegevens wijzigen";
            } else {
                return "Change information";
            }
        }
    }

    public String wijzigWachtwoord() {
        this.wachtwoordWijzigen = true;
        this.gebruikerWijzigen = false;
        
        return null;
    }
    
    public String wijzigOfBewaarWachtwoord() {
        if (this.wachtwoordWijzigen) {
            return bewaarWachtwoord();
        } else {
            return wijzigWachtwoord();
        }
    }
    
    public String wijzigOfBewaar() {
        if (this.gebruikerWijzigen) {
            return bewaarGebruiker();
        } else {
            return gebruikerGegevensWijzigen();
        }
    }

    public String wijzigGebruiker() {
        return gebruikerGegevensWijzigen();
    }
    
    public String wijzigOfBewaarActies() {
        if (this.actiesWijzigen) {
            return bewaarGebruiker();
        } else {
            return actiesGegevensWijzigen();
        }
    }
    
    public SecurityController getSecurityController() {
        return securityController;
    }

    public void setSecurityController(SecurityController securityController) {
        this.securityController = securityController;
    }

    public String actiesGegevensWijzigen() {
        setActiesWijzigen(true);
        
        return null;
    }
    
    public String gebruikerGegevensWijzigen() {
        setGebruikerWijzigen(true);
        
        return null;
    }
    
    public String executeAnnuleren() {
        setGebruikerWijzigen(false);
        setWachtwoordWijzigen(false);
        
        return null;
    }
    
    public String bewaarWachtwoord() {
        if (wachtwoord != null && wachtwoord1 != null && wachtwoord2 != null) {
            // Conrole oude wachtwoord
            if (!securityController.getGebruiker().getWachtwoord().equals(wachtwoord)) {
                if (getHomePlateSessionTO().isDutch()) {
                    MessageUtils.addErrorMessage("Oude wachtwoord is niet correct.");
                } else {
                    MessageUtils.addErrorMessage("Old password is not correct.");
                }
                return null;
            }
            
            if (wachtwoord1.equals(wachtwoord2)) {

                try {
                    securityController.getGebruiker().setWachtwoord(wachtwoord1);
                    gebruikersFacade.edit(securityController.getGebruiker());
                    if (getHomePlateSessionTO().isDutch()) {
                        MessageUtils.addSuccessMessage("Wijzigen wachtwoord gelukt.");
                    } else {
                        MessageUtils.addSuccessMessage("Changing password succeeded.");
                    }
                    setWachtwoordWijzigen(false);
                    setGebruikerWijzigen(false);
                } catch(Exception e) {
                    if (getHomePlateSessionTO().isDutch()) {
                        MessageUtils.addErrorMessage("Wijzigen wachtwoord niet gelukt.");
                    } else {
                        MessageUtils.addErrorMessage("Changing password not succeeded.");
                    }
                }
              
            } else {
                if (getHomePlateSessionTO().isDutch()) {
                    MessageUtils.addErrorMessage("Wachtwoorden zijn niet gelijk.");
                } else {
                    MessageUtils.addErrorMessage("Passwords are not the same.");
                }
            }
        } else {
            if (getHomePlateSessionTO().isDutch()) {
                MessageUtils.addErrorMessage("Wachtwoorden zijn verplicht.");
            } else {
                MessageUtils.addErrorMessage("Passwords are required.");
            }
        }

        return null;
    }

    public boolean isGebruikerWijzigen() {
        return this.gebruikerWijzigen;
    }

    public void setGebruikerWijzigen(boolean gebruikerWijzigen) {
        this.gebruikerWijzigen = gebruikerWijzigen;
        if (this.gebruikerWijzigen) {
            setWachtwoordWijzigen(false);
        }
    }
 
    public String getWachtwoord1() {
        return wachtwoord1;
    }

    public void setWachtwoord1(String wachtwoord1) {
        this.wachtwoord1 = wachtwoord1;
    }

    public String getWachtwoord2() {
        return wachtwoord2;
    }

    public void setWachtwoord2(String wachtwoord2) {
        this.wachtwoord2 = wachtwoord2;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public boolean isWachtwoordWijzigen() {
        return wachtwoordWijzigen;
    }

    public void setWachtwoordWijzigen(boolean wachtwoordWijzigen) {
        this.wachtwoordWijzigen = wachtwoordWijzigen;
        if (this.wachtwoordWijzigen) {
            setGebruikerWijzigen(false);
        }
    }

    public boolean isActiesWijzigen() {
        return actiesWijzigen;
    }

    public void setActiesWijzigen(boolean actiesWijzigen) {
        this.actiesWijzigen = actiesWijzigen;
    }
 
}
