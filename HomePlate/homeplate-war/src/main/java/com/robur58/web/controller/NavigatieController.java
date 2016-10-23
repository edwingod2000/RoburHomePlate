package com.robur58.web.controller;

import com.robur58.business.BsmFunctiesFacadeLocal;
import com.robur58.business.BsmMenuFacadeLocal;
import com.robur58.business.BsmTypeInfoFacadeLocal;
import com.robur58.domein.BsmFuncties;
import com.robur58.domein.BsmMenu;
import com.robur58.domein.BsmTypeInfo;
import com.robur58.web.common.ServerPropertiesHelper;
import com.robur58.web.navigatie.Navigatie;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import nl.profict.platform.web.util.FacesUtils;
import org.apache.commons.lang.StringUtils;

@ManagedBean(name = "navigatieController")
@SessionScoped
public class NavigatieController extends Controller {

    @EJB
    BsmFunctiesFacadeLocal functiesFacade;
    @EJB
    BsmTypeInfoFacadeLocal typeInfoFacade;
    @EJB
    BsmMenuFacadeLocal menuFacade;
    
    private String progress = "Opstarten......";
    private StringBuffer menu;
    private StringBuffer gebruikermenu;

    private String currentMenu = "1";

    private String currentTabblad = "1";
    private String currentBeheerTabblad = "2";

    /** Creates a new instance of NavigatieController */
    public NavigatieController() {
    }

    public String getTabbladId(String tabbladNumber) {
        if (currentTabblad.equals(tabbladNumber)) {
            return "current";
        } else {
            int currentTabbladInt = Integer.parseInt(tabbladNumber);
            switch (currentTabbladInt) {
                case 1:
                    return "tab_homeplate";
                case 2:
                    return "tab_teams";
                case 3:
                    return "tab_wedstrijden";
                case 4:
                    return "tab_kalender";
                case 5:
                    return "tab_gastenboek";
                case 6:
                    return "tab_nieuwlid";
                case 7:
                    return "tab_documenten";
                case 8:
                    return "tab_ideeenbus";
                case 9:
                    return "tab_inloggen";
                case 20:
                    return "tab_informatie";
            }
        }

        return null;
    }

    public String getTabbladClass(String tabbladNumber) {
        if (currentTabblad.equals(tabbladNumber)) {
            return "active";
        } else {
            int currentTabbladInt = Integer.parseInt(tabbladNumber);
            switch (currentTabbladInt) {
                case 1:
                    return "tab_homeplate";
                case 2:
                    return "tab_teams";
                case 3:
                    return "tab_wedstrijden";
                case 4:
                    return "tab_kalender";
                case 5:
                    return "tab_gastenboek";
                case 6:
                    return "tab_nieuwlid";
                case 7:
                    return "tab_documenten";
                case 8:
                    return "tab_ideeenbus";
                case 20:
                    return "tab_informatie";
            }
        }

        return null;
    }
    
    public String getBeheerTabbladId(String tabbladNumber) {
        if (currentBeheerTabblad.equals(tabbladNumber)) {
            return "current";
        } else {
            int currentBeheerTabbladInt = Integer.parseInt(tabbladNumber);
            switch (currentBeheerTabbladInt) {
                case 1:
                    return "tab_homeplate";
                case 2:
                    return "tab_gebruikersinstellingen";
                case 3:
                    return "tab_documenten";
            }
        }

        return null;
    }
    
    public String executeNavigatie(String id) throws IOException {
        int keuze = Integer.parseInt(id);
        String url = Navigatie.HOMEPLATE;
        switch(keuze) {
            case 1:
                url = Navigatie.HOMEPLATE;
                break;
            case 2:
                url = Navigatie.TEAMS;
                break;
            case 3:
                url = Navigatie.WEDSTRIJDEN;
                break;
            case 4:
                url = Navigatie.KALENDER;
                break;
            case 5:
                url = Navigatie.GASTENBOEK;
                break;
            case 6:
                url = Navigatie.NIEUWLID;
                break;
            case 7:
                url = Navigatie.DOCUMENTEN;
                break;
            case 8:
                url = Navigatie.IDEEENBUS;
                break;
            case 100:
                url = Navigatie.DOCUMENTEN_BEHEER;
                break;
            default:
                url = Navigatie.HOMEPLATE;
                break;
        }
        
        return url;

    }
    
