/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.domein;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gebruiker
 */
@Entity
@Table(name = "INFO")
@NamedQueries({
    @NamedQuery(name = "BsmInfo.findAll", query = "SELECT b FROM BsmInfo b"),
    @NamedQuery(name = "BsmInfo.findByVolgnr", query = "SELECT b FROM BsmInfo b WHERE b.volgnr = :volgnr"),
    @NamedQuery(name = "BsmInfo.findByTitel", query = "SELECT b FROM BsmInfo b WHERE b.titel = :titel"),
    @NamedQuery(name = "BsmInfo.findByTitelEn", query = "SELECT b FROM BsmInfo b WHERE b.titelEn = :titelEn"),
    @NamedQuery(name = "BsmInfo.findByPlaatje", query = "SELECT b FROM BsmInfo b WHERE b.plaatje = :plaatje"),
    @NamedQuery(name = "BsmInfo.findByDatumBericht", query = "SELECT b FROM BsmInfo b WHERE b.datumBericht = :datumBericht"),
    @NamedQuery(name = "BsmInfo.findByEinddatumBericht", query = "SELECT b FROM BsmInfo b WHERE b.einddatumBericht = :einddatumBericht"),
    @NamedQuery(name = "BsmInfo.findByGbrVolgnr", query = "SELECT b FROM BsmInfo b WHERE b.gbrVolgnr = :gbrVolgnr"),
    @NamedQuery(name = "BsmInfo.findByPgaVolgnr", query = "SELECT b FROM BsmInfo b WHERE b.pgaVolgnr = :pgaVolgnr"),
    @NamedQuery(name = "BsmInfo.findByPgaEnVolgnr", query = "SELECT b FROM BsmInfo b WHERE b.pgaEnVolgnr = :pgaEnVolgnr"),
    @NamedQuery(name = "BsmInfo.findByUrl", query = "SELECT b FROM BsmInfo b WHERE b.url = :url"),
    @NamedQuery(name = "BsmInfo.findByUrlOmschrijving", query = "SELECT b FROM BsmInfo b WHERE b.urlOmschrijving = :urlOmschrijving"),
    @NamedQuery(name = "BsmInfo.findByUrlOmschrijvingEn", query = "SELECT b FROM BsmInfo b WHERE b.urlOmschrijvingEn = :urlOmschrijvingEn"),
    @NamedQuery(name = "BsmInfo.findByUrlEn", query = "SELECT b FROM BsmInfo b WHERE b.urlEn = :urlEn"),
    @NamedQuery(name = "BsmInfo.findByDisplayVolgnr", query = "SELECT b FROM BsmInfo b WHERE b.displayVolgnr = :displayVolgnr"),
    @NamedQuery(name = "BsmInfo.findByToonOpHomepage", query = "SELECT b FROM BsmInfo b WHERE b.toonOpHomepage = :toonOpHomepage"),
    @NamedQuery(name = "BsmInfo.findByTypeInfoAndArchief", query = "SELECT b FROM BsmInfo b WHERE b.typeInfo.volgnr = :typeInfoVolgnr and b.archief = :archief ORDER BY b.displayVolgnr, b.datumBericht desc"),
    @NamedQuery(name = "BsmInfo.findByToonOpHomepageAndArchief", query = "SELECT b FROM BsmInfo b WHERE b.toonOpHomepage = :toonOpHomepage AND b.archief = :archief ORDER BY b.datumBericht desc"),
    @NamedQuery(name = "BsmInfo.findByArchief", query = "SELECT b FROM BsmInfo b WHERE b.archief = :archief"),
    @NamedQuery(name = "BsmInfo.findByDatumGewijzigd", query = "SELECT b FROM BsmInfo b WHERE b.datumGewijzigd = :datumGewijzigd"),
    @NamedQuery(name = "BsmInfo.findByGbrVolgnrGewijzigd", query = "SELECT b FROM BsmInfo b WHERE b.gbrVolgnrGewijzigd = :gbrVolgnrGewijzigd"),
    @NamedQuery(name = "BsmInfo.findByAutoArchief", query = "SELECT b FROM BsmInfo b WHERE b.autoArchief = :autoArchief"),
    @NamedQuery(name = "BsmInfo.findByVermeldAuteur", query = "SELECT b FROM BsmInfo b WHERE b.vermeldAuteur = :vermeldAuteur")})
