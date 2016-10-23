package com.robur58.business;

import com.robur58.domein.BsmDocInfo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author godefrooije
 */
@Local
public interface BsmDocInfoFacadeLocal {

    void create(BsmDocInfo bsmDocInfo);

    void edit(BsmDocInfo bsmDocInfo);

    void remove(BsmDocInfo bsmDocInfo);

    BsmDocInfo find(Object id);

    List<BsmDocInfo> findAll();

    List<BsmDocInfo> findRange(int[] range);

    int count();
    
}
