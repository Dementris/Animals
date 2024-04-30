package com.dementris.testtask.configuration;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import static java.util.Collections.singletonList;

@Configuration
public class MongoDbAppConfig extends AbstractMongoClientConfiguration {

  @Override
  public String getDatabaseName() {
    return "animalsDb";
  }

  @Override
  protected void configureClientSettings(MongoClientSettings.Builder builder) {

    builder
        .credential(MongoCredential.createCredential("root", "admin", "secret".toCharArray()))
        .applyToClusterSettings(settings  -> {
          settings.hosts(singletonList(new ServerAddress("localhost", 27017)));
        });
  }
}