package com.memoeslink.common;

public class WeightedItem<T> {
    private T value;
    private double weight;

    public WeightedItem() {
        value = null;
        weight = 0.0D;
    }

    public WeightedItem(T value, double weight) {
        this.value = value;
        this.weight = weight;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
