package com.robur58.business;

import com.robur58.domein.BsmGastenboek;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmGastenboekFacadeLocal {

    void create(BsmGastenboek bsmGastenboek);

    void edit(BsmGastenboek bsmGastenboek);

    void remove(BsmGastenboek bsmGastenboek);

    BsmGastenboek find(Object id);

    List<BsmGastenboek> findAll();

    List<BsmGastenboek> findRange(int[] range);

    List<BsmGastenboek> findHomeplateByJaar(String jaar);

    int count();

}
