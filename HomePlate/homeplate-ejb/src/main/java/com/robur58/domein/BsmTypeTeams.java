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

/**
 *
 * @author Gebruiker
 */
@Entity
@Table(name = "BSM_TYPE_TEAMS")
@NamedQueries({
    @NamedQuery(name = "BsmTypeTeams.findAll", query = "SELECT b FROM BsmTypeTeams b"),
    @NamedQuery(name = "BsmTypeTeams.findByCode", query = "SELECT b FROM BsmTypeTeams b WHERE b.code = :code"),
    @NamedQuery(name = "BsmTypeTeams.findByOmschrijving", query = "SELECT b FROM BsmTypeTeams b WHERE b.omschrijving = :omschrijving"),
    @NamedQuery(name = "BsmTypeTeams.findByDisplayVolgnr", query = "SELECT b FROM BsmTypeTeams b WHERE b.displayVolgnr = :displayVolgnr"),
    @NamedQuery(name = "BsmTypeTeams.findByOmschrijvingEn", query = "SELECT b FROM BsmTypeTeams b WHERE b.omschrijvingEn = :omschrijvingEn")})
public class BsmTypeTeams implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE")
    private String code;
    @Basic(optional = false)
    @Column(name = "OMSCHRIJVING")
    private String omschrijving;
    @Column(name = "DISPLAY_VOLGNR")
    private Short displayVolgnr;
    @Column(name = "OMSCHRIJVING_EN")
    private String omschrijvingEn;

    public BsmTypeTeams() {
    }

    public BsmTypeTeams(String code) {
        this.code = code;
    }

    public BsmTypeTeams(String code, String omschrijving) {
        this.code = code;
        this.omschrijving = omschrijving;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public Short getDisplayVolgnr() {
        return displayVolgnr;
    }

    public void setDisplayVolgnr(Short displayVolgnr) {
        this.displayVolgnr = displayVolgnr;
    }

    public String getOmschrijvingEn() {
        return omschrijvingEn;
    }

    public void setOmschrijvingEn(String omschrijvingEn) {
        this.omschrijvingEn = omschrijvingEn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BsmTypeTeams)) {
            return false;
        }
        BsmTypeTeams other = (BsmTypeTeams) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmTypeTeams[code=" + code + "]";
    }

}
