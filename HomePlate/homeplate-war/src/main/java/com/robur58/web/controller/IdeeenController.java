package com.robur58.web.controller;

import com.robur58.business.BsmIdeeenFacadeLocal;
import com.robur58.domein.BsmIdeeen;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import nl.profict.platform.web.util.MessageUtils;

/**
 *
 * @author Gebruiker
 */
@ManagedBean(name="ideeenController")
@RequestScoped
public class IdeeenController extends Controller {

    @EJB
    private BsmIdeeenFacadeLocal ideeenFacade;
    
    private BsmIdeeen ideeInfo;

    /** Creates a new instance of IdeeenController */
    public IdeeenController() {
        resetIdeeInfo();
    }

    public void verwijderContextItems() {
       getHomePlateSessionTO().setShowContextWedstrijdenActueel(false);        
    }
    
    public void setIdeeInfo(BsmIdeeen ideeInfo) {
        this.ideeInfo = ideeInfo;
    }
    
    private void resetIdeeInfo() {
        this.ideeInfo = new BsmIdeeen();
        ideeInfo.setTypeIdeeen(1L);
        ideeInfo.setDatum(new Date());
    }
    
    public BsmIdeeen getIdeeInfo() {
        return ideeInfo;
    }
    
    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }

    public String opslaan() {
        ideeenFacade.create(ideeInfo);
        if (getHomePlateSessionTO().isDutch()){
            MessageUtils.addSuccessMessage("Uw idee is verstuurd.");
        } else {
            MessageUtils.addSuccessMessage("Your idea is send.");
        }
        resetIdeeInfo();
        
        return null;
    }
}