public class BsmInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="InfoSequence")
    @SequenceGenerator(name="InfoSequence", sequenceName="INFO_SEQ", allocationSize=1)    
    @Basic(optional = false)
    @Column(name = "VOLGNR")
    private Long volgnr;
    @Column(name = "TITEL")
    private String titel;
    @Column(name = "TITEL_EN")
    private String titelEn;
    @Lob
    @Column(name = "BERICHT")
    private String bericht;
    @Lob
    @Column(name = "BERICHT_EN")
    private String berichtEn;
    @Column(name = "PLAATJE")
    private String plaatje;
    @Column(name = "DATUM_BERICHT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumBericht;
    @Column(name = "EINDDATUM_BERICHT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date einddatumBericht;
    @Column(name = "GBR_VOLGNR")
    private Long gbrVolgnr;
    @Column(name = "PGA_VOLGNR")
    private Long pgaVolgnr;
    @Column(name = "PGA_EN_VOLGNR")
    private Long pgaEnVolgnr;
    @Column(name = "URL")
    private String url;
    @Column(name = "URL_OMSCHRIJVING")
    private String urlOmschrijving;
    @Column(name = "URL_OMSCHRIJVING_EN")
    private String urlOmschrijvingEn;
    @Column(name = "URL_EN")
    private String urlEn;
    @Column(name = "DISPLAY_VOLGNR")
    private Long displayVolgnr;
    @Column(name = "TOON_OP_HOMEPAGE")
    private String toonOpHomepage;
    @Column(name = "ARCHIEF")
    private String archief;
    @Column(name = "DATUM_GEWIJZIGD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumGewijzigd;
    @Column(name = "GBR_VOLGNR_GEWIJZIGD")
    private Long gbrVolgnrGewijzigd;
    @Column(name = "AUTO_ARCHIEF")
    private String autoArchief;
    @Column(name = "VERMELD_AUTEUR")
    private String vermeldAuteur;
    @Lob
    @Column(name = "INLEIDING")
    private String inleiding;
    @Lob
    @Column(name = "INLEIDING_EN")
    private String inleidingEn;
    @JoinColumn(name = "TYPE_INFO", referencedColumnName = "VOLGNR")
    @ManyToOne
    private BsmTypeInfo typeInfo;

    public BsmInfo() {
    }

    public BsmInfo(Long volgnr) {
        this.volgnr = volgnr;
    }

    public Long getVolgnr() {
        return volgnr;
    }

    public void setVolgnr(Long volgnr) {
        this.volgnr = volgnr;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getTitelEn() {
        return titelEn;
    }

    public void setTitelEn(String titelEn) {
        this.titelEn = titelEn;
    }

    public String getBericht() {
        return bericht;
    }

    public void setBericht(String bericht) {
        this.bericht = bericht;
    }

    public String getBerichtEn() {
        return berichtEn;
    }

    public void setBerichtEn(String berichtEn) {
        this.berichtEn = berichtEn;
    }

    public String getPlaatje() {
        return plaatje;
    }

    public void setPlaatje(String plaatje) {
        this.plaatje = plaatje;
    }

    public Date getDatumBericht() {
        return datumBericht;
    }

    public void setDatumBericht(Date datumBericht) {
        this.datumBericht = datumBericht;
    }

    public Date getEinddatumBericht() {
        return einddatumBericht;
    }

    public void setEinddatumBericht(Date einddatumBericht) {
        this.einddatumBericht = einddatumBericht;
    }

    public Long getGbrVolgnr() {
        return gbrVolgnr;
    }

    public void setGbrVolgnr(Long gbrVolgnr) {
        this.gbrVolgnr = gbrVolgnr;
    }

    public Long getPgaVolgnr() {
        return pgaVolgnr;
    }

    public void setPgaVolgnr(Long pgaVolgnr) {
        this.pgaVolgnr = pgaVolgnr;
    }

    public Long getPgaEnVolgnr() {
        return pgaEnVolgnr;
    }

    public void setPgaEnVolgnr(Long pgaEnVolgnr) {
        this.pgaEnVolgnr = pgaEnVolgnr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlOmschrijving() {
        return urlOmschrijving;
    }

    public void setUrlOmschrijving(String urlOmschrijving) {
        this.urlOmschrijving = urlOmschrijving;
    }

    public String getUrlOmschrijvingEn() {
        return urlOmschrijvingEn;
    }

    public void setUrlOmschrijvingEn(String urlOmschrijvingEn) {
        this.urlOmschrijvingEn = urlOmschrijvingEn;
    }

    public String getUrlEn() {
        return urlEn;
    }

    public void setUrlEn(String urlEn) {
        this.urlEn = urlEn;
    }

    public Long getDisplayVolgnr() {
        return displayVolgnr;
    }

    public void setDisplayVolgnr(Long displayVolgnr) {
        this.displayVolgnr = displayVolgnr;
    }

    public String getToonOpHomepage() {
        return toonOpHomepage;
    }

    public void setToonOpHomepage(String toonOpHomepage) {
        this.toonOpHomepage = toonOpHomepage;
    }

    public String getArchief() {
        return archief;
    }

    public void setArchief(String archief) {
        this.archief = archief;
    }

    public Date getDatumGewijzigd() {
        return datumGewijzigd;
    }

    public void setDatumGewijzigd(Date datumGewijzigd) {
        this.datumGewijzigd = datumGewijzigd;
    }

    public Long getGbrVolgnrGewijzigd() {
        return gbrVolgnrGewijzigd;
    }

    public void setGbrVolgnrGewijzigd(Long gbrVolgnrGewijzigd) {
        this.gbrVolgnrGewijzigd = gbrVolgnrGewijzigd;
    }

    public String getAutoArchief() {
        return autoArchief;
    }

    public void setAutoArchief(String autoArchief) {
        this.autoArchief = autoArchief;
    }

    public String getVermeldAuteur() {
        return vermeldAuteur;
    }

    public void setVermeldAuteur(String vermeldAuteur) {
        this.vermeldAuteur = vermeldAuteur;
    }

    public String getInleiding() {
        return inleiding;
    }

    public void setInleiding(String inleiding) {
        this.inleiding = inleiding;
    }

    public String getInleidingEn() {
        return inleidingEn;
    }

    public void setInleidingEn(String inleidingEn) {
        this.inleidingEn = inleidingEn;
    }

    public BsmTypeInfo getTypeInfo() {
        return typeInfo;
    }

    public void setTypeInfo(BsmTypeInfo typeInfo) {
        this.typeInfo = typeInfo;
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
        if (!(object instanceof BsmInfo)) {
            return false;
        }
        BsmInfo other = (BsmInfo) object;
        if ((this.volgnr == null && other.volgnr != null) || (this.volgnr != null && !this.volgnr.equals(other.volgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmInfo[volgnr=" + volgnr + "]";
    }

}
