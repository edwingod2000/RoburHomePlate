/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 *
 * @author godefrooije
 */
public class ParameterHelper {

    private static final String HOMEPLATE_PROPERTIES_FILE = "homeplate.properties";

    private static ParameterHelper instance = new ParameterHelper();
    private Properties p;
    
    private ParameterHelper()  {
        try {
            URL url = ClassLoader.getSystemResource(HOMEPLATE_PROPERTIES_FILE);
            URL url2 =  this.getClass().getResource(HOMEPLATE_PROPERTIES_FILE);

            p = new Properties();
            p.load(new FileInputStream(new File(url.getFile())));

            if (p != null) {
                System.out.println("File gevonden.");
            }
        } catch (IOException ioe) {
            System.out.println("File niet gevonden!");
        }
    }
    
    public static ParameterHelper getInstance() {
        return instance;
    }
    
}
