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
@Table(name = "BSM_SYSVAR")
@NamedQueries({
    @NamedQuery(name = "BsmSysvar.findAll", query = "SELECT b FROM BsmSysvar b"),
    @NamedQuery(name = "BsmSysvar.findByCode", query = "SELECT b FROM BsmSysvar b WHERE b.code = :code"),
    @NamedQuery(name = "BsmSysvar.findByWaarde", query = "SELECT b FROM BsmSysvar b WHERE b.waarde = :waarde"),
    @NamedQuery(name = "BsmSysvar.findByOmschrijving", query = "SELECT b FROM BsmSysvar b WHERE b.omschrijving = :omschrijving"),
    @NamedQuery(name = "BsmSysvar.findByToonVolgnr", query = "SELECT b FROM BsmSysvar b WHERE b.toonVolgnr = :toonVolgnr")})
public class BsmSysvar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE")
    private String code;
    @Column(name = "WAARDE")
    private String waarde;
    @Column(name = "OMSCHRIJVING")
    private String omschrijving;
    @Column(name = "TOON_VOLGNR")
    private Short toonVolgnr;

    public BsmSysvar() {
    }

    public BsmSysvar(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getWaarde() {
        return waarde;
    }

    public void setWaarde(String waarde) {
        this.waarde = waarde;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public Short getToonVolgnr() {
        return toonVolgnr;
    }

    public void setToonVolgnr(Short toonVolgnr) {
        this.toonVolgnr = toonVolgnr;
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
        if (!(object instanceof BsmSysvar)) {
            return false;
        }
        BsmSysvar other = (BsmSysvar) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmSysvar[code=" + code + "]";
    }

}
