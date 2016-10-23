package com.robur58.web.helper;


import com.robur58.business.BsmDocumentenFacadeLocal;
import com.robur58.domein.BsmDocumenten;
import com.robur58.web.util.ImageTool;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.Closeable;  
import java.io.IOException;  
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;  
import javax.servlet.ServletException;  
import javax.servlet.ServletOutputStream;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

@WebServlet(name = "ImageServlet", urlPatterns = {"/ImageServlet/*"})  
public class ImageServlet extends HttpServlet {  

    @EJB  
    private BsmDocumentenFacadeLocal ejb;
    private static final Logger LOGGER = Logger.getLogger(ImageServlet.class.getName());
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  

        String nameString = request.getParameter("name");  
        String thumbnail = request.getParameter("thumbnail");
        
        if (nameString == null || nameString.isEmpty()) {  
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.  
            return;  
        }  

        String name = nameString.trim();  
        boolean showThumbnail = false;
        if (thumbnail != null) {
            showThumbnail = true;
        }
        
        BsmDocumenten entry = null;  

        try {  
            entry = (BsmDocumenten) ejb.findByName(name);  
        } catch (Exception ex) {  
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }  

        if (entry == null) {  
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.  
            return;  
        }  

        ServletOutputStream out = null;  

        try {  
            response.reset();  

            out = response.getOutputStream();  

            if (showThumbnail) {
                if (entry.getBlobContentThumbnail() != null && entry.getBlobContentThumbnail().length != 0) {  
                    out.write(entry.getBlobContentThumbnail());  
                } else {
                    // create thumbnail
                    if (entry.getBlobContent() != null && entry.getBlobContent().length != 0) {  
                        Image image = Toolkit.getDefaultToolkit().createImage(entry.getBlobContent());

                        Image thumbImage = ImageTool.createThumbnail(image);
                        byte[] thumbBytes = ImageTool.imageToByteArray(thumbImage);
                        entry.setBlobContentThumbnail(thumbBytes);
                        ejb.edit(entry);
                    }
                    out.write(entry.getBlobContentThumbnail());  
                }
            } else {
                if (entry.getBlobContent() != null && entry.getBlobContent().length != 0) {  
                    out.write(entry.getBlobContent());  
                }
            }
        } catch (IOException e) {  
            LOGGER.log(Level.SEVERE, e.getMessage());
        } finally {  
            close(out);  
        }  

    }  

    @Override  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        processRequest(request, response);  
    }  

    @Override  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        processRequest(request, response);  
    }  

    // Helpers (can be refactored to public utility class) ----------------------------------------  
    private static void close(Closeable resource) {  
        if (resource != null) {  
            try {  
                resource.close();  
            } catch (IOException e) {  
                LOGGER.log(Level.SEVERE, e.getMessage());
            }  
        }  
    }  
}  