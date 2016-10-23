package com.robur58.web.controller;

import com.robur58.business.BsmTeamsFacadeLocal;
import com.robur58.business.BsmWedstrijdenFacadeLocal;
import com.robur58.domein.BsmTeams;
import com.robur58.domein.BsmWedstrijden;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class WedstrijdenController extends Controller {

    private List<BsmWedstrijden> teamWedstrijden;
    private String titel;

    @EJB
    private BsmWedstrijdenFacadeLocal wedstrijdenFacade;

    @EJB
    private BsmTeamsFacadeLocal teamsFacade;

    public WedstrijdenController() {

    }

    public List<BsmWedstrijden> getTeamWedstrijden() {
        return teamWedstrijden;
    }

    public void setTeamWedstrijden(List<BsmWedstrijden> teamWedstrijden) {
        this.teamWedstrijden = teamWedstrijden;
    }

    private String ophalenTeamTitel(Long teamVolgnr) {
        StringBuffer result = new StringBuffer();
        BsmTeams team = teamsFacade.findByVolgnr(teamVolgnr);
        if (team != null) {
            result.append(team.getNaam());
            result.append(" (");
            result.append(team.getSoort());
            result.append(")");
        }

        return result.toString();
    }

    public String ophalenWedstrijden(String team) {
        getHomePlateSessionTO().setShowContextWedstrijdenTeamList(true);
        getHomePlateSessionTO().setShowContextLogin(false);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String jaar = sdf.format(new Date());
        String soort = null;
        Long teamVolgnr = null;

        if (team != null) {
            if (team.equals("SB") || team.equals("HB")) {
                soort = team;
                if (team.equals("SB")) {
                    if (getHomePlateSessionTO().isDutch()) {
                        titel = "Wedstrijden Softbal";
                    } else {
                        titel = "Games Softball";
                    }
                }
                if (team.equals("HB")) {
                    if (getHomePlateSessionTO().isDutch()) {
                        titel = "Wedstrijden Honkbal";
                    } else {
                        titel = "Games Baseball";
                    }
                }
                teamWedstrijden = wedstrijdenFacade.findBySoortAndYear(soort, jaar);

            } else if (team.equals("ALL")) {
                if (getHomePlateSessionTO().isDutch()) {
                    titel = "Alle Honkbal- en Softbal wedstrijden";
                } else {
                    titel = "All Base- and Softball games";
                }
                teamWedstrijden = wedstrijdenFacade.findByYear(jaar);
            } else {
                if (team != null && team.length() > 0) {
                    teamVolgnr = Long.parseLong(team);
                }
                if (getHomePlateSessionTO().isDutch()) {
                    titel = "Wedstrijden " + ophalenTeamTitel(teamVolgnr);
                } else {
                    titel = "Games " + ophalenTeamTitel(teamVolgnr);
                }
                teamWedstrijden = wedstrijdenFacade.findByTeamVolgnrAndYear(teamVolgnr, jaar);
            }
        }

        return null;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

}
