package db.data;

import java.util.List;

public class ComparatorCandidate {

  public static final List<String> NUMBER_COMP = List.of("<", ">", "<=", ">=", "=", "<>");
  public static final List<String> DATE_COMP = List.of("<", ">", "<=", ">=", "=", "<>");
  public static final List<String> STRING_COMP = List.of("=", "<>");
}
