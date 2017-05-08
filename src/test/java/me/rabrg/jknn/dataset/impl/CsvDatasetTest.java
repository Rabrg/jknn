package me.rabrg.jknn.dataset.impl;

import me.rabrg.jknn.dataset.Dataset;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class CsvDatasetTest {

    @Test
    void irisCsvLoadTest() {
        final Dataset dataset = new CsvDataset();
        try {
            dataset.load("/iris.csv");
        } catch (final IOException | URISyntaxException e) {
            fail(e);
        }
        assertEquals(150, dataset.getEntries().size());
    }
}