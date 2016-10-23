package com.robur58.web.documenten;

import java.io.File;
import java.util.List;

public class DirectoryEntry {
    
    private String naam;
    private boolean directory;
    private File bestand;
    List<DirectoryEntry> entries;
    
    public DirectoryEntry() {
        
    }

    public File getBestand() {
        return bestand;
    }

    public void setBestand(File bestand) {
        this.bestand = bestand;
    }

    public boolean isDirectory() {
        return directory;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    public List<DirectoryEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<DirectoryEntry> entries) {
        this.entries = entries;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
    
}
