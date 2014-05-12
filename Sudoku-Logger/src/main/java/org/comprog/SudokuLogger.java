package org.comprog;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.comprog.SudokuException.Type;

/**
 * Sudoku wrapper for logger
 */
public class SudokuLogger {

  private static final String LOG_FILE = "Sudoku.log";

  private static SudokuLogger instance = null;
  
  private static Logger logger;
  private static FileHandler fileHandler;
  
  /**
   * 
   * @return instance of SudokuLogger
   * @throws SudokuException
   */
  public static SudokuLogger getInstance() throws SudokuException {
    if (instance == null)
      instance = new SudokuLogger();
    
    return instance;
  }
  
  private SudokuLogger() throws SudokuException {
    logger = Logger.getLogger("Sudoku");
    
    try {
      fileHandler = new FileHandler(LOG_FILE, true);
      
      SimpleFormatter formatter = new SimpleFormatter();
      fileHandler.setFormatter(formatter);
      
      logger.addHandler(fileHandler);
    }
    catch (Exception e) {
      throw new SudokuException(Type.LOGGER_EXCEPTION, LOG_FILE);
    }
  }
  
  /**
   * Logs a message to a file & stderr
   * @param level level of message
   * @param msg message to log
   */
  public void log(Level level, String msg) {
    logger.log(level, msg);
  }
  
}
