package org.comprog.SudokuLab4;

import java.io.Serializable;

public class DaoFactory {
  
  public enum DaoTypes {
    FileSuokuBoard
  }
  
  /**
   * Method creating instance of {@link Dao}
   * @param type type of implementation of {@link #Dao<T>}
   * @param fileName file name to open
   * @return 
   * @return reference to created object
   */
  public static <T extends Serializable> Dao<T> create(DaoTypes type, String fileName) {
    Dao<T> instance = null;
    
    switch (type) {
    case FileSuokuBoard:
      instance = (Dao<T>) new FileSudokuBoardDao(fileName);
      break;
    }
    
    return instance;
  }
  
}
