package me.rabrg.jknn.dataset;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Dataset {

    private final List<Entry> entries = new ArrayList<>();

    public abstract int load(final String name) throws IOException, URISyntaxException;

    protected void addEntry(final double[] features, final int label) {
        final Entry entry = new Entry(features, label);
        entries.add(entry);
    }

    public List<Entry> getEntries() {
        return Collections.unmodifiableList(entries);
    }

    public static final class Entry {

        private final double[] features;
        private final int label;

        private Entry(final double[] features, final int label) {
            this.features = features;
            this.label = label;
        }

        public double[] getFeatures() {
            return features;
        }

        public int getLabel() {
            return label;
        }
    }
}
