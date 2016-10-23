package com.robur58.web.controller;

import com.robur58.business.*;
import com.robur58.domein.BsmDeelnemers;
import com.robur58.domein.BsmTeams;
import com.robur58.domein.BsmTrainingstijd;
import com.robur58.domein.BsmTypeTeams;
import com.robur58.domein.BsmVTeambeheerders;
import com.robur58.web.view.DeelnemerGebruikerVO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import nl.profict.platform.web.util.DateUtils;

@ManagedBean
@SessionScoped
public class TeamsController extends Controller {

    private List<BsmTypeTeams> typeTeams;

    @EJB
    private BsmTypeTeamsFacadeLocal typeTeamsFacade;

    @EJB
    private BsmTeamsFacadeLocal teamsFacade;

    @EJB
    private BsmDeelnemersFacadeLocal deelnemersFacade;

    @EJB
    private BsmGebruikersFacadeLocal gebruikersFacade;
    
    @EJB
    private BsmVTeambeheerdersFacadeLocal teamBeheerderFacade;
    
    @EJB
    private BsmTrainingstijdFacadeLocal trainingstijdFacade;
    
    private String teamVolgnr;
    private String currentTeamVolgnr;
    private BsmVTeambeheerders teamBeheerder;
    
    private BsmTeams team;
    private DeelnemerGebruikerVO deelnemerGebruiker = null;
    private BsmDeelnemers deelnemer = null;

    private String begeleider;
    private List<BsmDeelnemers> deelnemers;
    private List<DeelnemerGebruikerVO> deelnemerGebruikers;
    
    public TeamsController() {
    }

    @PostConstruct
    public void init() {
        ophalenTypeTeams();
    }
    
    public void begin() {
        getHomePlateSessionTO().setShowContextWedstrijdenActueel(false);
    }

    public List getTypeTeams() {
        return typeTeams;
    }

    public void setTypeTeams(List typeTeams) {
        this.typeTeams = typeTeams;
    }

    public void ophalenTypeTeams() {
        typeTeams = typeTeamsFacade.findAll();
    }

    public List ophalenTeamsPerSoort(String soort) {
        return teamsFacade.findBySoortAndNotAll(soort);
    }

    public void ophalenTeam() {
        if (teamVolgnr != null) {
            if (!teamVolgnr.equals(currentTeamVolgnr)) {
                getHomePlateSessionTO().setShowContextSpelersInfo(false);
                currentTeamVolgnr = teamVolgnr;
                team = teamsFacade.findByVolgnr(Long.valueOf(teamVolgnr));
                /* ophalen deelnemers */
                setDeelnemers(deelnemersFacade.findByTemVolgnr(Long.valueOf(teamVolgnr)));
                if (team != null) {
                    try {
                        teamBeheerder = teamBeheerderFacade.findByTemVolgnr(Long.valueOf(teamVolgnr));
                    } catch (Exception e) {
                        teamBeheerder = null;
                    }
                }
                //aanmakenDeelnemersGebruikers(deelnemers);
            }
        }
        getHomePlateSessionTO().setShowContextLogin(false);
        getHomePlateSessionTO().setShowContextTeamList(true);
    }

    private void aanmakenDeelnemersGebruikers(List<BsmDeelnemers> deelnemers) {
        deelnemerGebruikers = new ArrayList<DeelnemerGebruikerVO>();
        
        for (BsmDeelnemers deelnemer: deelnemers) {
            DeelnemerGebruikerVO deelnemerGebruikerVO = new DeelnemerGebruikerVO();
            if (deelnemer.getLidnummer() != null) {
                deelnemerGebruikerVO.setGebruiker(gebruikersFacade.findByLidNummer(deelnemer.getLidnummer().toString()) );
            } else {
                deelnemerGebruikerVO.setGebruiker(null);
            }
            deelnemerGebruikerVO.setDeelnemer(deelnemer);
            deelnemerGebruikers.add(deelnemerGebruikerVO);
        }
        
    }
    
