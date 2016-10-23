package com.robur58.web.controller;

import com.robur58.business.BsmMijnDocumentsFacadeLocal;
import com.robur58.domein.BsmMijnDocuments;
import com.robur58.domein.GebruikerStatus;
import com.robur58.web.common.ServerPropertiesHelper;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import nl.profict.platform.web.util.FacesUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name="documentController")
@SessionScoped
public class DocumentController extends Controller {

    private static final int BUFFER_SIZE = 6124;

    @EJB
    private BsmMijnDocumentsFacadeLocal mijnDocumentFacade;
    private List<BsmMijnDocuments> documenten = new ArrayList<BsmMijnDocuments>();

    private BsmMijnDocuments uploadedDocument;

    private BsmMijnDocuments selectedFolder;
    private List<BsmMijnDocuments> folderPath;
    
    private BsmMijnDocuments selectedBestand;
    
    private String nieuweMap;
    private String nieuweMapOmschrijving;
    private boolean subFolder = true;
    private boolean wijzigMode;
    
    public DocumentController() {
    }

    public void preRender() {
        if (selectedFolder == null) {
            List<BsmMijnDocuments> rootFolders = ophalenRootFolders();
            if (rootFolders != null && rootFolders.size() > 0) {
                selectedFolder = rootFolders.get(0);
                maakFolderPath(selectedFolder);
            }
        }
    }
    
