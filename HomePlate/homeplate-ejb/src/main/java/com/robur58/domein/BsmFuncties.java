package com.robur58.domein;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BSM_FUNCTIES")
@NamedQueries({
    @NamedQuery(name = "BsmFuncties.findAll", query = "SELECT b FROM BsmFuncties b"),
    @NamedQuery(name = "BsmFuncties.findByFunVolgnr", query = "SELECT b FROM BsmFuncties b WHERE b.funVolgnr = :funVolgnr"),
    @NamedQuery(name = "BsmFuncties.findByNaamFunctie", query = "SELECT b FROM BsmFuncties b WHERE b.naamFunctie = :naamFunctie"),
    @NamedQuery(name = "BsmFuncties.findByOmschrijvingFunctie", query = "SELECT b FROM BsmFuncties b WHERE b.omschrijvingFunctie = :omschrijvingFunctie"),
    @NamedQuery(name = "BsmFuncties.findByNivoMenu", query = "SELECT b FROM BsmFuncties b WHERE b.nivoMenu = :nivoMenu"),
    @NamedQuery(name = "BsmFuncties.findByUrl", query = "SELECT b FROM BsmFuncties b WHERE b.url = :url"),
    @NamedQuery(name = "BsmFuncties.findByFunVolgnrFk", query = "SELECT b FROM BsmFuncties b WHERE b.funVolgnrFk = :funVolgnrFk ORDER BY b.toonVolgnr"),
    @NamedQuery(name = "BsmFuncties.findByToonVolgnr", query = "SELECT b FROM BsmFuncties b WHERE b.toonVolgnr = :toonVolgnr"),
    @NamedQuery(name = "BsmFuncties.findByPlaatje", query = "SELECT b FROM BsmFuncties b WHERE b.plaatje = :plaatje"),
    @NamedQuery(name = "BsmFuncties.findByTypeInfoVolgnr", query = "SELECT b FROM BsmFuncties b WHERE b.typeInfoVolgnr = :typeInfoVolgnr"),
    @NamedQuery(name = "BsmFuncties.findByNaamFunctieEn", query = "SELECT b FROM BsmFuncties b WHERE b.naamFunctieEn = :naamFunctieEn"),
    @NamedQuery(name = "BsmFuncties.findForHoofdMenu", query = "SELECT b FROM BsmFuncties b WHERE b.toonVolgnr IS NOT NULL AND b.nivoMenu = 1 AND b.type IN ('FOLDER','INFO','LINK') ORDER BY b.toonVolgnr"),
    @NamedQuery(name = "BsmFuncties.findByOmschrijvingFunctieEn", query = "SELECT b FROM BsmFuncties b WHERE b.omschrijvingFunctieEn = :omschrijvingFunctieEn"),
    @NamedQuery(name = "BsmFuncties.findByDocumentVolgnr", query = "SELECT b FROM BsmFuncties b WHERE b.documentVolgnr = :documentVolgnr"),
    @NamedQuery(name = "BsmFuncties.findByType", query = "SELECT b FROM BsmFuncties b WHERE b.type = :type"),
    @NamedQuery(name = "BsmFuncties.findByTarget", query = "SELECT b FROM BsmFuncties b WHERE b.target = :target")})
