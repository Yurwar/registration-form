package com.yurwar.model.dao;

import com.yurwar.model.Record;

import java.util.List;

public interface RecordDao {
    void create(Record record);
    Record findById(int id);
    List<Record> findAll();
    void update(Record record);
    void delete(int id);
}
