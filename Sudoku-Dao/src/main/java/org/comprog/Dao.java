package org.comprog;

import java.io.Serializable;

/**
 * Interface providing methods for serialization
 */
public interface Dao<T extends Serializable> {
  /**
   * Reads a serialized object form a file
   * @return serialized object
   * @throws Exception
   */
  public T read() throws Exception;

  /**
   * Writes an object to a file
   * @param obj object to serialize
   * @throws Exception
   */
  public void write(T obj) throws Exception;
}
