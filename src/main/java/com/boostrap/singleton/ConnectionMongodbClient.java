/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boostrap.singleton;

import com.mongodb.Block;
import com.mongodb.MongoClient;

/**
 *
 * @author Marco
 */
public class ConnectionMongodbClient {

    private static final String HOST = "localhost";
    private static final Integer PORTA = 27017;
    public static  MongoClient mongodbClient = null;

    static {
        if(mongodbClient == null){
            try {

                mongodbClient = new MongoClient(HOST, PORTA);
              /*  mongodbClient.listDatabaseNames().forEach(new Block<String>() {
                    @Override
                    public void apply(final String s) {
                        System.out.println(s);
                    }
                });*/
            } catch (Exception ex) {
                System.err.println("ERRORE :::: IMPOSSIBILE AVVIARE CONNESSIONE AL DATABASE!!!!!!!!!!!!!!!" + ex);
            }
        }
            System.out.println("\n\n[[[[ SERVER IS READY!! ]]]]]");
    }

    private ConnectionMongodbClient() {
    }

    public static MongoClient getInstance() {
        return mongodbClient;
       // return ConnectionMongodbClientHolder.INSTANCE;
    }

    /*private static class ConnectionMongodbClientHolder {

        private static final ConnectionMongodbClient INSTANCE = new ConnectionMongodbClient();
    }*/
    
}
