package com.robur58.web.controller;

import com.robur58.business.BsmInfoFacadeLocal;
import com.robur58.business.BsmInfoPaginasFacadeLocal;
import com.robur58.business.BsmTypeInfoFacadeLocal;
import com.robur58.domein.BsmInfo;
import com.robur58.domein.BsmInfoPaginas;
import com.robur58.domein.BsmTypeInfo;
import com.robur58.web.view.InfoLijst;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import nl.profict.platform.web.util.FacesUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Gebruiker
 */
@ManagedBean(name="infoController")
@ViewScoped
public class InfoController extends Controller {

    private InfoLijst homepageInfo;
    private InfoLijst info;
    private String infoTitel;
    private BsmInfoPaginas infoPagina;
    private BsmInfo infoItem;
    
    @EJB
    private BsmInfoFacadeLocal infoFacade;

    @EJB
    private BsmTypeInfoFacadeLocal typeInfoFacade;
    
    @EJB
    private BsmInfoPaginasFacadeLocal infoPaginaFacade;
    
    /** Creates a new instance of InfoController */
    public InfoController() {
    }

    public void executeOphalenHomepageInfo() {
//        getHomePlateSessionTO().setShowContextWedstrijdenActueel(true);
        List<BsmInfo> lijst = infoFacade.findByToonOpHomepageAndArchief("Y","N");
        filterInfoLijst(lijst);
        homepageInfo = new InfoLijst(lijst);
    }

    public void executeOphalenInfoItem(String infoItemVolgnr) {
        if (infoItemVolgnr != null && !infoItemVolgnr.equals("")) {
            infoItem = infoFacade.findByVolgnr(Long.parseLong(infoItemVolgnr));
        }
    }
    
    public void executeOphalenInfo(String typeInfoVolgnr) {
        if (!StringUtils.isEmpty(typeInfoVolgnr)) {
            // Ophalen titel
            BsmTypeInfo typeInfo = typeInfoFacade.findByVolgnr(Long.valueOf(typeInfoVolgnr));

            if (getHomePlateSessionTO().isDutch()) {
                infoTitel = typeInfo.getOmschrijving();
            } else {
                infoTitel = typeInfo.getOmschrijvingEn();
            }

            // Ophalen informatie
            getHomePlateSessionTO().setShowContextWedstrijdenActueel(false);
            if (typeInfoVolgnr != null && !typeInfoVolgnr.equals("")) {
                info = new InfoLijst( infoFacade.findByTypeInfoAndArchief(Long.valueOf(typeInfoVolgnr), "N") );
            } else {
                info = new InfoLijst( new ArrayList<BsmInfo>() );
            }
            filterInfoLijst(info.getInfoLijst());
        }
    }

    public void ophalenInfoPagina(String volgnr) {
        if (!StringUtils.isEmpty(volgnr)) {
          infoPagina = infoPaginaFacade.findByVolgnr(Long.valueOf(volgnr));
          if (infoPagina != null && !StringUtils.isEmpty(infoPagina.getInhoud())) {
              infoPagina.setInhoud(filterString(infoPagina.getInhoud()));
          }
        } else {
            infoPagina = null;
        }
        
    }
    
    public void deleteInfo(String infoVolgnr) throws IOException {
        if (getHomePlateSessionTO().getEditInfoMode() && magWijzigen()) {
            BsmInfo infoToDelete = infoFacade.findByVolgnr(Long.parseLong(infoVolgnr));
            if (infoToDelete != null) {
                infoFacade.remove(infoItem);
                FacesMessage msg = new FacesMessage("Geslaagd!", "Info is verwijderd.");
            }
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect(FacesUtils.getContextPath() + "/pages/infopagina.faces?typeInfo="+infoVolgnr);
    }
    
    public void saveInfo() throws IOException {
        if (getHomePlateSessionTO().getEditInfoMode() && magWijzigen()) {
            infoFacade.edit(infoItem);
            FacesMessage msg = new FacesMessage("Geslaagd!", "Info is gewijzigd.");
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect(FacesUtils.getContextPath() + "/pages/infopagina.faces?typeInfo="+infoItem.getTypeInfo().getVolgnr());
    }
    
    public String extraPaginaTitel(String infoVolgnr) {
        if (!StringUtils.isEmpty(infoVolgnr)) {
            BsmInfo info = infoFacade.findByVolgnr(Long.parseLong(infoVolgnr));
            if (getHomePlateSessionTO().isDutch()) {
                return "Extra info: " + info.getTitel();
            } else {
                return "Extra info: " + info.getTitelEn();
            }
        } else {
            return "";
        }
    }
    
    public String extraPaginaTerugUrl(String infoVolgnr) {
        if (!StringUtils.isEmpty(infoVolgnr)) {
            BsmInfo info = infoFacade.findByVolgnr(Long.parseLong(infoVolgnr));
            return getRequest().getContextPath() + "/pages/infopagina.faces?typeInfo="+info.getTypeInfo().getVolgnr();
        } else {
            return "javascript:void(0)";
        }
    }
    
    public String extraPaginaTerugTekst(String infoVolgnr) {
        if (!StringUtils.isEmpty(infoVolgnr)) {
            BsmInfo info = infoFacade.findByVolgnr(Long.parseLong(infoVolgnr));
            if (this.getHomePlateSessionTO().isDutch()) {
                return "Terug naar " + info.getTypeInfo().getOmschrijving();
            } else {
                return "Back to " + info.getTypeInfo().getOmschrijvingEn();
            }
        } else {
            return "";
        }
    }
    
    public void startEditMode() {
        this.getHomePlateSessionTO().setEditInfoMode(true);
    }
    
    public void stopEditMode() {
        this.getHomePlateSessionTO().setEditInfoMode(false);
    }
    
    public InfoLijst getHomepageInfo() {
        return homepageInfo;
    }

    public void setHomepageInfo(InfoLijst homepageInfo) {
        this.homepageInfo = homepageInfo;
    }

    public InfoLijst getInfo() {
        return info;
    }

    public void setInfo(InfoLijst info) {
        this.info = info;
    }

    public String getInfoTitel() {
        if (infoTitel != null) {
            if (infoTitel.indexOf("Info") < 0) {
                return infoTitel + " Info";
            } else {
                return infoTitel;
            }
        }
        return "";
    }

    public void setInfoTitel(String infoTitel) {
        this.infoTitel = infoTitel;
    }

    public BsmInfoPaginas getInfoPagina() {
        return infoPagina;
    }

    public void setInfoPagina(BsmInfoPaginas infoPagina) {
        this.infoPagina = infoPagina;
    }

    public BsmInfo getInfoItem() {
        return infoItem;
    }

    public void setInfoItem(BsmInfo infoItem) {
        this.infoItem = infoItem;
    }

}
