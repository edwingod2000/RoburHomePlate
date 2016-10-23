package com.robur58.web.controller;

import com.robur58.business.BsmKalenderFacadeLocal;
import com.robur58.domein.BsmKalender;
import java.util.List;
import java.util.TimeZone;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Gebruiker
 */
@ManagedBean(name="kalenderController")
@SessionScoped
public class KalenderController extends Controller {

    @EJB
    private BsmKalenderFacadeLocal kalenderFacade;
    
    private List<BsmKalender> kalenderInfoHomepage;
    private List<BsmKalender> kalenderInfo;

    /** Creates a new instance of KalenderController */
    public KalenderController() {
    }

    public void executeOphalenKalenderInfo() {
       kalenderInfo = kalenderFacade.findActual();
    }

    public void executeOphalenKalenderInfoForHomepage() {
       kalenderInfoHomepage = kalenderFacade.findForHomepage();
    }

    public void verwijderContextItems() {
       getHomePlateSessionTO().setShowContextWedstrijdenActueel(false);        
       getHomePlateSessionTO().setShowContextCalendar(false);
    }
    
    public List<BsmKalender> getKalenderInfo() {
        if (kalenderInfo == null || kalenderInfo.size() == 0) {
            executeOphalenKalenderInfo();
        }
        return kalenderInfo;
    }

    public void setKalenderInfo(List<BsmKalender> kalenderInfo) {
        this.kalenderInfo = kalenderInfo;
    }

    public List<BsmKalender> getKalenderInfoHomepage() {
        return kalenderInfoHomepage;
    }

    public void setKalenderInfoHomepage(List<BsmKalender> kalenderInfoHomepage) {
        this.kalenderInfoHomepage = kalenderInfoHomepage;
    }
    
    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }

}
