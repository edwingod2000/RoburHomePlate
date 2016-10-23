package com.robur58.web.controller;

import com.robur58.web.view.HomePlateWidget;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

/**
 *
 * @author godefrooije
 */
@ManagedBean
@SessionScoped
public class DashboardController extends Controller implements Serializable {
    
    private Dashboard dashboard;
    public static final int DEFAULT_COLUMN_COUNT = 3;
    private int columnCount = DEFAULT_COLUMN_COUNT;
    
    public DashboardController() {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Application application = fc.getApplication();
 
        dashboard = (Dashboard) application.createComponent(fc, "org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer");
        dashboard.setId("dashboard");
        dashboard.setStyle("width: 100%;");
        DashboardModel model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        DashboardColumn column3 = new DefaultDashboardColumn();
        
        model.addColumn(column1);
        model.addColumn(column2);
        model.addColumn(column3);
        dashboard.setModel(model);

        DashboardColumn[] columns = new DashboardColumn[3];
        columns[0] = column1;
        columns[1] = column2;
        columns[2] = column3;
        
        List<HomePlateWidget> widgets = createWidgets();
 
        for (HomePlateWidget widget : widgets) {
            if (widget.getColumn() == null) {
                addWidget(application, columns[widget.getDefaultColumn()-1], widget);
            } else {
                addWidget(application, columns[widget.getColumn()-1], widget);
            }
        }
         
    }  

    private void addWidget(Application application, DashboardColumn column, HomePlateWidget widget) {
        FacesContext fc = FacesContext.getCurrentInstance();

        Panel panel = (Panel) application.createComponent(fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
        panel.setId(widget.getVar());
        panel.setHeader(widget.getTitel());
        panel.setClosable(true);
        panel.setToggleable(true);

        getDashboard().getChildren().add(panel);
        column.addWidget(panel.getId());
        HtmlOutputText text = new HtmlOutputText();
        text.setValue("Dashboard widget bits!" );

        panel.getChildren().add(text);
    }

    public void preRenderView() {
        
    }
      
 
    public Dashboard getDashboard() {
        return dashboard;
    }
 
    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }
 
    public int getColumnCount() {
        return columnCount;
    }
 
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }    
    
    public void handleReorder(DashboardReorderEvent event) {  
        FacesMessage message = new FacesMessage();  
        message.setSeverity(FacesMessage.SEVERITY_INFO);  
        message.setSummary("Reordered: " + event.getWidgetId());  
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());  
          
        addMessage(message);  
    }  
      
    public void handleClose(CloseEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");  
          
        addMessage(message);  
    }  
      
    public void handleToggle(ToggleEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());  
          
        addMessage(message);  
    }  
      
    private void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    } 
    
    private List<HomePlateWidget> createWidgets() {
        List<HomePlateWidget> widgets = new ArrayList<HomePlateWidget>();
        
        HomePlateWidget widget = new HomePlateWidget();
        widget.setVar("menu");
        widget.setTitel("Menu");
        widget.setDefaultColumn(1);
        widgets.add(widget);
        
        widget = new HomePlateWidget();
        widget.setVar("sponsors");
        widget.setTitel("Sponsors");
        widget.setDefaultColumn(1);
        widgets.add(widget);
        
        widget = new HomePlateWidget();
        widget.setVar("actueel");
        widget.setTitel("Actuele wedstrijden");
        widget.setDefaultColumn(2);
        widgets.add(widget);
        
        widget = new HomePlateWidget();
        widget.setVar("kalender");
        widget.setTitel("Kalender");
        widget.setDefaultColumn(3);
        widgets.add(widget);
        
        widget = new HomePlateWidget();
        widget.setVar("weer");
        widget.setTitel("Weer");
        widget.setDefaultColumn(3);
        widgets.add(widget);
        
        widget = new HomePlateWidget();
        widget.setVar("mijn");
        widget.setTitel("Mijn HomePlate");
        widget.setDefaultColumn(3);
        widgets.add(widget);

        widget = new HomePlateWidget();
        widget.setVar("hoogtepunten");
        widget.setTitel("Hoogtepunten");
        widget.setDefaultColumn(3);
        widgets.add(widget);

        widget = new HomePlateWidget();
        widget.setVar("social");
        widget.setTitel("Social Media");
        widget.setDefaultColumn(3);
        widgets.add(widget);

        return widgets;
    }
      
}
