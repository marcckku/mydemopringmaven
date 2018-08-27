/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boostrap.singleton;

import com.mongodb.util.MongodbQueryOperationsUtils;

/**
 *
 * @author Utente
 */
public class MongodbQueryOperationsUtilsSingleton {

    private static MongodbQueryOperationsUtils mongodbUtils = null;

    static {
        if (mongodbUtils == null) {
            try {
                mongodbUtils = new MongodbQueryOperationsUtils();
            } catch (Exception ex) {
                System.err.println("ERRORE :::: AVVIARE MongodbQueryOperationsUtils!!!!!!!!!!!!!!" + ex);
            }
            System.out.println("\n\n[[[[ SINGLETON MongodbQueryOperationsUtils AVVIATO !! ]]]]]");
        }
    }

    public static MongodbQueryOperationsUtils getIstanceMongodbQueryOperationsUtils() {
        return mongodbUtils;
    }

}
