package db.data;

import java.sql.Date;
import java.util.List;
import java.util.function.Predicate;

public class OldPrice {

  public final String modelName;
  public final Date measurementTime;
  public final int price;

  public OldPrice(String modelName, Date measurementTime, int price) {
    this.modelName = modelName;
    this.measurementTime = measurementTime;
    this.price = price;
  }

  public static record Key<T>(String name, String manual,
                              Predicate<T> predicate,
                              List<String> comparatorCandidates) {

    public static final Laptop.Key<String> MODEL_NAME = new Laptop.Key<>(
        "model_name",
        "모델명",
        str -> str.length() <= 20, ComparatorCandidate.STRING_COMP);
    // 나머지 필드는 지금 필요하지 않기 때문에 생략한다.
    public static final int LENGTH = 3;
    public static final String TABLE_NAME = "old_price";
  }
}
