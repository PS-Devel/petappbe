package com.ps.petappbe.configuration;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Iterator;

public class MongoDBConfig {
    public static void main (String[]args){
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase db = client.getDatabase("petapp");
        MongoCollection<Document> collection = db.getCollection("users");
        Document document = new Document("username", "testuser").append("password", "testpsw");
        collection.insertOne(document);
        System.out.println("Creating user for testing...");
        FindIterable<Document> docs = collection.find();
        Iterator<Document> it = docs.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
