package org.vaadin.artur.exampledata;

import java.time.LocalDate;
import java.util.Random;

public abstract class DataType<F> {

    public static final DataType<Integer> ID = new IdDataType();
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
    public static final DataType<Integer> AMOUNT_OF_MONEY = new IntegerProvider(1000, 100000);
    public static final DataType<Double> PRICE = new ChanceDoubleType("floating", "{min: 1, max: 500, fixed: 2}");
    public static final DataType<LocalDate> DATE_OF_BIRTH = new ChanceLocalDateType("birthday", "{string: true}");
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

    protected DataType() {
        //
    }

    public abstract F getValue(Random random, int seed);

}
