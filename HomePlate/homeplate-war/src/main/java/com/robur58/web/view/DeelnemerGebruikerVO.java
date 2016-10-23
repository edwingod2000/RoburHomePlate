package com.robur58.web.view;

import com.robur58.domein.BsmDeelnemers;
import com.robur58.domein.BsmGebruikers;

public class DeelnemerGebruikerVO {

    private BsmDeelnemers deelnemer;
    private BsmGebruikers gebruiker;
    
    public DeelnemerGebruikerVO() {
        
    }
    
    public DeelnemerGebruikerVO(BsmDeelnemers deelnemer, BsmGebruikers gebruiker) {
        this.deelnemer = deelnemer;
        this.gebruiker = gebruiker;
    }

    public BsmDeelnemers getDeelnemer() {
        return deelnemer;
    }

    public void setDeelnemer(BsmDeelnemers deelnemer) {
        this.deelnemer = deelnemer;
    }

    public BsmGebruikers getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(BsmGebruikers gebruiker) {
        this.gebruiker = gebruiker;
    }
    
}
