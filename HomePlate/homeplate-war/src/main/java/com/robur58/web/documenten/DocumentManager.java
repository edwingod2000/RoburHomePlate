package com.robur58.web.documenten;

import com.robur58.domein.BsmMijnDocuments;
import java.io.File;

public class DocumentManager {

    private DirectoryEntry directoryEntry;

    public DocumentManager() {

    }

    public void createDocument(File bestand, String naam, String type, String omschrijving, BsmMijnDocuments parent) {
        BsmMijnDocuments mijnDocument = new BsmMijnDocuments();
        mijnDocument.setNaam(naam);
        mijnDocument.setDirectory("N");
        mijnDocument.setType(type);
        mijnDocument.setOmschrijving(omschrijving);
        if (parent != null) {
            mijnDocument.setMdsVolgnr(parent);
        }
    }
}
