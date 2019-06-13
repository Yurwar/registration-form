package com.yurwar.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Contains records
 * @see com.yurwar.model.Record
 * @author Yurii Matora
 */
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

    public void addRecord(Record record) throws LoginNotUniqueException {
        checkLoginUnique(record.getLogin());
        records.add(record);
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }

    private void checkLoginUnique(String login) throws LoginNotUniqueException {
        for (Record record : records) {
            if (record.getLogin().equals(login)) {
                throw new LoginNotUniqueException(login);
            }
        }
    }
}
