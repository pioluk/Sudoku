package org.comprog;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Base class for exceptions
 * provides localization
 */
public abstract class SudokuExceptionBase extends Exception {
  
  private static final long serialVersionUID = 2348715283807357027L;
  
  protected static Locale locale;
  protected static ResourceBundle translationBundle;
  
  public SudokuExceptionBase(String msg) {
    super(msg);
    
    if (locale == null)
      locale = Locale.getDefault();
    
    setLocale(locale);
  }

  public static Locale getLocale() {
    return locale;
  }

  public static void setLocale(Locale locale) {
    locale = locale;
    translationBundle = ResourceBundle.getBundle("translations", locale);
  }

}
