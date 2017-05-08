package me.rabrg.jknn.classifier;

import me.rabrg.jknn.distance.Distance;
import me.rabrg.jknn.distance.impl.EuclideanDistance;

public abstract class Classifier {

    private final Distance distance;

    public Classifier() {
        this(new EuclideanDistance());
    }

    public Classifier(final Distance distance) {
        this.distance = distance;
    }

    public abstract void fit(double[][] features, String[] labels);

    public abstract String classify(double[] features, int k);

    protected Distance getDistance() {
        return distance;
    }

    // TODO: classifier builder class
    class Builder {

    }
}
