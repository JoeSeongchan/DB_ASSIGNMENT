package db.program;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProgramUtilities {

  public static String makeAndList(List<Field> fieldList, String tableAlias) {
    return fieldList.stream()
        .map(field -> String.format(
            "%1$s.%2$s %3$s %4$s",
            tableAlias,
            field.fieldName,
            field.comparator,
            field.value))
        .collect(Collectors.joining("\n  AND "));
  }

  public static String getComparatorInput(List<String> candidates) {
    Scanner sc = new Scanner(System.in);
    while (true) {
      System.out.print("연산자를 입력해주세요 : ");
      String input = sc.next();
      boolean isCorrect = candidates.stream()
          .anyMatch(candidate -> input.compareTo(candidate) == 0);
      if (isCorrect) {
        return input;
      }
      System.out.println("다시 입력해주세요. ");
    }
  }

  public static int getColumnInput(int length) {
    Scanner sc = new Scanner(System.in);
    System.out.print("대소비교 하고 싶은 column 을 선택해주세요 : ");
    while (true) {
      try {
        int selection = sc.nextInt();
        if (0 < selection && selection <= length) {
          return selection;
        }
      } catch (InputMismatchException e) {
        sc = new Scanner(System.in);
      }
      System.out.print("다시 입력해주세요 : ");
    }
  }

  public static int getIntInput() {
    Scanner sc = new Scanner(System.in);
    while (true) {
      try {
        return sc.nextInt();
      } catch (InputMismatchException e) {
        sc = new Scanner(System.in);
        System.out.print("다시 입력해주세요 : ");
      }
    }
  }

  public static double getDoubleInput() {
    Scanner sc = new Scanner(System.in);
    while (true) {
      try {
        return sc.nextDouble();
      } catch (InputMismatchException e) {
        sc = new Scanner(System.in);
        System.out.print("다시 입력해주세요 : ");
      }
    }
  }

  public static String getDateInput() {
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Scanner sc = new Scanner(System.in);
    while (true) {
      String dateStr = sc.next();
      try {
        dateFormat.parse(dateStr);
        return dateStr;
      } catch (ParseException e) {
        System.out.println("[ YYYY-MM-DD ] 이 형식대로 입력해주세요. ");
        System.out.print("다시 입력해주세요 : ");
      }
    }
  }

  public static String getStringInput() {
    Scanner sc = new Scanner(System.in);
    return sc.next();
  }

  public static String putQuotationMark(String value) {
    return String.format("'%1$s'", value);
  }
}
