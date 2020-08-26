package org.vaadin.artur.exampledata;

import java.util.Random;

public class PhoneNumberGenerator extends DataType<String> {
    private static int[] COUNTRY_CODES = new int[] {354, 91, 62, 98, 964, 353, 44, 972, 39, 225};

    @Override
    public String getValue(Random random) {
        StringBuilder phoneNumber = new StringBuilder("+");
        phoneNumber.append(COUNTRY_CODES[random.nextInt(COUNTRY_CODES.length)]);
        phoneNumber.append(" ");
        for (int i = 0; i < 6 + random.nextInt(3); i++) {
            phoneNumber.append(random.nextInt(10));
        }
        return phoneNumber.toString();
    }
}
