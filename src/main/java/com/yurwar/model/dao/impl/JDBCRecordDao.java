package com.yurwar.model.dao.impl;

import com.yurwar.model.Record;
import com.yurwar.model.dao.RecordDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCRecordDao implements RecordDao {
    private Connection connection;

    public JDBCRecordDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Record record) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO records (last_name, first_name, patronymic, login, mobile_phone, email)" +
                        "VALUES (?, ?, ?, ?, ?, ?)"
        )) {
            ps.setString(1, record.getLastName());
            ps.setString(2, record.getFirstName());
            ps.setString(3, record.getPatronymic());
            ps.setString(4, record.getLogin());
            ps.setString(5, record.getMobilePhone());
            ps.setString(6, record.getEmail());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Record findById(int id) {
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM records WHERE id=?"
        )) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return extractRecordFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Record> findAll() {
        List<Record> recordList = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM records");

            while (resultSet.next()) {
                recordList.add(extractRecordFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recordList;
    }

    @Override
    public void update(Record record) {
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE records " +
                        "SET last_name = ?, " +
                        "first_name = ?, " +
                        "patronymic = ?, " +
                        "login = ?, " +
                        "mobile_phone = ?, " +
                        "email = ? " +
                        "WHERE id = ?"
        )){
            ps.setString(1, record.getLastName());
            ps.setString(2, record.getFirstName());
            ps.setString(3, record.getPatronymic());
            ps.setString(4, record.getLogin());
            ps.setString(5, record.getMobilePhone());
            ps.setString(6, record.getEmail());
            ps.setInt(7, record.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM records WHERE id = ?"
        )) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Record extractRecordFromResultSet(ResultSet resultSet)
            throws SQLException {
        Record record = new Record();

        record.setId(resultSet.getInt("id"));
        record.setLastName(resultSet.getString("last_name"));
        record.setFirstName(resultSet.getString("first_name"));
        record.setPatronymic(resultSet.getString("patronymic"));
        record.setLogin(resultSet.getString("login"));
        record.setMobilePhone(resultSet.getString("mobile_phone"));
        record.setEmail(resultSet.getString("email"));

        return record;
    }
}