    public void verwijderContextItems() {
        getHomePlateSessionTO().setShowContextWedstrijdenActueel(false);        
        getHomePlateSessionTO().setShowContextWedstrijdenTeamList(false);
        getHomePlateSessionTO().setShowContextTeamList(false);
    }
    
    public Integer getLeeftijd() {
        if (deelnemerGebruiker != null && deelnemerGebruiker.getDeelnemer() != null) {
            if (deelnemerGebruiker.getDeelnemer().getGeboortedatum() != null) {
                return DateUtils.bepaalLeeftijd(deelnemerGebruiker.getDeelnemer().getGeboortedatum(), new Date());
            }
        }
        return null;
    }
    
    public String toonSpeler(BsmDeelnemers deelnemer) {
        this.getHomePlateSessionTO().setShowContextSpelersInfo(true);
        this.deelnemer = deelnemer;

        return null;
    }

    public boolean isShowTrainingstijden() {
        if (getTrainingsTijd() == null) {
            return false;
        } else {
            return true;
        }
    }
    
    public BsmTeams getTeam() {
        return team;
    }

    public void setTeam(BsmTeams team) {
        this.team = team;
    }

    public String getTeamVolgnr() {
        return teamVolgnr;
    }

    public void setTeamVolgnr(String teamVolgnr) {
        this.teamVolgnr = teamVolgnr;
    }

    public String getBegeleider() {
        return begeleider;
    }

    public void setBegeleider(String begeleider) {
        this.begeleider = begeleider;
    }

    public List<BsmDeelnemers> getDeelnemers() {
        return deelnemers;
    }

    public void setDeelnemers(List<BsmDeelnemers> deelnemers) {
        this.deelnemers = deelnemers;
    }

    public DeelnemerGebruikerVO getDeelnemerGebruiker() {
        return deelnemerGebruiker;
    }

    public void setDeelnemerGebruiker(DeelnemerGebruikerVO deelnemerGebruiker) {
        this.deelnemerGebruiker = deelnemerGebruiker;
    }
    
    public BsmVTeambeheerders getTeamBeheerder() {
        return teamBeheerder;
    }

    public void setTeamBeheerder(BsmVTeambeheerders teamBeheerder) {
        this.teamBeheerder = teamBeheerder;
    }

    public List<DeelnemerGebruikerVO> getDeelnemerGebruikers() {
        return deelnemerGebruikers;
    }

    public void setDeelnemerGebruikers(List<DeelnemerGebruikerVO> deelnemerGebruikers) {
        this.deelnemerGebruikers = deelnemerGebruikers;
    }

    public BsmDeelnemers getDeelnemer() {
        return deelnemer;
    }

    public void setDeelnemer(BsmDeelnemers deelnemer) {
        this.deelnemer = deelnemer;
    }
    
