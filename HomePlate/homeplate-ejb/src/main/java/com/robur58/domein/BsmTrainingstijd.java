/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.domein;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author godefrooije
 */
@Entity
@Table(name = "BSM_TRAININGSTIJD")
@NamedQueries({
    @NamedQuery(name = "BsmTrainingstijd.findAll", query = "SELECT b FROM BsmTrainingstijd b"),
    @NamedQuery(name = "BsmTrainingstijd.findByTemVolgnr", query = "SELECT b FROM BsmTrainingstijd b WHERE b.temVolgnr = :temVolgnr"),
    @NamedQuery(name = "BsmTrainingstijd.findByZoTijd", query = "SELECT b FROM BsmTrainingstijd b WHERE b.zoTijd = :zoTijd"),
    @NamedQuery(name = "BsmTrainingstijd.findByMaTijd", query = "SELECT b FROM BsmTrainingstijd b WHERE b.maTijd = :maTijd"),
    @NamedQuery(name = "BsmTrainingstijd.findByDiTijd", query = "SELECT b FROM BsmTrainingstijd b WHERE b.diTijd = :diTijd"),
    @NamedQuery(name = "BsmTrainingstijd.findByWoTijd", query = "SELECT b FROM BsmTrainingstijd b WHERE b.woTijd = :woTijd"),
    @NamedQuery(name = "BsmTrainingstijd.findByDoTijd", query = "SELECT b FROM BsmTrainingstijd b WHERE b.doTijd = :doTijd"),
    @NamedQuery(name = "BsmTrainingstijd.findByVrTijd", query = "SELECT b FROM BsmTrainingstijd b WHERE b.vrTijd = :vrTijd"),
    @NamedQuery(name = "BsmTrainingstijd.findByZaTijd", query = "SELECT b FROM BsmTrainingstijd b WHERE b.zaTijd = :zaTijd"),
    @NamedQuery(name = "BsmTrainingstijd.findByZoLok", query = "SELECT b FROM BsmTrainingstijd b WHERE b.zoLok = :zoLok"),
    @NamedQuery(name = "BsmTrainingstijd.findByMaLok", query = "SELECT b FROM BsmTrainingstijd b WHERE b.maLok = :maLok"),
    @NamedQuery(name = "BsmTrainingstijd.findByDiLok", query = "SELECT b FROM BsmTrainingstijd b WHERE b.diLok = :diLok"),
    @NamedQuery(name = "BsmTrainingstijd.findByWoLok", query = "SELECT b FROM BsmTrainingstijd b WHERE b.woLok = :woLok"),
    @NamedQuery(name = "BsmTrainingstijd.findByDoLok", query = "SELECT b FROM BsmTrainingstijd b WHERE b.doLok = :doLok"),
    @NamedQuery(name = "BsmTrainingstijd.findByVrLok", query = "SELECT b FROM BsmTrainingstijd b WHERE b.vrLok = :vrLok"),
    @NamedQuery(name = "BsmTrainingstijd.findByZaLok", query = "SELECT b FROM BsmTrainingstijd b WHERE b.zaLok = :zaLok")})
public class BsmTrainingstijd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TEM_VOLGNR")
    private Long temVolgnr;
    @Size(max = 10)
    @Column(name = "ZO_TIJD")
    private String zoTijd;
    @Size(max = 10)
    @Column(name = "MA_TIJD")
    private String maTijd;
    @Size(max = 10)
    @Column(name = "DI_TIJD")
    private String diTijd;
    @Size(max = 10)
    @Column(name = "WO_TIJD")
    private String woTijd;
    @Size(max = 10)
    @Column(name = "DO_TIJD")
    private String doTijd;
    @Size(max = 10)
    @Column(name = "VR_TIJD")
    private String vrTijd;
    @Size(max = 10)
    @Column(name = "ZA_TIJD")
    private String zaTijd;
    @Size(max = 200)
    @Column(name = "ZO_LOK")
    private String zoLok;
    @Size(max = 200)
    @Column(name = "MA_LOK")
    private String maLok;
    @Size(max = 200)
    @Column(name = "DI_LOK")
    private String diLok;
    @Size(max = 200)
    @Column(name = "WO_LOK")
    private String woLok;
    @Size(max = 200)
    @Column(name = "DO_LOK")
    private String doLok;
    @Size(max = 200)
    @Column(name = "VR_LOK")
    private String vrLok;
    @Size(max = 200)
    @Column(name = "ZA_LOK")
    private String zaLok;

    public BsmTrainingstijd() {
    }

    public BsmTrainingstijd(Long temVolgnr) {
        this.temVolgnr = temVolgnr;
    }

    public Long getTemVolgnr() {
        return temVolgnr;
    }

    public void setTemVolgnr(Long temVolgnr) {
        this.temVolgnr = temVolgnr;
    }

    public String getZoTijd() {
        return zoTijd;
    }

    public void setZoTijd(String zoTijd) {
        this.zoTijd = zoTijd;
    }

    public String getMaTijd() {
        return maTijd;
    }

    public void setMaTijd(String maTijd) {
        this.maTijd = maTijd;
    }

    public String getDiTijd() {
        return diTijd;
    }

    public void setDiTijd(String diTijd) {
        this.diTijd = diTijd;
    }

    public String getWoTijd() {
        return woTijd;
    }

    public void setWoTijd(String woTijd) {
        this.woTijd = woTijd;
    }

    public String getDoTijd() {
        return doTijd;
    }

    public void setDoTijd(String doTijd) {
        this.doTijd = doTijd;
    }

    public String getVrTijd() {
        return vrTijd;
    }

    public void setVrTijd(String vrTijd) {
        this.vrTijd = vrTijd;
    }

    public String getZaTijd() {
        return zaTijd;
    }

    public void setZaTijd(String zaTijd) {
        this.zaTijd = zaTijd;
    }

    public String getZoLok() {
        return zoLok;
    }

    public void setZoLok(String zoLok) {
        this.zoLok = zoLok;
    }

    public String getMaLok() {
        return maLok;
    }

    public void setMaLok(String maLok) {
        this.maLok = maLok;
    }

    public String getDiLok() {
        return diLok;
    }

    public void setDiLok(String diLok) {
        this.diLok = diLok;
    }

    public String getWoLok() {
        return woLok;
    }

    public void setWoLok(String woLok) {
        this.woLok = woLok;
    }

    public String getDoLok() {
        return doLok;
    }

    public void setDoLok(String doLok) {
        this.doLok = doLok;
    }

    public String getVrLok() {
        return vrLok;
    }

    public void setVrLok(String vrLok) {
        this.vrLok = vrLok;
    }

    public String getZaLok() {
        return zaLok;
    }

    public void setZaLok(String zaLok) {
        this.zaLok = zaLok;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (temVolgnr != null ? temVolgnr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BsmTrainingstijd)) {
            return false;
        }
        BsmTrainingstijd other = (BsmTrainingstijd) object;
        if ((this.temVolgnr == null && other.temVolgnr != null) || (this.temVolgnr != null && !this.temVolgnr.equals(other.temVolgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmTrainingstijd[ temVolgnr=" + temVolgnr + " ]";
    }
    
}