    public String getFileTypeImage(String mimeType) {
        if (mimeType.equals("application/pdf")){
            return ServerPropertiesHelper.getInstance().getFileImgTypePdf();
        } else if (mimeType.equals("application/msword")){
            return ServerPropertiesHelper.getInstance().getFileImgTypeDoc();
        } else if (mimeType.equals("application/xls") || mimeType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return ServerPropertiesHelper.getInstance().getFileImgTypeXls();
        } else if (mimeType.equals("application/ppt")){
            return ServerPropertiesHelper.getInstance().getFileImgTypePpt();
        } else if (mimeType.equals("application/gif")){
            return ServerPropertiesHelper.getInstance().getFileImgTypeGif();
        } else if (mimeType.equals("application/html")){
            return ServerPropertiesHelper.getInstance().getFileImgTypeHtml();
        } else if (mimeType.equals("image/png")){
            return ServerPropertiesHelper.getInstance().getFileImgTypePng();
        } else if (mimeType.equals("image/jpeg")){
            return ServerPropertiesHelper.getInstance().getFileImgTypeJpg();
        } else if (mimeType.equals("image/gif")){
            return ServerPropertiesHelper.getInstance().getFileImgTypeGif();
        } else if (mimeType.equals("application/octet-stream")) {
            return ServerPropertiesHelper.getInstance().getFileImgTypeDoc();
        } else if (mimeType.equals("folder_close")){
            return ServerPropertiesHelper.getInstance().getFileImgFolderClose();
        } else if (mimeType.equals("folder_open")){
            return ServerPropertiesHelper.getInstance().getFileImgFolderOpen();
        } else {
            return ServerPropertiesHelper.getInstance().getFileImgTypeUnknown();
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        String documentPath = ServerPropertiesHelper.getInstance().getDocumentPath();

        String fileName = generateRandomString() + "-" + event.getFile().getFileName();
        File result = new File(documentPath + fileName);
        
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(result);

            byte[] buffer = new byte[BUFFER_SIZE];

            int bulk;
            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();

            FacesMessage msg = new FacesMessage("Geslaagd!",
                    event.getFile().getFileName() + " is ge-upload.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            mijnDocumentFacade.createDocument(event.getFile().getFileName(), fileName, event.getFile().getContentType(), null, selectedFolder, getSecurityController().getGebruiker(), new Date(), false);
            
        } catch (IOException e) {
            e.printStackTrace();

            FacesMessage error = new FacesMessage("Niet geslaag!", "Het uploaden is fout gegaan.");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }
    }
    
    public StreamedContent handleFileDownload(BsmMijnDocuments bestand) {
        String documentPath = ServerPropertiesHelper.getInstance().getDocumentPath();

        String fileName = bestand.getOpslagnaam();
        InputStream stream = null;
        try {
            stream = new BufferedInputStream( new FileInputStream(documentPath + fileName));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        StreamedContent file = new DefaultStreamedContent(stream, bestand.getType(), bestand.getNaam());
        return file;
    }
    
    public String selectFolder(BsmMijnDocuments folder) {
        this.selectedFolder = folder;
        maakFolderPath(folder);
        
        return null;
    }
    
    public String deselectFolder() {
        this.selectedFolder = null;
        
        folderPath = new ArrayList<BsmMijnDocuments>();
        
        return null;
    }
    
    public String selectBestand(BsmMijnDocuments bestand) {
        this.selectedBestand = bestand;
        
        return null;
    }

    /**
     * Methode die zorgt dat de div de juiste class krijgt zodat met jquery gezorgd wordt dat een folder getoond of niet getoond wordt
     * @param folder
     * @return 
     */
    public boolean toonFolderOpen(BsmMijnDocuments folder) {
        if (selectedFolder != null) {
            if (selectedFolder.getVolgnr() == folder.getVolgnr() ) {
                return true;
            } else if (folder.getMdsVolgnr() != null && (selectedFolder.getVolgnr() == folder.getMdsVolgnr().getVolgnr())) {
                return true;
            } else if (folder.getMdsVolgnr() != null && selectedFolder.getMdsVolgnr() != null && (folder.getMdsVolgnr().getVolgnr() == selectedFolder.getMdsVolgnr().getVolgnr())) {
                return true;
            } else if (selectedFolder.getMdsVolgnr() != null && selectedFolder.getMdsVolgnr().getVolgnr() == folder.getVolgnr()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
    public boolean toonImageOpenFolder(BsmMijnDocuments folder) {
        if (selectedFolder.getVolgnr() == folder.getVolgnr() ) {
            return true;
        } else if (selectedFolder.getMdsVolgnr() != null && selectedFolder.getMdsVolgnr().getVolgnr() == folder.getVolgnr()) {
            return true;
        } else {
            return false;
        }
    }
    
    private void maakFolderPath(BsmMijnDocuments folder) {
        folderPath = new ArrayList<BsmMijnDocuments>();
        doorloopPath(folder);
    }
    
    private void doorloopPath(BsmMijnDocuments folder) {
        if (folder.getMdsVolgnr() != null) {
            doorloopPath(folder.getMdsVolgnr());
        }
        folderPath.add(folder);
    }

    public List<BsmMijnDocuments> ophalenRootFolders() {
        return mijnDocumentFacade.findDirectoriesByMdsVolgnr(null, !wijzigMode);
    }
    
    public List<BsmMijnDocuments> ophalenSubFolders(Long parent) {
        return mijnDocumentFacade.findDirectoriesByMdsVolgnr(parent, !wijzigMode);
    }
    
    public String bewaarFolder() {
        if (!StringUtils.isEmpty(nieuweMap)) {
            if (subFolder) {
                mijnDocumentFacade.createFolder(nieuweMap, nieuweMapOmschrijving, selectedFolder, getSecurityController().getGebruiker(), new Date(), false);
            } else {
                mijnDocumentFacade.createFolder(nieuweMap, nieuweMapOmschrijving, null, getSecurityController().getGebruiker(), new Date(), false);
            }
        }

        FacesMessage msg = new FacesMessage("Geslaagd!", "Map " + nieuweMap + " is aangemaakt.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        nieuweMap = null;
        subFolder = true;
        
        return null;
    }
    
    public boolean getMagDocumentenZien() {
        return magDocumentenZien();
    }
    public boolean magDocumentenZien() {
        boolean result = false;
        if (getSecurityController() == null) {
            result = false;
        } else {
            if (getSecurityController().getGebruiker() != null && getSecurityController().getGebruiker().getStatus() != null) {
                if (getSecurityController().getGebruiker().getStatus().equals(GebruikerStatus.OK)) {
                    result = true;
                }
            } else {
                result = false;
            }
        }
        return result;
    }
    
//    public String getDownloadLink(BsmMijnDocuments bestand) {
//        // evt. controle of het mag
//        String downloadLink = "javascript:void()";
////        if (getSecurityController().getGebruiker().getStatus().equals(GebruikerStatus.OK)) {
//            String documentPath = ServerPropertiesHelper.getInstance().getDocumentPath();
//
//            if (bestand != null && !StringUtils.isEmpty(bestand.getOpslagnaam())) {
//                downloadLink = documentPath + "/" + bestand.getOpslagnaam();
//            }
////        }
//        return downloadLink;
//    }
    
    public String gaNaarWijzigMode(ActionEvent actionEvent) {
        this.wijzigMode = true;
        return null;
    }
    
    public String gaNaarNietWijzigMode(ActionEvent actionEvent) {
        this.wijzigMode = false;
        return null;
    }
    
    public String bewaarBestandProperties() {
        mijnDocumentFacade.edit(selectedBestand);
        
        FacesMessage msg = new FacesMessage("Geslaagd!", "Bestand " + selectedBestand.getNaam() + " is gewijzigd.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        return null;
    }

    public String bewaarFolderProperties() throws IOException {
        mijnDocumentFacade.edit(selectedFolder);
        
        FacesMessage msg = new FacesMessage("Geslaagd!", "Map " + selectedFolder.getNaam() + " is gewijzigd.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        FacesContext.getCurrentInstance().getExternalContext().redirect(FacesUtils.getContextPath() + "/pages/documenten.faces");
        return null;
    }
    
    public String verwijderBestand() {
        mijnDocumentFacade.remove(selectedBestand);
        this.selectedBestand = null;
        
        FacesMessage msg = new FacesMessage("Geslaagd!", "Bestand is verwijderd.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        return null;
    }
    
    public String verwijderFolder() {
        mijnDocumentFacade.remove(selectedFolder);
        this.selectedFolder = null;
        
        FacesMessage msg = new FacesMessage("Geslaagd!", "Map is verwijderd.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        return null;
    }
    
    public List<BsmMijnDocuments> ophalenSubFiles(Long parent) {
        return mijnDocumentFacade.findFilesByMdsVolgnr(parent, !wijzigMode);
    }

    private String generateRandomString() {
        Calendar cal = Calendar.getInstance();
        return "" + cal.getTimeInMillis();
    }
        
    public BsmMijnDocuments getUploadedDocument() {
        return uploadedDocument;
    }

    public void setUploadedDocument(BsmMijnDocuments uploadedDocument) {
        this.uploadedDocument = uploadedDocument;
    }

    public List<BsmMijnDocuments> getDocumenten() {
        return documenten;
    }

    public void setDocumenten(List<BsmMijnDocuments> documenten) {
        this.documenten = documenten;
    }

    public BsmMijnDocuments getSelectedFolder() {
        return selectedFolder;
    }

    public void setSelectedFolder(BsmMijnDocuments selectedFolder) {
        this.selectedFolder = selectedFolder;
    }

    public List<BsmMijnDocuments> getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(List<BsmMijnDocuments> folderPath) {
        this.folderPath = folderPath;
    }

    public String getNieuweMap() {
        return nieuweMap;
    }

    public void setNieuweMap(String nieuweMap) {
        this.nieuweMap = nieuweMap;
    }

    public String getNieuweMapOmschrijving() {
        return nieuweMapOmschrijving;
    }

    public void setNieuweMapOmschrijving(String nieuweMapOmschrijving) {
        this.nieuweMapOmschrijving = nieuweMapOmschrijving;
    }

    public boolean isSubFolder() {
        return subFolder;
    }

    public void setSubFolder(boolean subFolder) {
        this.subFolder = subFolder;
    }

    public BsmMijnDocuments getSelectedBestand() {
        return selectedBestand;
    }

    public void setSelectedBestand(BsmMijnDocuments selectedBestand) {
        this.selectedBestand = selectedBestand;
    }

    public boolean isWijzigMode() {
        return wijzigMode;
    }

    public void setWijzigMode(boolean wijzigMode) {
        this.wijzigMode = wijzigMode;
    }

}
