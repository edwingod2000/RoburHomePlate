package com.robur58.business;

import com.robur58.domein.BsmDeelnemers;
import java.util.List;
import javax.ejb.Local;

@Local
public interface BsmDeelnemersFacadeLocal {

    void create(BsmDeelnemers bsmDeelnemers);

    void edit(BsmDeelnemers bsmDeelnemers);

    void remove(BsmDeelnemers bsmDeelnemers);

    BsmDeelnemers find(Object id);

    List<BsmDeelnemers> findAll();

    List<BsmDeelnemers> findRange(int[] range);

    List<BsmDeelnemers> findByTemVolgnr(Long temVolgnr);
    
    List<BsmDeelnemers> findByTemVolgnrExclusiveNonRegistered(Long temVolgnr);

    int count();

}
