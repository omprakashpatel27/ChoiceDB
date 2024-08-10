package com.choicedb.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

@Configuration
public class MongoConfig {

    @Autowired
    MongoClient mongoClient;


    public void mongoClientInit(){
        mongoClient = MongoClients.create("mongodb://localhost:27017");
    }

    public void mongoClientClose(){
        mongoClient.close();
    }

    // Insertion

    public void addDatabase(String databaseName){
        mongoClientInit();
        mongoClient.getDatabase(databaseName);
        mongoClientClose();
    }

    public void addCollection(String databaseName, String collectionName){
        mongoClientInit();
        MongoDatabase database =  mongoClient.getDatabase(databaseName);
        database.getCollection(collectionName);
        mongoClientClose();
    }

    public void addDocument(String databaseName, String collectionName, Map<String, Object> JSON){
        mongoClientInit();

        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document document = new Document(JSON);
        collection.insertOne(document);

        mongoClientClose();
    }

    // search

    public Document searchDocument(String databaseName, String collectionName, Map<String,Object> JSON){
        mongoClientInit();
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document document = new Document(JSON);
        Document foundDocument = collection.find(document).first();
        mongoClientClose();
        if(foundDocument != null) return foundDocument;
        return null;
    }

    // Update

    public void updateDocument(String databaseName, String collectionName, Map<String,Object> previousJSON, Map<String,Object> currentJSON){
        mongoClientInit();
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document filter = searchDocument(databaseName, collectionName, previousJSON);
        Document update = new Document("$set", currentJSON);
        collection.updateOne(filter, update);
        mongoClientClose();
    }

    // Deletion

    public void deleteDocument(String databaseName, String collectionName, Map<String,Object> JSON){
        mongoClientInit();
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document filter = searchDocument(databaseName, collectionName, JSON);
        collection.deleteOne(filter);
        mongoClientClose();
    }
}