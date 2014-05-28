package PBA.utils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 * @author Maria
 *
 */
public class ConnectionUtils
{    
 
      protected static ClassLoader getCurrentClassLoader(Object defaultObject) {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            if (loader == null) {
                  loader = defaultObject.getClass().getClassLoader();
            }
            return loader;
      }
     
      /**
       * 
     * @param bundleName = numele fisierului de configurare din care se vor lua datele 
     * 						pt conectarea la baza de date locala (in cazul meu "config")
     * @param id (ex: "phone.book.app.db.driver")
     * @return	un String ce corespunde id-ului cerut 
     */
    public static String getMessage(String bundleName, String id) {
            String text = null;
            ResourceBundle bundle = ResourceBundle.getBundle(bundleName, Locale
                        .getDefault(), getCurrentClassLoader(null));
            try {
                  text = bundle.getString(id).trim();
            } catch (MissingResourceException e) {
                  text = "!! key " + id + " not found !!";
            }
            return text;
      }
}