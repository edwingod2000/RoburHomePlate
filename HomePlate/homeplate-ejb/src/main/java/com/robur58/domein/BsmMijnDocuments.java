/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.domein;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author godefrooije
 */
@Entity
@Table(name = "BSM_MIJN_DOCUMENTS")
@NamedQueries({
    @NamedQuery(name = "BsmMijnDocuments.findAll", query = "SELECT b FROM BsmMijnDocuments b"),
    @NamedQuery(name = "BsmMijnDocuments.findByVolgnr", query = "SELECT b FROM BsmMijnDocuments b WHERE b.volgnr = :volgnr"),
    @NamedQuery(name = "BsmMijnDocuments.findByNaam", query = "SELECT b FROM BsmMijnDocuments b WHERE b.naam = :naam"),
    @NamedQuery(name = "BsmMijnDocuments.findByDirectory", query = "SELECT b FROM BsmMijnDocuments b WHERE b.directory = :directory"),
    @NamedQuery(name = "BsmMijnDocuments.findByDirectoryPublished", query = "SELECT b FROM BsmMijnDocuments b WHERE b.directory = :directory AND b.published = 'J'"),
    @NamedQuery(name = "BsmMijnDocuments.findRootDirectories", query = "SELECT b FROM BsmMijnDocuments b WHERE b.directory = 'J' AND b.mdsVolgnr IS NULL ORDER BY b.naam"),
    @NamedQuery(name = "BsmMijnDocuments.findRootDirectoriesPublished", query = "SELECT b FROM BsmMijnDocuments b WHERE b.directory = 'J' AND b.mdsVolgnr IS NULL AND b.published = 'J' ORDER BY b.naam"),
    @NamedQuery(name = "BsmMijnDocuments.findDirectoriesByMdsVolgnr", query = "SELECT b FROM BsmMijnDocuments b WHERE b.directory = 'J' AND b.mdsVolgnr IS NOT NULL AND b.mdsVolgnr.volgnr = :mdsvolgnr ORDER BY b.naam"),
    @NamedQuery(name = "BsmMijnDocuments.findDirectoriesByMdsVolgnrPublished", query = "SELECT b FROM BsmMijnDocuments b WHERE b.directory = 'J' AND b.mdsVolgnr IS NOT NULL AND b.mdsVolgnr.volgnr = :mdsvolgnr AND b.published = 'J' ORDER BY b.naam"),
    @NamedQuery(name = "BsmMijnDocuments.findFilesNoMdsVolgnr", query = "SELECT b FROM BsmMijnDocuments b WHERE b.directory = 'N' AND b.mdsVolgnr IS NULL ORDER BY b.naam"),
    @NamedQuery(name = "BsmMijnDocuments.findFilesNoMdsVolgnrPublished", query = "SELECT b FROM BsmMijnDocuments b WHERE b.directory = 'N' AND b.mdsVolgnr IS NULL AND b.published = 'J' ORDER BY b.naam"),
    @NamedQuery(name = "BsmMijnDocuments.findFilesByMdsVolgnr", query = "SELECT b FROM BsmMijnDocuments b WHERE b.directory = 'N' AND b.mdsVolgnr IS NOT NULL AND b.mdsVolgnr.volgnr = :mdsvolgnr ORDER BY b.naam"),
    @NamedQuery(name = "BsmMijnDocuments.findFilesByMdsVolgnrPublished", query = "SELECT b FROM BsmMijnDocuments b WHERE b.directory = 'N' AND b.mdsVolgnr IS NOT NULL AND b.mdsVolgnr.volgnr = :mdsvolgnr AND b.published = 'J' ORDER BY b.naam"),
    @NamedQuery(name = "BsmMijnDocuments.findByOmschrijving", query = "SELECT b FROM BsmMijnDocuments b WHERE b.omschrijving = :omschrijving"),
    @NamedQuery(name = "BsmMijnDocuments.findByType", query = "SELECT b FROM BsmMijnDocuments b WHERE b.type = :type"),
    @NamedQuery(name = "BsmMijnDocuments.findByOpslagnaam", query = "SELECT b FROM BsmMijnDocuments b WHERE b.opslagnaam = :opslagnaam")})
