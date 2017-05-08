package me.rabrg.jknn.classifier.impl;

import me.rabrg.jknn.classifier.Classifier;

import java.util.*;

public final class BruteForceClassifier extends Classifier {

    private double[][] trainingFeatures;
    private String[] trainingLabels;

    @Override
    public void fit(final double[][] features, final String[] labels) {
        trainingFeatures = concatFeatures(trainingFeatures == null ? new double[0][] : trainingFeatures, features);
        trainingLabels = concatLabels(trainingLabels == null ? new String[0] : trainingLabels, labels);
    }

    private static double[][] concatFeatures(final double[][] a, final double[][] b) {
        final double[][] result = new double[a.length + b.length][];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    private static String[] concatLabels(final String[] a, final String[] b) {
        final String[] result = new String[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    @Override
    public String classify(final double[] features, final int k) {
        final Map<Double, List<String>> distanceLabelMap = distanceLabelMap(features);
        final List<String> kNearestNeighbors = kNearestNeighbors(distanceLabelMap, k);
        return mode(kNearestNeighbors);
    }

    private Map<Double, List<String>> distanceLabelMap(final double[] features) {
        final Map<Double, List<String>> map = new TreeMap<>();
        for (int i = 0; i < trainingLabels.length; i++) {
            final double distance = getDistance().getDistance(features, trainingFeatures[i]);
            final List<String> labels = map.getOrDefault(distance, new ArrayList<>());
            labels.add(trainingLabels[i]);
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