public class BsmFuncties implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FunctiesSequence")
    @SequenceGenerator(name="FunctiesSequence", sequenceName="FUN_SEQ", allocationSize=1)    
    @Basic(optional = false)
    @Column(name = "FUN_VOLGNR")
    private Long funVolgnr;
    @Column(name = "NAAM_FUNCTIE")
    private String naamFunctie;
    @Column(name = "OMSCHRIJVING_FUNCTIE")
    private String omschrijvingFunctie;
    @Basic(optional = false)
    @Column(name = "NIVO_MENU")
    private short nivoMenu;
    @Column(name = "URL")
    private String url;
    @Column(name = "FUN_VOLGNR_FK")
    private Long funVolgnrFk;
    @Column(name = "TOON_VOLGNR")
    private Long toonVolgnr;
    @Column(name = "PLAATJE")
    private String plaatje;
    @Column(name = "TYPE_INFO_VOLGNR")
    private Long typeInfoVolgnr;
    @Column(name = "NAAM_FUNCTIE_EN")
    private String naamFunctieEn;
    @Column(name = "OMSCHRIJVING_FUNCTIE_EN")
    private String omschrijvingFunctieEn;
    @Column(name = "DOCUMENT_VOLGNR")
    private Long documentVolgnr;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "TARGET")
    private String target;
    @Column(name = "FOTOALBUM_VOLGNR")
    private Long fotoalbumVolgnr;

    public BsmFuncties() {
    }

    public BsmFuncties(Long funVolgnr) {
        this.funVolgnr = funVolgnr;
    }

    public BsmFuncties(Long funVolgnr, short nivoMenu) {
        this.funVolgnr = funVolgnr;
        this.nivoMenu = nivoMenu;
    }

    public Long getFunVolgnr() {
        return funVolgnr;
    }

    public void setFunVolgnr(Long funVolgnr) {
        this.funVolgnr = funVolgnr;
    }

    public String getNaamFunctie() {
        return naamFunctie;
    }

    public void setNaamFunctie(String naamFunctie) {
        this.naamFunctie = naamFunctie;
    }

    public String getOmschrijvingFunctie() {
        return omschrijvingFunctie;
    }

    public void setOmschrijvingFunctie(String omschrijvingFunctie) {
        this.omschrijvingFunctie = omschrijvingFunctie;
    }

    public short getNivoMenu() {
        return nivoMenu;
    }

    public void setNivoMenu(short nivoMenu) {
        this.nivoMenu = nivoMenu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getFunVolgnrFk() {
        return funVolgnrFk;
    }

    public void setFunVolgnrFk(Long funVolgnrFk) {
        this.funVolgnrFk = funVolgnrFk;
    }

    public Long getToonVolgnr() {
        return toonVolgnr;
    }

    public void setToonVolgnr(Long toonVolgnr) {
        this.toonVolgnr = toonVolgnr;
    }

    public String getPlaatje() {
        return plaatje;
    }

    public void setPlaatje(String plaatje) {
        this.plaatje = plaatje;
    }

    public Long getTypeInfoVolgnr() {
        return typeInfoVolgnr;
    }

    public void setTypeInfoVolgnr(Long typeInfoVolgnr) {
        this.typeInfoVolgnr = typeInfoVolgnr;
    }

    public String getNaamFunctieEn() {
        return naamFunctieEn;
    }

    public void setNaamFunctieEn(String naamFunctieEn) {
        this.naamFunctieEn = naamFunctieEn;
    }

    public String getOmschrijvingFunctieEn() {
        return omschrijvingFunctieEn;
    }

    public void setOmschrijvingFunctieEn(String omschrijvingFunctieEn) {
        this.omschrijvingFunctieEn = omschrijvingFunctieEn;
    }

    public Long getDocumentVolgnr() {
        return documentVolgnr;
    }

    public void setDocumentVolgnr(Long documentVolgnr) {
        this.documentVolgnr = documentVolgnr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Long getFotoalbumVolgnr() {
        return fotoalbumVolgnr;
    }

    public void setFotoalbumVolgnr(Long fotoalbumVolgnr) {
        this.fotoalbumVolgnr = fotoalbumVolgnr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (funVolgnr != null ? funVolgnr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BsmFuncties)) {
            return false;
        }
        BsmFuncties other = (BsmFuncties) object;
        if ((this.funVolgnr == null && other.funVolgnr != null) || (this.funVolgnr != null && !this.funVolgnr.equals(other.funVolgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmFuncties[funVolgnr=" + funVolgnr + "]";
    }

}
