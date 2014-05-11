package org.comprog;

import java.io.Serializable;

/**
 * Enum type containing existing implementations of
 * the Dao interface
 */
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
