package PBA.utils;



import java.util.Locale;

import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


import org.apache.log4j.Logger;



/**
 * @author Maria
 * 
 * Clasa folosita pt internationalizarea aplicatiei
 * 			foloseste fisierele language*.properties pt a traduce 
 * 			label-urile in alte limbi
 *
 */
public class LanguageUtils {

	
   private static Logger logger=Logger.getLogger(LanguageUtils.class);
   private static PropertyResourceBundle tmpPropertyResourceBundle;
   private static final String LANGUAGEBASENAME="language";	
   private static PropertyResourceBundle configPropertyResourceBundle;
   private static final String CONFIGBASENAME="configlanguage";
	
   
   public static String getI18NString(String key){
       String keyValue="";
       if(key==null){
           logger.error("getI18NString()->",new NullPointerException("The argument key can't be null!"));
           return keyValue;
       }
       if(key.isEmpty()){
           logger.error("getI18NString()->",new Exception("The argument key can't be empty!"));
           return keyValue;
       }
       String loc=Locale.getDefault().toString();
       try
       {
           if (tmpPropertyResourceBundle==null || !loc.equals(""))
               tmpPropertyResourceBundle = (PropertyResourceBundle) ResourceBundle.getBundle(LANGUAGEBASENAME,Locale.ENGLISH);
           keyValue = tmpPropertyResourceBundle.getString(key);
       } catch (MissingResourceException e){
             keyValue="<"+key+">";
       }
       if (keyValue==null || keyValue.equals(""))
           keyValue="<"+key+">";
       return keyValue;
   }
   

   public static String getConfigString(String key){
       String keyValue=null;
       if(key==null){
           logger.error("getConfigString()->",new NullPointerException("The argument key can't be null!"));
           return keyValue;
       }
       if(key.isEmpty()){
           logger.error("getConfigString()->",new Exception("The argument key can't be empty!"));
           return keyValue;
       }
       try {
           if (configPropertyResourceBundle==null)
               configPropertyResourceBundle = (PropertyResourceBundle) ResourceBundle.getBundle(CONFIGBASENAME);
           keyValue = configPropertyResourceBundle.getString(key);
       } catch (Exception e) {
           logger.error("getConfigString()->",e);
       }

       return keyValue;
   }
}
