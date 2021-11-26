package db.program.program1;

import static db.program.ProgramUtilities.getColumnInput;
import static db.program.ProgramUtilities.getComparatorInput;
import static db.program.ProgramUtilities.getDateInput;
import static db.program.ProgramUtilities.getDoubleInput;
import static db.program.ProgramUtilities.getIntInput;
import static db.program.ProgramUtilities.getStringInput;
import static db.program.ProgramUtilities.makeAndList;
import static db.program.ProgramUtilities.putQuotationMark;

import db.Db;
import db.data.Goods;
import db.data.Goods.Key;
import db.data.Laptop;
import db.program.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

public class Program1 {

  private final Db db;

  private Program1() throws SQLException {
    db = new Db();
  }

  public static Program1 create() throws SQLException {
    return new Program1();
  }

  private void printFieldList() {
    System.out.println();
    System.out.println("=====================");
    System.out.println("[제품 (goods) 속성]");
    System.out.println("1. 모델 이름");
    System.out.println("2. 카테 고리");
    System.out.println("3. 제조사");
    System.out.println("4. 출시일");
    System.out.println("5. 무게");
    System.out.println("6. 가격");
    System.out.println("[랩탑 (laptop) 속성]");
    System.out.println("7. 운영체제");
    System.out.println("8. 색깔");
    System.out.println("9. 스크린 크기");
    System.out.println("10. 프로세서 브랜드");
    System.out.println("11. 코어 수");
    System.out.println("12. 하드드라이브 크기");
    System.out.println("13. 램 크기");
    System.out.println("14. 선택 종료.");
    System.out.println("---------------------");
  }

  private Map<String, List<Field>> getAllFieldInput() {
    List<Field> goodsFieldList = new ArrayList<>();
    List<Field> laptopFieldList = new ArrayList<>();
    Field input = null;
    while (true) {
      printFieldList();
      int columnSelection = getColumnInput(Goods.Key.LENGTH + Laptop.Key.LENGTH);
      if (columnSelection <= Goods.Key.LENGTH) {
        goodsFieldList.add(getGoodsFieldInput(columnSelection));
      } else if (columnSelection <= Goods.Key.LENGTH + Laptop.Key.LENGTH - 1) {
        laptopFieldList.add(getLaptopFieldInput(columnSelection));
      } else if (columnSelection == Goods.Key.LENGTH + Laptop.Key.LENGTH) {
        break;
      }
    }
    HashMap<String, List<Field>> result = new HashMap<>();
    result.put("GOODS_FIELD_LIST", goodsFieldList);
    result.put("LAPTOP_FIELD_LIST", laptopFieldList);
    return result;
  }

  private Field getGoodsFieldInput(int selection) {
    while (true) {
      switch (selection) {
        case 1 -> {
          System.out.print("모델 이름 : ");
          String model = getStringInput();
          if (Key.MODEL_NAME.predicate().test(model)) {
            return new Field(Key.TABLE_NAME,
                Key.MODEL_NAME.name(),
                getComparatorInput(Key.MODEL_NAME.comparatorCandidates()),
                putQuotationMark(model));
          }
        }
        case 2 -> {
          System.out.print("카테 고리 : ");
          String category = getStringInput();
          if (Key.CATEGORY.predicate().test(category)) {
            return new Field(Key.TABLE_NAME,
                Key.CATEGORY.name(),
                getComparatorInput(Key.CATEGORY.comparatorCandidates()),
                putQuotationMark(category));
          }
        }
        case 3 -> {
          System.out.print("제조사 : ");
          String manufacturer = getStringInput();
          if (Key.MANUFACTURER.predicate().test(manufacturer)) {
            return new Field(Key.TABLE_NAME,
                Key.MANUFACTURER.name(),
                getComparatorInput(Key.MANUFACTURER.comparatorCandidates()),
                putQuotationMark(manufacturer));
          }
        }
        case 4 -> {
          System.out.print("출시일 : ");
          String releaseDate = getDateInput();
          if (Key.RELEASE_DATE.predicate().test(releaseDate)) {
            return new Field(Key.TABLE_NAME,
                Key.RELEASE_DATE.name(),
                getComparatorInput(Key.RELEASE_DATE.comparatorCandidates()),
                putQuotationMark(releaseDate));
          }
        }
        case 5 -> {
          System.out.print("제품 무게 : ");
          Double productWeight = getDoubleInput();
          if (Key.PRODUCT_WEIGHT.predicate().test(productWeight)) {
            return new Field(Key.TABLE_NAME,
                Key.PRODUCT_WEIGHT.name(),
                getComparatorInput(Key.PRODUCT_WEIGHT.comparatorCandidates()),
                productWeight.toString());
          }
        }
        case 6 -> {
          System.out.print("가격 : ");
          Integer price = getIntInput();
          if (Key.PRICE.predicate().test(price)) {
            return new Field(Key.TABLE_NAME,
                Key.PRICE.name(),
                getComparatorInput(Key.PRICE.comparatorCandidates()),
                price.toString());
          }
        }
        default -> {
        }
      }
      System.out.println("다시 입력해주세요.");
    }
  }

