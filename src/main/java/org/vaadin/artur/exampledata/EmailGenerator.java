package org.vaadin.artur.exampledata;

import java.time.LocalDateTime;
import java.util.Random;

public class EmailGenerator extends DataType<String> {

    @Override
    public String getValue(Random random, int seed, LocalDateTime referenceTime) {
        String email = DataType.FIRST_NAME.getValue(random, seed, referenceTime).toLowerCase() + "."
                + DataType.LAST_NAME.getValue(random, seed, referenceTime).toLowerCase() + "@"
                + DataType.DOMAIN.getValue(random, seed, referenceTime);
        return email.replace(" ", "");
    }

}
