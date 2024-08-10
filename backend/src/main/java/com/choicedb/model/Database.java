package com.choicedb.model;

public enum Database {
    MYSQL,
    MONGODB,
    REDIS;

    public static boolean contains(String dbName){
        for(Database db : Database.values()){
            if(db.name().equals(dbName)) return true;
        }
        return false;
    }
}
