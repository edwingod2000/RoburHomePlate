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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "BSM_SYSTEEM_MEDEDELING")
@NamedQueries({
    @NamedQuery(name = "BsmSysteemMededeling.findAll", query = "SELECT b FROM BsmSysteemMededeling b"),
    @NamedQuery(name = "BsmSysteemMededeling.findByVolgnr", query = "SELECT b FROM BsmSysteemMededeling b WHERE b.volgnr = :volgnr"),
    @NamedQuery(name = "BsmSysteemMededeling.findByDatum", query = "SELECT b FROM BsmSysteemMededeling b WHERE b.datum = :datum"),
    @NamedQuery(name = "BsmSysteemMededeling.findByMededeling", query = "SELECT b FROM BsmSysteemMededeling b WHERE b.mededeling = :mededeling"),
    @NamedQuery(name = "BsmSysteemMededeling.findByType", query = "SELECT b FROM BsmSysteemMededeling b WHERE b.type = :type AND b.actief = 'J'" )})
public class BsmSysteemMededeling implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final String TYPE_STARTPAGINA_NOTIFICATIE = "STARTPAGINA";
    public static final String TYPE_PAGINA_SCRIPT = "SCRIPT";
    public static final String TYPE_MIJN_NOTIFICATIE = "MIJN";
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "VOLGNR")
    private Long volgnr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    @Size(max = 4000)
    @Column(name = "MEDEDELING")
    private String mededeling;
    @Size(max = 50)
    @Column(name = "TYPE")
    private String type;
    @Size(max = 1)
    @Column(name = "ACTIEF")
    private String actief;
    
    public BsmSysteemMededeling() {
    }

    public BsmSysteemMededeling(Long volgnr) {
        this.volgnr = volgnr;
    }

    public BsmSysteemMededeling(Long volgnr, Date datum) {
        this.volgnr = volgnr;
        this.datum = datum;
    }

    public Long getVolgnr() {
        return volgnr;
    }

    public void setVolgnr(Long volgnr) {
        this.volgnr = volgnr;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getMededeling() {
        return mededeling;
    }

    public void setMededeling(String mededeling) {
        this.mededeling = mededeling;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActief() {
        return actief;
    }

    public void setActief(String actief) {
        this.actief = actief;
    }

    public boolean isMededelingActief() {
        if (actief != null && actief.equals("J")) {
            return true;
        } else {
            return false;
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
        if (!(object instanceof BsmSysteemMededeling)) {
            return false;
        }
        BsmSysteemMededeling other = (BsmSysteemMededeling) object;
        if ((this.volgnr == null && other.volgnr != null) || (this.volgnr != null && !this.volgnr.equals(other.volgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmSysteemMededeling[ volgnr=" + volgnr + " ]";
    }
    
}
