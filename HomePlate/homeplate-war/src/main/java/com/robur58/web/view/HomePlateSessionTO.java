package com.robur58.web.view;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import nl.profict.platform.web.util.FacesUtils;

@ManagedBean(name = "homePlateSessionTO")
@SessionScoped
public class HomePlateSessionTO implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String HOMEPLATESESSIONTO = "homePlateSessionTO";
    private TimeZone timeZone;
    private String webserverName;
    private List menuItems;
    private List highlightItems;
    private List calendarItems;
    private int currentMenu;
    private String language = "NL";
    private String typeTeamList;
    private boolean showContextHighlights;
    private boolean showContextCalendar;
    private String showContextAgenda;
    private boolean showContextTeamList;
    private boolean showContextWedstrijdenTeamList;
    private boolean showContextSpelersInfo;
    private boolean showContextWedstrijdenActueel;
    private boolean showContextSponsors;
    private boolean showContextWeer;
    private String showContextGoogleNews;
    private boolean showContextLogin;
    private boolean showKnbsbNews;
    private boolean showContextSocialMedia;
    private boolean showContextSponsorKliks;
    private String showWsbNews;
    private List beheerModules;
    private List sponsorLogos;
    private Map googleNews;
    private List knbsbNews;
    private int knbsbNewsSize;
    private List wsbNews;
    private int wsbNewsSize;
    private String loginAccordionIndex;
    private TimeZone timezone;
    private boolean editInfoMode;
    private boolean showContextCalendarDefault;
    private boolean showContextHighlightsDefault;
    private boolean showContextWedstrijdenActueelDefault;
    private boolean showContextWeerDefault;
    
    public HomePlateSessionTO() {
        showContextHighlights = true;
        showContextCalendar = true;
        showContextWedstrijdenActueel = true;
        showContextSponsors = true;
        showContextWeer = true;
        showKnbsbNews = true;
        showContextLogin = true;
        showContextSocialMedia = true;
        
        currentMenu = 1;
    }

    public String switchLanguage() {
        if (isDutch()) {
            language = "EN";
        } else {
            language = "NL";
        }
        String servername = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getServerName();
        if ("localhost".equals(servername)) {
            FacesUtils.handleNavigationRedirect("/pages/bsm/homeplate.xhtml");
        } else {
            FacesUtils.handleNavigationRedirect("http://homeplate.robur58.com/HomePlate-war/pages/bsm/homeplate.faces?faces-redirect=true");
        }
        return null;
    }

    public String switchLanguageV2() {
        if (isDutch()) {
            language = "EN";
        } else {
            language = "NL";
        }
        String servername = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getServerName();
        if ("localhost".equals(servername)) {
            FacesUtils.handleNavigationRedirect("/pages/homeplate_v2.xhtml");
        } else {
            FacesUtils.handleNavigationRedirect("http://homeplate.robur58.com/HomePlate-war/pages/homeplate_v2.faces?faces-redirect=true");
        }
        return null;
    }
 
    public List getSponsorLogos() {
        return sponsorLogos;
    }

    public void setSponsorLogos(List sponsorLogos) {
        this.sponsorLogos = sponsorLogos;
    }

    public List getBeheerModules() {
        return beheerModules;
    }

    public void setBeheerModules(List beheerModules) {
        this.beheerModules = beheerModules;
    }

    public boolean isShowContextSpelersInfo() {
        return showContextSpelersInfo;
    }

    public void setShowContextSpelersInfo(boolean showContextSpelersInfo) {
        this.showContextSpelersInfo = showContextSpelersInfo;
    }

    public boolean isShowContextTeamList() {
        return showContextTeamList;
    }

    public void setShowContextTeamList(boolean showContextTeamList) {
        this.showContextTeamList = showContextTeamList;
    }

    public int getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(int currentMenu) {
        this.currentMenu = currentMenu;
    }

    public List getHighlightItems() {
        return highlightItems;
    }

    public void setHighlightItems(List highlightItems) {
        this.highlightItems = highlightItems;
    }

    public List getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List menuItems) {
        this.menuItems = menuItems;
    }

    public boolean isShowContextHighlights() {
        return showContextHighlights;
    }

    public void setShowContextHighlights(boolean showContextHighlights) {
        this.showContextHighlights = showContextHighlights;
    }

    public List getCalendarItems() {
        return calendarItems;
    }

    public void setCalendarItems(List calendarItems) {
        this.calendarItems = calendarItems;
    }

    public Map getGoogleNews() {
        return googleNews;
    }

    public void setGoogleNews(Map googleNews) {
        this.googleNews = googleNews;
    }

    public boolean isShowContextCalendar() {
        return showContextCalendar;
    }

    public void setShowContextCalendar(boolean showCalendar) {
        this.showContextCalendar = showCalendar;
    }

    public boolean isShowContextSponsorKliks() {
        return showContextSponsorKliks;
    }

    public void setShowContextSponsorKliks(boolean showContextSponsorKliks) {
        this.showContextSponsorKliks = showContextSponsorKliks;
    }
    
    public void hideContextWedstrijdenActueel() {
        this.showContextWedstrijdenActueel = false;
    }
    public boolean isShowContextWedstrijdenActueel() {
        return showContextWedstrijdenActueel;
    }

    public void setShowContextWedstrijdenActueel(boolean showContextWedstrijdenActueel) {
        this.showContextWedstrijdenActueel = showContextWedstrijdenActueel;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isDutch() {
        if ("NL".equals(language)) {
            return true;
        } else {
            return false;
        }
    }

    public void setDutch() {
        this.language = "NL";
    }

    public void setEnglish() {
        this.language = "EN";
    }

    public String getTypeTeamList() {
        return typeTeamList;
    }

    public void setTypeTeamList(String typeTeamList) {
        this.typeTeamList = typeTeamList;
    }

    public boolean isShowContextSponsors() {
        return showContextSponsors;
    }

    public void setShowContextSponsors(boolean showContextSponsors) {
        this.showContextSponsors = showContextSponsors;
    }

    public boolean isShowContextWeer() {
        return showContextWeer;
    }

    public void setShowContextWeer(boolean showContextWeer) {
        this.showContextWeer = showContextWeer;
    }

    public String isShowContextGoogleNews() {
        return showContextGoogleNews;
    }

    public void setShowContextGoogleNews(String showContextGoogleNews) {
        this.showContextGoogleNews = showContextGoogleNews;
    }

    public List getKnbsbNews() {
        return knbsbNews;
    }

    public void setKnbsbNews(List knbsbNews) {
        this.knbsbNews = knbsbNews;
    }

    public boolean isShowKnbsbNews() {
        return showKnbsbNews;
    }

    public void setShowKnbsbNews(boolean showKnbsbNews) {
        this.showKnbsbNews = showKnbsbNews;
    }

    public int getKnbsbNewsSize() {
        return knbsbNewsSize;
    }

    public void setKnbsbNewsSize(int knbsbNewsSize) {
        this.knbsbNewsSize = knbsbNewsSize;
    }

    public boolean isShowContextWedstrijdenTeamList() {
        return showContextWedstrijdenTeamList;
    }

    public void setShowContextWedstrijdenTeamList(boolean showContextWedstrijdenTeamList) {
        this.showContextWedstrijdenTeamList = showContextWedstrijdenTeamList;
    }

    public boolean isShowContextLogin() {
        return showContextLogin;
    }

    public void setShowContextLogin(boolean showContextLogin) {
        this.showContextLogin = showContextLogin;
    }

    public String isShowWsbNews() {
        return showWsbNews;
    }

    public void setShowWsbNews(String showWsbNews) {
        this.showWsbNews = showWsbNews;
    }

    public List getWsbNews() {
        return wsbNews;
    }

    public void setWsbNews(List wsbNews) {
        this.wsbNews = wsbNews;
    }

    public int getWsbNewsSize() {
        return wsbNewsSize;
    }

    public void setWsbNewsSize(int wsbNewsSize) {
        this.wsbNewsSize = wsbNewsSize;
    }

    public String isShowContextAgenda() {
        return showContextAgenda;
    }

    public void setShowContextAgenda(String showContextAgenda) {
        this.showContextAgenda = showContextAgenda;
    }

    public boolean isShowContextSocialMedia() {
        return showContextSocialMedia;
    }

    public void setShowContextSocialMedia(boolean showContextSocialMedia) {
        this.showContextSocialMedia = showContextSocialMedia;
    }

    public String getWebserverName() {
        return webserverName;
    }

    public void setWebserverName(String webserverName) {
        this.webserverName = webserverName;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public String getLoginAccordionIndex() {
        return loginAccordionIndex;
    }

    public void setLoginAccordionIndex(String loginAccordionIndex) {
        this.loginAccordionIndex = loginAccordionIndex;
    }

    public TimeZone getTimezone() {
        if (timezone == null) {
            timezone = TimeZone.getDefault();
        }
        return timezone;
    }

    public void setTimezone(TimeZone timezone) {
        this.timezone = timezone;
    }

    public boolean isShowContextCalendarDefault() {
        return showContextCalendarDefault;
    }

    public void setShowContextCalendarDefault(boolean showContextCalendarDefault) {
        this.showContextCalendarDefault = showContextCalendarDefault;
    }

    public boolean isShowContextHighlightsDefault() {
        return showContextHighlightsDefault;
    }

    public void setShowContextHighlightsDefault(boolean showContextHighlightsDefault) {
        this.showContextHighlightsDefault = showContextHighlightsDefault;
    }

    public boolean isShowContextWedstrijdenActueelDefault() {
        return showContextWedstrijdenActueelDefault;
    }

    public void setShowContextWedstrijdenActueelDefault(boolean showContextWedstrijdenActueelDefault) {
        this.showContextWedstrijdenActueelDefault = showContextWedstrijdenActueelDefault;
    }

    public boolean isShowContextWeerDefault() {
        return showContextWeerDefault;
    }

    public void setShowContextWeerDefault(boolean showContextWeerDefault) {
        this.showContextWeerDefault = showContextWeerDefault;
    }

    public boolean getEditInfoMode() {
        return editInfoMode;
    }

    public void setEditInfoMode(boolean editInfoMode) {
        this.editInfoMode = editInfoMode;
    }
    
}
