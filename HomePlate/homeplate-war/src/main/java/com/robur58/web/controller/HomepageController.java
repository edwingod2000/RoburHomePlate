package com.robur58.web.controller;

import com.robur58.business.BsmInfoFacadeLocal;
import com.robur58.business.BsmSponsorLogosFacadeLocal;
import com.robur58.business.BsmSysteemMededelingFacadeLocal;
import com.robur58.business.BsmVWedstrijdenInclSeriesFacadeLocal;
import com.robur58.domein.BsmInfo;
import com.robur58.domein.BsmSponsorLogos;
import com.robur58.domein.BsmSysteemMededeling;
import com.robur58.domein.BsmVWedstrijdenInclSeries;
import com.robur58.web.rss.RSSFeedReader;
import com.robur58.web.view.HomePlateSessionTO;
import com.robur58.web.view.UserInfo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "homepageController")
@SessionScoped
public class HomepageController extends Controller {

    private static final String HEADER_PREFIX = "/resources/bsm/img/header/RoburHeader";
    
    @EJB
    private BsmSponsorLogosFacadeLocal sponsorLogosFacade;

    @EJB
    private BsmVWedstrijdenInclSeriesFacadeLocal wedstrijdenFacade;

    @EJB
    private BsmInfoFacadeLocal infoFacade;
    
    @EJB
    private BsmSysteemMededelingFacadeLocal systeemMededelingFacade;
    
    @ManagedProperty(value="#{contextController}")
    private ContextController contextController;

    @ManagedProperty(value="#{homePlateSessionTO}")
    private HomePlateSessionTO homePlateSessionTODummy;
    
    private UserInfo userInfo;
    private String titelLogo;
    private List<BsmSponsorLogos> sponsorLogos;
    private List<BsmVWedstrijdenInclSeries> actueleWedstrijden;
    private Map<Date, Map<String, List<BsmVWedstrijdenInclSeries>>> groupedWedstrijden;

    private List<BsmSysteemMededeling> systeemMededelingScripts = null;
    private List<BsmSysteemMededeling> systeemMededelingHomepage = null;
    
    private boolean notificatieTonen = true;
    
    private String weerUrl = "http://gratis.weer.nl/meteo/hptool/index.php?cid=31X4403&cityName=Apeldoorn&l=nl&style=14&v=nl&ver=2&c1=000000&c2=FFFFFF&c3=000000&c4=FFFFFF&c5=000000&c6=FFFFFF&c7=FFFFFF&f1a=1&f1b=2&f2a=1&f2b=1&f3a=1&f3b=1&ct1=1&ct2=2&ct3=6&ct4=10&ct5=0&fcd=0";

    /** Creates a new instance of HomepageController */
    public HomepageController() {
    }

    @PostConstruct
    public void init() {
//        setHomePlateSessionTO(new HomePlateSessionTO());
        getHomePlateSessionTO().setLoginAccordionIndex("0");
        getHomePlateSessionTO().setLanguage("NL");
        getHomePlateSessionTO().setTimeZone(TimeZone.getDefault());
        getHomePlateSessionTO().setCurrentMenu(0);

        contextController.vulContextWaardes(getHomePlateSessionTO());
        resetContext();
        
        ophalenInfo();
        
        systeemMededelingScripts = ophalenSysteemMededelingen(BsmSysteemMededeling.TYPE_PAGINA_SCRIPT);
        systeemMededelingHomepage = ophalenSysteemMededelingen(BsmSysteemMededeling.TYPE_STARTPAGINA_NOTIFICATIE);
        
//        if (systeemMededelingHomepage != null && systeemMededelingHomepage.size() > 0) {
//            FacesContext context = FacesContext.getCurrentInstance();  
//            for (BsmSysteemMededeling mededeling: systeemMededelingHomepage) {
//                context.addMessage(null, new FacesMessage(mededeling.getMededeling()));  
//            }
//        }
    }

    private List<BsmSysteemMededeling> ophalenSysteemMededelingen(String type) {
        return systeemMededelingFacade.findByType(type);
    }
        
    public void resetContext() {
        getHomePlateSessionTO().setShowContextSponsors(true);
        getHomePlateSessionTO().setShowKnbsbNews(false);
        getHomePlateSessionTO().setShowContextTeamList(false);
        getHomePlateSessionTO().setShowContextSpelersInfo(false);
        getHomePlateSessionTO().setShowContextLogin(true);
        getHomePlateSessionTO().setShowContextWedstrijdenTeamList(false);
        getHomePlateSessionTO().setShowContextSponsorKliks(true);
        getHomePlateSessionTO().setShowContextWedstrijdenActueel( getHomePlateSessionTO().isShowContextWedstrijdenActueelDefault());
        getHomePlateSessionTO().setShowContextHighlights( getHomePlateSessionTO().isShowContextHighlightsDefault());
        getHomePlateSessionTO().setShowContextCalendar( getHomePlateSessionTO().isShowContextCalendarDefault());
        getHomePlateSessionTO().setShowContextWeer( getHomePlateSessionTO().isShowContextWeerDefault());
    }
    
