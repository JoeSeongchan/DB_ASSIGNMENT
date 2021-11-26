package db.program;

public class Field {

  public final String tableName;
  public final String fieldName;
  public final String comparator;
  public final String value;

  public Field(String tableName, String fieldName, String comparator, String value) {
    this.tableName = tableName;
    this.fieldName = fieldName;
    this.comparator = comparator;
    this.value = value;
  }
}
