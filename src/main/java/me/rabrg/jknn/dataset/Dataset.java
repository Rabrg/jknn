package me.rabrg.jknn.dataset;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public abstract class Dataset {

    private final List<Entry> entries = new ArrayList<>();

    public abstract int load(final String name) throws IOException, URISyntaxException;

    protected void addEntry(final double[] features, final String label) {
        final Entry entry = new Entry(features, label);
        entries.add(entry);
    }

    public List<Entry> getEntries() {
        return Collections.unmodifiableList(entries);
    }

    public static final class Entry {

        private final double[] features;
        private final String label;

        private Entry(final double[] features, final String label) {
            this.features = features;
            this.label = label;
        }

        public double[] getFeatures() {
            return features;
        }

        public String getLabel() {
            return label;
        }
    }
}