public class BsmMijnDocuments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BsmMijnDocumentsSequence")
    @SequenceGenerator(name="BsmMijnDocumentsSequence", sequenceName="MDS_SEQ", allocationSize=1)        
    @Basic(optional = false)
    @NotNull
    @Column(name = "VOLGNR")
    private Long volgnr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "NAAM")
    private String naam;
    @Size(max = 1)
    @Column(name = "DIRECTORY")
    private String directory;
    @Size(max = 2000)
    @Column(name = "OMSCHRIJVING")
    private String omschrijving;
    @Size(max = 500)
    @Column(name = "TYPE")
    private String type;
    @Size(max = 1000)
    @Column(name = "OPSLAGNAAM")
    private String opslagnaam;
    @Column(name = "DATUM_AANGEMAAKT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumAangemaakt;
    @Column(name = "PUBLISHED")
    private String published;
    @JoinColumn(name = "GBR_VOLGNR", referencedColumnName = "VOLGNR")
    @ManyToOne
    private BsmGebruikers gbrVolgnr;
    @OneToMany(mappedBy = "mdsVolgnr", orphanRemoval=true)
    private List<BsmMijnDocuments> bsmMijnDocumentsList;
    @JoinColumn(name = "MDS_VOLGNR", referencedColumnName = "VOLGNR")
    @ManyToOne
    private BsmMijnDocuments mdsVolgnr;

    public BsmMijnDocuments() {
    }

    public BsmMijnDocuments(Long volgnr) {
        this.volgnr = volgnr;
    }

    public BsmMijnDocuments(Long volgnr, String naam) {
        this.volgnr = volgnr;
        this.naam = naam;
    }

    public Long getVolgnr() {
        return volgnr;
    }

    public void setVolgnr(Long volgnr) {
        this.volgnr = volgnr;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOpslagnaam() {
        return opslagnaam;
    }

    public void setOpslagnaam(String opslagnaam) {
        this.opslagnaam = opslagnaam;
    }

    public List<BsmMijnDocuments> getBsmMijnDocumentsList() {
        return bsmMijnDocumentsList;
    }

    public void setBsmMijnDocumentsList(List<BsmMijnDocuments> bsmMijnDocumentsList) {
        this.bsmMijnDocumentsList = bsmMijnDocumentsList;
    }

    public BsmMijnDocuments getMdsVolgnr() {
        return mdsVolgnr;
    }

    public void setMdsVolgnr(BsmMijnDocuments mdsVolgnr) {
        this.mdsVolgnr = mdsVolgnr;
    }

    public BsmGebruikers getGbrVolgnr() {
        return gbrVolgnr;
    }

    public void setGbrVolgnr(BsmGebruikers gbrVolgnr) {
        this.gbrVolgnr = gbrVolgnr;
    }

    public Date getDatumAangemaakt() {
        return datumAangemaakt;
    }

    public void setDatumAangemaakt(Date datumAangemaakt) {
        this.datumAangemaakt = datumAangemaakt;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public void setGepubliceerd(boolean published) {
        if (published) {
            this.published = "J";
        } else {
            this.published = "N";
        }
    }
    
    public boolean isGepubliceerd() {
        if (published != null && published.equals("J")) {
            return true;
        } else {
            return false;
        }
    }

    public void setFolder(boolean folder) {
        if (folder) {
            directory = "J";
        } else {
            directory = "N";
        }
    }
    
    public boolean isFolder() {
        if (directory != null && directory.equals("J")) {
            return true;
        } else {
            return false;
        }
    }
    
    public int getSubFolderSize() {
        if (bsmMijnDocumentsList == null) {
            return 0;
        } else {
            return bsmMijnDocumentsList.size();
        }
    }
     
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (volgnr != null ? volgnr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BsmMijnDocuments)) {
            return false;
        }
        BsmMijnDocuments other = (BsmMijnDocuments) object;
        if ((this.volgnr == null && other.volgnr != null) || (this.volgnr != null && !this.volgnr.equals(other.volgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmMijnDocuments[ volgnr=" + volgnr + " ]";
    }
    
}
