/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.web.controller;

import com.robur58.business.BsmSysvarFacadeLocal;
import com.robur58.domein.BsmSysvar;
import com.robur58.web.view.HomePlateSessionTO;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author godefrooije
 */
@ManagedBean(name="contextController")
@SessionScoped
public class ContextController extends Controller {

    private static final String AANTAL_FOTOS_ALBUM = "AANTAL_FOTOS_ALBUM";
    private static final String AANTAL_KARAKTERS_ALBUM = "AANTAL_KARAKTERS_ALBUM";
    private static final String APP_TITLE = "APP_TITLE";
    private static final String AUTO_ARCHIEF_TERMIJN = "AUTO_ARCHIEF_TERMIJN";
    private static final String BREEDTE_MENU = "BREEDTE_MENU";
    private static final String DIR_HOR_BEHEERMENU = "DIR_HOR_BEHEERMENU";
    private static final String DIR_IMAGES = "DIR_IMAGES";
    private static final String DIR_ROOT = "DIR_ROOT";
    private static final String DIR_VER_BEHEERMENU = "DIR_VER_BEHEERMENU";
    private static final String DISCUSSIE_TOPIC_VERLOOPTIJD = "DISCUSSIE_TOPIC_VERLOOPTIJD";
    private static final String FORMAAT_SPELERSFOTO = "FORMAAT_SPELERSFOTO";
    private static final String FORMAAT_TEAMFOTO = "FORMAAT_TEAMFOTO";
    private static final String LEDEN_LOGIN = "LEDEN_LOGIN";
    private static final String NAME_WEBSERVER = "NAME_WEBSERVER";
    private static final String SPELERSFOTO_CATEGORIE = "SPELERSFOTO_CATEGORIE";
    private static final String SPONSORFOTO_CATEGORIE = "SPONSORFOTO_CATEGORIE";
    private static final String TEAMFOTO_CATEGORIE = "TEAMFOTO_CATEGORIE";
    private static final String URL_WEB_AGENT = "URL_WEB_AGENT";
    private static final String WEBSITE_CURSOR = "WEBSITE_CURSOR";
    
    private static final String LAATSTE_UPDATE_DOC = "LAATSTE_UPDATE_DOC";
    private static final String LAATSTE_UPDATE_GAS = "LAATSTE_UPDATE_GAS";
    private static final String LAATSTE_UPDATE_LIN = "LAATSTE_UPDATE_LIN";
    private static final String LAATSTE_UPDATE_WED = "LAATSTE_UPDATE_WED";
    
    private static final String STARTPAGINA_HIGHLIGHTS = "STARTPAGINA_HIGHLIGHTS";
    private static final String STARTPAGINA_KALENDER = "STARTPAGINA_KALENDER";
    private static final String STARTPAGINA_TRIVIA = "STARTPAGINA_TRIVIA";
    private static final String STARTPAGINA_WEDSTRIJDEN = "STARTPAGINA_WEDSTRIJDEN";
    private static final String STARTPAGINA_WEER = "STARTPAGINA_WEER";

    @EJB
    private BsmSysvarFacadeLocal sysvarFacade;

    public ContextController() {
        
    }
    
    public String ophalenWaarde(String key) {
        BsmSysvar sysvar = sysvarFacade.findByCode(key);
        
        if (sysvar != null) {
            return sysvar.getWaarde();
        } else {
            return null;
        }
    }
    
    public void vulContextWaardes(HomePlateSessionTO homePlateSessionTO) {
        if (ophalenWaarde(STARTPAGINA_KALENDER).equals("YES")) {
            homePlateSessionTO.setShowContextCalendar(true);
            homePlateSessionTO.setShowContextCalendarDefault( true );
        } else {
            homePlateSessionTO.setShowContextCalendar(false);
            homePlateSessionTO.setShowContextCalendarDefault( false );
        }
        
        if (ophalenWaarde(STARTPAGINA_HIGHLIGHTS).equals("YES")) {
            homePlateSessionTO.setShowContextHighlights(true);
            homePlateSessionTO.setShowContextHighlightsDefault( true );
        } else {
            homePlateSessionTO.setShowContextHighlights(false);
            homePlateSessionTO.setShowContextHighlightsDefault( false );
        }

        if (ophalenWaarde(STARTPAGINA_WEER).equals("YES")) {
            homePlateSessionTO.setShowContextWeer(true);
            homePlateSessionTO.setShowContextWeerDefault( true );
        } else {
            homePlateSessionTO.setShowContextWeer(false);
            homePlateSessionTO.setShowContextWeerDefault( false );
        }
        
        if (ophalenWaarde(STARTPAGINA_WEDSTRIJDEN).equals("YES")) {
            homePlateSessionTO.setShowContextWedstrijdenActueel(true);
            homePlateSessionTO.setShowContextWedstrijdenActueelDefault( true );
        } else {
            homePlateSessionTO.setShowContextWedstrijdenActueel(false);
            homePlateSessionTO.setShowContextWedstrijdenActueelDefault( false );
        }
    }

}
