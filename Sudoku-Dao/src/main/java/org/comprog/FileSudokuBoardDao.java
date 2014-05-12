package org.comprog;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.comprog.SudokuException.Type;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
  
  private String fileName;

  public FileSudokuBoardDao(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public SudokuBoard read() throws SudokuException {
    SudokuBoard obj = null;

    try (ObjectInput in = new ObjectInputStream(new FileInputStream(fileName))) {
      obj = (SudokuBoard) in.readObject();
    }
    catch (FileNotFoundException e) {
      throw new SudokuException(Type.FILE_NOT_FOUND, fileName);
    }
    catch (IOException e) {
      throw new SudokuException(Type.IO_EXCEPTION, "");
    }
    catch (ClassNotFoundException e) {
      throw new SudokuException(Type.CLASS_NOT_FOUND, "");
    }

    return obj;
  }

  @Override
  public void write(SudokuBoard obj) throws SudokuException {
    try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName))) {
      out.writeObject(obj);
    }
    catch (FileNotFoundException e) {
      throw new SudokuException(Type.FILE_NOT_FOUND, fileName);
    }
    catch (IOException e) {
      throw new SudokuException(Type.IO_EXCEPTION, "");
    }
  }

}
