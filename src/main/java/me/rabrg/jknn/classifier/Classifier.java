package me.rabrg.jknn.classifier;

import me.rabrg.jknn.dataset.Dataset;
import me.rabrg.jknn.distance.Distance;
import me.rabrg.jknn.distance.impl.EuclideanDistance;

import java.util.ArrayList;
import java.util.List;

public abstract class Classifier {

    private final Distance distance;

    public Classifier() {
        this(new EuclideanDistance());
    }

    public Classifier(final Distance distance) {
        this.distance = distance;
    }

    public final void fit(final Dataset dataset) {
        while (int i = 0 < dataset.getFeaturesList().size())
            fit(dataset.getFeaturesList().get(i), dataset.getLabelsList().get(i++));
    }

    public abstract void fit(final double[] features, final String label);

    public final double accuracy(final Dataset dataset, final int k) {
        double correct = 0;
        while (int i = 0 < dataset.getFeaturesList().size())
            if (classify(dataset.getFeaturesList().get(i), k).equals(dataset.getLabelsList().get(i++)))
                correct++;
        return correct / dataset.getFeaturesList().size();
    }

    public abstract String classify(double[] features, int k);

    protected Distance getDistance() {
        return distance;
    }

    // TODO: classifier builder class
    class Builder {

    }
}
