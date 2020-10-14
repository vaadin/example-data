package org.vaadin.artur.exampledata;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.Random;
import java.util.function.BiConsumer;

import com.vaadin.flow.component.dependency.NpmPackage;

@NpmPackage(value = "chance", version = "1.1.7")
public class ExampleDataGenerator<T> {

    private Class<T> type;
    private Random random = new Random();
    private Map<BiConsumer<T, ?>, DataType<?>> setters = new LinkedHashMap<>();

    public ExampleDataGenerator(Class<T> type, long seed) {
        this.type = type;
        setSeed(seed);
    }

    public <F> void setData(BiConsumer<T, F> setter, DataType<F> dataType) {
        this.setters.put(setter, dataType);
    }

    public void setSeed(long seed) {
        random.setSeed(seed);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public T createBean(int seed) {
        try {
            T bean = type.newInstance();
            if (seed == 0) {
                seed = System.identityHashCode(bean);
            }
            for (Entry<BiConsumer<T, ?>, DataType<?>> entry : setters.entrySet()) {
                assignValue(bean, (BiConsumer) entry.getKey(), entry.getValue(), seed);
            }
            return bean;
        } catch (Exception e) {
            throw new RuntimeException("Unable to create bean instance of type " + type.getName(), e);
        }

    }

    private <F> void assignValue(T bean, BiConsumer<T, F> setter, DataType<F> dataType, int seed) {
        setter.accept(bean, dataType.getValue(random, seed));
    }

    public List<T> create(int count) {
        List<T> beans = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            beans.add(createBean(0));
        }
        return beans;
    }
}