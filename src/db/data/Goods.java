package db.data;

import java.sql.Date;
import java.util.List;
import java.util.function.Predicate;

public class Goods {

  public final String modelName;
  public final String category;
  public final String manufacturer;
  public final Date releaseDate;
  public final boolean productWeight;
  public final int price;

  public Goods(String modelName, String category, String manufacturer, Date releaseDate,
      boolean productWeight, int price) {
    this.modelName = modelName;
    this.category = category;
    this.manufacturer = manufacturer;
    this.releaseDate = releaseDate;
    this.productWeight = productWeight;
    this.price = price;
  }

  public static record Key<T>(String name, String manual,
                              Predicate<T> predicate,
                              List<String> comparatorCandidates) {

    public static final Key<String> MODEL_NAME = new Key<>(
        "model_name",
        "모델명",
        str -> str.length() <= 20, ComparatorCandidate.STRING_COMP);
    public static final Key<String> CATEGORY = new Key<>(
        "category",
        "카테고리",
        str -> str.length() <= 15, ComparatorCandidate.STRING_COMP);
    public static final Key<String> MANUFACTURER = new Key<>(
        "manufacturer",
        "제조회사",
        str -> str.length() <= 15, ComparatorCandidate.STRING_COMP);
    public static final Key<Double> PRODUCT_WEIGHT = new Key<>(
        "product_weight",
        "무게",
        value -> value < 100 && value > 0, ComparatorCandidate.NUMBER_COMP);
    public static final Key<Integer> PRICE = new Key<>(
        "price",
        "가격",
        value -> value > 0, ComparatorCandidate.NUMBER_COMP);
    public static final int LENGTH = 6;
    public static final String TABLE_NAME = "goods";
    public static final Key<String> RELEASE_DATE = new Key<>(
        "release_date",
        "출시일",
        dateStr -> true, ComparatorCandidate.DATE_COMP);
  }
}
