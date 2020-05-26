package org.vaadin.artur.exampledata;

import java.util.Random;

public class EmailGenerator extends DataType<String> {

    @Override
    public String getValue(Random random) {
        return DataType.FIRST_NAME.getValue(random).toLowerCase() + "."
                + DataType.LAST_NAME.getValue(random).toLowerCase() + "@" + DataType.DOMAIN.getValue(random);
    }

}
