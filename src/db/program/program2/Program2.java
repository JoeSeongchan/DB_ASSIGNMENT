package db.program.program2;

import static db.program.ProgramUtilities.getStringInput;
import static db.program.ProgramUtilities.makeAndList;
import static db.program.ProgramUtilities.putQuotationMark;

import db.Db;
import db.data.OldPrice;
import db.data.OldPrice.Key;
import db.program.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Program2 {

  private final Db db;

  private Program2() throws SQLException {
    this.db = new Db();
  }

  public static Program2 create() throws SQLException {
    return new Program2();
  }

  private Field getModelFieldInput() {
    while (true) {
      System.out.print("모델 명 : ");
      String model = getStringInput();
      if (Key.MODEL_NAME.predicate().test(model)) {
        return new Field(OldPrice.Key.TABLE_NAME,
            OldPrice.Key.MODEL_NAME.name(),
            "=",
            putQuotationMark(model));
      }
      System.out.println("다시 입력해주세요.");
    }

  }

  public ResultSet run()
      throws SQLException {
    System.out.println();
    System.out.println("=====================");
    System.out.println("/ 이전 가격 확인 기능 (30일 이내) /");
    Field modelField = getModelFieldInput();
    System.out.println("---------------------");
    String sql = String.format("SELECT *\n"
            + "  FROM old_price AS o\n"
            + "  WHERE %1$s\n"
            + "  AND o.measurement_date > NOW() - INTERVAL 30 DAY;",
        makeAndList(List.of(modelField), "o").replace("AND", ""));
    System.out.printf("SQL 문 : \n%1$s\n", sql);
    return db.connection.prepareStatement(sql).executeQuery();
  }
}
