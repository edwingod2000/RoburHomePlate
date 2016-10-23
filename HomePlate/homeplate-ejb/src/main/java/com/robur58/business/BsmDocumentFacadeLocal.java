/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.business;

import com.robur58.domein.BsmDocument;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author godefrooije
 */
@Local
public interface BsmDocumentFacadeLocal {

    void create(BsmDocument bsmDocument);

    void edit(BsmDocument bsmDocument);

    void remove(BsmDocument bsmDocument);

    List<BsmDocument> findByDceVolgnr(long dceVolgnr);
    
    BsmDocument find(Object id);

    List<BsmDocument> findAll();

    List<BsmDocument> findRange(int[] range);

    int count();
    
}
