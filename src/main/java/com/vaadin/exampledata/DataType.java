package com.vaadin.exampledata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;
import java.util.UUID;

public abstract class DataType<F> {

    public static final DataType<Integer> ID = new IdDataType();
    public static final DataType<UUID> UUID = new UUIDDataType();
    public static final DataType<String> FIRST_NAME = new ChanceStringType("first");
    public static final DataType<String> LAST_NAME = new ChanceStringType("last");
    public static final DataType<String> FULL_NAME = new CombinedStringGenerator(FIRST_NAME, LAST_NAME);
    public static final DataType<String> COMPANY_NAME = new ChanceStringType("company");
    public static final DataType<String> DOMAIN = new ChanceStringType("domain");
    public static final DataType<String> IBAN = new DataTypeWithRandomOptions("IBAN.txt");
    public static final DataType<String> OCCUPATION = new DataTypeWithRandomOptions("Occupation.txt");
    public static final DataType<String> TRANSACTION_STATUS = new DataTypeWithRandomOptions("TransactionStatus.txt");
    public static final DataType<String> EMAIL = new EmailGenerator();
    public static final DataType<String> PROFILE_PICTURE_URL = new DataTypeWithRandomOptions("ProfilePictureURL.txt");
    public static final DataType<Integer> AMOUNT_OF_MONEY = new ChanceIntegerType("integer",
            "{min: 1000, max: 100000}");
    public static final DataType<Double> PRICE = new ChanceDoubleType("floating", "{min: 1, max: 500, fixed: 2}");
    public static final DataType<LocalDate> DATE_OF_BIRTH = new RandomPastDate(365 * 100);
    public static final DataType<LocalDate> DATE_LAST_10_YEARS = new RandomPastDate(365 * 10);
    public static final DataType<LocalDate> DATE_LAST_1_YEAR = new RandomPastDate(365);
    public static final DataType<LocalDate> DATE_LAST_30_DAYS = new RandomPastDate(30);
    public static final DataType<LocalDate> DATE_LAST_7_DAYS = new RandomPastDate(7);
    public static final DataType<LocalDate> DATE_NEXT_10_YEARS = new RandomFutureDate(365 * 10);
    public static final DataType<LocalDate> DATE_NEXT_1_YEAR = new RandomFutureDate(365);
    public static final DataType<LocalDate> DATE_NEXT_30_DAYS = new RandomFutureDate(30);
    public static final DataType<LocalDate> DATE_NEXT_7_DAYS = new RandomFutureDate(7);
    public static final DataType<LocalDateTime> DATETIME_LAST_10_YEARS = new RandomPastDateTime(365 * 10, false);
    public static final DataType<LocalDateTime> DATETIME_LAST_1_YEAR = new RandomPastDateTime(365, false);
    public static final DataType<LocalDateTime> DATETIME_LAST_30_DAYS = new RandomPastDateTime(30, false);
    public static final DataType<LocalDateTime> DATETIME_LAST_7_DAYS = new RandomPastDateTime(7, false);
    public static final DataType<LocalDateTime> DATETIME_NEXT_10_YEARS = new RandomFutureDateTime(365 * 10, false);
    public static final DataType<LocalDateTime> DATETIME_NEXT_1_YEAR = new RandomFutureDateTime(365, false);
    public static final DataType<LocalDateTime> DATETIME_NEXT_30_DAYS = new RandomFutureDateTime(30, false);
    public static final DataType<LocalDateTime> DATETIME_NEXT_7_DAYS = new RandomFutureDateTime(7, false);
    public static final DataType<LocalTime> TIME_RANDOM = new RandomTime(false);
    public static final DataType<LocalTime> TIME_HOURS = new RandomTime(true);
    public static final DataType<String> PHONE_NUMBER = new ChanceStringType("phone");
    public static final DataType<String> CITY = new ChanceStringType("city");
    public static final DataType<String> STATE = new ChanceStringType("state", "{ full: true }");
    public static final DataType<String> COUNTRY = new ChanceStringType("country", "{ full: true }");
    public static final DataType<String> ZIP_CODE = new ChanceStringType("zip");
    public static final DataType<String> ADDRESS = new ChanceStringType("address");
    public static final DataType<String> BOOK_TITLE_PREFIX = new DataTypeWithRandomOptions("BookTitlePrefix.txt");
    public static final DataType<String> BOOK_TITLE_SUFFIX = new DataTypeWithRandomOptions("BookTitleSuffix.txt");
    public static final DataType<String> BOOK_TITLE = new CombinedStringGenerator(BOOK_TITLE_PREFIX, BOOK_TITLE_SUFFIX);
    public static final DataType<String> BOOK_GENRE = new DataTypeWithRandomOptions("BookGenre.txt");
    public static final DataType<String> BOOK_IMAGE_BACKGROUND = new DataTypeWithRandomOptions(
            "BookImageBackground.txt");
    public static final DataType<String> BOOK_IMAGE_URL = new BookImageGenerator();
    public static final DataType<String> WORD = new ChanceStringType("word");
    public static final DataType<String> TWO_WORDS = new CombinedStringGenerator(false, 1, WORD, WORD);
    public static final DataType<String> SENTENCE = new ChanceStringType("sentence");
    public static final DataType<String> EAN13 = new EanGenerator();
    public static final DataType<Integer> NUMBER_UP_TO_10 = new ChanceIntegerType("integer", "{min: 1, max: 10}");
    public static final DataType<Integer> NUMBER_UP_TO_100 = new ChanceIntegerType("integer", "{min: 1, max: 100}");
    public static final DataType<Integer> NUMBER_UP_TO_1000 = new ChanceIntegerType("integer", "{min: 1, max: 1000}");
    public static final DataType<Integer> NUMBER_UP_TO_10000 = new ChanceIntegerType("integer", "{min: 1, max: 10000}");
    public static final DataType<String> FOOD_PRODUCT_EAN = new FoodProductEan();
    public static final DataType<String> FOOD_PRODUCT_NAME = new FoodProductName();
    public static final DataType<String> FOOD_PRODUCT_IMAGE = new FoodProductImage();
    public static final DataType<Boolean> BOOLEAN_50_50 = new ChanceBooleanType("bool", "{likelihood: 50}");
    public static final DataType<Boolean> BOOLEAN_90_10 = new ChanceBooleanType("bool", "{likelihood: 90}");
    public static final DataType<Boolean> BOOLEAN_10_90 = new ChanceBooleanType("bool", "{likelihood: 10}");

    protected DataType() {
        //
    }

    public abstract F getValue(Random random, int seed, LocalDateTime referenceTime);

}
