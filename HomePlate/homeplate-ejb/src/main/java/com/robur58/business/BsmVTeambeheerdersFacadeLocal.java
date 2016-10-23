/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmVTeambeheerders;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmVTeambeheerdersFacadeLocal {

    void create(BsmVTeambeheerders bsmTeambeheerders);

    void edit(BsmVTeambeheerders bsmTeambeheerders);

    void remove(BsmVTeambeheerders bsmTeambeheerders);

    BsmVTeambeheerders find(Object id);

    BsmVTeambeheerders findByTemVolgnr(Long temVolgnr);    
    
    List<BsmVTeambeheerders> findAll();

    List<BsmVTeambeheerders> findRange(int[] range);

    int count();

}
