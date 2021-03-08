package org.vaadin.artur.exampledata;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.function.BiConsumer;

public class ExampleDataGenerator<T> {

    private Class<T> type;
    private Random random = new Random();
    private Map<BiConsumer<T, ?>, DataType<?>> setters = new LinkedHashMap<>();
    private LocalDateTime referenceTime;

    @Deprecated
    public ExampleDataGenerator(Class<T> type) {
        this(type, LocalDateTime.now());
    }

    public ExampleDataGenerator(Class<T> type, LocalDateTime referenceTime) {
        this.type = type;
        this.referenceTime = referenceTime;
    }

    public <F> void setData(BiConsumer<T, F> setter, DataType<F> dataType) {
        this.setters.put(setter, dataType);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public T createBean(int seed) {
        try {
            T bean = type.newInstance();
            for (Entry<BiConsumer<T, ?>, DataType<?>> entry : setters.entrySet()) {
                assignValue(bean, (BiConsumer) entry.getKey(), entry.getValue(), seed);
            }
            return bean;
        } catch (Exception e) {
            throw new RuntimeException("Unable to create bean instance of type " + type.getName(), e);
        }

    }

    private <F> void assignValue(T bean, BiConsumer<T, F> setter, DataType<F> dataType, int seed) {
        setter.accept(bean, dataType.getValue(random, seed, referenceTime));
    }

    public List<T> create(int count, int seed) {
        List<T> beans = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            beans.add(createBean(seed + i));
        }
        return beans;
    }
}