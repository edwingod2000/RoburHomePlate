package com.robur58.web.controller;

import com.robur58.business.BsmDeelnemersFacadeLocal;
import com.robur58.business.BsmDocumentFacadeLocal;
import com.robur58.business.BsmDocumentTypeFacadeLocal;
import com.robur58.domein.BsmDocument;
import com.robur58.domein.BsmDocumentType;
import java.io.Serializable;
import java.sql.Blob;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.event.FlowEvent;

@ManagedBean
@ViewScoped
public class ImageController extends Controller implements Serializable {

    Logger logger = Logger.getLogger(ImageController.class.getName());
    @EJB
    private BsmDocumentTypeFacadeLocal documentTypeFacade;
    @EJB
    private BsmDocumentFacadeLocal documentFacade;
    private Long dceVolgnr;
    private List<BsmDocumentType> documentTypes;
    private List<BsmDocument> documenten;
    private boolean skip;

    public ImageController() {
    }

    @PostConstruct
    public void postConstruct() {
        documentTypes = retreiveDocumentTypes();
        dceVolgnr = documentTypes.get(0).getDceVolgnr();
    }
    
    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back  
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public List<BsmDocumentType> getDocumentTypes() {
        if (documentTypes == null) {
            documentTypes = retreiveDocumentTypes();
        }
        return documentTypes;
    }

    private List<BsmDocumentType> retreiveDocumentTypes() {
        return documentTypeFacade.findAll();
    }

    public void setDocumentTypes(List<BsmDocumentType> documentTypes) {
        this.documentTypes = documentTypes;
    }

    private List<BsmDocument> retreiveDocumenten(long dceVolgnr) {
        return documentFacade.findByDceVolgnr(dceVolgnr);
    }

    private void dceVolgnrValueChange(ValueChangeEvent event) {
        System.out.println("Test");
    }

    public Long getDceVolgnr() {
        return dceVolgnr;
    }

    public void setDceVolgnr(Long dceVolgnr) {
        this.dceVolgnr = dceVolgnr;
    }

    public void updateImages() {
        this.documenten = retreiveDocumenten(this.dceVolgnr);
    }

    public List<BsmDocument> getDocumenten() {
        if (documenten == null) {
            if (getDocumentTypes() != null && getDocumentTypes().get(0) != null) {
                dceVolgnr = getDocumentTypes().get(0).getDceVolgnr();
            }
            documenten = retreiveDocumenten(dceVolgnr);
        }
        return documenten;
    }

    public void setDocumenten(List<BsmDocument> documenten) {
        this.documenten = documenten;
    }
   
}
