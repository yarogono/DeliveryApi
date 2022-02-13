package arthur.deliveryapi.exception;

public final class ExceptionMessages {

    // FOOD
    public static final String RESTAURANT_FOOD_DUPLICATE = "중복된 이름의 음식이 존재합니다.";
    public static final String FOOD_PRICES_TOO_LOW = "음식 가격이 100원 미만입니다.";
    public static final String FOOD_PRICES_TOO_HIGH = "음식 가격이 1,000,000원을 초과했습니다.";
    public static final String ILLEGAL_FOOD_PRICES_UNIT = "음식 가격이 100원 단위로 입력되었습니다.";

    public static final String RESTAURANT_IS_NULL = "레스토랑이 없습니다.";

}
