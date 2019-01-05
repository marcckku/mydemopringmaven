/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootstrap.mongodata;

import org.springframework.data.mongodb.core.MongoOperations;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.Query;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author marco
 *
 * Ricorda che in questa maniera non sto usando la DI di Spring ansi sto
 * customizzando
 * For impostazione log4j
 * http://logging.apache.org/log4j/1.2/manual.html  
 */
public class TestMongoOperationsSingleton {

    private static MongoOperations mongoOps = null;

    private static String COLLECTION_NAME = "user";
    private static final Logger log = Logger.getLogger(TestMongoOperationsSingleton.class);

    public static void main(String[] args) {
        ///Logger.getRootLogger().setLevel(Level.INFO);
        // log.setLevel(Level.INFO);
        // Set up a simple configuration that logs on the console.
        BasicConfigurator.configure();
       // String pattern = "%-4r [%t] %-5p %c %x - %m%n";
       //PropertyConfigurator.configure(  "getClass().getProtectionDomain().getCodeSource().getLocation().getPath()"+"BasicPropertiesConfigurationLog4j.properties");
        //    PropertyConfigurator.configure("BasicPropertiesConfigurationLog4j.properties");

        
        
        MongoOperationsFactorySingleton.setNomeDatabase("SpringDB");

        mongoOps = MongoOperationsFactorySingleton.getInstanceOfMongoOperations();

        User u1 = new User(2, "Marco", "Pavon", "1234", "marcckku@hotmail.com");

        if (mongoOps == null) {
            System.err.println("ERRORE ISTANZA NULLA");
        }

        mongoOps.insert(u1, COLLECTION_NAME);

        User user1 = mongoOps.findOne(new Query(where("nome").is("Marco")), User.class);

        log.info(user1.toString());

        ///   System.out.println("USER INSERITO \n\n"   +   user1.toString());
        ///	log.info(mongoOps.findOne(new Query(where("nome").is("Marco")), User.class));
        //	User user =  mongoOps.findById( 	new Query(Criteria.where("nome").is("Marco")), User.class, "user"); 
    }

    
    
}
