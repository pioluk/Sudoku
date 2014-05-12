package org.comprog;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class TestTranslations {

  @Test
  public void testIfPolishTranslationWorks() {
    Locale locale = new Locale("pl", "PL");
    
    ResourceBundle translationBundle = ResourceBundle.getBundle("translations", locale);
    
    assertEquals(translationBundle.getString("language"), "J\u0119zyk");
  }
  
  @Test
  public void testIfEnglishTranslationWorks() {
    Locale locale = new Locale("en", "GB");
    
    ResourceBundle translationBundle = ResourceBundle.getBundle("translations", locale);
    
    assertEquals(translationBundle.getString("language"), "Language");
  }

}
