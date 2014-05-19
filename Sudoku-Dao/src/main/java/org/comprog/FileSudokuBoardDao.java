package org.comprog;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.comprog.SudokuException.Type;


public class FileSudokuBoardDao implements Dao<SudokuBoard> {

  private String fileName;

  public FileSudokuBoardDao(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public SudokuBoard read() throws SudokuException {
    SudokuBoard obj = new SudokuBoard(Difficulty.NONE);

    try (FileChannel channel = FileChannel.open(Paths.get(fileName), StandardOpenOption.READ)) {
      ByteBuffer buffer = channel.map(MapMode.READ_ONLY, 0, Integer.BYTES * 81);

      for (int i = 0; i < 9; ++i) {
        for (int j = 0; j < 9; ++j) {
          obj.set(i, j, buffer.getInt());
        }
      }
    }
    catch (IOException e) {
      throw new SudokuException(Type.IO_EXCEPTION, "");
    }
    
    return obj;
  }

  @Override
  public void write(SudokuBoard obj) throws SudokuException {
      try (FileChannel channel = FileChannel.open(Paths.get(fileName), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
      ByteBuffer buffer = channel.map(MapMode.READ_WRITE, 0, Integer.BYTES * 81);

      for (int i = 0; i < 9; ++i) {
        for (int j = 0; j < 9; ++j) {
          buffer.putInt(obj.get(i, j));
        }
      }
    }
    catch (IOException e) {
      throw new SudokuException(Type.IO_EXCEPTION, "");
    }
  }

}
