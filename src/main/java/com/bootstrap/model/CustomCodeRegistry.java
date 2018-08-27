/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootstrap.model;

import com.mongodb.MongoClient;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author Utente
 */
public class CustomCodeRegistry {
    
    
    CodecRegistry pojoCodecRegistry =null;
    public CodecRegistry getCodecRegistry(){
         return pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    }


    
 //  MongoClient mongoClient = MongoClient.create(MongoClientSettings.builder()
                                   ///       .codecRegistry(pojoCodecRegistry).build());
}
