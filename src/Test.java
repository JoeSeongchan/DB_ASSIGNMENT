import db.program.program1.Program1;
import db.program.program2.Program2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {

  // `customer` table 안에 있는 모든 tuple 정보를 출력하는 함수.
  private static void printResultOfProgram1(ResultSet resultSet) {
    System.out.println();
    System.out.println("=====================");
    System.out.println("/ 결과 /");
    try {
      while (resultSet.next()) {
        System.out.println("---------------------");
        System.out.println("모델명 : " + resultSet.getString("model_name"));
        System.out.println("제조사 : " + resultSet.getString("manufacturer"));
        System.out.println("별점 : " + resultSet.getString("rating"));
        System.out.println("가격 : " + resultSet.getString("price"));
        System.out.println();
      }
    } catch (SQLException e) {
      System.err.println("Query 결과 출력 중 오류 발생 : " + e.getMessage() + "\n");
    }
  }

  private static void printResultOfProgram2(ResultSet resultSet) {
    System.out.println();
    System.out.println("=====================");
    System.out.println("/ 결과 /");
    try {
      while (resultSet.next()) {
        System.out.println("---------------------");
        System.out.println("모델명 : " + resultSet.getString("model_name"));
        System.out.println("일시 : " + resultSet.getString("measurement_date"));
        System.out.println("가격 : " + resultSet.getString("price"));
        System.out.println();
      }
    } catch (SQLException e) {
      System.out.println("Query 결과 출력 중 오류 발생 : " + e.getMessage() + "\n");
    }
  }

  public static void main(String[] args) {
    run();
  }

  private static void run() {
    Scanner sc = new Scanner(System.in);
    outerWhile:
    while (true) {
      printMenu();
      try {
        System.out.print("기능을 선택하세요 : ");
        int menuSelection = sc.nextInt();
        switch (menuSelection) {
          case 1 -> {
            ResultSet result1 = Program1.create().run();
            printResultOfProgram1(result1);
          }
          case 2 -> {
            ResultSet result2 = Program2.create().run();
            printResultOfProgram2(result2);
          }
          case 3 -> {
            System.out.println("프로그램을 종료합니다.");
            break outerWhile;
          }
          default -> {
            System.out.println("잘못된 값을 입력하셨습니다.");
            System.out.println("다시 입력해주세요.");
          }
        }
      } catch (InputMismatchException e) {
        sc = new Scanner(System.in);
        System.out.println("다시 입력해주세요.");
      } catch (SQLException e) {
        System.out.println("error!");
        System.out.println(e.getMessage());
        System.out.println(e.getSQLState());
        break;
      }
    }
  }

  private static void printMenu() {
    System.out.println();
    System.out.println("=====================");
    System.out.println("/ 기능 선택 /");
    System.out.println("1. 제품 (랩톱) 상세 검색");
    System.out.println("2. 제품 과거 가격 검색");
    System.out.println("3. 프로그램 종료");
    System.out.println("---------------------");
  }
}