    public String getTrainingsTijd() {
        BsmTrainingstijd trainingstijd = null;
        if (team != null) {
            trainingstijd = trainingstijdFacade.find(team.getVolgnr());
        }
        
        StringBuilder trainingsTekstBuilder = new StringBuilder();
        int aantal = 0;
        String training = null;
        String zijn = null, om = null, uur = null, en = null;
        String zondag = null, maandag = null, dinsdag = null, woensdag = null, donderdag = null, vrijdag = null, zaterdag = null;
        
        if (trainingstijd != null) {
            if (super.getHomePlateSessionTO().isDutch()) {
                training = "Trainingen van ";
                zijn = " zijn ";
                zondag = "zondag";
                maandag = "maandag";
                dinsdag = "dinsdag";
                woensdag = "woensdag";
                donderdag = "donderdag";
                vrijdag = "vrijdag";
                zaterdag = "zaterdag";
                om = " om ";
                uur = " uur";
                en = " en ";
            } else {
                training = "Practice of ";
                zijn = " are";
                zondag = "sunday";
                maandag = "monday";
                dinsdag = "tuesday";
                woensdag = "wednesday";
                donderdag = "thursday";
                vrijdag = "friday";
                zaterdag = "saturday";
                om = " at ";
                uur = " hour";
                en = " and ";
            }

            trainingsTekstBuilder.append(training)
                                 .append(team.getNaam())
                                 .append(" ")
                                 .append(team.getSoort())
                                 .append(zijn);

            // Zondag
            if (trainingstijd.getZoTijd() != null) {
                aantal++;
                if (aantal > 1) {
                    trainingsTekstBuilder.append(en);
                }
                trainingsTekstBuilder.append(zondag)
                                     .append(om)
                                     .append(trainingstijd.getZoTijd())
                                     .append(uur);
                if (trainingstijd.getZoLok() != null) {
                    trainingsTekstBuilder.append(", ")
                                         .append(trainingstijd.getZoLok());
                }
            }

            // Maandag
            if (trainingstijd.getMaTijd() != null) {
                aantal++;
                if (aantal > 1) {
                    trainingsTekstBuilder.append(en);
                }
                trainingsTekstBuilder.append(maandag)
                                     .append(om)
                                     .append(trainingstijd.getMaTijd())
                                     .append(uur);
                if (trainingstijd.getMaLok() != null) {
                    trainingsTekstBuilder.append(", ")
                                         .append(trainingstijd.getMaLok());
                }
            }

            // Dinsdag
            if (trainingstijd.getDiTijd() != null) {
                aantal++;
                if (aantal > 1) {
                    trainingsTekstBuilder.append(en);
                }
                trainingsTekstBuilder.append(dinsdag)
                                     .append(om)
                                     .append(trainingstijd.getDiTijd())
                                     .append(uur);
                if (trainingstijd.getDiLok() != null) {
                    trainingsTekstBuilder.append(", ")
                                         .append(trainingstijd.getDiLok());
                }
            }

            // Woensdag
            if (trainingstijd.getWoTijd() != null) {
                aantal++;
                if (aantal > 1) {
                    trainingsTekstBuilder.append(en);
                }
                trainingsTekstBuilder.append(woensdag)
                                     .append(om)
                                     .append(trainingstijd.getWoTijd())
                                     .append(uur);
                if (trainingstijd.getWoLok() != null) {
                    trainingsTekstBuilder.append(", ")
                                         .append(trainingstijd.getWoLok());
                }
            }

            // Donderdag
            if (trainingstijd.getDoTijd() != null) {
                aantal++;
                if (aantal > 1) {
                    trainingsTekstBuilder.append(en);
                }
                trainingsTekstBuilder.append(donderdag)
                                     .append(om)
                                     .append(trainingstijd.getDoTijd())
                                     .append(uur);
                if (trainingstijd.getDoLok() != null) {
                    trainingsTekstBuilder.append(", ")
                                         .append(trainingstijd.getDoLok());
                }
            }

             // Vrijdag
            if (trainingstijd.getVrTijd() != null) {
                aantal++;
                if (aantal > 1) {
                    trainingsTekstBuilder.append(en);
                }
                trainingsTekstBuilder.append(vrijdag)
                                     .append(om)
                                     .append(trainingstijd.getVrTijd())
                                     .append(uur);
                if (trainingstijd.getVrLok() != null) {
                    trainingsTekstBuilder.append(", ")
                                         .append(trainingstijd.getVrLok());
                }
            }

            // Zaterdag
            if (trainingstijd.getZaTijd() != null) {
                aantal++;
                if (aantal > 1) {
                    trainingsTekstBuilder.append(en);
                }
                trainingsTekstBuilder.append(zaterdag)
                                     .append(om)
                                     .append(trainingstijd.getZaTijd())
                                     .append(uur);
                if (trainingstijd.getZaLok() != null) {
                    trainingsTekstBuilder.append(", ")
                                         .append(trainingstijd.getZaLok());
                }
            }
            return trainingsTekstBuilder.toString();
        } else {
            return null;
        }
    }
    
}
