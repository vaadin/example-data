
package com.vaadin.exampledata;

import java.time.LocalDateTime;
import java.util.Random;

public class FoodProductImage extends DataType<byte[]> {

    public byte[] getValue(Random random, int seed, LocalDateTime referenceTime) {
        random.setSeed(seed);

        String[] foodData = FileCache.get("FoodProducts.txt");
        return (foodData[random.nextInt(foodData.length)].split("\t")[2]).getBytes();
    }

}