  private Field getLaptopFieldInput(int selection) {
    while (true) {
      switch (selection) {
        case 7 -> {
          System.out.print("운영체제 : ");
          String operatingSystem = getStringInput();
          if (Laptop.Key.OPERATING_SYSTEM.predicate().test(operatingSystem)) {
            return new Field(Laptop.Key.TABLE_NAME,
                Laptop.Key.OPERATING_SYSTEM.name(),
                getComparatorInput(Laptop.Key.OPERATING_SYSTEM.comparatorCandidates()),
                putQuotationMark(operatingSystem));
          }
        }
        case 8 -> {
          System.out.print("색깔 : ");
          String color = getStringInput();
          if (Laptop.Key.COLOR.predicate().test(color)) {
            return new Field(Laptop.Key.TABLE_NAME,
                Laptop.Key.COLOR.name(),
                getComparatorInput(Laptop.Key.COLOR.comparatorCandidates()),
                putQuotationMark(color));
          }
        }
        case 9 -> {
          System.out.print("화면 크기 (인치) : ");
          Double screenSizeInch = getDoubleInput();
          if (Laptop.Key.SCREEN_SIZE_INCH.predicate().test(screenSizeInch)) {
            return new Field(Laptop.Key.TABLE_NAME,
                Laptop.Key.SCREEN_SIZE_INCH.name(),
                getComparatorInput(Laptop.Key.SCREEN_SIZE_INCH.comparatorCandidates()),
                screenSizeInch.toString());
          }
        }
        case 10 -> {
          System.out.print("프로세서 브랜드 : ");
          String processorBrand = getStringInput();
          if (Laptop.Key.PROCESSOR_BRAND.predicate().test(processorBrand)) {
            return new Field(Laptop.Key.TABLE_NAME,
                Laptop.Key.PROCESSOR_BRAND.name(),
                getComparatorInput(Laptop.Key.PROCESSOR_BRAND.comparatorCandidates()),
                putQuotationMark(processorBrand));
          }
        }

        case 11 -> {
          try {
            System.out.print("코어 수 : ");
            Integer coreCount = getIntInput();
            if (Laptop.Key.CORE_COUNT.predicate().test(coreCount)) {
              return new Field(Laptop.Key.TABLE_NAME,
                  Laptop.Key.CORE_COUNT.name(),
                  getComparatorInput(Laptop.Key.CORE_COUNT.comparatorCandidates()),
                  coreCount.toString());
            }
          } catch (InputMismatchException e) {
            continue;
          }
        }
        case 12 -> {
          System.out.print("하드 디스크 크기 : ");
          Integer hardDriveSize = getIntInput();
          if (Laptop.Key.HARD_DRIVE_SIZE.predicate().test(hardDriveSize)) {
            return new Field(Laptop.Key.TABLE_NAME,
                Laptop.Key.HARD_DRIVE_SIZE.name(),
                getComparatorInput(Laptop.Key.HARD_DRIVE_SIZE.comparatorCandidates()),
                hardDriveSize.toString());
          }
        }
        case 13 -> {
          System.out.print("램 크기 : ");
          Integer ramSize = getIntInput();
          if (Laptop.Key.RAM_SIZE.predicate().test(ramSize)) {
            return new Field(Laptop.Key.TABLE_NAME,
                Laptop.Key.RAM_SIZE.name(),
                getComparatorInput(Laptop.Key.RAM_SIZE.comparatorCandidates()),
                ramSize.toString());
          }
        }
        default -> {
        }
      }
      System.out.println("다시 입력해주세요.");
    }
  }

  private double getRatingCutOffInput() {
    System.out.print("기준 별점을 입력해주세요 : ");
    while (true) {
      double selection = getDoubleInput();
      if (selection <= 0) {
        System.out.println("다시 입력해주세요. ");
      } else {
        return selection;
      }
    }
  }

  public ResultSet run()
      throws SQLException {
    System.out.println();
    System.out.println("=====================");
    System.out.println("/ 상세 검색 기능 /");
    Map<String, List<Field>> allFieldInput = getAllFieldInput();
    List<Field> goodsFieldList = allFieldInput.get("GOODS_FIELD_LIST");
    List<Field> laptopFieldList = allFieldInput.get("LAPTOP_FIELD_LIST");
    System.out.println("---------------------");
    double ratingCutOffPoint = getRatingCutOffInput();
    String sql = String.format("SELECT *\n"
            + "  FROM goods AS g NATURAL JOIN\n"
            + "  laptop AS l NATURAL JOIN\n"
            + "  review AS r\n"
            + "  WHERE model_name IN\n"
            + "  ( SELECT model_name\n"
            + "  FROM review AS rr\n"
            + "  GROUP BY rr.model_name\n"
            + "  HAVING avg(rating) >= %1$s)\n"
            + "  %2$s\n"
            + "  %3$s"
            + ";",
        ratingCutOffPoint,
        goodsFieldList.size() == 0 ? "" : "AND " +
            makeAndList(goodsFieldList, "g"),
        laptopFieldList.size() == 0 ? "" : "AND " +
            makeAndList(laptopFieldList, "l")
    );
    System.out.printf("sql : \n%1$s\n", sql);
    return db.connection.prepareStatement(sql).executeQuery();
  }
}
