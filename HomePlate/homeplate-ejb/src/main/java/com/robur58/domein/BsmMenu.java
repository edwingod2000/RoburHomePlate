/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.domein;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author godefrooije
 */
@Entity
@Table(name = "BSM_MENU")
@NamedQueries({
    @NamedQuery(name = "BsmMenu.findAll", query = "SELECT b FROM BsmMenu b"),
    @NamedQuery(name = "BsmMenu.findByMnuVolgnr", query = "SELECT b FROM BsmMenu b WHERE b.mnuVolgnr = :mnuVolgnr"),
    @NamedQuery(name = "BsmMenu.findByOmschrijving", query = "SELECT b FROM BsmMenu b WHERE b.omschrijving = :omschrijving"),
    @NamedQuery(name = "BsmMenu.findByUrl", query = "SELECT b FROM BsmMenu b WHERE b.url = :url"),
    @NamedQuery(name = "BsmMenu.findByHint", query = "SELECT b FROM BsmMenu b WHERE b.hint = :hint"),
    @NamedQuery(name = "BsmMenu.findByPlaatje", query = "SELECT b FROM BsmMenu b WHERE b.plaatje = :plaatje"),
    @NamedQuery(name = "BsmMenu.findByNivo", query = "SELECT b FROM BsmMenu b WHERE b.nivo = :nivo"),
    @NamedQuery(name = "BsmMenu.findByWidth", query = "SELECT b FROM BsmMenu b WHERE b.width = :width"),
    @NamedQuery(name = "BsmMenu.findByTarget", query = "SELECT b FROM BsmMenu b WHERE b.target = :target"),
    @NamedQuery(name = "BsmMenu.findByOmschrijvinglang", query = "SELECT b FROM BsmMenu b WHERE b.omschrijvinglang = :omschrijvinglang"),
    @NamedQuery(name = "BsmMenu.findParents", query = "SELECT b FROM BsmMenu b WHERE b.nivo = 1 order by b.toonVolgnr"),
    @NamedQuery(name = "BsmMenu.findByParent", query = "SELECT b FROM BsmMenu b WHERE b.mnuVolgnr = :mnuVolgnr order by b.toonVolgnr"),
    @NamedQuery(name = "BsmMenu.findByToonVolgnr", query = "SELECT b FROM BsmMenu b WHERE b.toonVolgnr = :toonVolgnr")})
public class BsmMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MNU_VOLGNR")
    private Long mnuVolgnr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "OMSCHRIJVING")
    private String omschrijving;
    @Size(max = 500)
    @Column(name = "URL")
    private String url;
    @Size(max = 500)
    @Column(name = "HINT")
    private String hint;
    @Size(max = 500)
    @Column(name = "PLAATJE")
    private String plaatje;
    @Column(name = "NIVO")
    private Short nivo;
    @Column(name = "WIDTH")
    private Long width;
    @Size(max = 100)
    @Column(name = "TARGET")
    private String target;
    @Size(max = 500)
    @Column(name = "OMSCHRIJVINGLANG")
    private String omschrijvinglang;
    @Column(name = "TOON_VOLGNR")
    private Long toonVolgnr;
    @Lob
    @Column(name = "HELPTEKST")
    private String helptekst;
    @OneToMany(mappedBy = "parent")
    private List<BsmMenu> bsmMenuList;
    @JoinColumn(name = "PARENT", referencedColumnName = "MNU_VOLGNR")
    @ManyToOne
    private BsmMenu parent;

    public BsmMenu() {
    }

    public BsmMenu(Long mnuVolgnr) {
        this.mnuVolgnr = mnuVolgnr;
    }

    public BsmMenu(Long mnuVolgnr, String omschrijving) {
        this.mnuVolgnr = mnuVolgnr;
        this.omschrijving = omschrijving;
    }

    public Long getMnuVolgnr() {
        return mnuVolgnr;
    }

    public void setMnuVolgnr(Long mnuVolgnr) {
        this.mnuVolgnr = mnuVolgnr;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getPlaatje() {
        return plaatje;
    }

    public void setPlaatje(String plaatje) {
        this.plaatje = plaatje;
    }

    public Short getNivo() {
        return nivo;
    }

    public void setNivo(Short nivo) {
        this.nivo = nivo;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getOmschrijvinglang() {
        return omschrijvinglang;
    }

    public void setOmschrijvinglang(String omschrijvinglang) {
        this.omschrijvinglang = omschrijvinglang;
    }

    public Long getToonVolgnr() {
        return toonVolgnr;
    }

    public void setToonVolgnr(Long toonVolgnr) {
        this.toonVolgnr = toonVolgnr;
    }

    public String getHelptekst() {
        return helptekst;
    }

    public void setHelptekst(String helptekst) {
        this.helptekst = helptekst;
    }

    public List<BsmMenu> getBsmMenuList() {
        return bsmMenuList;
    }

    public void setBsmMenuList(List<BsmMenu> bsmMenuList) {
        this.bsmMenuList = bsmMenuList;
    }

    public BsmMenu getParent() {
        return parent;
    }

    public void setParent(BsmMenu parent) {
        this.parent = parent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mnuVolgnr != null ? mnuVolgnr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BsmMenu)) {
            return false;
        }
        BsmMenu other = (BsmMenu) object;
        if ((this.mnuVolgnr == null && other.mnuVolgnr != null) || (this.mnuVolgnr != null && !this.mnuVolgnr.equals(other.mnuVolgnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmMenu[ mnuVolgnr=" + mnuVolgnr + " ]";
    }
    
}
