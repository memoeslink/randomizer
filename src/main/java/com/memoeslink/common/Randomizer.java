package com.memoeslink.common;

import java.util.*;

public class Randomizer {
    private static final HashMap<Integer, Double> ARRAY_WEIGHT_REGISTRY = new HashMap<>();
    private Long seed;
    private Random r;

    public Randomizer() {
        this(null);
    }

    public Randomizer(Long seed) {
        bindSeed(seed);
    }

    public Long getSeed() {
        return seed;
    }

    public void bindSeed(Long seed) {
        this.seed = seed;

        if (seed != null) r = new Random(seed);
        else r = new Random();
    }

    public void unbindSeed() {
        bindSeed(null);
    }

    public boolean getBoolean() {
        return r.nextBoolean();
    }

    public int getInt() {
        return r.nextInt();
    }

    public int getInt(int n) {
        if (n < 1) n = 1;
        return r.nextInt(n);
    }

    public int getInt(int origin, int bound) {
        if (bound <= origin) return 0;
        return r.nextInt(bound - origin) + origin; //r.nextInt(origin, bound);
    }

    public int getIntInRange(int min, int max) {
        if (max > min) return r.nextInt(max - min + 1) + min;

        if (min == max) return min;
        return 0;
    }

    public List<Integer> getInts(int size) {
        if (size <= 0) return new ArrayList<>();
        List<Integer> ints = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            ints.add(getInt());
        }
        return ints;
    }

    public List<Integer> getInts(int size, int n) {
        if (size <= 0) return new ArrayList<>();
        List<Integer> ints = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            ints.add(getInt(n));
        }
        return ints;
    }

    public List<Integer> getInts(int size, int origin, int bound) {
        if (size <= 0) return new ArrayList<>();
        List<Integer> ints = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            ints.add(getInt(origin, bound));
        }
        return ints;
    }

    public List<Integer> getIntsInRange(int size, int min, int max) {
        if (size <= 0) return new ArrayList<>();
        List<Integer> ints = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            ints.add(getIntInRange(min, max));
        }
        return ints;
    }

    public long getLong() {
        return r.nextLong();
    }

    public long getLong(long n) {
        if (n < 1L) n = 1L;
        return (long) (r.nextDouble() * n); //r.nextLong(n);
    }

    public long getLong(long origin, long bound) {
        if (bound <= origin) return 0L;
        return (long) ((bound - origin) * r.nextDouble()) + origin; //r.nextLong(origin, bound);
    }

    public long getLongInRange(long min, long max) {
        if (max > min) return (long) (r.nextDouble() * (max - min + 1L)) + min; //r.nextLong(max - min + 1) + min;

        if (min == max) return min;
        return 0L;
    }

    public List<Long> getLongs(int size) {
        if (size <= 0) return new ArrayList<>();
        List<Long> longs = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            longs.add(getLong());
        }
        return longs;
    }

    public List<Long> getLongs(int size, long n) {
        if (size <= 0) return new ArrayList<>();
        List<Long> longs = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            longs.add(getLong(n));
        }
        return longs;
    }

    public List<Long> getLongs(int size, long origin, long bound) {
        if (size <= 0) return new ArrayList<>();
        List<Long> longs = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            longs.add(getLong(origin, bound));
        }
        return longs;
    }

    public List<Long> getLongsInRange(int size, long min, long max) {
        if (size <= 0) return new ArrayList<>();
        List<Long> longs = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            longs.add(getLongInRange(min, max));
        }
        return longs;
    }

    public float getFloat() {
        return r.nextFloat();
    }

    public float getFloat(float n) {
        if (n <= 0.0F) return 0.0F;
        return r.nextFloat() * n; //r.nextFloat(n);
    }

    public float getFloat(float origin, float bound) {
        if (bound <= origin) return 0.0F;
        return r.nextFloat() * (bound - origin) + origin; //r.nextFloat(origin, bound);
    }

    public float getFloatInRange(float min, float max) {
        if (max > min) return r.nextFloat() * (max - min + Float.MIN_VALUE) + min;

        if (min == max) return min;
        return 0.0F;
    }

    public List<Float> getFloats(int size) {
        if (size <= 0) return new ArrayList<>();
        List<Float> floats = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            floats.add(getFloat());
        }
        return floats;
    }

    public List<Float> getFloats(int size, float n) {
        if (size <= 0) return new ArrayList<>();
        List<Float> floats = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            floats.add(getFloat(n));
        }
        return floats;
    }

    public List<Float> getFloats(int size, float origin, float bound) {
        if (size <= 0) return new ArrayList<>();
        List<Float> floats = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            floats.add(getFloat(origin, bound));
        }
        return floats;
    }

    public List<Float> getFloatsInRange(int size, float min, float max) {
        if (size <= 0) return new ArrayList<>();
        List<Float> floats = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            floats.add(getFloatInRange(min, max));
        }
        return floats;
    }

    public double getDouble() {
        return r.nextDouble();
    }

    public double getDouble(double n) {
        if (n <= 0.0D) return 0.0D;
        return r.nextDouble() * n; //r.nextDouble(n);
    }

    public double getDouble(double origin, double bound) {
        if (bound <= origin) return 0.0D;
        return r.nextDouble() * (bound - origin) + origin; //r.nextDouble(origin, bound);
    }

    public double getDoubleInRange(double min, double max) {
        if (max > min) return r.nextDouble() * (max - min + Double.MIN_VALUE) + min;

        if (min == max) return min;
        return 0.0D;
    }

    public List<Double> getDoubles(int size) {
        if (size <= 0) return new ArrayList<>();
        List<Double> doubles = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            doubles.add(getDouble());
        }
        return doubles;
    }

    public List<Double> getDoubles(int size, double n) {
        if (size <= 0) return new ArrayList<>();
        List<Double> doubles = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            doubles.add(getDouble(n));
        }
        return doubles;
    }

    public List<Double> getDoubles(int size, double origin, double bound) {
        if (size <= 0) return new ArrayList<>();
        List<Double> doubles = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            doubles.add(getDouble(origin, bound));
        }
        return doubles;
    }

    public List<Double> getDoublesInRange(int size, double min, double max) {
        if (size <= 0) return new ArrayList<>();
        List<Double> doubles = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            doubles.add(getDoubleInRange(min, max));
        }
        return doubles;
    }

    public double getGaussian() {
        return r.nextGaussian();
    }

    public double getGaussian(double mean, double stdDeviation) {
        if (stdDeviation < 0.0D) return 0.0D;
        return r.nextGaussian() * stdDeviation + mean; //r.nextGaussian(mean, stdDeviation);
    }

    public double getGaussian(double mean, double stdDeviation, double constraint) {
        for (int tries = 100; tries > 0; tries--) {
            double value = getGaussian(mean, stdDeviation);

            if (value <= constraint) return value;
        }
        return constraint;
    }

    public int getGaussianInt() {
        return (int) Math.round(getGaussian());
    }

    public int getGaussianInt(double mean, double stdDeviation) {
        return (int) Math.round(getGaussian(mean, stdDeviation));
    }

    public int getGaussianInt(double mean, double stdDeviation, double constraint) {
        return (int) Math.round(getGaussian(mean, stdDeviation, constraint));
    }

    private double computeTotalWeight(WeightedItem<?>[] weightedItems, int registryKey) {
        double completeWeight = ARRAY_WEIGHT_REGISTRY.getOrDefault(registryKey, 0.0D);

        if (completeWeight == 0.0D) {
            for (WeightedItem<?> item : weightedItems) {
                completeWeight += item.getWeight();
            }
            ARRAY_WEIGHT_REGISTRY.put(registryKey, completeWeight);
        }
        return completeWeight;
    }

    private <T> T pickFromWeightedItems(WeightedItem<T>[] weightedItems, double completeWeight) {
        double probability = getDouble() * completeWeight;
        double weight = 0.0D;

        for (WeightedItem<T> item : weightedItems) {
            weight += item.getWeight();
            if (weight > 0.0D && weight >= probability) return item.getValue();
        }
        return null;
    }

    public <T> T getElementBasedOnWeight(WeightedItem<T>[] weightedItems) {
        if (weightedItems == null || weightedItems.length == 0) return null;
        int key = System.identityHashCode(weightedItems);
        double completeWeight = computeTotalWeight(weightedItems, key);
        return pickFromWeightedItems(weightedItems, completeWeight);
    }

    public <T> T getElementBasedOnWeight(List<WeightedItem<T>> weightedItems) {
        if (weightedItems == null || weightedItems.isEmpty()) return null;
        int key = System.identityHashCode(weightedItems);
        double completeWeight = ARRAY_WEIGHT_REGISTRY.getOrDefault(key, 0.0D);

        if (completeWeight == 0.0D) {
            for (WeightedItem<T> item : weightedItems) {
                completeWeight += item.getWeight();
            }
            ARRAY_WEIGHT_REGISTRY.put(key, completeWeight);
        }
        double probability = getDouble() * completeWeight;
        double weight = 0.0D;

        for (WeightedItem<T> item : weightedItems) {
            weight += item.getWeight();
            if (weight > 0.0D && weight >= probability) return item.getValue();
        }
        return null;
    }

    public <T> T getElementBasedOnWeight(Set<WeightedItem<T>> weightedItems) {
        if (weightedItems == null || weightedItems.isEmpty()) return null;
        int key = System.identityHashCode(weightedItems);
        double completeWeight = ARRAY_WEIGHT_REGISTRY.getOrDefault(key, 0.0D);

        if (completeWeight == 0.0D) {
            for (WeightedItem<T> item : weightedItems) {
                completeWeight += item.getWeight();
            }
            ARRAY_WEIGHT_REGISTRY.put(key, completeWeight);
        }
        double probability = getDouble() * completeWeight;
        double weight = 0.0D;

        for (WeightedItem<T> item : weightedItems) {
            weight += item.getWeight();
            if (weight > 0.0D && weight >= probability) return item.getValue();
        }
        return null;
    }

    public <T, U> U getElementBasedOnWeight(Map<T, WeightedItem<U>> weightedItems) {
        if (weightedItems == null || weightedItems.isEmpty()) return null;
        int key = System.identityHashCode(weightedItems);
        double completeWeight = ARRAY_WEIGHT_REGISTRY.getOrDefault(key, 0.0D);

        if (completeWeight == 0.0D) {
            for (WeightedItem<U> item : weightedItems.values()) {
                completeWeight += item.getWeight();
            }
            ARRAY_WEIGHT_REGISTRY.put(key, completeWeight);
        }
        double probability = getDouble() * completeWeight;
        double weight = 0.0D;

        for (Map.Entry<T, WeightedItem<U>> entry : weightedItems.entrySet()) {
            weight += entry.getValue().getWeight();
            if (weight > 0.0D && weight >= probability) return entry.getValue().getValue();
        }
        return null;
    }

    public <T extends Enum<?>> T getEnumBasedOnWeight(Map<T, Double> weightedItems) {
        if (weightedItems == null || weightedItems.isEmpty()) return null;
        int key = System.identityHashCode(weightedItems);
        double completeWeight = ARRAY_WEIGHT_REGISTRY.getOrDefault(key, 0.0D);

        if (completeWeight == 0.0D) {
            for (double w : weightedItems.values()) {
                completeWeight += w;
            }
            ARRAY_WEIGHT_REGISTRY.put(key, completeWeight);
        }
        double probability = getDouble() * completeWeight;
        double weight = 0.0D;

        for (Map.Entry<T, Double> entry : weightedItems.entrySet()) {
            weight += entry.getValue();
            if (weight > 0.0D && weight >= probability) return entry.getKey();
        }
        return null;
    }

    public <T> T getElement(T[] array) {
        if (array == null || array.length == 0) return null;
        return array[r.nextInt(array.length)];
    }

    public <T> T getElement(List<T> list) {
        if (list == null || list.isEmpty()) return null;
        return list.get(r.nextInt(list.size()));
    }

    public <T> T getElement(Set<T> set) {
        if (set == null || set.isEmpty()) return null;
        int index = r.nextInt(set.size());

        if (set instanceof LinkedHashSet) {
            for (T item : set) {
                if (index-- == 0) return item;
            }
            return null;
        }
        Iterator<T> it = set.iterator();

        for (int i = 0; i < index; i++) it.next();
        return it.next();
    }

    public <T, U> U getElement(Map<T, U> map) {
        if (map == null || map.isEmpty()) return null;
        int index = r.nextInt(map.size());

        if (map instanceof LinkedHashMap) {
            for (Map.Entry<T, U> entry : map.entrySet()) {
                if (index-- == 0) return entry.getValue();
            }
            return null;
        }
        Iterator<Map.Entry<T, U>> it = map.entrySet().iterator();

        for (int i = 0; i < index; i++) it.next();
        return it.next().getValue();
    }

    public <T extends Enum<?>> T getEnum(Class<T> clazz) {
        if (clazz == null) return null;
        T[] constants = clazz.getEnumConstants();

        if (constants.length == 0) return null;
        return constants[r.nextInt(constants.length)];
    }
}
