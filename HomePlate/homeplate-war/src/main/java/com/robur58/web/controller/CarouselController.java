package com.robur58.web.controller;

import com.robur58.business.BsmKalenderFacadeLocal;
import com.robur58.business.BsmTeamsFacadeLocal;
import com.robur58.business.BsmWedstrijdenFacadeLocal;
import com.robur58.domein.BsmKalender;
import com.robur58.domein.BsmTeams;
import com.robur58.domein.BsmVWedstrijdenInclSeries;
import com.robur58.domein.BsmWedstrijden;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "carouselController")
@SessionScoped
public class CarouselController extends Controller {
    
    private List<CarouselInfo> carouselInfos = new ArrayList<CarouselInfo>();
    
    @EJB
    private BsmWedstrijdenFacadeLocal wedstrijdenFacade;
    
    @EJB
    private BsmTeamsFacadeLocal teamsFacade;
    
    @EJB
    private BsmKalenderFacadeLocal kalenderFacade;
    
    @ManagedProperty(value="#{homepageController}")
    private HomepageController homepageController;
            
    /** Creates a new instance of CarouselController */
    public CarouselController() {
        
    }
    
    @PostConstruct
    public void init() {
        ophalenActueleWedstrijden();
        ophalenActueleKalender();
    }

    private void ophalenActueleWedstrijden() {
        boolean softbalGevonden = false;
        boolean honkbalGevonden = false;
        BsmTeams hoofdteamHonkbal = teamsFacade.findHoofdteamBySoort("HB");
        BsmTeams hoofdteamSoftbal = teamsFacade.findHoofdteamBySoort("SB");
        
        BsmWedstrijden wedstrijdHonkbal = wedstrijdenFacade.findFirstGameByTeamVolgnr(hoofdteamHonkbal.getVolgnr());
        BsmWedstrijden wedstrijdSoftbal = wedstrijdenFacade.findFirstGameByTeamVolgnr(hoofdteamSoftbal.getVolgnr());

        maakWedstrijdCarouselInfo(wedstrijdHonkbal, "HB");
        maakWedstrijdCarouselInfo(wedstrijdSoftbal, "SB");
    }
    
    private void ophalenActueleKalender() {
        List<BsmKalender> findActual = kalenderFacade.findActual();
    
        if (findActual != null & findActual.size() > 0) {
            BsmKalender kalender = findActual.get(0);
            String titel = "Eerstkomend evenement";
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE d-M");
            StringBuilder subtitel = new StringBuilder();
            subtitel.append(sdf.format(kalender.getDatum()));
            subtitel.append(" - ");
            subtitel.append(kalender.getTitel());

            carouselInfos.add(new CarouselInfo(titel, subtitel.toString(), "slide-03.jpg"));
        }
    }
    
    private void maakWedstrijdCarouselInfo(BsmWedstrijden wedstrijd, String soort) {
        String titel = "";
        StringBuilder subtitel = new StringBuilder();
        String imgUrl = "";
        
        if (wedstrijd != null) {
            titel = wedstrijd.getTeamNaam();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE d-M");
            subtitel.append(sdf.format(wedstrijd.getDatum()));
            subtitel.append(" - ");
            subtitel.append(wedstrijd.getTijdstip());
            subtitel.append(" - ");
            subtitel.append(wedstrijd.getDisplayTeamThuis());
            subtitel.append(" - ");
            subtitel.append(wedstrijd.getDisplayTeamUit());
        } else {
            if ("HB".equals(soort)) {
                titel = "Honkbal";
            } else if ("SB".equals(soort)) {
                titel = "Softbal";
            }
            subtitel.append("Geen aankomende wedstrijden");
        }
        
        if (("HB").equals(soort)) {
            imgUrl = "slide-05.jpg";
        } else if ("SB".equals(soort)) {
            imgUrl = "slide-06.jpg";
        }
        carouselInfos.add(new CarouselInfo(titel, subtitel.toString(), imgUrl));
    }
    
    public HomepageController getHomepageController() {
        return homepageController;
    }

    public void setHomepageController(HomepageController homepageController) {
        this.homepageController = homepageController;
    }

    public List<CarouselInfo> getCarouselInfos() {
        return carouselInfos;
    }

    public void setCarouselInfos(List<CarouselInfo> carouselInfos) {
        this.carouselInfos = carouselInfos;
    }
    
    public class CarouselInfo {
        private String titel;
        private String subtitel;
        private String imgUrl;

        public CarouselInfo(String titel, String subtitel, String imgUrl) {
            this.titel = titel;
            this.subtitel = subtitel;
            this.imgUrl = imgUrl;
        }
        
        public String getTitel() {
            return titel;
        }

        public void setTitel(String titel) {
            this.titel = titel;
        }

        public String getSubtitel() {
            return subtitel;
        }

        public void setSubtitel(String subtitel) {
            this.subtitel = subtitel;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
