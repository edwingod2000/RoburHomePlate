package com.robur58.web.controller;

import com.robur58.business.BsmTypeInfoFacadeLocal;
import com.robur58.domein.BsmTypeInfo;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author godefrooije
 */
@ManagedBean
@SessionScoped
public class TypeInfoController extends Controller implements Serializable {
    
    @EJB
    BsmTypeInfoFacadeLocal typeInfoFacade;

    private List<BsmTypeInfo> typeInfoLijst;
    private String titel;
    
    public TypeInfoController() {
        titel ="Beheer Type Info";
    }

    public List<BsmTypeInfo> getTypeInfoLijst() {
        if (typeInfoLijst == null) {
            ophalenTypeInfo();
        }
        return typeInfoLijst;
    }

    public void setTypeInfoLijst(List<BsmTypeInfo> typeInfoLijst) {
        this.typeInfoLijst = typeInfoLijst;
    }

    private void ophalenTypeInfo() {
        typeInfoLijst = typeInfoFacade.findAll();
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }
    
    
}
