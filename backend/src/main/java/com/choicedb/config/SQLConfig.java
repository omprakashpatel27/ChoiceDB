package com.choicedb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

public class SQLConfig {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addDatabase(String databaseName){
        String sql = String.format("CREATE DATABASE IF NOT EXISTS %s", databaseName);
        jdbcTemplate.execute(sql);
    }

    public void addTable(String tableName, String tableSchema){
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s (%s)", tableName, tableSchema);
        jdbcTemplate.execute(sql);
    }

    public void addValues(String tableName, String columns, String values){
        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columns, values);
        jdbcTemplate.update(sql);
    }

    public void search(String tableName, String columns, String condition){
        String sql = String.format("SELECT %s FROM %s WHERE %s", columns, tableName, condition);
        jdbcTemplate.update(sql);
    }

    public void update(String tableName, String updates, String condition){
        String sql = String.format("UPDATE %s SET %s WHERE %s", tableName, updates, condition);
        jdbcTemplate.update(sql);
    }

    public void delete(String tableName, String condition){
        String sql = String.format("DELETE FROM %s WHERE %s", tableName, condition);
        jdbcTemplate.update(sql);
    }

}