    public String executeNavigation(String id) throws IOException {
        int keuze = Integer.parseInt(id);
        String url = Navigatie.V2_HOMEPLATE;
        switch(keuze) {
            case 1:
                url = Navigatie.V2_HOMEPLATE;
                break;
            case 2:
                url = Navigatie.V2_TEAMS;
                break;
            case 3:
                url = Navigatie.V2_WEDSTRIJDEN;
                break;
            case 4:
                url = Navigatie.V2_KALENDER;
                break;
            case 5:
                url = Navigatie.V2_GASTENBOEK;
                break;
            case 6:
                url = Navigatie.V2_NIEUWLID;
                break;
            case 7:
                url = Navigatie.V2_DOCUMENTEN;
                break;
            case 8:
                url = Navigatie.IDEEENBUS;
                break;
            case 100:
                url = Navigatie.DOCUMENTEN_BEHEER;
                break;
            default:
                url = Navigatie.HOMEPLATE;
                break;
        }
        
        return url;

    }

    public void bepaalNavigatie() throws IOException {
        String registratieSid = getRequest().getParameter("registratieSid");
        if (StringUtils.isEmpty(registratieSid)) {
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesUtils.getContextPath() + "/pages/homeplate.faces");
        } else {
            setObjectOpSessie("registratieSid", registratieSid);

            String url = "pages/bevestig_registratie.faces?registratieSid="+registratieSid;
            HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String requestServer = ServerPropertiesHelper.getInstance().getServername();
            String requestScheme = request.getScheme();

            String serverport = ServerPropertiesHelper.getInstance().getServerport();
            String serverportString = "";
            if (serverport != null) {
                serverportString = ":" + serverport;
            }
            String urlNew = requestScheme + "://" + requestServer  + serverportString + FacesUtils.getContextPath() + "/" + url;
            FacesContext.getCurrentInstance().getExternalContext().redirect(urlNew);
        }
    }

    public String getProgress() {
        return progress;
    }

    public String getMenu() {
        menu = ophalenMenu();

        return menu.toString();
    }

    public String getMenuV2() {
        menu = ophalenMenuV2();
        
        return menu.toString();
    }
    private StringBuffer ophalenMenu() {
        StringBuffer menu = new StringBuffer();
        String titel = "";
        List<BsmFuncties> functies = functiesFacade.findHoofdMenu();
        menu.append("<ul>");
        for (BsmFuncties functie : functies) {
            menu.append("<li>");

            if (functie.getType().equals("FOLDER")) {
                titel = functie.getNaamFunctie();

                List<BsmFuncties> subFuncties = functiesFacade.findSubMenu(functie.getFunVolgnr());
                if (subFuncties != null && subFuncties.size() > 0) {
                    menu.append("<a class=\"folder\" href=\"#\">").append(titel);
                    menu.append("</a>");
                } else {
                    menu.append("<a class=\"folder\" href=\"#\">").append(titel);
                    menu.append("</a>");
                }
                menu.append("<ul>");
                for (BsmFuncties subFunctie : subFuncties) {
                    menu.append("<li>");
                    if (subFunctie.getType().equals("INFO")) {
                        BsmTypeInfo typeInfo = typeInfoFacade.find(subFunctie.getTypeInfoVolgnr());
                        if (typeInfo != null) {
                            titel = typeInfo.getOmschrijving();
                        } else {
                            titel = "onbekend " + subFunctie.getTypeInfoVolgnr();
                        }
                        menu.append("<a href=\"").append( FacesUtils.getContextPath()).append("/pages/bsm/infopagina.faces?typeInfo=").append(typeInfo.getVolgnr()).append("\">" + titel);
                        menu.append("</a>");
                } else {
                        if (subFunctie.getType().equals("LINK")) {
                        menu.append("<a href=\"").append(subFunctie.getUrl()).append("\">").append(subFunctie.getNaamFunctie());
                        menu.append("</a>");
                        }
                    }
                    menu.append("</li>");
                }
                menu.append("</ul>");
            } else {
                if (functie.getType().equals("INFO")) {
                    BsmTypeInfo typeInfo = typeInfoFacade.find(functie.getTypeInfoVolgnr());
                    if (typeInfo != null) {
                        titel = typeInfo.getOmschrijving();
                    } else {
                        titel = "onbekend " + functie.getTypeInfoVolgnr();
                    }
                    menu.append("<a href=\"").append( FacesUtils.getContextPath()).append("/pages/bsm/infopagina.faces?typeInfo=").append(typeInfo.getVolgnr()).append("\">" + titel);
                    menu.append("</a>");
                } else {
                    if (functie.getType().equals("LINK")) {
                        menu.append("<a href=\"").append(functie.getUrl()).append("\">").append(functie.getNaamFunctie());
                        menu.append("</a>");
                    } else {
                        menu.append("<a href=\"#\">" + functie.getNaamFunctie());
                        menu.append(" (" + functie.getType() + ")</a>");
                    }
                }
            }
            menu.append("</li>");
        }
        menu.append("</ul>");
        return menu;
    }

    private StringBuffer ophalenMenuV2() {
        boolean overigMenuAanwezig = false;
        StringBuffer menu = new StringBuffer();
        StringBuffer menuOverig = new StringBuffer();
        String titel = "";
        List<BsmFuncties> functies = functiesFacade.findHoofdMenu();
        menu.append("<ul class=\"submenuv2\">");
        for (BsmFuncties functie : functies) {

            if (functie.getType().equals("FOLDER")) {
                menu.append("<li>");
                if (getHomePlateSessionTO().isDutch()) {
                    titel = functie.getNaamFunctie();
                } else {
                    titel = functie.getNaamFunctieEn();
                }

                List<BsmFuncties> subFuncties = functiesFacade.findSubMenu(functie.getFunVolgnr());
                if (subFuncties != null && subFuncties.size() > 0) {
                    menu.append("<label class=\"folder\" href=\"#\">").append(titel);
                    menu.append("</label>");
                } else {
                    menu.append("<label class=\"folder\" href=\"#\">").append(titel);
                    menu.append("</label>");
                }
                menu.append("<ul class='submenuitems'>");
                for (BsmFuncties subFunctie : subFuncties) {
                    menu.append("<li>");
                    if (subFunctie.getType().equals("INFO")) {
                        BsmTypeInfo typeInfo = typeInfoFacade.find(subFunctie.getTypeInfoVolgnr());
                        if (typeInfo != null) {
                            if (getHomePlateSessionTO().isDutch()) {
                                titel = typeInfo.getOmschrijving();
                            } else {
                                titel = typeInfo.getOmschrijvingEn();
                            }
                        } else {
                            titel = "onbekend " + subFunctie.getTypeInfoVolgnr();
                        }
                        menu.append("<span class=\"glyphicon glyphicon-info-sign\"></span>");
                        menu.append("<a href=\"").append( FacesUtils.getContextPath()).append("/pages/infopagina.faces?typeInfo=").append(typeInfo.getVolgnr()).append("\">" + titel);
                        menu.append("</a>");
                } else {
                        if (subFunctie.getType().equals("LINK")) {
                            if (getHomePlateSessionTO().isDutch()) {
                                titel = subFunctie.getNaamFunctie();
                            } else {
                                titel = subFunctie.getNaamFunctieEn();
                            }
                            menu.append("<span class=\"glyphicon glyphicon-info-sign\"></span>");
                            menu.append("<a href=\"").append(subFunctie.getUrl()).append("\">").append(titel);
                            menu.append("</a>");
                        }
                    }
                    menu.append("</li>");
                }
                menu.append("</ul>");
                menu.append("</li>");
            } else {
                if (!overigMenuAanwezig) {
                    if (getHomePlateSessionTO().isDutch()) {
                        titel = "Overig";
                    } else {
                        titel = "Other";
                    }
                    overigMenuAanwezig = true;
                    menuOverig.append("<li>");
                    menuOverig.append("<label class=\"folder\" href=\"#\">").append(titel);
                    menuOverig.append("</label>");
                    menuOverig.append("<ul class='submenuitems'>");
                }
                menuOverig.append("<li>");
                if (functie.getType().equals("INFO")) {
                    BsmTypeInfo typeInfo = typeInfoFacade.find(functie.getTypeInfoVolgnr());
                    if (typeInfo != null) {
                        if (getHomePlateSessionTO().isDutch()) {
                            titel = typeInfo.getOmschrijving();
                        } else {
                            titel = typeInfo.getOmschrijvingEn();
                        }
                    } else {
                        titel = "onbekend " + functie.getTypeInfoVolgnr();
                    }
                    menuOverig.append("<span class=\"glyphicon glyphicon-info-sign\"></span>");
                    menuOverig.append("<a href=\"").append( FacesUtils.getContextPath()).append("/pages/infopagina.faces?typeInfo=").append(typeInfo.getVolgnr()).append("\">" + titel);
                    menuOverig.append("</a>");
                } else {
                    menuOverig.append("<span class=\"glyphicon glyphicon-info-sign\"></span>");
                    if (getHomePlateSessionTO().isDutch()) {
                        titel = functie.getNaamFunctie();
                    } else {
                        titel = functie.getNaamFunctieEn();
                    }
                    if (functie.getType().equals("LINK")) {
                        menuOverig.append("<a href=\"").append(functie.getUrl()).append("\">").append(titel);
                        menuOverig.append("</a>");
                    } else {
                        menuOverig.append("<a href=\"#\">" + titel);
                        menuOverig.append(" (" + functie.getType() + ")</a>");
                    }
                }
                menuOverig.append("</li>");
            }
        }
        menuOverig.append("</li></ul>");
        menu.append(menuOverig);
        menu.append("</ul>");
        menu.append("</div");
        return menu;
    }
 
    public String getGebruikerMenu() {
        gebruikermenu = ophalenGebruikerMenu();

        return gebruikermenu.toString();
    }
    
    private StringBuffer ophalenGebruikerMenu() {
        
        StringBuffer menuString = new StringBuffer();
        String titel = "";
        List<BsmMenu> menus = menuFacade.findParents();

        menuString.append("<ul>");
        for (BsmMenu menu : menus) {
            menuString.append("<li>");

            titel = menu.getOmschrijving();

            menuString.append("<a href=\"#\">").append(titel).append("</a>");

            if (menu.getBsmMenuList() != null && menu.getBsmMenuList().size() > 0) {
                menuString.append("<ul>");
                
                for (BsmMenu subMenu : menu.getBsmMenuList()) {
                    menuString.append("<li>");

                    titel = subMenu.getOmschrijving();
                    menuString.append("<a href=\"").append( FacesUtils.getContextPath()).append(subMenu.getUrl()).append("\">" + titel);
                    menuString.append("</a>");
                    menuString.append("</li>");
                }
                
                menuString.append("</ul>");
            }
            
            menuString.append("</li>");
        }
        menuString.append("</ul>");

        return menuString;
    }
    
    public String getCurrentTabblad() {
        return currentTabblad;
    }

    public void setCurrentTabblad(String currentTabblad) {
        this.currentTabblad = currentTabblad;
    }

    public String getCurrentBeheerTabblad() {
        return currentBeheerTabblad;
    }

    public void setCurrentBeheerTabblad(String currentBeheerTabblad) {
        this.currentBeheerTabblad = currentBeheerTabblad;
    }

    public String getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(String currentMenu) {
        this.currentMenu = currentMenu;
    }

}