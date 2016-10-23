package com.robur58.web.view;

import org.primefaces.component.api.Widget;

public class HomePlateWidget implements Widget  {

    private String titel;
    private String url;
    private String var;
    private Integer defaultColumn;
    private Integer column;
    
    public HomePlateWidget() {
        
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Integer getDefaultColumn() {
        return defaultColumn;
    }

    public void setDefaultColumn(Integer defaultColumn) {
        this.defaultColumn = defaultColumn;
    }
    
    @Override
    public String resolveWidgetVar() {
        return var;
    }
    
}
