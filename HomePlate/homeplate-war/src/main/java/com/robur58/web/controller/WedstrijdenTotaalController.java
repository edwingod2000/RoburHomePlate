package com.robur58.web.controller;

import com.robur58.business.BsmVWedstrijdenTotaalFacadeLocal;
import com.robur58.domein.BsmVWedstrijdenTotaal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author godefrooije
 */
@ManagedBean
@SessionScoped
public class WedstrijdenTotaalController extends Controller implements Serializable {
    
    @EJB
    BsmVWedstrijdenTotaalFacadeLocal wedstrijdenTotaalFacade;

    private List<BsmVWedstrijdenTotaal> wedstrijdenLijst;
    private String titel;
    
    public WedstrijdenTotaalController() {
        titel ="Beheer Wedstrijden";
    }

    public List<BsmVWedstrijdenTotaal> getWedstrijdenLijst() {
        if (wedstrijdenLijst == null) {
            ophalenWedstrijden();
        }
        return wedstrijdenLijst;
    }

    public void setWedstrijdenLijst(List<BsmVWedstrijdenTotaal> wedstrijdenLijst) {
        this.wedstrijdenLijst = wedstrijdenLijst;
    }

    private void ophalenWedstrijden() {
        wedstrijdenLijst = wedstrijdenTotaalFacade.findAll();
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }
    
    
}
