package com.choicedb.service;

import com.choicedb.model.Database;

import java.util.Map;


public class DatabaseCheck {

    public boolean isDatabaseExists(Map<String, Object> JSON){
        return Database.contains((String) JSON.get("database"));
    }

    public boolean isTableExists(Map<String, Object> JSON){
        // TODO :: call the mysql/mongodb/redis check whether table/collection/key exist or not
        return false;
    }

}
