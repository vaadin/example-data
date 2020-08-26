package org.vaadin.artur.exampledata;

import java.util.Random;

// Note this is manually kept in sync with /data-generator.tpl.ts
public class FullNameGenerator extends DataType<String> {

    @Override
    public String getValue(Random random) {
        return DataType.FIRST_NAME.getValue(random) + " " + DataType.LAST_NAME.getValue(random);
    }

}
