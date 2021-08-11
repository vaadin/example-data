package com.vaadin.exampledata;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class UUIDDataType extends DataType<UUID> {

    @Override
    public UUID getValue(Random random, int seed, LocalDateTime referenceTime) {
        return java.util.UUID.randomUUID();
    }

}
