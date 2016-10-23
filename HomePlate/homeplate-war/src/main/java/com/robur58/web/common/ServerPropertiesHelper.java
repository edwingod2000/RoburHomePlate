package com.robur58.web.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Deze class gaat uit van een aanwezige system property die op de applicatie-server moet worden ingesteld:
 *      properties_path = "C:\\ServerConfig\\HomePlate\\"
 *   of properties_path = "/Users/godefrooije/ServerConfig/HomePlate/"
 * 
 * @author godefrooije
 */
public class ServerPropertiesHelper {
    public final String SERVER_PROPERTIES = "homeplate.properties";

    public final String SERVERNAME = "servername";
    public final String SERVERPORT = "serverport";
    public final String FOTOSERVICE = "fotoservice";
    public final String IMAGEPREFIX = "imageprefix";
    public final String SPONSORIMAGEPREFIX = "sponsorimageprefix";
    public final String DOCUMENT_PATH = "documenten_path";
    
    public final String SMTP_SERVER = "smtp_server";
    public final String SMTP_PORT = "smtp_port";
    public final String SMTP_USER = "smtp_user";
    public final String SMTP_PASSWORD = "smtp_password";
    public final String SMTP_EMAIL_FROM = "smtp_email_from";
    public final String SMTP_EMAIL_FROMNAME = "smtp_email_fromname";

    public final String FILE_IMG_FOLDER_OPEN = "file_img_folder_open";
    public final String FILE_IMG_FOLDER_CLOSE = "file_img_folder_close";
    public final String FILE_IMG_FOLDER_NEW = "file_img_folder_new";
    public final String FILE_IMG_TYPE_DOC = "file_img_type_doc";
    public final String FILE_IMG_TYPE_PNG = "file_img_type_png";
    public final String FILE_IMG_TYPE_PDF = "file_img_type_pdf";
    public final String FILE_IMG_TYPE_AVI = "file_img_type_avi";
    public final String FILE_IMG_TYPE_GIF = "file_img_type_gif";
    public final String FILE_IMG_TYPE_JPG = "file_img_type_jpg";
    public final String FILE_IMG_TYPE_HTML = "file_img_type_html";
    public final String FILE_IMG_TYPE_CD = "file_img_type_cd";
    public final String FILE_IMG_TYPE_XLS = "file_img_type_xls";
    public final String FILE_IMG_TYPE_PPT = "file_img_type_ppt";
    public final String FILE_IMG_TYPE_UNKNOWN = "file_img_type_unknown";
    public final String FILE_UPLOAD = "file_upload";    
    
    private static ServerPropertiesHelper instance;
    private Properties serverProperties;
    
    private ServerPropertiesHelper() {
        String propertiesPath = System.getProperty("properties_path") ;
        
        //try retrieve data from file
        try {
            serverProperties = new Properties();
            serverProperties.load(new FileInputStream(propertiesPath + SERVER_PROPERTIES));
        }

        //catch exception in case properties file does not exist
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getServername() {
        return getValue(SERVERNAME);
    }

    public String getServerport() {
        return getValue(SERVERPORT);
    }
    
    public String getFotoService() {
        return getValue(FOTOSERVICE);
    }
    
    public String getImageprefix() {
        return getValue(IMAGEPREFIX);
    }
    
    public String getDocumentPath() {
        return getValue(DOCUMENT_PATH);
    }
    
    public String getSponsorimageprefix() {
        return getValue(SPONSORIMAGEPREFIX);
    }
    
    public String getSmtpServer() {
        return getValue(SMTP_SERVER);
    }
    
    public String getSmtpPort() {
        return getValue(SMTP_PORT);
    }
    
    public String getSmtpUser() {
        return getValue(SMTP_USER);
    }
    
    public String getSmtpPassword() {
        return getValue(SMTP_PASSWORD);
    }
    
    public String getSmtpEmailFrom() {
        return getValue(SMTP_EMAIL_FROM);
    }
    
    public String getSmtpEmailFromName() {
        return getValue(SMTP_EMAIL_FROMNAME);
    }
    
    public String getFileImgFolderOpen() {
        return getValue(FILE_IMG_FOLDER_OPEN);
    }
    
    public String getFileImgFolderClose() {
        return getValue(FILE_IMG_FOLDER_CLOSE);
    }
    
    public String getFileImgFolderNew() {
        return getValue(FILE_IMG_FOLDER_NEW);
    }
    
    public String getFileImgTypeDoc() {
        return getValue(FILE_IMG_TYPE_DOC);
    }
    
    public String getFileImgTypePng() {
        return getValue(FILE_IMG_TYPE_PNG);
    }
    
    public String getFileImgTypePdf() {
        return getValue(FILE_IMG_TYPE_PDF);
    }
    
    public String getFileImgTypeAvi() {
        return getValue(FILE_IMG_TYPE_AVI);
    }

    public String getFileImgTypeGif() {
        return getValue(FILE_IMG_TYPE_GIF);
    }

    public String getFileImgTypeJpg() {
        return getValue(FILE_IMG_TYPE_JPG);
    }
    
    public String getFileImgTypeHtml() {
        return getValue(FILE_IMG_TYPE_HTML);
    }

    public String getFileImgTypeCd() {
        return getValue(FILE_IMG_TYPE_CD);
    }

    public String getFileImgTypePpt() {
        return getValue(FILE_IMG_TYPE_PPT);
    }
    
    public String getFileImgTypeXls() {
        return getValue(FILE_IMG_TYPE_XLS);
    }

    public String getFileImgTypeUnknown() {
        return getValue(FILE_IMG_TYPE_UNKNOWN);
    }

    public String getFileUpload() {
        return getValue(FILE_UPLOAD);
    }        
    
    public String getValue(String key) {
         return serverProperties.getProperty(key);
    }

    public static ServerPropertiesHelper getInstance() {
        if (instance == null) {
            instance = new ServerPropertiesHelper();
        }
        return instance;
    }
}
