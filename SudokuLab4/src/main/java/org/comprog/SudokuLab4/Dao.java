package org.comprog.SudokuLab4;

import java.io.Serializable;

/**
 * Interface providing methods for serialization
 */
public interface Dao<T extends Serializable> {
  /**
   * Reads a serialized object form a file
   * @return serialized object
   */
  public T read();
  
  /**
   * Writes an object to a file
   * @param obj object to serialize
   */
  public void write(T obj);
}
