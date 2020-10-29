
package org.vaadin.artur.exampledata;

import java.util.Random;

public class FoodProductImage extends DataType<String> {

    public String getValue(Random random, int seed) {
        random.setSeed(seed);

        String[] foodData = FileCache.get("FoodProducts.txt");
        return foodData[random.nextInt(foodData.length)].split("\t")[2];
    }

}
