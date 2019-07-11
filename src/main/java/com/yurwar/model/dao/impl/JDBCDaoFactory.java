package com.yurwar.model.dao.impl;

import com.yurwar.model.dao.DaoFactory;
import com.yurwar.model.dao.RecordDao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCDaoFactory extends DaoFactory {
    @Override
    public RecordDao createRecordDao() {
        return new JDBCRecordDao(getConnection());
    }

    private Connection getConnection() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return DriverManager.getConnection(
                    properties.getProperty("datasource.url"),
                    properties.getProperty("datasource.user"),
                    properties.getProperty("datasource.password")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
