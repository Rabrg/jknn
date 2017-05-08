package me.rabrg.jknn.dataset.impl;

import me.rabrg.jknn.dataset.Dataset;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DelimiterDataset extends Dataset {

    private final String delimiter;

    public DelimiterDataset(final String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public int load(final String name) throws IOException, URISyntaxException {
        final URI uri = getClass().getResource(name).toURI();
        final Path path = Paths.get(uri);
        final List<String> lines = Files.readAllLines(path);
        for (final String line : lines) {
            final String[] split = line.split(delimiter);

            final double[] features = new double[split.length - 1];
            for (int i = 0; i < features.length; i++)
                features[i] = Double.parseDouble(split[i]);

            final int label = Integer.parseInt(split[features.length]);

            addEntry(features, label);
        }
        return getEntries().size();
    }
}
