package org.comprog.SudokuLab5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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
  public SudokuBoard read() {
    SudokuBoard obj = null;
    
    try (ObjectInput in = new ObjectInputStream(new FileInputStream(fileName))) {
      obj = (SudokuBoard) in.readObject();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    return obj;
  }

  @Override
  public void write(SudokuBoard obj) {
    try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName))) {
      out.writeObject(obj);
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
  }

}
