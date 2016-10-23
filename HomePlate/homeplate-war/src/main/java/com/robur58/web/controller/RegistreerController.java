package com.robur58.web.controller;

import com.robur58.business.BsmGebruikersFacadeLocal;
import com.robur58.domein.BsmGebruikers;
import com.robur58.domein.GebruikerStatus;
import com.robur58.web.email.EmailManager;
import com.robur58.web.util.RandomPassword;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import nl.profict.platform.web.util.FacesUtils;
import nl.profict.platform.web.util.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author godefrooije
 */
@ManagedBean
@SessionScoped
public class RegistreerController extends Controller implements Serializable {
        
    private static final int LENGTE_PASSWORD = 8;
    
    @EJB
    BsmGebruikersFacadeLocal gebruikersFacade;
    
    private BsmGebruikers gebruiker;
    private String voornaam;
    private String achternaam;
    private String tussenvoegsel;
    private String gebruikersnaam;
    private Date geboortedatum;
    private String lidnummer;
    private String email;
    private String email2;
    
    private String resultaat;
    private String resultaatBevestigRegistratie;
    
    public RegistreerController() {
    }

    public BsmGebruikers getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(BsmGebruikers gebruiker) {
        this.gebruiker = gebruiker;
    }

    public void changeLoginTab(TabChangeEvent event) {
        String index = event.getTab().getId();
        initVelden();
        getHomePlateSessionTO().setLoginAccordionIndex(index);
    }
    
    public void initVelden() {
        gebruikersnaam = null;
        email = null;
        voornaam = null;
        achternaam = null;
        geboortedatum = null;
        resultaat = null;
        lidnummer = null;
        email = null;
        email2 = null;
        
        
    }
    
    public String executeRegistreer() throws IOException {
        resultaat = null;
        resultaatBevestigRegistratie = null;
        
        getHomePlateSessionTO().setLoginAccordionIndex("2");
        
        boolean verplichtVeldFout = false;
        // Controleer verplichte velden
        // gebruikersnaam, voornaam, achternaam, geboortedatum, lidnummer, e-mail, e-mail opnieuw
        if (StringUtils.isEmpty(gebruikersnaam)) {
            MessageUtils.addErrorMessage("Gebruikersnaam is verplicht.");            
            verplichtVeldFout = true;
        }
        if (StringUtils.isEmpty(voornaam)) {
            MessageUtils.addErrorMessage("Voornaam is verplicht.");            
            verplichtVeldFout = true;
        }
        if (StringUtils.isEmpty(achternaam)) {
            MessageUtils.addErrorMessage("Achternaam is verplicht.");            
            verplichtVeldFout = true;
        }
//        if (StringUtils.isEmpty(lidnummer)) {
//            MessageUtils.addErrorMessage("Lidnummer is verplicht.");            
//            verplichtVeldFout = true;
//        }
        if (StringUtils.isEmpty(email)) {
            MessageUtils.addErrorMessage("'E-Mail' is verplicht.");            
            verplichtVeldFout = true;
        }
        if (StringUtils.isEmpty(email2)) {
            MessageUtils.addErrorMessage("'E-Mail opnieuw' is verplicht.");            
            verplichtVeldFout = true;
        }
        if(verplichtVeldFout) {
            // Eerst alle verplichte velden laten invullen
            return null;
        }
        
        // Als alle verplichte velden ingevuld zijn dan inhoudelijke controles
        // Lidnummer: validator="nummerValidator"
        // Email: validator="emailValidator" 
        // Geboortedatum: converter="datumConverter"

        if (!email.equals(email2)) {
            MessageUtils.addErrorMessage("E-Mail adressen komen niet overeen!");
            return null;
        }

        if (!gebruikerAanwezig()) {
            maakGebruiker();
        } else {
            MessageUtils.addErrorMessage("Gebruikersnaam bestaat al!");
            return null;
        }
        
        try {
            String registratieSid = generateSid();
            gebruiker.setRegistreerSid(registratieSid);
            gebruiker.setDatumInlog(new Date());
            
            gebruikersFacade.create(gebruiker);

            Map<String,String> parameters = new TreeMap<String,String>();
            parameters.put("registratieSid", registratieSid );
            parameters.put("gebruiker", gebruiker.getDisplayNaam());
            parameters.put("userid", gebruiker.getGebruikersid());
            EmailManager em = new EmailManager("Bevestiging Registratie", gebruiker.getEmail(), gebruiker.getDisplayNaam(), EmailManager.REGISTREER_TEMPLATE, parameters);
            if (!em.sendHTMLEmail()) {
                MessageUtils.addErrorMessage("Registratie is niet gelukt. Het versturen van de mail ging fout. Probeer het later nog eens.");
                return null;
            }
            
            gebruiker.setStatus(GebruikerStatus.AANVRAAG_EMAIL_VERSTUURD);
            gebruiker.setDatumInlog(new Date());
            gebruikersFacade.edit(gebruiker);
            resultaat = "Registratie is gelukt. U ontvangt nu een E-mail met daarin een link om te bevestigen.";

            MessageUtils.addSuccessMessage("Een email wordt naar u toegestuurd.");
        } catch(Exception e) {
            MessageUtils.addSuccessMessage("Opslaan wijzigingen niet gelukt");
        }
//        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        String requestServer = request.getServerName();
//        String requestScheme = request.getScheme();
//    
//        int serverPort = request.getServerPort();
//        String url = requestScheme + "://" + requestServer + ":" + Integer.toString(serverPort) + FacesUtils.getContextPath() + "/pages/homeplate.faces";
//        FacesContext.getCurrentInstance().getExternalContext().redirect(url);

        return "registratie_resultaat";
    }

