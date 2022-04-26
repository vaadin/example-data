
package com.vaadin.exampledata;

import java.time.LocalDateTime;
import java.util.Random;

public class FoodProductImageBytes extends DataType<byte[]> {

    public byte[] getValue(Random random, int seed, LocalDateTime referenceTime) {
        FoodProductImage foodProductImage = new FoodProductImage();
        return foodProductImage.getValue(random, seed, referenceTime).getBytes();
    }

}
