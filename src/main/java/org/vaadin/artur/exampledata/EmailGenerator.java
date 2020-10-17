package org.vaadin.artur.exampledata;

import java.util.Random;

public class EmailGenerator extends DataType<String> {

    @Override
    public String getValue(Random random, int seed) {
        String email = DataType.FIRST_NAME.getValue(random, seed).toLowerCase() + "."
                + DataType.LAST_NAME.getValue(random, seed).toLowerCase() + "@"
                + DataType.DOMAIN.getValue(random, seed);
        return email.replace(" ", "");
    }

}
