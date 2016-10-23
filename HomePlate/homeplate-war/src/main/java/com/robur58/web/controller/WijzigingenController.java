package com.robur58.web.controller;

import com.robur58.business.BsmVUpdateInfoFacadeLocal;
import com.robur58.domein.BsmVUpdateInfo;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author godefrooije
 */
@ManagedBean(name="wijzigingenController")
@SessionScoped
public class WijzigingenController extends Controller {
 
    @EJB
    private BsmVUpdateInfoFacadeLocal updateInfoFacade;

    private List<BsmVUpdateInfo> wijzigingen;
    
    public void begin() {
        getHomePlateSessionTO().setShowContextHighlights(true);
        getHomePlateSessionTO().setShowContextWedstrijdenActueel(false);
        getHomePlateSessionTO().setShowContextCalendar(true);
        getHomePlateSessionTO().setShowContextWedstrijdenTeamList(false);
        getHomePlateSessionTO().setShowContextTeamList(false);
        getHomePlateSessionTO().setShowContextSponsors(true);
        
        wijzigingen = updateInfoFacade.findAllOrderByDatum();
    }

    public List<BsmVUpdateInfo> getWijzigingen() {
        return wijzigingen;
    }

    public void setWijzigingen(List<BsmVUpdateInfo> wijzigingen) {
        this.wijzigingen = wijzigingen;
    }

}
