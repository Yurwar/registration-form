package com.yurwar.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Notebook {
    private Set<Record> records;

    public Notebook() {
        records = new HashSet<>();
    }

    public Notebook(Record[] records) {
        this.records = new HashSet<>(Arrays.asList(records));
    }

    public Notebook(HashSet<Record> records) {
        this.records = records;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }
}
