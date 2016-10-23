package com.robur58.web.view;

import com.robur58.domein.BsmInfo;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class InfoLijst {

    private List<BsmInfo> infoLijst;
    
    public InfoLijst(List<BsmInfo> infoLijst) {
        this.infoLijst = infoLijst;
    }

    public boolean isNLEmpty() {
        boolean result = true;
        
        if (infoLijst.size() > 0) {
            result = false;
        }
        
        return result;
    }
    
    public boolean isENEmpty() {
        boolean result = true;
        
        if (infoLijst != null && infoLijst.size() > 0) {
            for (BsmInfo info : infoLijst) {
                if (!StringUtils.isEmpty(info.getTitelEn())) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
    
    public List<BsmInfo> getInfoLijst() {
        return infoLijst;
    }

    public void setInfoLijst(List<BsmInfo> infoLijst) {
        this.infoLijst = infoLijst;
    }
    
}