    public String executeVerwerkWachtwoordVergeten() {
        getHomePlateSessionTO().setLoginAccordionIndex("1");
        
        boolean verplichtVeldFout = false;
        
        // Controleer verplichte velden
        // gebruikersnaam, e-mail
        if (StringUtils.isEmpty(gebruikersnaam)) {
            MessageUtils.addErrorMessage("Gebruikersnaam is verplicht.");            
            verplichtVeldFout = true;
        }
        if (StringUtils.isEmpty(email)) {
            MessageUtils.addErrorMessage("'E-Mail' is verplicht.");            
            verplichtVeldFout = true;
        }
        if(verplichtVeldFout) {
            // Eerst alle verplichte velden laten invullen
            return null;
        }        
        
        // Controle of gebruiker bestaat met opgegeven gebruikersnaam en email
        BsmGebruikers wachtwoordVergetenGebruiker = gebruikersFacade.findByGebruikersId(gebruikersnaam);
        if (wachtwoordVergetenGebruiker == null) {
            MessageUtils.addErrorMessage("Combinatie van gegevens zijn niet juist");
            return null;
        } else {
            if (email.equals(wachtwoordVergetenGebruiker.getEmail())) {
                // gebruiker bestaat, met juiste email.
                try {
                    String nieuwWachtwoord = generateString(new Random(), "iejdhadehfgekc", 10);
                    wachtwoordVergetenGebruiker.setWachtwoord(nieuwWachtwoord);

                    Map<String,String> parameters = new TreeMap<String,String>();
                    parameters.put("wachtwoord", wachtwoordVergetenGebruiker.getWachtwoord() );

                    EmailManager em = new EmailManager("Nieuw wachtwoord", wachtwoordVergetenGebruiker.getEmail(), wachtwoordVergetenGebruiker.getDisplayNaam(), EmailManager.WACHTWOORD_TEMPLATE, parameters);
                    em.sendHTMLEmail();
                    
                    wachtwoordVergetenGebruiker.setStatus(GebruikerStatus.WACHTWOORD_VERSTUURD);
                    wachtwoordVergetenGebruiker.setDatumUitlog(new Date());
                    gebruikersFacade.edit(wachtwoordVergetenGebruiker);
                    
                    resultaat = "De aanvraag voor een nieuw wachtwoord is gelukt. U ontvangt nu een E-mail met daarin het nieuwe wachtwoord.";
                    
                    MessageUtils.addSuccessMessage("Een email wordt naar u toegestuurd.");
                    
                } catch(Exception e) {
                    MessageUtils.addErrorMessage("Aanvraag nieuw wachtwoord niet gelukt");
                }
            } else {
                MessageUtils.addErrorMessage("Combinatie van gegevens zijn niet juist");
                return null;
            }
        }
        
//        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        String requestServer = request.getServerName();
//        String requestScheme = request.getScheme();
//    
//        int serverPort = request.getServerPort();
//        String url = requestScheme + "://" + requestServer + ":" + Integer.toString(serverPort) + "/pages/homeplate.xhtml";
//
//        FacesUtils.handleNavigationRedirect(url);
        return "registratie_resultaat";
    }

