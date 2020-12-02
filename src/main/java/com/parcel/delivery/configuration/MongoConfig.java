package com.parcel.delivery.configuration;



import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;

import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "deliveryDb";
    }

   /* @Override
    public MongoClient mongoClient() {
        MongoClient mongoClient = MongoClients.create(
                "mongodb+srv://deliveryAdmin:deliveryAdPwd345@cluster0.1aqqw.mongodb.net/deliveryDb?retryWrites=true&w=majority");
        return mongoClient;

    }*/

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://deliveryAdmin:deliveryAdPwd345@cluster0.1aqqw.mongodb.net/deliveryDb?retryWrites=true&w=majority");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }



}
