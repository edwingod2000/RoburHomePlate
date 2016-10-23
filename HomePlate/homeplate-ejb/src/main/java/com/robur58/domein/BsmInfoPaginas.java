/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.domein;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Gebruiker
 */
@Entity
@Table(name = "INFO_PAGINAS")
@NamedQueries({
    @NamedQuery(name = "BsmInfoPaginas.findAll", query = "SELECT b FROM BsmInfoPaginas b"),
    @NamedQuery(name = "BsmInfoPaginas.findByVolgnr", query = "SELECT b FROM BsmInfoPaginas b WHERE b.volgnr = :volgnr")})
public class BsmInfoPaginas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="InfoPaginasSequence")
    @SequenceGenerator(name="InfoPaginasSequence", sequenceName="IPA_SEQ", allocationSize=1)    
    @Basic(optional = false)
    @Column(name = "VOLGNR")
    private Long volgnr;
    @Lob
    @Column(name = "INHOUD")
    private String inhoud;

    public BsmInfoPaginas() {
    }

    public BsmInfoPaginas(Long volgnr) {
        this.volgnr = volgnr;
    }

    public Long getVolgnr() {
        return volgnr;
    }

    public void setVolgnr(Long volgnr) {
        this.volgnr = volgnr;
    }

    public String getInhoud() {
        return inhoud;
    }

    public void setInhoud(String inhoud) {
        this.inhoud = inhoud;
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
        if (!(object instanceof BsmInfoPaginas)) {
            return false;
        }
        BsmInfoPaginas other = (BsmInfoPaginas) object;
        if ((this.volgnr == null && other.volgnr != null) || (this.volgnr != null && !this.volgnr.equals(other.volgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmInfoPaginas[volgnr=" + volgnr + "]";
    }

}