    public String closeNotificatie() {
        notificatieTonen = false;
        return null;
    }
    
    public void ophalenInfo() {
        String reason = getRequest().getParameter("reason");
        if (reason != null) {
            if (reason.equals("expired")) {
                FacesContext context = FacesContext.getCurrentInstance();  
                context.addMessage("growl", new FacesMessage("Sessie is verlopen. Session is expired.", null));
                reason = null;
            }
        }

//        ophalenKNBSBNews();
        ophalenHighlights();
        ophalenActueleWedstrijden();
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getTitelLogo() {
        Random rnd = new Random();
        int number = Math.abs(rnd.nextInt()) % 9;
        titelLogo = getRequest().getContextPath() + HEADER_PREFIX + number + ".gif";
        return titelLogo;
    }

    public void setTitelLogo(String titelLogo) {
        this.titelLogo = titelLogo;
    }

    public String executeSwitchLanguage() {
        if (getHomePlateSessionTO().isDutch()) {
            getHomePlateSessionTO().setEnglish();
        } else {
            getHomePlateSessionTO().setDutch();
        }
        // Terug naar de huidige pagina
        return "/pages/homeplate.xhtml?redirect=true";
    }

    public String getSponsorLogos() {
        StringBuffer result = new StringBuffer();

        if (sponsorLogos == null) {
            sponsorLogos = sponsorLogosFacade.findByTonenInCarroussel();
        }

        boolean kommaZetten = false;
        for (BsmSponsorLogos logo : sponsorLogos) {
            if (kommaZetten && sponsorLogos.size() > 1) {
                result.append(",");
            } else {
                kommaZetten = true;
            }
            result.append("\"" + logo.getBestandsnaam() + "\"");
        }
        return result.toString();
    }

    public List<BsmSponsorLogos> getSponsorCarroussel() {
        if (sponsorLogos == null) {
            sponsorLogos = sponsorLogosFacade.findByTonenInCarroussel();
        }
        return hussleSponsorCarroussel(sponsorLogos);
    }
    
    /**
     * To change the sponsorLogs we have to shuffle them.
     * 
     * @param sponsorLogos
     * @return 
     */
    public List<BsmSponsorLogos> hussleSponsorCarroussel(List<BsmSponsorLogos> sponsorLogos) {
        long seed = System.nanoTime();
        Collections.shuffle(sponsorLogos, new Random(seed));
        
        return sponsorLogos;
    }
    
    public String getSponsorUrls() {
        StringBuffer result = new StringBuffer();

        if (sponsorLogos == null) {
            sponsorLogos = sponsorLogosFacade.findByTonenInCarroussel();
        }

        boolean kommaZetten = false;
        for (BsmSponsorLogos logo : sponsorLogos) {
            if (kommaZetten && sponsorLogos.size() > 1) {
                result.append(",");
            } else {
                kommaZetten = true;
            }
            result.append("\"" + logo.getUrl() + "\"");
        }
        return result.toString();
    }

    public String getSponsorNames() {
        StringBuffer result = new StringBuffer();

        if (sponsorLogos == null) {
            sponsorLogos = sponsorLogosFacade.findByTonenInCarroussel();
        }

        boolean kommaZetten = false;
        for (BsmSponsorLogos logo : sponsorLogos) {
            if (kommaZetten && sponsorLogos.size() > 1) {
                result.append(",");
            } else {
                kommaZetten = true;
            }
            result.append("\"" + logo.getTitel() + "\"");
        }
        return result.toString();
    }

    public void ophalenKNBSBNews() {
        String knbsbRSS = "http://www.knbsb.nl/nw/index.php?option=com_ninjarsssyndicator&feed_id=1&format=raw&lang=nl";
        List result = RSSFeedReader.read(knbsbRSS);

        getHomePlateSessionTO().setKnbsbNews(result);
        if (result.size() > 0) {
            System.out.println("Test");
        }
    }

    public List<Date> getWedstrijdDatums() {
        Set keySet = groupedWedstrijden.keySet();

        List<Date> result = new ArrayList<Date>();
        for (Object datum : keySet) {
            result.add((Date) datum);
        }

        return result;
    }

    public List<String> wedstrijdSoorten(Date datum) {
        List<String> result = new ArrayList<String>();
        try {
            if (getHomePlateSessionTO().isShowContextWedstrijdenActueel()) {
                if (groupedWedstrijden != null) {
                    Map<String, List<BsmVWedstrijdenInclSeries>> wedstrijden = groupedWedstrijden.get(datum);
                    if (wedstrijden != null) {
                        Set keySet = wedstrijden.keySet();

                        for (Object soort : keySet) {
                            result.add((String) soort);
                        }
                    }

                }
            }
        } catch (Exception e) {
            result = new ArrayList<String>();
        }
        return result;
    }

    public List<BsmVWedstrijdenInclSeries> wedstrijdenPerSoort(Date datum, String soort) {
        List<BsmVWedstrijdenInclSeries> result = new ArrayList<BsmVWedstrijdenInclSeries>();
        
        try {
            Map<String, List<BsmVWedstrijdenInclSeries>> soorten = groupedWedstrijden.get(datum);
            result = soorten.get(soort);
        } catch (Exception e) {
            result = new ArrayList<BsmVWedstrijdenInclSeries>();
        }
        return result;
    }

    public void ophalenActueleWedstrijden() {
        // De servtice BsmVWedstrijdenInclSeriesFacadeLocal.findAll geeft al een beperkte lijst wedstrijden
        // terug. huidige datum + 7
        if (actueleWedstrijden == null) {
            actueleWedstrijden = wedstrijdenFacade.findAll();

            groupedWedstrijden = new TreeMap<Date, Map<String, List<BsmVWedstrijdenInclSeries>>>();

            Map<String, List<BsmVWedstrijdenInclSeries>> soortWedstrijdEntry;
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            try {
                for (BsmVWedstrijdenInclSeries wedstrijd : actueleWedstrijden) {
                    String datum = sdf.format(wedstrijd.getDatum());
                    Date date = sdf.parse(datum); // datum zonder tijd
                    if (groupedWedstrijden.containsKey(date)) {
                        soortWedstrijdEntry = groupedWedstrijden.get(date);

                        if (soortWedstrijdEntry.containsKey(wedstrijd.getSoort())) {
                            List wedstrijden = soortWedstrijdEntry.get(wedstrijd.getSoort());
                            wedstrijden.add(wedstrijd);
                        } else {
                            List wedstrijden = new ArrayList<BsmVWedstrijdenInclSeries>();
                            wedstrijden.add(wedstrijd);
                            soortWedstrijdEntry.put(wedstrijd.getSoort(), wedstrijden);
                        }
                    } else {
                        soortWedstrijdEntry = new TreeMap<String, List<BsmVWedstrijdenInclSeries>>();
                        List<BsmVWedstrijdenInclSeries> wedstrijdLijst = new ArrayList<BsmVWedstrijdenInclSeries>();
                        wedstrijdLijst.add(wedstrijd);
                        soortWedstrijdEntry.put(wedstrijd.getSoort(), wedstrijdLijst);
                        groupedWedstrijden.put(date, soortWedstrijdEntry);
                    }
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

        }
        
//        ParameterHelper.getInstance();

    }

    public void ophalenHighlights() {
        this.getHomePlateSessionTO().setHighlightItems(infoFacade.findHighlights());
    }

    public String executeHighlightKeuze(BsmInfo highlight) {
        return null;
    }

    public List<BsmVWedstrijdenInclSeries> getActueleWedstrijden() {
        return actueleWedstrijden;
    }

    public Map<Date, Map<String, List<BsmVWedstrijdenInclSeries>>> getGroupedWedstrijden() {
        return groupedWedstrijden;
    }

    public void setGroupedWedstrijden(Map<Date, Map<String, List<BsmVWedstrijdenInclSeries>>> groupedWedstrijden) {
        this.groupedWedstrijden = groupedWedstrijden;
    }

    public ContextController getContextController() {
        return contextController;
    }

    public void setContextController(ContextController contextController) {
        this.contextController = contextController;
    }

    public String getWeerUrl() {
        return weerUrl;
    }

    public void setWeerUrl(String weerUrl) {
        this.weerUrl = weerUrl;
    }

    public List<BsmSysteemMededeling> getSysteemMededelingScripts() {
        return systeemMededelingScripts;
    }

    public void setSysteemMededelingScripts(List<BsmSysteemMededeling> systeemMededelingScripts) {
        this.systeemMededelingScripts = systeemMededelingScripts;
    }

    public List<BsmSysteemMededeling> getSysteemMededelingHomepage() {
        return systeemMededelingHomepage;
    }

    public void setSysteemMededelingHomepage(List<BsmSysteemMededeling> systeemMededelingHomepage) {
        this.systeemMededelingHomepage = systeemMededelingHomepage;
    }

    public boolean isNotificatieTonen() {
        return notificatieTonen;
    }

    public void setNotificatieTonen(boolean notificatieTonen) {
        this.notificatieTonen = notificatieTonen;
    }

    public HomePlateSessionTO getHomePlateSessionTODummy() {
        return homePlateSessionTODummy;
    }

    public void setHomePlateSessionTODummy(HomePlateSessionTO homePlateSessionTODummy) {
        this.homePlateSessionTODummy = homePlateSessionTODummy;
        super.setHomePlateSessionTO(homePlateSessionTODummy);
    }
 
}
