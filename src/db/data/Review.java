package db.data;

import java.sql.Date;

public class Review {

  public final String cId;
  public final String shoppingMallDomain;
  public final String storeName;
  public final String modelName;
  public final Date registerationTime;
  public final String contents;
  public final double rating;

  public Review(String cId, String shoppingMallDomain, String storeName, String modelName,
      Date registerationTime, String contents, double rating) {
    this.cId = cId;
    this.shoppingMallDomain = shoppingMallDomain;
    this.storeName = storeName;
    this.modelName = modelName;
    this.registerationTime = registerationTime;
    this.contents = contents;
    this.rating = rating;
  }
}
