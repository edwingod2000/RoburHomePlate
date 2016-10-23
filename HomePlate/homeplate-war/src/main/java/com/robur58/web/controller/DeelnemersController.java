package com.robur58.web.controller;

import com.robur58.business.BsmDeelnemersFacadeLocal;
import com.robur58.domein.BsmDeelnemers;
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
public class DeelnemersController extends Controller implements Serializable {
    
    @EJB
    BsmDeelnemersFacadeLocal deelnemersFacade;

    private List<BsmDeelnemers> deelnemerLijst;
    private String titel;
    
    public DeelnemersController() {
        titel ="Beheer Deelnemers";
    }

    public List<BsmDeelnemers> getDeelnemerLijst() {
        if (deelnemerLijst == null) {
            ophalenDeelnemers();
        }
        return deelnemerLijst;
    }

    public void setDeelnemerLijst(List<BsmDeelnemers> deelnemerLijst) {
        this.deelnemerLijst = deelnemerLijst;
    }

    private void ophalenDeelnemers() {
        deelnemerLijst = deelnemersFacade.findAll();
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }
    
    
}
