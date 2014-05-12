package org.comprog;

/**
 * Sudoku exception class
 */
public class SudokuException extends SudokuExceptionBase {

  private static final long serialVersionUID = -3987989929138826422L;

  public enum Type {
    FILE_NOT_FOUND,
    IO_EXCEPTION,
    CLASS_NOT_FOUND,
    NULL_POINTER_EXCEPTION,
    LOGGER_EXCEPTION
  }

  private Type type;

  public SudokuException(Type type, String msg) {
    super(msg);
    this.type = type;
  }

  @Override
  public String getLocalizedMessage() {
    return translationBundle.getString("exception") + " " +
           translationBundle.getString(type.toString().toLowerCase(locale)) + " " +
           getMessage();
  }

}
