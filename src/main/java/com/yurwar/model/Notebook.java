package com.yurwar.model;

import com.yurwar.exception.LoginNotUniqueException;
import com.yurwar.model.dao.RecordDao;
import com.yurwar.model.dao.impl.JDBCDaoFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Contains records
 * @see com.yurwar.model.Record
 * @author Yurii Matora
 */
public class Notebook {
    private RecordDao recordDao = JDBCDaoFactory.getInstance().createRecordDao();

    public void addRecord(Record record) throws LoginNotUniqueException {
        try {
            recordDao.create(record);
        } catch (RuntimeException e) {
            throw new LoginNotUniqueException(e, record.getLogin());
        }
    }

    public List<Record> getRecords() {
        return recordDao.findAll();
    }

}
