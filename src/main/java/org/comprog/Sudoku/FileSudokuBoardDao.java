package org.comprog.Sudoku;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

  private String fileName;
  
  public FileSudokuBoardDao(String fileName) {
    this.fileName = fileName;
  }
  
  @Override
  public SudokuBoard read() throws Exception {
    SudokuBoard obj = null;
    
    try (ObjectInput in = new ObjectInputStream(new FileInputStream(fileName))) {
      obj = (SudokuBoard) in.readObject();
    }
    catch (FileNotFoundException e) {
      System.err.println("FileNotFounException: " + e.getMessage());
      throw e;
    }
    catch (IOException e) {
      System.err.println("IOException: " + e.getMessage());
      throw e;
    }
    catch (ClassNotFoundException e) {
      System.err.println("ClassNotFoundException: " + e.getMessage());
      throw e;
    }
    
    return obj;
  }

  @Override
  public void write(SudokuBoard obj) throws Exception {
    try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName))) {
      out.writeObject(obj);
    }
    catch (FileNotFoundException e) {
      System.err.println("FileNotFounException: " + e.getMessage());
      throw e;
    }
    catch (IOException e) {
      System.err.println("IOException: " + e.getMessage());
      throw e;
    }
  }

}
