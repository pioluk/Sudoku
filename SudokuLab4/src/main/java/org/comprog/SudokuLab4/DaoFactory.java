package org.comprog.SudokuLab4;

import java.io.Serializable;

enum DaoTypes {
  FileSuokuBoard
}

public class DaoFactory {
  
  /**
   * Method creating instance og {@link Dao}
   * @param type type of implementation of {@link #Dao<T>}
   * @param fileName file name to open
   * @return reference to created object
   */
  public static Dao<? extends Serializable> create(DaoTypes type, String fileName) {
    Dao<?> instance = null;
    
    switch (type) {
    case FileSuokuBoard:
      instance = new FileSudokuBoardDao(fileName);
      break;
    }
    
    return instance;
  }
  
}
