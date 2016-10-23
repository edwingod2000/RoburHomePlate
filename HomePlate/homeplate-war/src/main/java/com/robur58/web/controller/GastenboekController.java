package com.robur58.web.controller;

import com.robur58.business.BsmGastenboekFacadeLocal;
import com.robur58.business.BsmTypeGastenboekFacadeLocal;
import com.robur58.business.BsmVGastenboekJaarFacadeLocal;
import com.robur58.domein.BsmGastenboek;
import com.robur58.domein.BsmTypeGastenboek;
import com.robur58.domein.BsmVGastenboekJaar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="gastenboekController")
@RequestScoped
public class GastenboekController extends Controller {

    @EJB
    private BsmGastenboekFacadeLocal gastenboekFacade;

    @EJB
    private BsmVGastenboekJaarFacadeLocal gastenboekJaarFacade;

    @EJB
    private BsmTypeGastenboekFacadeLocal typeGastenboekFacade;
    
    private List<BsmGastenboek> gastenboekInfo;
    private List<BsmVGastenboekJaar> gastenboekJaren;

    private String naam;
    private String email;
    private String homepageNaam;
    private String homepageURL;
    private String opmerking;

    private BsmTypeGastenboek typeGastenboek;

    @ManagedProperty(value="#{param.jaar}")
    private String actueelJaar;

    /** Creates a new instance of HomepageController */
    public GastenboekController() {

    }
    
    @PostConstruct
    public void init() {
        BsmTypeGastenboek type = new BsmTypeGastenboek();
        type.setVolgnr(1L);
        
        typeGastenboek = typeGastenboekFacade.find(type.getVolgnr());
    }

    public String getCurrentJaar() {
        return bepaalCurrentJaar();
    }
    
    /**
     * PreEvent actie
     */
    public void executeOphalenGastenboekInfo() {
        getHomePlateSessionTO().setShowContextWedstrijdenActueel(false);
        
        if (actueelJaar == null) {
            actueelJaar = bepaalCurrentJaar();

        }

        if (gastenboekJaren == null || gastenboekJaren.size() == 0) {
            // Ophalen gastenboek jaren
            gastenboekJaren = gastenboekJaarFacade.findJaren();

            // Toevoegen huidig jaar aan lijst
            List<BsmVGastenboekJaar> nieuweLijst = new ArrayList<BsmVGastenboekJaar>();
            BsmVGastenboekJaar huidigJaar = new BsmVGastenboekJaar();
            huidigJaar.setJaar(bepaalCurrentJaar());
            if (!huidigJaarInLijst(huidigJaar)) {
                nieuweLijst.add(huidigJaar);            
            }
            nieuweLijst.addAll(gastenboekJaren);
            gastenboekJaren = nieuweLijst;
            
            int aantal = gastenboekJaren.size() - 1;
            for (int i=aantal; i > 11 ; i--) {
                gastenboekJaren.remove(i);
            }
        }
    
        gastenboekInfo = gastenboekFacade.findHomeplateByJaar(actueelJaar);

    }

    private boolean huidigJaarInLijst(BsmVGastenboekJaar huidigJaar) {
        boolean result = false;
        for (BsmVGastenboekJaar jaar: gastenboekJaren) {
            if (jaar.getJaar().equals(huidigJaar.getJaar())) {
                result = true;
                break;
            }
        }
        return result;    
    }
    
    private String bepaalCurrentJaar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(new Date());
    }

    public String getActueelJaar() {
        return actueelJaar;
    }

    public void setActueelJaar(String actueelJaar) {
        this.actueelJaar = actueelJaar;
    }

    public List<BsmGastenboek> getGastenboekInfo() {
        return gastenboekInfo;
    }

    public void setGastenboekInfo(List<BsmGastenboek> gastenboekInfo) {
        this.gastenboekInfo = gastenboekInfo;
    }

    public String executeNaarNieuw() {
        initGastenboekVelden();

        return "nieuw_gastenboek";
    }

    private void initGastenboekVelden() {
        this.email = null;
        this.homepageNaam = null;
        this.homepageURL = null;
        this.naam = null;
        this.opmerking = null;
    }

    public String executeBewaarNieuw() {
        BsmGastenboek gastenboek = new BsmGastenboek();
        gastenboek.setDatum(new Date());
        gastenboek.setNaam(naam);
        gastenboek.setEmail(email);
        gastenboek.setHomepage(homepageURL);
        gastenboek.setHomepagenaam(homepageNaam);
        gastenboek.setOpmerking(opmerking);
        gastenboek.setTypeGastenboek(typeGastenboek);
        gastenboekFacade.create(gastenboek);
        
        initGastenboekVelden();
        return "gastenboek";
    }

    public List<BsmVGastenboekJaar> getGastenboekJaren() {
        if (gastenboekJaren == null) {
            executeOphalenGastenboekInfo();
        }
        return gastenboekJaren;
    }

    public void setGastenboekJaren(List<BsmVGastenboekJaar> gastenboekJaren) {
        this.gastenboekJaren = gastenboekJaren;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomepageNaam() {
        return homepageNaam;
    }

    public void setHomepageNaam(String homepageNaam) {
        this.homepageNaam = homepageNaam;
    }

    public String getHomepageURL() {
        return homepageURL;
    }

    public void setHomepageURL(String homepageURL) {
        this.homepageURL = homepageURL;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getOpmerking() {
        return opmerking;
    }

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

}
