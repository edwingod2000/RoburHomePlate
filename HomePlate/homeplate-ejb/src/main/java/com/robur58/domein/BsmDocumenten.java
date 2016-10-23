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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "BSM_DOCUMENTEN")
@NamedQueries({
    @NamedQuery(name = "BsmDocumenten.findAll", query = "SELECT b FROM BsmDocumenten b"),
    @NamedQuery(name = "BsmDocumenten.findByName", query = "SELECT b FROM BsmDocumenten b WHERE b.name = :name"),
    @NamedQuery(name = "BsmDocumenten.findByMimeType", query = "SELECT b FROM BsmDocumenten b WHERE b.mimeType = :mimeType"),
    @NamedQuery(name = "BsmDocumenten.findByDocSize", query = "SELECT b FROM BsmDocumenten b WHERE b.docSize = :docSize"),
    @NamedQuery(name = "BsmDocumenten.findByDadCharset", query = "SELECT b FROM BsmDocumenten b WHERE b.dadCharset = :dadCharset"),
    @NamedQuery(name = "BsmDocumenten.findByLastUpdated", query = "SELECT b FROM BsmDocumenten b WHERE b.lastUpdated = :lastUpdated"),
    @NamedQuery(name = "BsmDocumenten.findByContentType", query = "SELECT b FROM BsmDocumenten b WHERE b.contentType = :contentType")})
public class BsmDocumenten implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DocumentenSequence")
    @SequenceGenerator(name="DocumentenSequence", sequenceName="DOC_SEQ", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Column(name = "MIME_TYPE")
    private String mimeType;
    @Column(name = "DOC_SIZE")
    private BigInteger docSize;
    @Column(name = "DAD_CHARSET")
    private String dadCharset;
    @Column(name = "LAST_UPDATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;
    @Column(name = "CONTENT_TYPE")
    private String contentType;
    @Lob
    @Column(name = "CONTENT")
    private byte[] content;
    @Lob
    @Column(name = "BLOB_CONTENT")
    private byte[]  blobContent;
    @Lob
    @Column(name = "BLOB_CONTENT_THUMBNAIL")
    private byte[] blobContentThumbnail;

    public BsmDocumenten() {
    }

    public BsmDocumenten(String name) {
        this.name = name;
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

    public byte[] getBlobContentThumbnail() {
        return blobContentThumbnail;
    }

    public void setBlobContentThumbnail(byte[] blobContentThumbnail) {
        this.blobContentThumbnail = blobContentThumbnail;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BsmDocumenten)) {
            return false;
        }
        BsmDocumenten other = (BsmDocumenten) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.robur58.domein.BsmDocumenten[name=" + name + "]";
    }

}