    private String generateSid() {
        String result = null;
         //generate random UUIDs
        UUID idOne = UUID.randomUUID();
        result = idOne.toString();
        return result;
    }
    
    public static String generateString(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    private boolean gebruikerAanwezig() {
        BsmGebruikers bestaandeGebruiker = gebruikersFacade.findByGebruikersId(gebruikersnaam);
        if (bestaandeGebruiker != null) {
            return true;
        } else {
            return false;
        }
    }
    
    private void maakGebruiker() {
        gebruiker = new BsmGebruikers();
        gebruiker.setGebruikersid(gebruikersnaam);
        gebruiker.setVoornaam(voornaam);
        gebruiker.setTussenvoegsel(tussenvoegsel);
        gebruiker.setAchternaam(achternaam);
        gebruiker.setEmail(email);
        gebruiker.setLidNummer(lidnummer);
        gebruiker.setStatus(GebruikerStatus.AANGEVRAAGD);
        gebruiker.setPapierenNieuws("N");
    }
    
    public void controleerRegistratie() {
        getHomePlateSessionTO().setShowContextWedstrijdenActueel(false);
        String registratieSid = (String)getObjectVanSessie("registratieSid");
        if (StringUtils.isNotEmpty(registratieSid)) {
            BsmGebruikers registratieGebruiker = gebruikersFacade.findByRegistreerSid(registratieSid);
            if (registratieGebruiker != null && registratieGebruiker.getStatus().equals(GebruikerStatus.AANVRAAG_EMAIL_VERSTUURD)) {
                resultaatBevestigRegistratie = "Bevestigen van de Registratie is gelukt. U ontvangt nu een E-mail met daarin een wachtwoord.";

                try {

                    registratieGebruiker.setStatus(GebruikerStatus.AANVRAAG_GEVERIFIEERD);
                    registratieGebruiker.setDatumUitlog(new Date());
                    String generatedPassword = RandomPassword.getRandomString(LENGTE_PASSWORD);
                    registratieGebruiker.setWachtwoord(generatedPassword);
                    gebruikersFacade.edit(registratieGebruiker);

                    Map<String,String> parameters = new TreeMap<String,String>();
                    parameters.put("wachtwoord", registratieGebruiker.getWachtwoord() );
                    EmailManager em = new EmailManager("Nieuw wachtwoord", registratieGebruiker.getEmail(), registratieGebruiker.getDisplayNaam(), EmailManager.WACHTWOORD_TEMPLATE, parameters);
                    em.sendHTMLEmail();
                    
                    registratieGebruiker.setStatus(GebruikerStatus.WACHTWOORD_VERSTUURD);
                    registratieGebruiker.setDatumUitlog(new Date());
                    gebruikersFacade.edit(registratieGebruiker);
                    
                } catch (IOException ex) {
                    Logger.getLogger(RegistreerController.class.getName()).log(Level.SEVERE, null, ex);
                    resultaatBevestigRegistratie = "Bevestigen van de Registratie is niet gelukt. Probeer het later nog een keer.";
                }
            } else {
                FacesUtils.handleNavigationRedirect("/pages/homeplate.faces");
            }
        } else {
            resultaatBevestigRegistratie = "Bevestigen van de Registratie is niet gelukt. Neem contact op via mail met webmaster@robur58.com.";
        }
    }
    
    //////////////// GETTERS EN SETTERS ////////////////
    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getLidnummer() {
        return lidnummer;
    }

    public void setLidnummer(String lidnummer) {
        this.lidnummer = lidnummer;
    }

    public String getResultaat() {
        return resultaat;
    }

    public void setResultaat(String resultaat) {
        this.resultaat = resultaat;
    }

    public String getResultaatBevestigRegistratie() {
        return resultaatBevestigRegistratie;
    }

    public void setResultaatBevestigRegistratie(String resultaatBevestigRegistratie) {
        this.resultaatBevestigRegistratie = resultaatBevestigRegistratie;
    }
    
}
