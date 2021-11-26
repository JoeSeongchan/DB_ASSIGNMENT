package db.data;

import java.util.List;
import java.util.function.Predicate;

public class Laptop {

  public final String modelName;
  public final String operatingSystem;
  public final String color;
  public final boolean screenSizzeinch;
  public final String processorBrand;
  public final int coreCount;
  public final int hardDriveSize;
  public final int ramSize;

  public Laptop(String modelName, String operatingSystem, String color, boolean screenSizzeinch,
      String processorBrand, int coreCount, int hardDriveSize, int ramSize) {
    this.modelName = modelName;
    this.operatingSystem = operatingSystem;
    this.color = color;
    this.screenSizzeinch = screenSizzeinch;
    this.processorBrand = processorBrand;
    this.coreCount = coreCount;
    this.hardDriveSize = hardDriveSize;
    this.ramSize = ramSize;
  }

  public static record Key<T>(String name, String manual,
                              Predicate<T> predicate,
                              List<String> comparatorCandidates) {

    public static final Key<String> MODEL_NAME = new Key<>(
        "model_name",
        "모델명",
        str -> str.length() <= 20,
        ComparatorCandidate.STRING_COMP);
    public static final Key<String> OPERATING_SYSTEM = new Key<>(
        "operating_system",
        "운영체제",
        str -> str.length() <= 10,
        ComparatorCandidate.STRING_COMP);
    public static final Key<String> COLOR = new Key<>(
        "color",
        "색깔",
        str -> str.length() <= 10,
        ComparatorCandidate.STRING_COMP);
    public static final Key<Double> SCREEN_SIZE_INCH = new Key<>(
        "screen_size_inch",
        "스크린 사이즈",
        value -> value < 100 && value > 0, ComparatorCandidate.NUMBER_COMP);
    public static final Key<String> PROCESSOR_BRAND = new Key<>(
        "processor_brand",
        "프로세서 브랜드",
        str -> str.length() <= 10, ComparatorCandidate.STRING_COMP);
    public static final Key<Integer> CORE_COUNT = new Key<>(
        "core_count",
        "코어 개수",
        value -> value > 0 && value <= 255, ComparatorCandidate.NUMBER_COMP);
    public static final Key<Integer> HARD_DRIVE_SIZE = new Key<>(
        "hard_drive_size",
        "하드 드라이브 크기",
        value -> value > 0, ComparatorCandidate.NUMBER_COMP);
    public static final Key<Integer> RAM_SIZE = new Key<>(
        "ram_size",
        "램 사이즈",
        value -> value > 0, ComparatorCandidate.NUMBER_COMP);
    public static final int LENGTH = 8;
    public static final String TABLE_NAME = "laptop";
  }
}
