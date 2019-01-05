/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootstrap.mongodata;

import com.mongodb.MongoClient;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 *
 * @author marco
 * 
 * Uso MongoTemplate programmaticamente cioè non usiamo da DI di Spring
 */
public class MongoOperationsFactorySingleton {
    
    private static final String HOST = "localhost";
    private static final Integer PORTA = 27017;
    private static final String DB_NAME="SpringDB";
    private static MongoOperations mongoOps = null;

    public static void setNomeDatabase(String nomeDatabase) {
        nomeDatabase = nomeDatabase;
    }

   

    public static MongoOperations getInstanceOfMongoOperations() {
        
        try {
            //internamente a MongoTemplate c'è già SimpleMongoDbFactory
            mongoOps = new MongoTemplate(new MongoClient(HOST, PORTA), DB_NAME);
             System.out.println("\n\n  ***** Server in ascolto !!! ****** \n\n");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("ERRORE :::: IMPOSSIBILE AVVIARE CONNESSIONE AL DATABASE!!!!!!!!!!!!!!!\n\n" + ex);
        }

 
        return mongoOps;
    }

}
    

