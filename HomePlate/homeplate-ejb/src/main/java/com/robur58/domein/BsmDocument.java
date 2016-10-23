/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.domein;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "BSM_V_DOCUMENTEN")
@NamedQueries({
    @NamedQuery(name = "BsmDocument.findAll", query = "SELECT b FROM BsmDocument b"),
    @NamedQuery(name = "BsmDocument.findByDctVolgnr", query = "SELECT b FROM BsmDocument b WHERE b.dctVolgnr = :dctVolgnr"),
    @NamedQuery(name = "BsmDocument.findByDatum", query = "SELECT b FROM BsmDocument b WHERE b.datum = :datum"),
    @NamedQuery(name = "BsmDocument.findByDatumOpname", query = "SELECT b FROM BsmDocument b WHERE b.datumOpname = :datumOpname"),
    @NamedQuery(name = "BsmDocument.findByInfo", query = "SELECT b FROM BsmDocument b WHERE b.info = :info"),
    @NamedQuery(name = "BsmDocument.findByName", query = "SELECT b FROM BsmDocument b WHERE b.name = :name"),
    @NamedQuery(name = "BsmDocument.findByMimeType", query = "SELECT b FROM BsmDocument b WHERE b.mimeType = :mimeType"),
    @NamedQuery(name = "BsmDocument.findByDocSize", query = "SELECT b FROM BsmDocument b WHERE b.docSize = :docSize"),
    @NamedQuery(name = "BsmDocument.findByDadCharset", query = "SELECT b FROM BsmDocument b WHERE b.dadCharset = :dadCharset"),
    @NamedQuery(name = "BsmDocument.findByLastUpdated", query = "SELECT b FROM BsmDocument b WHERE b.lastUpdated = :lastUpdated"),
    @NamedQuery(name = "BsmDocument.findByContentType", query = "SELECT b FROM BsmDocument b WHERE b.contentType = :contentType"),
    @NamedQuery(name = "BsmDocument.findByDceVolgnr", query = "SELECT b FROM BsmDocument b WHERE b.dceVolgnr = :dceVolgnr")})
public class BsmDocument implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @NotNull
    @Column(name = "DCT_VOLGNR")
    private long dctVolgnr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    @Column(name = "DATUM_OPNAME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumOpname;
    @Size(max = 4000)
    @Column(name = "INFO")
    private String info;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "NAME")
    private String name;
    @Size(max = 128)
    @Column(name = "MIME_TYPE")
    private String mimeType;
    @Column(name = "DOC_SIZE")
    private BigInteger docSize;
    @Size(max = 128)
    @Column(name = "DAD_CHARSET")
    private String dadCharset;
    @Column(name = "LAST_UPDATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;
    @Size(max = 128)
    @Column(name = "CONTENT_TYPE")
    private String contentType;
    @Lob
    @Column(name = "CONTENT")
    private byte[] content;
    @Lob
    @Column(name = "BLOB_CONTENT")
    private byte[] blobContent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DCE_VOLGNR")
    private long dceVolgnr;

    public BsmDocument() {
    }

    public long getDctVolgnr() {
        return dctVolgnr;
    }

    public void setDctVolgnr(long dctVolgnr) {
        this.dctVolgnr = dctVolgnr;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getDatumOpname() {
        return datumOpname;
    }

    public void setDatumOpname(Date datumOpname) {
        this.datumOpname = datumOpname;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        String result = getName();
        if (getName() != null) {
            if (getName().contains("/")) {
                result = getName().substring(getName().indexOf("/")+1);
            }
        }
        
        return result;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public BigInteger getDocSize() {
        return docSize;
    }

    public void setDocSize(BigInteger docSize) {
        this.docSize = docSize;
    }

    public String getDadCharset() {
        return dadCharset;
    }

    public void setDadCharset(String dadCharset) {
        this.dadCharset = dadCharset;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public byte[] getBlobContent() {
        return blobContent;
    }

    public void setBlobContent(byte[] blobContent) {
        this.blobContent = blobContent;
    }

    public long getDceVolgnr() {
        return dceVolgnr;
    }

    public void setDceVolgnr(long dceVolgnr) {
        this.dceVolgnr = dceVolgnr;
    }
    
}
