package me.rabrg.jknn.classifier.impl;

import me.rabrg.jknn.classifier.Classifier;
import me.rabrg.jknn.dataset.Dataset;

import java.util.*;

public final class BruteForceClassifier extends Classifier {

    private final List<double[]> trainingFeatures = new ArrayList<>();
    private final List<String> trainingLabels = new ArrayList<>();

    @Override
    public void fit(final double[] features, final String label) {
        trainingFeatures.add(features);
        trainingLabels.add(label);
    }

    @Override
    public String classify(final double[] features, final int k) {
        final Map<Double, List<String>> distanceLabelMap = distanceLabelMap(features);
        final List<String> kNearestNeighbors = kNearestNeighbors(distanceLabelMap, k);
        return mode(kNearestNeighbors);
    }

    private Map<Double, List<String>> distanceLabelMap(final double[] features) {
        final Map<Double, List<String>> map = new TreeMap<>();
        for (int i = 0; i < trainingLabels.size(); i++) {
            final double distance = getDistance().getDistance(features, trainingFeatures.get(i));
            final List<String> labels = map.getOrDefault(distance, new ArrayList<>());
            labels.add(trainingLabels.get(i));
            map.put(distance, labels);
        }
        return map;
    }

    private static List<String> kNearestNeighbors(final Map<Double, List<String>> map, final int k) {
        final List<String> list = new ArrayList<>();
        for (final List<String> labels : map.values())
            if (list.addAll(labels) && list.size() > k)
                break;
        return list;
    }

    private static String mode(final List<String> list) {
        final Map<String, Integer> map = new HashMap<>();
        int maxFreq = 0;
        String maxFreqLabel = null;
        for (final String s : list) {
            final int freq = map.getOrDefault(s, 0) + 1;
            if (freq > maxFreq) {
                maxFreq = freq;
                maxFreqLabel = s;
            }
            map.put(s, freq);
        }
        return maxFreqLabel;
    }
}
