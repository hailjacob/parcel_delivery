package com.parcel.delivery.configuration;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
@Profile("test")
public class MongoConfigTest extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "deliveryDb_test";
    }


    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://deliveryAdmin:deliveryAdPwd345@cluster0.1aqqw.mongodb.net/deliveryDb_test?retryWrites=true&w=majority");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

}
