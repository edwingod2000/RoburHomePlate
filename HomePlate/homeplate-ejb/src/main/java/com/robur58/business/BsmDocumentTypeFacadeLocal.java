/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmDocumentType;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmDocumentTypeFacadeLocal {

    void create(BsmDocumentType bsmDocumentType);

    void edit(BsmDocumentType bsmDocumentType);

    void remove(BsmDocumentType bsmDocumentType);

    BsmDocumentType find(Object id);

    List<BsmDocumentType> findAll();

    List<BsmDocumentType> findRange(int[] range);

    int count();

}
