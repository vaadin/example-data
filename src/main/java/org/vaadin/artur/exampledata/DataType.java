package org.vaadin.artur.exampledata;

import java.util.Random;

public abstract class DataType<F> {

    public static final DataType<Integer> ID = new IdDataType();
    public static final DataType<String> FIRST_NAME = new DataTypeWithRandomOptions("FirstName.txt");
    public static final DataType<String> LAST_NAME = new DataTypeWithRandomOptions("LastName.txt");
    public static final DataType<String> COMPANY_NAME = new DataTypeWithRandomOptions("CompanyName.txt");
    public static final DataType<String> DOMAIN = new DataTypeWithRandomOptions("Domain.txt");
    public static final DataType<String> IBAN = new DataTypeWithRandomOptions("IBAN.txt");
    public static final DataType<String> OCCUPATION = new DataTypeWithRandomOptions("Occupation.txt");
    public static final DataType<String> TRANSACTION_STATUS = new DataTypeWithRandomOptions("TransactionStatus.txt");
    public static final DataType<String> EMAIL = new EmailGenerator();
    public static final DataType<String> PROFILE_PICTURE_URL = new DataTypeWithRandomOptions("ProfilePictureURL.txt");
    public static final DataType<Integer> AMOUNT_OF_MONEY = new IntegerProvider(1000, 100000);

    protected DataType() {
        //
    }

    public abstract F getValue(Random random);

}