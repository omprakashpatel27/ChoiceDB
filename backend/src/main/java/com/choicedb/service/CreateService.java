package com.choicedb.service;

import com.choicedb.config.MongoConfig;
import com.choicedb.config.RedisConfig;
import com.choicedb.config.SQLConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateService {

    MongoConfig mongoConfig;
    SQLConfig sqlConfig;
    RedisConfig redisConfig;

    // SQL

    public void addToSQLDatabase(Map<String,Object> JSON){
        sqlConfig.addDatabase((String) JSON.get("database_name"));
    }

    public void addToSQLTable(Map<String, Object> JSON){
        String tableName = null;
        List<String> columns = new ArrayList<>();
        List<String> datatype = new ArrayList<>();

        for(String filed : JSON.keySet()){
            if(filed.equals("table_name")) tableName = (String) JSON.get(filed);
            if(filed.equals("columns_name")){
                List<String> columnValues = (List<String>) JSON.get(filed);
                for(String columnName : columnValues){
                    columns.add(columnName);
                }
            }
            if(filed.equals("columns_data_type")){
                List<String> columnValues = (List<String>) JSON.get(filed);
                for(String columnDataType : columnValues){
                    datatype.add(columnDataType);
                }
            }
        }

        sqlConfig.addTable(tableName, getTableSchema(columns,datatype));

    }

    // MongoDb

    public void addToMongoDatabase(Map<String, Object> JSON){
        mongoConfig.addDatabase((String) JSON.get("database_name"));
    }

    public void addToMongoCollection(Map<String, Object> JSON){
        mongoConfig.addCollection((String) JSON.get("database_name"), (String) JSON.get("collection_name"));
    }

    // Redis

    public void addToRedisKey(Map<String, Object> JSON){
        redisConfig.setKeyValue((String) JSON.get("key_name"), (String) JSON.get("key_value"));
    }

    public void createDatabase(String dbName, Map<String,Object> JSON){
         switch (dbName){
             case "mySQL":
                 addToSQLDatabase(JSON);
                 break;
             case "mongoDb":
                 addToMongoDatabase(JSON);
             case "redis":
                 addToRedisKey(JSON);
         }
    }

    public String getTableSchema(List<String> columnsNames, List<String> columnDataTypes){
        String tableName = null;
        int length = columnsNames.size();
        for(int i = 0;i<length;i++){
            tableName += columnsNames.get(i) + " " + columnDataTypes.get(i);
            if(i == length - 1) tableName += ";";
            else tableName += ",";
        }
        return tableName;
    }

    public void createTable(String dbName, Map<String, Object> JSON){
        switch (dbName){
            case "mySQL":
                addToSQLTable(JSON);
                break;
            case "mongoDb":
                addToMongoCollection(JSON);
        }
    }

}
