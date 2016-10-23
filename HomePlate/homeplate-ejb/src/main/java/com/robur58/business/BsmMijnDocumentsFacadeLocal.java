/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.business;

import com.robur58.domein.BsmGebruikers;
import com.robur58.domein.BsmMijnDocuments;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author godefrooije
 */
@Local
public interface BsmMijnDocumentsFacadeLocal {

    void create(BsmMijnDocuments bsmMijnDocuments);

    void edit(BsmMijnDocuments bsmMijnDocuments);

    void remove(BsmMijnDocuments bsmMijnDocuments);

    BsmMijnDocuments find(Object id);

    List<BsmMijnDocuments> findAll();

    List<BsmMijnDocuments> findRange(int[] range);

    List<BsmMijnDocuments> findDirectoriesByMdsVolgnr(Long parentVolgnr, boolean published);

    List<BsmMijnDocuments> findFilesByMdsVolgnr(Long parentVolgnr, boolean published);
    
    void createDocument(String naam, String opslagNaam, String type, String omschrijving, BsmMijnDocuments parent, BsmGebruikers gbrVolgnr, Date datumAangemaakt, boolean gepubliceerd);

    void createFolder(String naam, String omschrijving, BsmMijnDocuments parent, BsmGebruikers gbrVolgnr, Date datumAangemaakt, boolean gepubliceerd);
    
    int count();
    
}
