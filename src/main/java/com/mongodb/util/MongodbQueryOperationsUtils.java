/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongodb.util;

import com.boostrap.singleton.ConnectionMongodbClient;
import com.bootstrap.model.ChunksFile;
import com.bootstrap.model.Image;
import com.bootstrap.model.MetadatoFile;
import com.bootstrap.model.User;
import com.bootstrap.model.User_;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.my.librery.MyLibrery;
import com.my.librery.UtilGridFSDBFile;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.bson.Document;
import org.bson.internal.Base64;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @author Utente
 *
 * HELPER CHE LAVORA TRA LA CONNECTION AL MONGODB E LE VARIE OP.
 */

public class MongodbQueryOperationsUtils {
    
    public static  DB db = null;
    public static MongoClient mongodbClient = null;
    public static MongoDatabase mongodatabase=null;

     
    
    
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MongodbQueryOperationsUtils.class);

    public MongodbQueryOperationsUtils(String nomeDB, String collectionName) {
        
        try {
           db= initConnectionDB(nomeDB);
           mongodatabase=initConnectionMongoDatabase(nomeDB);
        } catch (Exception ex) {
            System.err.println("ERRORE " + ex.getLocalizedMessage());
            Logger.getLogger(MongodbQueryOperationsUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MongodbQueryOperationsUtils() {
    }
    
    
    
    
    
    
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-------------------------------------METODI PER AVVIARE ISTANZA COLLEGAMENTO CON MONGODB-------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------

    /***
     * <br>
     * Questo metodo a partire del nomeDB ritorna un Oggetto DB.
     * NULL altrimenti.
     * <br>
     * <h4>
     * Ricordare che la Classe DB è una versione ormai deprecata che viene usata
     * insieme alla Classe BasicDBObject
     * </h4>
     * 
     * @param nomeDB
     * @return 
     * @throws java.lang.Exception 
     */
    public DB initConnectionDB(String nomeDB) throws Exception {
        MongodbQueryOperationsUtils.mongodbClient = ConnectionMongodbClient.getInstance();//<<<<<----- AVVIA ISTANZA COLLEGAMENTO CON MONGODB "SINGLETON"

        if (MongodbQueryOperationsUtils.mongodbClient == null) {
            String errore="ERRORE: ISTANZA \"MongodbQueryOperationsUtils.mongodbClient\" NON RIUSCITA!!\n AVVIARE IL SERVER MONGODB!!!!";
            logger.error(errore);
            throw new Exception( errore );
        }
        db= MongodbQueryOperationsUtils.mongodbClient.getDB(nomeDB);
         if (MongodbQueryOperationsUtils.db == null) {
             String errore="ERRORE: NELLA ISTANZA \"MongodbQueryOperationsUtils.db\" NON RIUSCITA!!\n NOME NON ESISTENE O NULL SUL DATABASE MONGO!!!!";
              logger.error(errore);
             throw new Exception(errore);
        }
        return db;
    }
    
    

    /***
     * <br>Questo metodo avvia un collegamento "singleton" al nostro server db, 
     * precedentemente avviatoritorna. 
     * Ritorna un oggetto MongoDatabase, il quale viene invocato se e soltanto se 
     * stiamo usando la Classe Document!!. NULL altrimenti.
     * <h4>
     * Ricordare che la versione corrente di questo progetto è il DRIVER
     * di MONGODB alla versione 3.6.x. Questa versione usa la Classe MongoDatabase
     * insieme alla Classe Document,"più attuale", anche se possiamo usare benissimo
     * la Classe BasicDBObject ma insieme alla Classe DB.
     * </h4><br>
     * 
     * 
     * @param nomeDB
     * @return 
     */
    public MongoDatabase initConnectionMongoDatabase(String nomeDB)throws Exception {
        MongodbQueryOperationsUtils.mongodbClient = ConnectionMongodbClient.getInstance();
         if (MongodbQueryOperationsUtils.mongodbClient == null) {
            throw new Exception("ERRORE: ISTANZA \"MongodbQueryOperationsUtils.mongodbClient\" NON RIUSCITA!!\n AVVIARE IL SERVER MONGODB!!!!");
        }
        return MongodbQueryOperationsUtils.mongodbClient.getDatabase(nomeDB);
    }
    
    /**
     *
     * Questo metodo ritorna un'istanza dell' oggetto MongoCollection, la quale è risultato
     * da una connection al server db mongo, giachè al suo interno verrà usato il metodo
     * "this.initConnectionMongoDatabase(dbName)", il quale avvia un collegamento "singleton" . 
     * al server mongo db. La medesima istanza farà riferimento al dbName e collectionName passsato 
     * come paramtro. Null altrimenti.  
     * <br>
     *
     * Ver. Document<br>
     *
     * @param dbName
     * @param collectionName
     * @return
     */
    public MongoCollection<Document> getMongoCollection(String dbName, String collectionName) {
        MongoDatabase mdb = null;
        MongoCollection<Document> mcoll = null;
        try {
            mdb = this.initConnectionMongoDatabase(dbName);
            mcoll = mdb.getCollection(collectionName);
            if (mcoll == null) {
                return null;
            }
            return mcoll;
        } catch (Exception ex) {
            System.err.println("" + ex.getLocalizedMessage());
            Logger.getLogger(MongodbQueryOperationsUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mcoll;
    }

    /***
     * Questo metodo attraverso il nome del nostro database e della nostra collection,
     * ritorna un oggetto DBCollection.<br>
     * il metodo usato al suo interno è "DB initConnectionDB(String dbName)" che si trova 
     * all'interno di questa classe.<br>
     * @param dbName
     * @param collectionName
     * @return 
     */
    public DBCollection getCollDB(String dbName, String collectionName){
        try {
            return this.initConnectionDB(dbName).getCollection(collectionName);
        } catch (Exception ex) {
            System.err.println("ERRORE CONECTION!!"+ ex.getLocalizedMessage());
            Logger.getLogger(MongodbQueryOperationsUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-------------------------------------FINE METODI PER AVVIARE ISTANZA COLLEGAMENTO CON MONGODB--------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-------------------------------------INIT METODI PER OPERARE CON MONGODB ----------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    
    /**
     * Questo metodo ritorna true se il nome della collection passata come paramtro esiste, false altrimenti.<br>
     * <br><h4>
     *  Per Documento intendiamo ogni riga o tupla del nostro database.
     *  Le Collezione si devono intendere come la o le tabella/le in cui si trovano tutte le nostre righe o tuple.
     * Il nome dato del nostro Database viene dato dal valore che contiene la Stringa nomeDB.
     * <br></h4>
     * @param nomeDB
     * @param collectionName
     * @return 
     */
    public boolean existCollection(String nomeDB, String collectionName) {
        boolean risp=false;
        try {
            risp= initConnectionDB(nomeDB).collectionExists(collectionName) ;
        } catch (Exception ex) {
            System.err.println(""+ ex.getLocalizedMessage());
            Logger.getLogger(MongodbQueryOperationsUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return risp;
    }

    /**
     * Questo metodo ritorna un oggetto DBCollection a partire del nome database e del nome della collection. Altrimenti NULL. <br>
     * <h4>
     * Ricorda che una collection senza dati al suo interno non viene vista anche se la crea comunque.
     * Dove una collection è l'analogo di una tabella in un db relazionale.
     * </h4><br>
     *
     * @param nomeDB
     * @param collectionName
     * @return 
     */
    public DBCollection createCollection(String nomeDB, String collectionName) {
         DBCollection dbCollection = null;
        try {
           
            dbCollection = this.initConnectionDB(nomeDB).getCollection(collectionName);
            System.out.println("\n\n***********NUOVA COLLECTIONS CREATA!!*************\n\n");
            return dbCollection;
        } catch (Exception ex) {
            System.err.println(""+ ex.getLocalizedMessage());
            Logger.getLogger(MongodbQueryOperationsUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dbCollection;
    }

    /**
     * Questo meotodo ritorna l'oggetto DBCursor (oggetti generici) il quale contiene tutti gli
     * oggetti della mia collezione passata come parametro. Altrimenti NUll.
     * Se la collection non esiste allora viene creata!!!.
     * Di solito questo metodo si usa dopo che si è già fatta la connection al db.
     *<br>
     * Ver BasicDBObject
     * @param collectionName
     * @return
     */
    public DBCursor getListDocuments_(String collectionName) {
        DBCollection collections = MongodbQueryOperationsUtils.db.getCollection(collectionName);
        if (collections == null) {
            return null;
        }

        DBCursor dbCursor = collections.find();
        return dbCursor;
    }
    //****************************FILES METHODS***********************************
    // Lista di List<DBObject> GENERICI .
    public List<DBObject> getListDBObjectGenerics(String collectionName, String nomeDB) {
        DBCollection myColl = this.getCollDB(nomeDB, collectionName);
        DBCursor dbCursor = myColl.find();
        List<DBObject> list = new ArrayList<>();
        while (dbCursor.hasNext()) {
            DBObject nxtDbObject = dbCursor.next();
            list.add(nxtDbObject);
        }
        return list;
    }

    public DBObject findSearchedObjectByID(String collectionName, String nomeDB, int _id) {
      List<DBObject> objects = this.getListDBObjectGenerics(nomeDB, collectionName);
     // DBObject objSearched= null; 
        for (Iterator<DBObject> iterator = objects.iterator(); iterator.hasNext();) {
            DBObject next = iterator.next();
            if( (Integer)next.get("_id") == _id  ){
                return next;
            }
        }
        return null;
    }
    
     public BasicDBObject findSearchedBasicDBObjectByID(String collectionName, String nomeDB, int _id) {
      List<DBObject> objects = this.getListDBObjectGenerics(nomeDB, collectionName);
     // DBObject objSearched= null; 
        for (Iterator<DBObject> iterator = objects.iterator(); iterator.hasNext();) {
            BasicDBObject next = (BasicDBObject) iterator.next();
            if( (Integer)next.get("_id") == _id  ){
                return next;
            }
        }
        return null;
    }
    public void updateIDVideo(String key, int val, String dbName, String collectionName, BasicDBObject old ){
    
        ////////////////// QUERY UPDATE INC _ID ////////////////////////////////
        //trovo ogni volta lo specifico obj mykey_seq_name di seq.
        BasicDBObject updateField = new BasicDBObject();
        //inc di 1, val di seq cosi avere il totale di elem nella collection.
        updateField.put("$inc", new BasicDBObject(key, val));
        // faccio la query di update
        this.update(dbName, collectionName, old, updateField);
        ////////////////// QUERY UPDATE INC  _ID ////////////////////////////////
    
    }
    
    //*****************************************************************************
    

     /**
     * Questo meotodo ritorna l'oggetto FindIterable un iteratore di obj, cioè
     * tutti i Docs, del nome del database e della collecction passata come parametro. 
     * Altrimenti NUll. <br>
     * 
     * Ver Doc
     * @param collectionName
     * @return
     */
    public FindIterable<Document> getListDocuments(String dbName, String collectionName) {
        MongoCollection mgocoll = this.getMongoCollection(dbName, collectionName);
        
        if (mgocoll == null) {
            return null;
        }

        return mgocoll.find();
    }

    
    
    
    
    
    
    /**
     * 
     * Questo metodo ritorna una lista di tutti gli USERS presenti nel db. NULL
     * altrimenti. Ogni volta si interroga il db, cosi da avere dinamicamente
     * gli users presenti o meno.<br>
     *
     * Ver BasicDBObject
     *
     */
    public List<User_> getListUsers_(String nomeDB, String collectionName) {

        List<User_> users_ = null;
        DBCollection myColl = null;
        try {
            users_ = new ArrayList<>();
            myColl = this.getCollDB(nomeDB, collectionName);
            if (myColl == null) {
                return null;
            }
            DBCursor dbCursor = myColl.find();
            DBObject searched = null;
            String _idName = DatabasesAndCollectionsNames.KEY_ID_NAME;
            String seq_name = DatabasesAndCollectionsNames.KEY_SEQ_NAME;
            ObjectId mypkeySeq = new ObjectId(DatabasesAndCollectionsNames.CURRENT_VAL_PKEY_OBJ_SEQ);
            while (dbCursor.hasNext()) {
                searched = dbCursor.next();
                ObjectId pkey = (ObjectId) searched.get(_idName);
                if (!pkey.equals(mypkeySeq)) {
                    User_ newUser = new User_();
                    newUser.setEmail((String) searched.get("email"));
                    newUser.setName((String) searched.get("name"));
                    Integer seqFind = (Integer) searched.get(seq_name);
                    newUser.setSeq(seqFind);
                    users_.add(newUser);
                   // System.out.println("\n\n\n\ncom.mongodb.util.MongodbQueryOperationsUtils.getListUsers_()" + users_.toString() + "\n\n\n\n");
                }
            }
            return users_;
        } catch (Exception ex) {
            System.err.println(" " + ex.getLocalizedMessage());
            Logger.getLogger(MongodbQueryOperationsUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users_;
    }
    
    
    /**
     * Questo metodo ritorna una lista di tutti gli USERS presenti nel db. NULL
     * altrimenti. Ogni volta si interroga il db, cosi da avere dinamicamente
     * gli users presenti o meno.<br>
     *
     * Ver Doc
     */
    public List<User> getListUsers(String nomeDB, String collectionName) {

        List<Document> l = this.getAllDocuments(nomeDB, collectionName);
        List<User> usersList = new ArrayList<>();
        for (Iterator<Document> iterator = l.iterator(); iterator.hasNext();) {
            Document currentDoc = (Document) iterator.next();
            if (!(currentDoc.getObjectId("_id").equals(new ObjectId(DatabasesAndCollectionsNames.CURRENT_VAL_PKEY_OBJ_SEQ)))) {
                //crea e inserisci nella lista di users, se e solo, se non è il doc contattore di seq ma un user.
                User userSearched = new User();
                userSearched.setName((String) currentDoc.get("name"));
                userSearched.setEmail((String) currentDoc.get("email"));
                userSearched.setSeq((Integer) currentDoc.get("seq"));
                usersList.add(userSearched);
            }

        }
        return usersList;

    }

    /**
     * Metodo ritorna una List<Document> di Documenti presenti nella Collection
     * null altrimenti.
     *
     * Ver Doc.
     */
    public List<Document> getAllDocuments(String dbName, String collectionName) {
        MongoCollection mcoll = this.getMongoCollection(dbName, collectionName);
        if (mcoll == null) {
            return null;
        }
        FindIterable<Document> l = mcoll.find();
        List<Document> listDocs = new ArrayList<>();
        for (Iterator iterator = l.iterator(); iterator.hasNext();) {
            Document currentDoc = (Document) iterator.next();
            listDocs.add(currentDoc);
        }
        return listDocs;
    }


    
    
    /***
     * Questo metodo stampa tutti gli oggetti presenti nella collection
     * del nostro database corrente. <br>
     * 
     * @param collectionName
     * vER BasicDBObject
     */
    public void viewAllDocuments(String collectionName) {
         DBCursor dbCursor = getListDocuments_(collectionName);
         while(dbCursor.hasNext()){
             System.out.println("\n\n[ " + dbCursor.next().toString()  + " ]\n\n");
         }

    }

    
    //-----------------------------------------------------------------------------
    /**
     * 
     * Questo metodo ritorna true se la pkey dell'oggetto obj passato come parametro,
     * esiste nella collection, false altrimenti.<br>
     * Ver. BasicDBObject
     * @param collectionName
     * @param obj
     * @return 
     */
    public boolean existBDBObjectInDB(String collectionName, BasicDBObject obj) {
        DBCursor dbCursor = getListDocuments_(collectionName);
        String _idName=DatabasesAndCollectionsNames.KEY_ID_NAME;
        while (dbCursor.hasNext()) {
            ObjectId pkey1 = (ObjectId) dbCursor.next().get(_idName);
            ObjectId pkey2 = (ObjectId) obj.get(_idName);
            if (pkey1.equals(pkey2)) {
                return true;
            }
        }

        return false;
    }
    
    //-----------------------------------------------------------------------------
    /**
     * 
     * Questo metodo ritorna true se userDoc esiste nella collection, false altrimenti.
     * Ver. Document
     * @param collectionName
     * @param userDoc
     * @return 
     */
    public boolean isDocUserInsert(String dbName, String collectionName, User userDoc) {

        ObjectId pkeySeqObj = new ObjectId(DatabasesAndCollectionsNames.CURRENT_VAL_PKEY_OBJ_SEQ);
        String _idName = DatabasesAndCollectionsNames.KEY_ID_NAME;
        String seq_name = DatabasesAndCollectionsNames.KEY_SEQ_NAME;

        FindIterable<Document> iterDocs = getListDocuments(dbName, collectionName);
        Iterator it = iterDocs.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            Document nxtDoc = (Document) obj;
            ObjectId nxtpkey1 = (ObjectId) nxtDoc.get(_idName);
            if (!pkeySeqObj.equals(nxtpkey1)) {
                //ObjectId pkeyDoc = (ObjectId) userDoc.get(_idName);
                Integer nxtSeqVal = (int) nxtDoc.get(seq_name);
                User nxtUser = new User();
                nxtUser.setSeq(nxtSeqVal);
                if (nxtUser.getSeq() == userDoc.getSeq()) {
                    System.out.println("com.mongodb.util.MongodbQueryOperationsUtils.isDocUserInsert() [ TRUE ] ");
                    return true;
                }
            }
        }
        return false;
    }
    
    //-----------------------------------------------------------------------------
    /**
     * Questo metodo ritorna true se nella collection NON esistono elementi inseriti. 
     * False altrimenti.
     */
    public boolean isCollectionEmpty(String collectionName) {
        DBCursor dbCursor = getListDocuments_(collectionName);
       return( dbCursor.count() <= 0 ? true : false );
    }

    
    /**
     * Questo metodo ritorna true se nella collection NON esistono elementi
     * inseriti. False altrimenti. <br>
     * 
     * Ver Doc
     */
    public boolean isCollectionDocsEmpty(String dbName, String collectionName) {
        FindIterable fI = getListDocuments(dbName, collectionName);
        if (fI.first() == null) {
            return false;
        }

        return true;
    }
    
    /**
     * Questo metodo ritorna true se il User_ passato come parametro esiste nella Collection.
     * False altrimenti.
     * 
     * Var. BasicDBObject
     */
    public boolean isUserInsert(String nomeDB, String collectionName, User_ currentUser_) {

        DBCollection myColl = null;
        myColl = this.getCollDB(nomeDB, collectionName);

        DBCursor dbCursor = myColl.find();
        DBObject searched = null;
        String _idName = DatabasesAndCollectionsNames.KEY_ID_NAME;
        String seq_name = DatabasesAndCollectionsNames.KEY_SEQ_NAME;
        ObjectId mypkeySeq = new ObjectId(DatabasesAndCollectionsNames.CURRENT_VAL_PKEY_OBJ_SEQ);
        while (dbCursor.hasNext()) {
            searched = dbCursor.next();
            ObjectId pkey = (ObjectId) searched.get(_idName);
            if (!pkey.equals(mypkeySeq)) {
                User_ userDB = new User_();
                userDB.setEmail((String) searched.get("email"));
                userDB.setName((String) searched.get("name"));
                Integer seqFind = (Integer) searched.get(seq_name);
                userDB.setSeq(seqFind);
                if (userDB.getSeq() == currentUser_.getSeq()) {
                    System.out.println("  USER_.toString() = " + userDB.toString());
                    System.out.println(" PKEY USER_ CERCATO = " + searched.get(_idName));
                    return true;
                }
            }
        }
        return false;
    }
    
    
    
     /**
     * Questo metodo ritorna true se il User passato come parametro esiste nella Collection.
     * False altrimenti.
     * 
     * Var. Doc
     */
    
    /*
    public boolean isDocUserInsert(String nomeDB, String collectionName, User currentUserDoc) {

        this.getListUsers(nomeDB, collectionName);

        MongoCollection<Document> mcoll = this.getMongoCollection(nomeDB, collectionName);
        FindIterable<Document> iterDocsUsers = mcoll.find();
        Iterator it = iterDocsUsers.iterator();
        String _idName = DatabasesAndCollectionsNames.KEY_ID_NAME;
        String seq_name = DatabasesAndCollectionsNames.KEY_SEQ_NAME;
        ObjectId pkeySeqObj = new ObjectId(DatabasesAndCollectionsNames.CURRENT_VAL_PKEY_OBJ_SEQ);
        ObjectId pkeyCurrentUser = null;

        if (this.isCollectionEmpty(collectionName)) {
            return false;
        }
        while (it.hasNext()) {
            org.bson.Document d = (org.bson.Document) it.next(); */
           // if (d.isEmpty())*///se il primo elemento è vuoto allora non ci sono valori nella 
           /* {
                return false;
            }
            User u = (User)d;
            System.out.println(" toString() " +    u.toString());
            pkeyCurrentUser = (ObjectId) d.get(_idName);
            if (!pkeySeqObj.equals(pkeyCurrentUser)) {
                int currentValSeq = (int) d.get(seq_name);
                int val = (int) currentUserDoc.get(seq_name);
                if (currentValSeq == val) {
                    return true;
                }
            }

        }
        return false;

      /**  DBCollection myColl = null;
        myColl =   this.getCollDB(nomeDB, collectionName);

        DBCursor listMatadatoFiles = myColl.find();
        DBObject chunksCurrent = null;
        String _idName = DatabasesAndCollectionsNames.KEY_ID_NAME;
        String seq_name = DatabasesAndCollectionsNames.KEY_SEQ_NAME;
        ObjectId mypkeySeq = new ObjectId(DatabasesAndCollectionsNames.CURRENT_VAL_PKEY_OBJ_SEQ);
        while (listMatadatoFiles.hasNext()) {
            chunksCurrent = listMatadatoFiles.next();
            ObjectId pkey = (ObjectId) chunksCurrent.get(_idName);
            if (!pkey.equals(mypkeySeq)) {
                User_ userDB = new User_();
                userDB.setEmail((String) chunksCurrent.get("email"));
                userDB.setName((String) chunksCurrent.get("name"));
                Integer seqFind = (Integer) chunksCurrent.get(seq_name);
                userDB.setSeq(seqFind);
                if (userDB.getSeq() == currentUserDoc.getSeq()) {
                    System.out.println("  USER_.toString() = " + userDB.toString());
                    System.out.println(" PKEY USER_ CERCATO = " + chunksCurrent.get(_idName));
                    return true;
                }
            }
        }
        return false; */
  // }
    
    
    
    
    //-----------------------------------------------------------------------------
    
    
    
    //-----------------------------------------------------------------------------
  
    /***
     * Questo metodo tramite il nome della collection e tramite l'oggetto BasicDBObject objSearched
     * ritorna la Pkey, del medesimo oggetto, cercandola nel db, altrimenti ritorna NULL.<br>
     * 
     * Ver. BasicDBObject
     * 
     * @param collectionName
     * @param objSearched
     * @return 
     */
    public ObjectId getMyObjectIdByBasicDBObject(String collectionName, BasicDBObject objSearched) {
        DBCursor dbCursor = getListDocuments_(collectionName);
        String _idName=DatabasesAndCollectionsNames.KEY_ID_NAME;
        while (dbCursor.hasNext()) {
            ObjectId pkey1 = (ObjectId) dbCursor.next().get(_idName);
            ObjectId pkey2 = (ObjectId) objSearched.get(_idName);
            if (pkey1.equals(pkey2)) {
                return pkey2;
            }
        }

        return null;
    }

     //-----------------------------------------------------------------------------
    /**
     * Ritorna l'oggetto cercato nel db, scorrendo l'oggetto DBCursor,
     * se la pkey passata come parametro corrisponde, allora ritorna l'oggetto. Null altrimenti.<br>
     * 
     * Ver. BasicDBObject
     * @param collectionName
     * @param pkey2
     * @return 
     */
    public DBObject findBDBObjectByPkey(String collectionName, ObjectId pkey2) {
        DBCursor dbCursor = getListDocuments_(collectionName);
        String _idName=DatabasesAndCollectionsNames.KEY_ID_NAME;
        DBObject searched = null;
        while (dbCursor.hasNext()) {
            searched = dbCursor.next();
            ObjectId pkey1 = (ObjectId) searched.get(_idName);
            if (pkey1.equals(pkey2)) {
                return searched;
            }
        }

        return searched;
    }
    
    
    
    /***
     * Questo metodo ritorna un DBObject a partire del valore di seq_val,
     * scorrendo l'oggetto DBCursor, ossia se l'oggetto User_, che abbia il campo 
     * seq, con con lo stesso valore di seq_val, quindi si trova nel db allora 
     * l'oggetto viene ritornato. Null altrimenti.<br>
     * 
     * Ver BasicDBObject.
     */
     public DBObject findByValField(String nomeDB,String collectionName, int seq_val) {
         
        DBCollection collections =  this.getCollDB(nomeDB,collectionName);
        
        String seq_name= DatabasesAndCollectionsNames.KEY_SEQ_NAME;
        DBCursor dbCursor = collections.find();//   getListDocuments_(collectionName);
        DBObject searched = null;
        while (dbCursor.hasNext()) {
            searched = dbCursor.next();
            //ObjectId pkey1 = (ObjectId) chunksCurrent.get("metadatoFile_Id");
            int seq_ = (Integer)searched.get(seq_name);
            if ( seq_val == seq_) {
                return searched;
            }
        }

        return searched;
    }

    /***
     * Metodo ritorna un user trovato nella lista di Users attraverso il val di seq. 
     * La lista è stata riempita di users in un primo momento.
     * Ogni volta la Lista viene riempita dagli Users_ presenti nel db.
     * 
     * Ver BasicDBObject
     */
    public User_ findByValFielFromMyListUsers_(String nomeDB,String collectionName, int seq_val) {
        
        List<User_> l = this.getListUsers_(nomeDB, collectionName);
        boolean stop=false;
        for (Iterator<User_> iterator = l.iterator(); iterator.hasNext();) {
            User_ next = iterator.next();
            if(next.getSeq() == seq_val & !(new ObjectId( DatabasesAndCollectionsNames.CURRENT_VAL_PKEY_OBJ_SEQ).equals(next.get("_id")) ) ){
                return next;
            }
        }
        return null;
    } 
    
    
    
    
    
    
    
    
    /*
     public DBObject findDocumentByPkey(String collectionName, ObjectId pkey2) {
        DBCursor listMatadatoFiles = getListDocuments_(collectionName);
        String _idName=DatabasesAndCollectionsNames.KEY_ID_NAME;
        DBObject chunksCurrent = null;
        while (listMatadatoFiles.hasNext()) {
            chunksCurrent = listMatadatoFiles.next();
            ObjectId pkey1 = (ObjectId) chunksCurrent.get(_idName);
            if (pkey1.equals(pkey2)) {
                return chunksCurrent;
            }
        }

        return chunksCurrent;
    }
    */
    
    
    
    
      /**
     * *
     * Metodo ritorna un User trovato nella lista di Documenti attraverso il val di
     * seq. Null altrimenti.<br>
     *
     * Ver Doc
     */
    public User findByValFielFromMyListOfDocuments(String nomeDB, String collectionName, int seq_val) {

        List<Document> l = this.getAllDocuments(nomeDB, collectionName);//this.getListUsers(nomeDB, collectionName); 
        User userSearched = null;
        for (Iterator<Document> iterator = l.iterator(); iterator.hasNext();) {
            Document currentDoc = (Document) iterator.next();
            if (!(currentDoc.getObjectId("_id").equals(new ObjectId(DatabasesAndCollectionsNames.CURRENT_VAL_PKEY_OBJ_SEQ))) && (Integer) currentDoc.get("seq") == seq_val) {
                //crea user se e solo se non è il doc contattore di seq ma un user con seq_val =val_seq_current del Documento corrente
                userSearched = new User();
                userSearched.setName((String) currentDoc.get("name"));
                userSearched.setEmail((String) currentDoc.get("email"));
                userSearched.setSeq((Integer) currentDoc.get("seq"));
            }

        }

        return userSearched;
    }

      /**
     * *
     * Metodo ritorna un Document "GENERICO", trovato nella lista di Documenti  partire dal valore di seq, 
     * escludendo la pkey del Documento Contattore Sequenze. Null altrimenti.<br>
     * <br>
     *
     * Ver Doc
     */
    public Document findByValFielFromMyListDocument(String nomeDB, String collectionName, int seq_val) {
        List<Document> l = this.getAllDocuments(nomeDB, collectionName);//this.getListUsers(nomeDB, collectionName); 
        for (Iterator<Document> iterator = l.iterator(); iterator.hasNext();) {
            Document currentDoc = (Document) iterator.next();
            if (!(currentDoc.getObjectId("_id").equals(new ObjectId(DatabasesAndCollectionsNames.CURRENT_VAL_PKEY_OBJ_SEQ))) && (Integer) currentDoc.get("seq") == seq_val) {
               return currentDoc;
            }
        }
        return null;
    }

    
    
    //-----------------------------------------------------------------------------
  
    /**
     * Questo metodo ritorna un oggetto DBObject utilizzando l'oggetto DBCollection.
     * Se l'oggetto cercato NON esiste allora questo metodo lancia una Eccezione, del tipo:<br>
     *              throw new Exception(" Documento Non Esistente!!!");<br>
     * Ver. BasicDBObject
     * @param queryBDbObj
     * @param collectionName
     * @return 
     * @throws java.lang.Exception
     */
    private DBObject getSpecificBDBObject(BasicDBObject queryBDbObj, String collectionName) throws Exception {

        if (!existBDBObjectInDB(collectionName, queryBDbObj)) {
            throw new Exception(" Documento Non Esistente!!!");
        }
        DBCollection mycoll = MongodbQueryOperationsUtils.db.getCollection(collectionName);
        DBObject dbObject = mycoll.findOne(queryBDbObj);
        return dbObject;
    }

    /**
     *
     * Questo metodo ritorna il doc cercato, null altrimenti.<br>
     * Lancia una Eccezione se questo Ogetto non esisten nel db, del tipo:<br>
     *          throw new Exception(" Documento Non Esistente!!!");<br>
     * Ver. Document
     * @param queryDoc
     * @param collectionName
     * @return 
     * @throws java.lang.Exception 
     */
    private DBObject getSpecificDocument(User queryDoc, String mongoDbName, String collectionName)  throws Exception{
        if (!isDocUserInsert(mongoDbName,collectionName, queryDoc)) {
           throw new Exception(" Documento Non Esistente!!!");
        }
        DBCollection mycoll = MongodbQueryOperationsUtils.db.getCollection(collectionName);
        DBObject dbObject = mycoll.findOne(queryDoc);
      
        return dbObject;
    }
    
    
   //-----------------------------------------------------------------------------
    /**
     * 
     * Questo metodo ritorna true se nel Oggetto "BasicDBObject queryFind", 
     * esiste il valore della "key" passata come parametro.
     * False altrimenti.<br>
     * Lancia una Eccezione se l'oggetto cercato non esiste nel db, del tipo:<br>
     *          throw new Exception(" Documento Non Esistente!!! ");<br>
     * 
     * Ver BasicDBObject<br>
     * @param collectionName
     * @param queryFind
     * @param key
     * @return 
     * @throws java.lang.Exception
     */
   public boolean containsField(String collectionName, BasicDBObject queryFind, String key) throws Exception{
       
        if (!existBDBObjectInDB(collectionName, queryFind)) {
           throw new Exception(" Documento Non Esistente!!! ");
        }
           
        DBObject dboj = getSpecificBDBObject(queryFind, collectionName);
        if( !dboj.containsField(key) )
            return false;
        
         return true;
    }
      
    //-----------------------------------------------------------------------------
    /** 
     * Questo metodo ritorna la somma, del totale degli oggetti esistenti
     * nella collection del nostro Database. Zero altrimenti<br>
     * 
     * @param collectionName
     * @return 
     */
    public int getTotalDocsCollection(String collectionName) {
        int somma = 0;
        DBCursor dbCursor = getListDocuments_(collectionName);       
        while (dbCursor.hasNext()) {
            dbCursor.next();
            somma++;
        }
        return somma;
    }

    //-----------------------------------------------------------------------------
   
    /**
     * Questo metodo va a trovare un oggetto tramite la pkey, passata come
     * parametro la quale appartiene ad un obj nel db, che fa da contattore
     * degli oggetti inseriti inserito.<br>
 Ritorna la un il valore intero di seq, incrementata di 1, se e solo se, 
 esiste l'oggetto  "contattore= con i suoi vari campi seq, ref, metadatoFile_Id",  
 costruito dall'Utente precedentemenete, con l'especifico ObjectId primaryKey .
     * Altrimenti avrai un errore perchè il medesimo non esiste.<br>
     * <h4>L'autoincremento sembrerà automatico ad ogni inserzione </h4>
     * <br>
     * 
     */
    public int incrSeqVal(String myDB, String collectionName, String mykey_id, ObjectId primaryKey) {

        int valSeq = 0;
        BasicDBObject findByField = new BasicDBObject();
        findByField.put(mykey_id, primaryKey);
        String seq_name_key = DatabasesAndCollectionsNames.KEY_SEQ_NAME;

        ////////////////// QUERY UPDATE INC SEQ ////////////////////////////////
        //trovo ogni volta lo specifico obj mykey_seq_name di seq.
        DBObject objFinded = this.findBDBObjectByPkey(collectionName, primaryKey);
        BasicDBObject updateField = new BasicDBObject();
        //inc di 1, val di seq cosi avere il totale di elem nella collection.
        updateField.put("$inc", new BasicDBObject(seq_name_key, 1));
        // faccio la query di update
        this.update(myDB, collectionName, findByField, updateField);
        ////////////////// QUERY UPDATE INC SEQ ////////////////////////////////

        //cerco nel db l'oggetto aggiornato, sempre perchè cosi avremo il dato sempre fresco.
        objFinded = this.findBDBObjectByPkey(collectionName, primaryKey);
        //strappo il valore della seq corrente.
        Object obj = objFinded.get(seq_name_key);
        valSeq = (Integer) obj;
        return valSeq;
    }

    /***
     * Questo metodo private ritorna l'indice o sequenza logica progressiva
     * del enesimo elemento presente nel db. Il db deve avere almeno un elemento.
     * Altrimenti viene ritornato lo stesso val_seq passato come parametro.
     * 
     */
    private int getCurrentSeqVal(String collectionName, String seq_name_key, int val_seq) {
        //cerchiamo l'ultimo val di seq dell'ultimo Obj della collection nel db.
        int lastValSeqLastDoc = this.getLastValLastObjDoc(collectionName, seq_name_key);
        //verifico che sia almeno un elemento nella collection, cosi da assegnare il val corrente del Doc nel prossimo Doc da inserire.
        if (lastValSeqLastDoc >= 1) {
            lastValSeqLastDoc++;
            val_seq = lastValSeqLastDoc;
        }
        System.out.println("com.mongodb.util.MongodbQueryOperationsUtils.getCurrentSeqVal(), <<< SEQ VAL CURRENT >>> [ " +lastValSeqLastDoc + " ]");
        return lastValSeqLastDoc;//val_seq;
    }
    
    /***
     * 
     * Ritorna un intero cioè il valore seguente del campo "seq". Altrimenti -1.
     * 
     * Ver. BasicDBObject
     */
    public int getNextSequence(String nomeDB, String collectionName, ObjectId pkeyObjContattore, String seq_name_key) {
        try {
            //throws Exception

            DB db = this.initConnectionDB(nomeDB);
            DBCollection mycoll = db.getCollection(collectionName);
            // fields to return
            DBObject fields = BasicDBObjectBuilder.start()
                    // .append("metadatoFile_Id", 1)
                    .append(seq_name_key, 1).get();
            DBObject objFinded = this.findBDBObjectByPkey(collectionName, pkeyObjContattore);

            DBObject result = mycoll.findAndModify(objFinded, //query  new BasicDBObject("metadatoFile_Id", "addressId")
                    fields, // what fields to return
                    null, // no sorting
                    false, //we don't remove selected document
                    new BasicDBObject("$inc", new BasicDBObject(seq_name_key, 1)), //increment value
                    true, //true = return modified document
                    true); //true = upsert, insert if no matching document
            return (int) result.get(seq_name_key);
        } catch (Exception ex) {
            System.err.println("ERRORE " + ex.getLocalizedMessage());
            Logger.getLogger(MongodbQueryOperationsUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    //Decr seq obj funziona vER BasicDBObjec
    public void decrementSeq(String nomeDB, String collectionName, ObjectId pkey) {

        //straggo obj mykey_seq_name  di seq
        DBObject objSeq = this.findBDBObjectByPkey(collectionName, pkey);
        
        
        String mykey_seq_name = DatabasesAndCollectionsNames.KEY_SEQ_NAME;
        String key_id_name = DatabasesAndCollectionsNames.KEY_ID_NAME;
        String key_ref_name = DatabasesAndCollectionsNames.KEY_REF_NAME;
        String ref_val = DatabasesAndCollectionsNames.KEY_REF_VAL;
       

        int val_seq_current = (int) objSeq.get(mykey_seq_name);
        //decremento il val corrente di seq
        val_seq_current--;

        ////////////////////////////////CREO QUERY DI UPDATE CON VALORE DI SEQ ////////////////////////////////////////////
        //ricompongo l'oggetto da cercare cioè il vecchio obj nel db.
        BasicDBObject findOldObj = new BasicDBObject();
        findOldObj.put(key_id_name, pkey);
        // your update condition - or the query
        BasicDBObject queryUpdate = new BasicDBObject();
        //aggiorno l'obj con i valori del da me scelti. In questi casi il decremento e ref e contattore_seq
        queryUpdate.put(mykey_seq_name, val_seq_current);
        queryUpdate.put(key_ref_name, ref_val);

        this.update(nomeDB, collectionName, findOldObj, queryUpdate);
        // ricerco l'oggetto aggiornato, si deve sempre  prendere obj freschi, quindi interrogare sempre al DB.
        DBObject objUpdated =  this.findBDBObjectByPkey(collectionName, pkey);
        
        System.out.println(" VALORE CORRENTE DI SEQ " + val_seq_current);
        System.out.println("\n\n\ncom.mongodb.util.MongodbQueryOperationsUtils.decrementSeq()\n======= oggetti presenti nella Collection ========  " + objUpdated.get(mykey_seq_name) + "\n\n\n");
        //end mykey_seq_name seq.
        ////////////////////////////////CREO QUERY DI UPDATE CON VALORE DI SEQ ////////////////////////////////////////////
    }
    
    
    /**
     * 
     * Questo metodo decrementa l'oggetto Contattore di Sequenze.
     *
     * Ver Doc
     */
    public void decrementSeqDoc(String nomeDB, String collectionName, ObjectId pkey) {

        try {
            //straggo obj mykey_seq_name  di seq
            Document oldDoc = this.findDoc(nomeDB, collectionName, pkey);
            String mykey_seq_name = DatabasesAndCollectionsNames.KEY_SEQ_NAME;
            String key_ref_name = DatabasesAndCollectionsNames.KEY_REF_NAME;
            String ref_val = DatabasesAndCollectionsNames.KEY_REF_VAL;
            int val_seq_current = oldDoc.getInteger(mykey_seq_name);
            //decremento il val corrente di seq
            val_seq_current--;

            Document queryUpdate = new Document();
            queryUpdate.append(mykey_seq_name, val_seq_current);
            queryUpdate.append(key_ref_name, ref_val);
            // docContattoreSeq.
            ////////////////////////////////CREO QUERY DI UPDATE CON VALORE DI SEQ ////////////////////////////////////////////
            this.updateDoc(oldDoc, queryUpdate, nomeDB, collectionName);
            System.out.println("<<<<<<<<<<<<< VALORE CORRENTE DI SEQ DECREMENTATO  >>>>>>>>>>>>>  [ " + this.findDoc(nomeDB, collectionName, pkey).getInteger(mykey_seq_name) + " ]");
            //end mykey_seq_name seq.
            ////////////////////////////////CREO QUERY DI UPDATE CON VALORE DI SEQ ////////////////////////////////////////////
        } catch (Exception ex) {
            System.out.println("ERRORE " + ex.getLocalizedMessage());
            Logger.getLogger(MongodbQueryOperationsUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    

    //***************************************************************************
    
   
    //***************************************************************************
    
    //ver BasicDBObject e Ver Document giachè per forza un
    public DBObject update(String nomeDB, String collectionName, BasicDBObject objOld, BasicDBObject queryUpdate) {
        //apro collegamento col db e mia collection(table)
        DBCollection dbColl = this.getCollDB(nomeDB, collectionName);
        //aggiorno la riga con la query
        return dbColl.findAndModify(objOld, queryUpdate);
    }

    //vero update con BasicDBObject
    public void updateBasicDBObject(String nomeDB, String collectionName,BasicDBObject query, BasicDBObject updateObject, BasicDBObject modifiCObj) {

        DBCollection dbColl = this.getCollDB(nomeDB, collectionName);
        ///query.put("name", "Shubham");
       // BasicDBObject newDocument = new BasicDBObject();
        // newDocument.put("name", "John");/inserire modifiche
        updateObject.put("$set", modifiCObj);
        dbColl.update(query, updateObject);
    }

     //ver BasicDBObject
    public void delete(String nomeDB, String collectionName, BasicDBObject obj) {
        //apro collegamento col db e mia collection(table)
        DBCollection dbColl = this.getCollDB(nomeDB, collectionName);
        //elimino l'obj passato
        dbColl.remove(obj);
    }
    //ver BasicDBObject
    public void deleteBasicDBObject(String nomeDB, String collectionName,BasicDBObject searchQuery) {

        DBCollection dbColl = this.getCollDB(nomeDB, collectionName);
        //searchQuery.put("name", "John");
        dbColl.remove(searchQuery);
    }

   //***************************************************************************

    
    //-----------------------------------------------------------------------------
    /**
     * Questo metodo ritorna il valore dell'ultimo campo seq o dell'ultimo Documento 
     * inserito nel db. -1 altrimenti.<br>
     *
     * @param collectionName
     * @param keySeq
     * @return
     */
    public Integer getLastValLastObjDoc(String collectionName, String keySeq) {

        int lastSeq = -1;
        DBObject dbo = null;
        DBCursor dbCursor = getListDocuments_(collectionName);
       if(dbCursor.count() ==0)
           return lastSeq;
       
        while (dbCursor.hasNext()) {
            dbo = dbCursor.next();
            lastSeq = (Integer) dbo.get(keySeq);
        }
        System.out.println("Last Val Seq trovato ==========   [ " + lastSeq + " ] \n");
        return lastSeq;
    }
    
   //-----------------------------------------------------------------------------
   
    /**
     * Elimina doc specifico atraverso l'oggetto e nome Collection passato come parametro attuale.
     */
    public void deleteDocByObject(BasicDBObject docDaEliminare, String collectionName) {
         if (!existBDBObjectInDB(collectionName, docDaEliminare)) {
            return;
        }
        DBCollection mycoll = MongodbQueryOperationsUtils.db.getCollection(collectionName);
        mycoll.remove(docDaEliminare);
        System.out.println("\n DOCUMENTO RIMOSSO!!");
    }    
    //-----------------------------------------------------------------------------
    /**
     * Elimina tutti i documenti esistenti nella collection..
     */
    public void deleteAllDocs(String collectionName) {
        DBCollection mycoll = MongodbQueryOperationsUtils.db.getCollection(collectionName);
        DBCursor dbCursor = getListDocuments_(collectionName);
         while(dbCursor.hasNext())
             mycoll.remove(dbCursor.next());
    }
    
   //-----------------------------------------------------------------------------
    /**
     * Ritorna il primo Documento che si trova nella collection passata come parametro attuale.
     */
    private DBObject getFistDocFromCollection(String collectionName) {
        DBCollection mycoll = MongodbQueryOperationsUtils.db.getCollection(collectionName);
        DBObject dbObject = mycoll.findOne();
        System.out.println(" First Doc From Top Collection [ " + dbObject.toString() + "]");
        return dbObject;
    }
    
    /**
     * Ritorna true se per ogni record o documento della collection, esiste lo stesso campoKey.
     * False altrimenti.
     */
    public boolean containsFieldKeydInAllDocsFromCollection(String collectionName, String campoKey) {
        DBCursor dbCursor = getListDocuments_(collectionName);
        while (dbCursor.hasNext()) {
            if (!dbCursor.next().containsField(campoKey)) {
                return false;
            }
        }
        return true;
    }
    
    
     //------------------------------------INSERT USER SIA Ver BasicDBObject CHE Ver DOCUMENT----------------------------------
    /**
     * Questo metodo inserisci un User_ con valore di seq incrementata di +1.<br>
     * Siccome val di seq è un contattore è allo stesso tempo un assegnatore di 
     * sequenza di ogni obj, allora ogni volta questo metodo confronta anche il
     * valore di seq del ultimo Dco rimasto nel db, se questo doc.val_seq >= val_seq
     * allora il valore di seq viene incrementato di +1. <br>
     * 
     * Ver BasicDBObject.<br>
     *
     * @param userDocument
     * @param myDBName
     * @param collectionName
     * @return
     */
    public boolean insertBDBOjectIntoDB(User_ userDocument, String myDBName, String collectionName, String pkeyObjId) {
        int seqCurrent = 0;
        //creo l'oggetto ObjectId contenente la pkey nel nostro db
        ObjectId pkey = new ObjectId(pkeyObjId);
        String _idName = DatabasesAndCollectionsNames.KEY_ID_NAME;
        String seq_name_key = DatabasesAndCollectionsNames.KEY_SEQ_NAME;
        //Ottengo n+1 seq current.
        seqCurrent = this.incrSeqVal(myDBName, collectionName, _idName, pkey);
        //aggiorno seq val con il seqval corrente dell'ultimo Doc della Collection se questo esiste.
        seqCurrent = this.getCurrentSeqVal(collectionName, seq_name_key, seqCurrent);
        //inserisco nel db l'oggetto User_  con la seq successiva corrente.
        DBCollection dbColl = this.getCollDB(myDBName, collectionName);
        if (dbColl == null) {
            return false;
        }
        //Setto seq val con il val current, e poi inserisco nel db.
        userDocument.setSeq(seqCurrent);
        if (dbColl.insert(userDocument) != null) {
            return true;
        } else {
            return false;
        }
    }
    
   //-----------------------------------------------------------------------------------------------------------------------
     /**
     * Questo metodo inserisci un User con la pkey incrementata di +1.<br>
     * Ver Documenent.<br>
     *
     * @param userDocument
     * @param myDBName
     * @param collectionName
     * @return
     */
    public boolean insertDocumenttIntoDB(User userDocument, String myDBName, String collectionName, String pkeyObjId) {
        int seqCurrent = 0;
        //creo l'oggetto ObjectId contenente la pkey nel nostro db
        ObjectId pkey = new ObjectId(pkeyObjId);
        String mykey_id=DatabasesAndCollectionsNames.KEY_ID_NAME;
        String seq_name_key=DatabasesAndCollectionsNames.KEY_SEQ_NAME;
        //Ottengo n+1 seq current.
        seqCurrent = this.getNextSequence(myDBName, collectionName, pkey, seq_name_key);
        //aggiorno seq val con il seqval corrente dell'ultimo Doc della Collection se questo esiste.
        seqCurrent = this.getCurrentSeqVal(collectionName, seq_name_key, seqCurrent);
        //inserisco nel db l'oggetto User_  con la seq successiva corrente.
        MongoCollection<Document> mcoll = this.getMongoCollection(myDBName, collectionName);
        if (mcoll == null) {
            return false;
        }
        
        userDocument.setSeq(seqCurrent);
        mcoll.insertOne(userDocument);
        System.out.println("\n\n USER INSERITO CON SUCCESSO\n\n");
        return true;
    }
    
    //------------------------------------END INSERT USER SIA Ver BasicDBObject CHE Ver DOCUMENT----------------------------------
    
    //-------------------------------------delete user---------------------------------------------------------
   
    /**
     * Non viene cancellato un USER_ nella Lista perchè questa non viene
     * mantenuta, cioè la Lista viene caricata ogni volta con tutti gli Users_
     * presenti nel db.<br>
     * Questo metodo ritorna true se è stato possibile cancellare un USER_ nel
     * db. false altrimenti.<br>
     *
     * Ver BasicDBObject.<br>
     *
     * @param userDocument
     * @param collectionName
     * @param myDBName@return
     */
    public boolean deleteBDBOjectIntoDB(int removeSeqVal, String myDBName, String collectionName) {
        User_ userDaElim = this.findByValFielFromMyListUsers_(myDBName, collectionName, removeSeqVal);
        //elimino user
        this.delete(myDBName, collectionName, userDaElim);
        //ad ogni eleminazione obj avvenuta, si decrementa il val corrente del campo seq.
        this.decrementSeq(myDBName, collectionName, new ObjectId(DatabasesAndCollectionsNames.CURRENT_VAL_PKEY_OBJ_SEQ));
        return true;
    }


    
    /**
     * *
     * Questo metodo ritorna l'user_ aggiornato con il user_ passato come parametro.
     *
     * Ver. BasicDBObject
     */
    public User_ updateUser_(User_ user_Updated, String myDBName, String collectionName) throws Exception {

        if (user_Updated == null) {
            throw new Exception(" user da aggiornare passato come parametro è null!!! ");
        }

        System.out.println(" user paasato come parametro,  user_Updated.getSeq() = " + user_Updated.getSeq());
        User_ queryUser_Old = this.findByValFielFromMyListUsers_(myDBName, collectionName, user_Updated.getSeq());
        //aggiorna
        this.update(myDBName, collectionName, queryUser_Old, user_Updated);
        System.out.println("com.mongodb.util.MongodbQueryOperationsUtils.updateUser_(), user updated = " + user_Updated.toString());
        
        return this.findByValFielFromMyListUsers_(myDBName, collectionName, user_Updated.getInt("seq"));

    }
    
    
    
    
    
    
    
    
    
    
      /**
     *
     * Questo metodo ritorna un user aggiornato con il user passato come
     * parametro.
     *
     * Ver. Document
     */
    public User updateUserDoc(Document queryUserUpdated, String myDBName, String collectionName) throws Exception {

        if (queryUserUpdated == null) {
            throw new Exception(" USER DA AGGIORNARE E' NULL!!! ");
        }
        MongoCollection mcoll = this.getMongoCollection(myDBName, collectionName);
        Document oldDocSearched = findByValFielFromMyListDocument(myDBName, collectionName, (int) queryUserUpdated.get("seq"));
        if (mcoll.replaceOne(in("_id", oldDocSearched.getObjectId("_id")), queryUserUpdated) == null) {
            return null;
        }
        User userUpdated = this.findByValFielFromMyListOfDocuments(myDBName, collectionName, queryUserUpdated.getInteger("seq"));
        return userUpdated;
    }

    //Ver Doc Generic Document
    public void updateDoc(Document oldDoc, Document queryUserUpdated, String myDBName, String collectionName) throws Exception {

        if (queryUserUpdated == null | oldDoc == null) {
            throw new Exception(" USERS VECCHIO E NUOVO PASSATO COME PARAMETRO, SONO A NULL!!! ");
        }
        MongoCollection mcoll = this.getMongoCollection(myDBName, collectionName);
        if (mcoll.replaceOne(oldDoc, queryUserUpdated) == null) {
            return;
        }
        return;
    }


    
    
    
    
    
    
    
    
    
       /**
     * BasicDBObject old = new BasicDBObject (oldUser); BasicDBObject update =
 new BasicDBObject (queryUserUpdated); Bson filter =
 BasicDBObject.parse(oldUser.toJson()); // queryUserUpdated,
 queryUserUpdated Bson update_ =
 BasicDBObject.parse(queryUserUpdated.toJson()); //new UpdateOptions(); //
 System.out.println("\n\ncom.mongodb.util.MongodbQueryOperationsUtils.updateUserDoc()
 ================================= (ObjectId)oldUser.get(\"metadatoFile_Id\")) " +
 oldUser.getObjectId("metadatoFile_Id")    );
     */
    //-----------------------------------------------------------------------------
    //stampa docs - Ver Doc.
    private static Block<Document> printBlockGenericDoc = new Block<Document>() {
        @Override
        public void apply(final Document document) {
            System.out.println("\n\n<<<<<<<<< VIEW CURRENT DOCUMENT >>>>>  [ " + document.toJson() + " ] \n\n");
        }
    };

    //stampa docs - Ver Doc. No funziona la customizzazione
    private static Block<User> printBlockUser = new Block<User>() {
        @Override
        public void apply(final User user) {
            System.out.println(" STAMPA DOCUMENTS --> " + user.toJson());
        }
    };

    //UpdateOptions quando usi i POJOS DA VEDERE ANCORA
    SingleResultCallback<User> printCallbackWhenFinished = new SingleResultCallback<User>() {
        @Override
        public void onResult(final User person, final Throwable t) {
            System.out.println(" ------------------------------->>>>>>>>>>>>>>>>>>>>>>> " + person);
        }
    };

    public void viewAllDocs(String dbName, String collectionName) {
        System.out.println("\n[ VISTA DI TUTTI I DOCUMENTI PRESENTI NELLA COLLECTION   " + collectionName + " ]\n");
        MongoCollection<Document> mcoll = this.getMongoCollection(dbName, collectionName);
        mcoll.find().forEach(printBlockGenericDoc);
        // mcoll.find(eq("user.name", "Marco")).find( printCallbackWhenFinished);
        // mcoll.find().forEach(printBlock, callbackWhenFinished);

    }
    

     
     public void viewSingleDocument(String dbName, String collectionName, Document singleDoc)  {
         MongoCollection<Document> mcoll = this.getMongoCollection(dbName, collectionName);
         mcoll.find(in("_id", singleDoc.getObjectId("_id")))
                 .projection(singleDoc)
                 .forEach(printBlockGenericDoc);
    }

    /***
     * Questo metodo ritorna un Documento presente nella Collection e nel Db passati come 
     * parametro. Null altrimenti.
     * 
     * Internamente viene usato un Filtro [in], per trovare l'oggetto Generico
     * a partire della sua primary Key [ObjectId pkeyDoc].
     * <br>
     * Ver Doc.
     * 
     */
    public Document findDoc(String dbName, String collectionName, ObjectId pkeyDoc)  throws Exception{
        
        if(pkeyDoc == null )
           throw new Exception("ERRORE IL DOCUMENTO O LA PKEY, CHE HAI INSERITO E' NULLO!!!!!!");
        
         MongoCollection<Document> mcoll = this.getMongoCollection(dbName, collectionName);
         return mcoll.find(in("_id", pkeyDoc)).first();
    }

    /***
     * Questo metodo ritorna un Documento riferitosi al campo passato come parametro file_id.
     */
     public Document findDocFileInfo(String dbName, String collectionName, int file_id)  throws Exception{
        
        if(file_id==0 )
           throw new Exception("ERRORE IL DOCUMENTO O LA PKEY, CHE HAI INSERITO E' a ZERO!!!!!!");
        
         MongoCollection<Document> mcoll = this.getMongoCollection(dbName, collectionName);
         return mcoll.find(in("_id", file_id)).first();
    }

    /***
     * Questo metodo eleimina un Documento presente nella Collection a partire della pkey
     * del Documento passato come parametro.
     * Ver. Doc
     */
    public void deleteDocument_(String dbName, String collectionName, ObjectId pkeyDoc) throws Exception {
        
        Document doc = findDoc(dbName, collectionName, pkeyDoc);
        int valseq = doc.getInteger("seq");
        MongoCollection<Document> mcoll = this.getMongoCollection(dbName, collectionName);
        mcoll.deleteMany(doc);
       System.out.println("\n[ DOCUMENTO ELIMINATO CON SUCCESSO,  CON SEQ VAL = [ " + valseq + " ]\n");
    }

//-----------------------------------------------------------------------------
    
    
    /***
     * Metodo elimina un Documento dalla Collection e nome database passati come parametro.
     * <br>
     * Ver. Dco
     */
    public boolean deleteDocument(int removeSeqVal, String myDBName, String collectionName) {
        User userDaElim = findByValFielFromMyListOfDocuments(myDBName, collectionName, removeSeqVal);
         MongoCollection mcoll = this.getMongoCollection(myDBName, collectionName);
         long contElim = mcoll.deleteOne(userDaElim).getDeletedCount();
        //elimino user
        if( contElim <= 0)
            return false;
        System.out.println("com.mongodb.util.MongodbQueryOperationsUtils.deleteDocument(),\t[ Documenti eliminati = " + contElim + " ]");
        //ad ogni eleminazione obj avvenuta, si decrementa il val corrente del campo seq.
        this.decrementSeqDoc(myDBName, collectionName, new ObjectId(DatabasesAndCollectionsNames.CURRENT_VAL_PKEY_OBJ_SEQ));
        return true;
    }
    
    
    
    
    
    
    
    
    
    
    
    

    //Doc
  /*  public void stampaSingleDoc(String myDBName, String collectionName, ObjectId pkeyDoc) {
        MongoCollection mcoll = this.getMongoCollection(myDBName, collectionName);
        mcoll.find(  eq("metadatoFile_Id", ""+pkeyDoc)).forEach(printBlock);

        mcoll.find(eq("name", "456 Cookies Shop"))
            .forEach(printBlock);
    }*/
    //Doc
   /* public void replaceDoc(String myDBName, String collectionName, ObjectId pkeyDocOld, Document updateDoc) {
         MongoCollection mcoll = this.getMongoCollection(myDBName, collectionName);
        mcoll.replaceOne( eq("metadatoFile_Id", pkeyDocOld), updateDoc );
    
    }*/
    
    /*
    private  MyBlock_<Document> currenDoc =new MyBlock_<Document>(){
        @Override
        public Document getDocumentCurrent(final Document t) {
            return t; //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    public interface MyBlock_<T>{ 
        Document getDocumentCurrent(T t);
    }
*/
    
    
    
    
    
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-------------------------------------FINE METODI PER OPERARE CON MONGODB ----------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------


    
    
    
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-------------------------------------FINE METODI PER OPERARE CON MONGODB ----------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    
    
    
    
    
    
    
    
    
     
     
     
     
     
     
     
     
     
     
     
     
     
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-------------------------------------INIT METODI PER INSERIMENTO FILE IN MONGODB--------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
     
    //https://avaldes.com/upload-and-download-multiple-binary-files-using-mongodb/
    public void downloadFileFromMongoDB(DB db, String id) {

        BasicDBObject query = new BasicDBObject();

        query.put("_id", id);

        GridFS fileStore = new GridFS(db, db.getName());

        GridFSDBFile gridFile = fileStore.findOne(query);

        if (gridFile != null && id.equalsIgnoreCase((String) gridFile.getId())) {

            try {
                /*  logger.info("ID...........: " + gridFile.getId());
                
                logger.info("FileName.....: " + gridFile.getFilename());
                
                logger.info("Length.......: " + gridFile.getLength());
                
                logger.info("Upload Date..: " + gridFile.getUploadDate());
                 */
                InputStream in = gridFile.getInputStream();

                ByteArrayOutputStream out = new ByteArrayOutputStream();

                int data = in.read();

                while (data >= 0) {

                    out.write((char) data);

                    data = in.read();

                }

                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(MyLibrery.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
    //-----------------------------------------------------------------------------------------------------------------------
    
    
    //    private static int cont=3; //attenti qui perchè ogni volta che riavvi il server ricomincia da uno.
    // LINK IN CUI TROVI L'ESEMPIO CON MultipartFile:  https://www.journaldev.com/2573/spring-mvc-file-upload-example-single-multiple-files
    /**
     * Metodo inserisce un file in mongodb. Mongodb deve essere accesso.
     */
    //public GridFSDBFile myfile = null;

    public Image insertIntoMongoDBWithMultipartFile(MultipartFile mfile, DB db) {

        InputStream is = null;
        GridFS gridfs = null;
        GridFSInputFile gfsInputFile = null;
        Image img = null;
        String fileName = "";
        ObjectId _idChunksFile = null;
        if (!mfile.isEmpty()) {
            try {
                String contentType = mfile.getContentType();
                fileName = mfile.getOriginalFilename();
                gridfs = new GridFS(db, db.getName());// db=ref al db usato, invece db.getName() = nome dato alla tua collection esem: VIDEO.currentChunksList o VIDEO.files altrimenti sè vuoto allora rimane soltanto : currentChunksList e files.

                // Carichiamo il mfile
                is = mfile.getInputStream();
                gridfs.createFile(is);
                gfsInputFile = gridfs.createFile(is);
                _idChunksFile = (ObjectId) gfsInputFile.getId();

                gfsInputFile.setId(DatabasesAndCollectionsNames.CONTATTORE_VIDEO_COLLECTION++);
                gfsInputFile.setFilename(fileName);
                int numberNextChunk = gfsInputFile.saveChunks();
                gfsInputFile.setContentType(contentType);
               // String alias = gfsInputFile.getAliases().get(0);
               // gfsInputFile.setChunkSize(chunkSize);
                if (numberNextChunk > 0) {
                    gfsInputFile.save();
                } else {
                    return null;
                }
            } catch (IOException ex) {
                String errore = "\"ERRORE FILE NON TROVATO  O NULLO   ::::::::::::::::: ";
                logger.error(errore);
                ex.printStackTrace();
                System.err.println(errore + ex.getMessage());
            } finally {
                try {
                    //DBObject currentChunksList= this.getCollDB(DatabasesAndCollectionsNames.DBVIDEO, DatabasesAndCollectionsNames.COLLECTION_CHUNKS_NAME).find().curr();
                    DBObject chunks = this.findBDBObjectByPkey(DatabasesAndCollectionsNames.COLLECTION_CHUNKS_NAME, _idChunksFile);
                    int _id = (int) chunks.get("files_id");

                    //  DBObject metadatoFile= this.getCollDB(DatabasesAndCollectionsNames.DBVIDEO, DatabasesAndCollectionsNames.COLLECTION_FILES_NAME).find().curr();
                    DBObject metadatoFile = this.findBDBObjectFileByField(DatabasesAndCollectionsNames.COLLECTION_FILES_NAME, _id);

                    //  System.out.println("  currentChunksList.toString() "   +  currentChunksList.toString());
                    // System.out.println(" metadatoFile.toString()  "  + metadatoFile.toString()  );
                    if (chunks == null || metadatoFile == null) {
                        System.out.println("  <<<<<<<<<<<<<<<<<<< impossibile caricamento file upload perchè:\tchunks == null  || metadatoFile==null >>>>>>>>>>>>>>>>>>>>>>>>");
                        return null;
                    }

                    img = new Image(mfile, chunks, metadatoFile);
                    // img = new Image(mfile); 

                    // this.riempiImagine(currentChunksList, mfile);
                    is.close();
                    if (img == null) {
                        return null;
                    }
                } catch (IOException ex) {
                    String errore = "ERRORE NEL CHIUDERE LO STREAM!!";
                    logger.error(errore);
                    ex.printStackTrace();
                    System.err.println(errore + ex.getMessage());
                }
            }
        }
        return img;
    }

    
     public  List<MetadatoFile> metadatoFileList=new ArrayList<>();
     public   List<ChunksFile> chunksList = new ArrayList<>();
     
     public boolean saveMultipartFile(MultipartFile mfile, DB db) throws Exception {
         
         if (mfile == null || db == null) {
             throw new Exception("mfile NULLO!! ||  db NULLO||");
         }
         GridFSInputFile gfsInputFile = null;
         //carico tutte le liste 
         this.updateLocalChunksAndMetaFileList();
         if (this.metadatoFileList.isEmpty() && this.chunksList.isEmpty()) {//se non ci sono obj nel db.
             DatabasesAndCollectionsNames.CONTATTORE_VIDEO_COLLECTION = 1;
             System.out.println("\n(this.metadatoFileList.isEmpty() = " + this.metadatoFileList.isEmpty());
             System.out.println("\n( this.chunksList.isEmpty() = " + this.chunksList.isEmpty()  );
         } else {
             DatabasesAndCollectionsNames.CONTATTORE_VIDEO_COLLECTION = this.getCurrentSeqVal(DatabasesAndCollectionsNames.COLLECTION_FILES_NAME, "_id", DatabasesAndCollectionsNames.CONTATTORE_VIDEO_COLLECTION);
         }
         System.out.println("\n( DatabasesAndCollectionsNames.CONTATTORE_VIDEO_COLLECTION = " +DatabasesAndCollectionsNames.CONTATTORE_VIDEO_COLLECTION  );
        int currentIndexChunkAndFileId =DatabasesAndCollectionsNames.CONTATTORE_VIDEO_COLLECTION++;
        gfsInputFile = UtilGridFSDBFile.saveMultipartFileVer2(mfile, db, currentIndexChunkAndFileId );
        if(gfsInputFile==null)
            throw new Exception("gfsInputFile NULLO");
        
         System.out.println(" gfsInputFile.getId()== " + gfsInputFile.getId());
         String index=""+currentIndexChunkAndFileId;
      //  ObjectId _idChunksFile =  (ObjectId) gfsInputFile.getId();
        InputStream is = null;
        GridFS gridfs = null;
        //GridFSInputFile gfsInputFile = null;
        String fileName = "";
         DBObject chunks =    this.findBDBObjectFileByField(DatabasesAndCollectionsNames.COLLECTION_CHUNKS_NAME, currentIndexChunkAndFileId);                  //this.findBDBObjectByPkey(DatabasesAndCollectionsNames.COLLECTION_CHUNKS_NAME, _idChunksFile);
        // System.out.println(" chunks.toString() === " + chunks.toString());
        // int _id = (int) chunks.get("files_id");
         DBObject metadatoFile = this.findBDBObjectFileByField(DatabasesAndCollectionsNames.COLLECTION_FILES_NAME, currentIndexChunkAndFileId);
         if (chunks == null || metadatoFile == null) {
             System.out.println("  <<<<<<<<<<<<<<<<<<< impossibile caricamento file upload perchè:\tchunks == null  || metadatoFile==null >>>>>>>>>>>>>>>>>>>>>>>>");
             return false;
         }
         // ChunksFile chunk = new ChunksFile((Map)JSON.parse(gfsInputFile.toString()));
        /* ChunksFile chunk = new ChunksFile(chunks.toMap());
         chunk.setFiles_id(currentIndexChunkAndFileId);
         chunk.setData(mfile.getBytes());*/
         
         //
       //  BasicDBObject newMetadato = new BasicDBObject((Map) JSON.parse(metadatoFile.toString()));
       //  MetadatoFile metadati = new MetadatoFile(newMetadato.toMap());
         //li passo tutti i bytes dei currentChunksList e lo converto in base64 quindi stringa.
       /*  metadati.setId(currentIndexChunkAndFileId);
         metadati.setFilename(gfsInputFile.getFilename().split("\\.")[0]);
         metadati.setFormato(gfsInputFile.getFilename().split("\\.")[1]);
         metadati.setDato(Base64.encode(mfile.getBytes()));
         if (metadati.getDato() == null) {
             return false;
         }
         
         this.chunksList.add(chunk);
         this.metadatoFileList.add(metadati);
         
         
         //aggiungo gli oggetti creati direttamente se e solo se sono formato mp4, video ecc.
         //   metadatoFileList.add(metadati);
         //  chunksList.add(chunk);
         */
        return true;
    }

    public static boolean existFileInMongoDb(int id){
        return (id <= 0 ? false : true);
    }
    
    /**
     * Metodo ritorna l'oggetto DBCursor contenente tutta la lista.
     */
    public DBCursor getAllFiles(DB db){
        GridFS gridfs = new GridFS (db, db.getName());
        return gridfs.getFileList();
    }
    
    public  List<DBObject>  getAllMetadatoFileList(DB db){
       DBCursor dbCursor = getAllFiles(db);
       List<DBObject> list = new  ArrayList<>();
       while(dbCursor.hasNext()){
         DBObject obj = dbCursor.next();
         list.add(obj);
       }
       return list;
    }
    
    /**
     * Questo metodo ritorna la lista di tutti i currentChunksList spezzati nel db.
     * Null altrimenti.<br>
     * 
     */
    public List<ChunksFile> getAllChunksFileList(String dbName, String collectionNameChunks) {
        ChunksFile chunksFiles = null;
        List<ChunksFile> list = new ArrayList<>();
        try {
            DB db = this.initConnectionDB(dbName);
            GridFS gridfs = new GridFS(db, db.getName());
            DBCursor listMatadatoFiles = gridfs.getFileList();
            DBCursor chunksList = getListDocuments_(collectionNameChunks);
            while (listMatadatoFiles.hasNext()) {
                DBObject metadato = listMatadatoFiles.next();
                //System.out.println(" metadato.toString() ========= "    + metadato.toString()   );
                for (DBObject chunks : chunksList) {
                    //trovo metadatoFile_Id corrispondente al chunk corrente
                    if ((int) metadato.get("_id") == (int) chunks.get("files_id")) {
                        chunksFiles = new ChunksFile();
                        chunksFiles.setId((ObjectId) chunks.get("_id"));
                        chunksFiles.setFiles_id((Integer) chunks.get("files_id"));
                        chunksFiles.setN((Integer) chunks.get("n"));
                        chunksFiles.setData((byte[]) chunks.get("data"));
                        list.add(chunksFiles);
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MongodbQueryOperationsUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    /**
     * Questo metodo ritorna la lista di tutti i metadati nel db. Null
     * altrimenti.<br>
     * Purtroppo Occupa molto spazio!! va fuori memoria heap quando carica ogni volta il dato+ .
     * link : https://stackoverflow.com/questions/10066349/spring-display-image-on-jsp-file 
     */
    public List<MetadatoFile> getAllMetadatoFileList(String dbName, String collectionNameMetadatoFiles) {
        MetadatoFile metadatoFiles = null;
        List<MetadatoFile> list = new ArrayList<>();
        try {
            DB db = this.initConnectionDB(dbName);
            GridFS gridfs = new GridFS(db, db.getName());
            DBCursor listMatadatoFiles = gridfs.getFileList();
            List<ChunksFile> currentChunksList = getAllChunksFileList(dbName, DatabasesAndCollectionsNames.COLLECTION_CHUNKS_NAME);
            while (listMatadatoFiles.hasNext()) {
                DBObject metadato = listMatadatoFiles.next(); 
                int metadatoFile_Id = (Integer) metadato.get("_id");
                boolean trovato=false;
                String dato="";
               
                String fileName=(String) metadato.get("filename");
                String aliases = (String) metadato.get("aliases");
                Integer chunksSize = Integer.parseInt(""+metadato.get("chunkSize"));
                Date date = (Date) metadato.get("uploadDate");
                String contentType = (String) metadato.get("contentType");
                Integer length =Integer.parseInt(""+metadato.get("length"));
                String md5 = (String) metadato.get("md5");
                //
                metadatoFiles = new MetadatoFile();
                metadatoFiles.setId(metadatoFile_Id);
                metadatoFiles.setFilename((fileName ));
                metadatoFiles.setAliases((aliases));
                metadatoFiles.setChunkSize(( chunksSize));
                metadatoFiles.setUploadDate(( date ));
                metadatoFiles.setContentType((contentType));
                metadatoFiles.setLength( length );
                metadatoFiles.setMd5(md5 );
                 String[] str = fileName.split("\\.");
                metadatoFiles.setFormato(str[1]);
                //strappolo tutti i byte del dato(file) dalla lista di Chunks, da inserire poi nel dato come parametro corrente di setDato.
                 List<ByteBuffer> listArrBytes= new ArrayList<>();
                for (Iterator<ChunksFile> iterator = currentChunksList.iterator(); iterator.hasNext() ;) {
                    ChunksFile next = iterator.next();
                    if(next.getFiles_id()==metadatoFile_Id){
                        listArrBytes.add(ByteBuffer.wrap( next.getData() ));
                    }
                }
                //raccolta all bytes from al chunks from db
                byte[] allbytesSingleFile = UtilGridFSDBFile.getAllBytesFromChunksList(listArrBytes).array();
                /// allbytesSingleFile=UtilGridFSDBFile.compress_(allbytesSingleFile);
                //  StringBuilder sb = new StringBuilder();//buono
                StringBuffer sbuf = new StringBuffer(); //da provare ancora - StringBuffer che è anche sincronizzato, 
                                                        //quindi è ideale per gli ambienti multithread. 
                                                        //Differenza tra StringBuffer e StringBuilder = StringBuffer è sincronizzato, StringBuilder no.
                                                        //link examples https://code-examples.net/it/q/4fdde3
                if (metadatoFiles.getFormato().equalsIgnoreCase("mp4")) {
                    //  sb.append("data:video/mp4;base64,");
                    //  sb.append(  Base64.encode(allbytesSingleFile) );
                    sbuf.append("data:video/mp4;base64,");
                    sbuf.append(Base64.encode(allbytesSingleFile));
                } else if (metadatoFiles.getFormato().equalsIgnoreCase("jpg")) {
                    //  sb.append("data:image/jpg;base64,");
                    //  sb.append(  Base64.encode(allbytesSingleFile) );
                    sbuf.append("data:image/jpg;base64,");
                    sbuf.append(Base64.encode(allbytesSingleFile));
                } else if (metadatoFiles.getFormato().equalsIgnoreCase("png")) {
                    // sb.append("data:image/png;base64,");
                    // sb.append(  Base64.encode(allbytesSingleFile) );
                     sbuf.append("data:image/png;base64,");
                     sbuf.append(  Base64.encode(allbytesSingleFile) );
                }

                //System.out.println("\n\nsb.toString()\t= "   + sb.toString() );
                
                
               //  System.out.println(" encode UTF-8 length\t= "   +  UtilGridFSDBFile.encodeUrlInUTF8(dato).length() );
               // dato=UtilGridFSDBFile.deflate(allbytesSingleFile);
             
                metadatoFiles.setDato( Base64.encode(allbytesSingleFile));
                list.add(metadatoFiles);
                 
            }
        } catch (Exception ex) {
            Logger.getLogger(MongodbQueryOperationsUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
   
   
    
    
    
    
    
    
    
    public void viewAllMetadatoFiles(DB db){
        DBCursor dbCursor = getAllFiles(db);
        while(dbCursor.hasNext())
            System.out.println("\n\nMetadatoFiles\t" + dbCursor.next().toString());
    }
    
    public void viewAllChunksFile(String dbName, String collectionNameChunks){
        for (ChunksFile chunksFile :  getAllChunksFileList(dbName, collectionNameChunks)) {
            System.out.println("\n\nchunksFile.toString()=\t"  + chunksFile.toString() );
        }
    }
    
    
    //******************************************************************************************************************************************************************************************************
    //******************************************************************************************************************************************************************************************************
    //https://stackoverflow.com/questions/19510656/how-to-upload-files-on-server-folder-using-jsp
    //Qui il controller extends HttpServlet, in Spring non deve esserci un controller che extende la medesima classe.
    //Ma soltanto per prova ho estesso la classe HttpServlet e ho aggiunto al web.xml un tag che specifica
    //che ci sarà un file da uploadare insieme  a questo proviamo il form normale(HTML), insieme a questo metodo
    // cosi da testare l'inserimento del file (foto,video ecc..) in Mongodb.
    //Ricorda che i controllori di Spring non extendono la classe HttpServlet.
    //Ma per prova lo avevo fatto!!!. e funzione
    //Per usare questo metodo "insertFileFromContextServletDoPost" bisogna che il tuo Controller abbia la Annotations 
    //-->> @MultipartConfig  o la configurazione web.xml
    /**
     * <!--  servlet collegamento fatto il 10/05/2018 -->
     * <context-param>
     * <description>Location to store uploaded file</description>
     * <param-name>file-upload</param-name>
     * <param-value>
     * C:\Users\Utente\Music\tmp\
     * </param-value>
     * </context-param>
     *
     */
    //***********************insertFileFromContextServletDoPost questo metodo funziona mettendolo direttamente nell controller che extende HttpServlet*******************************************************************************************************************************************************************************
    //******metodo del controller si chiamava doPost addesso insertFileFromContextServletDoPost************************************************************************************************************************************************************************************************
    public static boolean insertFileFromContextServletDoPost(HttpServletRequest request, HttpServletResponse response, DB db) throws ServletException, java.io.IOException, Exception{

         String  filePath =  request.getServletContext().getInitParameter("file-upload"); 
        
        if(request == null || response == null  )
            throw new Exception("Request or Response sono a NULL!!!" );
        
        File file = null;
     //   String filePath = null;
        boolean isMultipart = false;
        int maxFileSize = 1000000000 * 1000000000; // 1GB
        int maxMemSize = 1000000000 * 1000000000; // 1GB

        // Check that we have a file upload request
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        if (!isMultipart) {
            return false;
        }

        filePath = request.getServletContext().getInitParameter("file-upload");//    "C:/Users/Utente/Music/solo_se_me_occurre_amarte_Ajenadro_Sanz.mp4");

        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("C:/Users/Utente/Music/tmp/"));
        //C:/Users/Utente/Music/
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);

        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();

            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    String fileName = fi.getName();
                    // Write the file
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath
                                + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath
                                + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fi.write(file);
                    insertInToMongoDB(file, fi, db);//<<--------- inserisci file in mongodb.
                    return true;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;

    }
    
    //-----------------------------------------------------------------------------------------------------------------------
    //In caso tu abbia inserito il metodo del form come GET allora si solleva un Exception
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

        throw new ServletException("GET method used with " + getClass().getName() + ": POST method required.");
    }

    //-----------------------------------------------------------------------------------------------------------------------
    /**
     * Questo metodo private prende in
     * input: un file, fileItem, un dbStorage e una istanza di DB se si sta
     * usando ancora la versione *** @Deprecata di DB **, si consiglia di passara a
     * MongoDatabase quindi al uso della ** Classe Document e non piu
     * BasicDBObject ** . Salva un file in Mongodb grazie al uso della classe
     * GridFS, che serve quando viuoi inserire file molto più grossi di 16 GB!!.
     */
    private static void insertInToMongoDB(File file, FileItem fiItem, DB db) {

        try {
            ////////////////////////////////
            // Accesso a GridFS
            GridFS gridfs = new GridFS(db, db.getName() + "");//id
            // Carichiamo il file
            GridFSInputFile gfsInputFile = gridfs.createFile(file);
            //  gfsInputFile.setId(id++);
            if (fiItem != null) {
                //String fieldName = fiItem.getFieldName();
                String fileName = fiItem.getName();
                String contentType = fiItem.getContentType();
                boolean isInMemory = fiItem.isInMemory();
                long sizeInBytes = fiItem.getSize();
                gfsInputFile.setFilename(fileName);
                gfsInputFile.saveChunks(sizeInBytes);
                gfsInputFile.setContentType(contentType);
            }
            // gfsInputFile.setFilename(file.getName());
            // gfsInputFile.saveChunks(file.length());                
            gfsInputFile.save();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    //-----------------------------------------------------------------------------------------------------------------------
    public static byte[] getBytesFromFile(File f) throws IOException {
        FileInputStream fis = null;
        byte[] arrb = null;
        int index = 0;
        try {
            try {
                fis = new FileInputStream(f);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MyLibrery.class.getName()).log(Level.SEVERE, null, ex);
            }

            arrb = new byte[fis.available()]; // [268435456] ; ///1048576
            try {
                // new input stream created
                fis = new FileInputStream(f.getPath()); //"C://test.txt"
                System.out.println("Characters printed:");
                int i = 0;

                // reads till the end of the stream
                while ((i = fis.read()) != -1) {
                    // converts int to byte
                    byte b = (byte) i;
                    // prints character
                    arrb[index++] = b;
                }
            } catch (Exception e) {
                // if any I/O error occurs
                e.printStackTrace();
            } finally {
                // releases system resources associated with this stream
                if (fis != null) {
                    fis.close();

                }
            }

        } finally {
            fis.close();

        }

        byte[] barr = new byte[index];
        for (int i = 0; i < arrb.length; i++) {
            barr[i] = arrb[i];
        }

        return barr;
    }

    //-----------------------------------------------------------------------------------------------------------------------
    /**
     * Spring MVC sample application for downloading files Link to code:
     * http://www.codejava.net/frameworks/spring/spring-mvc-sample-application-for-downloading-files
     * Size of a byte buffer to read/write file
     */
    private static final int BUFFER_SIZE = 4096;

    /**
     * Path of the file to be downloaded, relative to application's directory
     */
    private static String filePath_ = "/downloads/SpringProject.zip";

    public static boolean downloadFile(HttpServletRequest request, HttpServletResponse response) {

        FileInputStream inputStream = null;
        try {
            // get absolute path of the application
            ServletContext context = request.getServletContext();
            String appPath = context.getRealPath("");
            System.out.println("appPath = " + appPath);
            ///request.getSession().getServletContext().getRealPath("/");
            // construct the complete absolute path of the file
            String fullPath = appPath + filePath_;
            File downloadFile = new File(fullPath);
            inputStream = new FileInputStream(downloadFile);
            // get MIME type of the file
            String mimeType = context.getMimeType(fullPath);
            if (mimeType == null) {
                // set to binary type if MIME mapping not found
                mimeType = "application/octet-stream";
            }
            System.out.println("MIME type: " + mimeType);
            // set content attributes for the response
            response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());
            // set headers for the response
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"",
                    downloadFile.getName());
            response.setHeader(headerKey, headerValue);
            // get output stream of the response
            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            // write bytes read from the input stream into the output stream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outStream.close();
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyLibrery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyLibrery.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(MyLibrery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    //-----------------------------------------------------------------------------------------------------------------------
    public static String creaRandomPassword(String parola) {

        String[] arrSplit = parola.split("@");
        String str = arrSplit[0];
        int dimStr = str.trim().length();
        int init = 0;
        String passwordRandom = "";
        char[] arr = (str.trim() + Math.random()).toCharArray();
        int lenArr = arr.length;
        //str almento di 10 caratteri
        if (dimStr <= 10 & dimStr >= 8) {
            System.err.println(" LENGHT   " + lenArr);
            init = (lenArr == 29 ? 19 : (lenArr == 28 ? 18 : (lenArr == 27 ? 17 : (lenArr == 26 ? 16 : 17))));// 
        } else {
            arr = ("" + Math.random()).trim().toCharArray();
            lenArr = arr.length;
            System.err.println(" LENGHT   " + lenArr);
            init = (lenArr == 19 ? 11 : lenArr == 18 ? 10 : 9);
        }
        //in totale sono 8 scelte in tutto.
        //Math.random() di default sono 17, 18 O 19 digiti oltre a str.lengh()

        System.err.println(" arr[ " + "init=" + init + " ]");
        for (int i = init; i < arr.length; i++) {
            int n = (int) arr[i];
            if (n >= 48 && n <= 57) { //num
                char tmp1 = (char) (49 + n);//converto in lettere {48,57}+49=97 lettere minuscole.
                passwordRandom += tmp1;
            }
            if (n >= 65 && n <= 122) {// alfabeto Aa -> Zz
                int tmp2 = (int) arr[i] - 7;
                passwordRandom += (char) tmp2;
            }
        }
        return passwordRandom;

    }

    //----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-------------------------------------FINE METODI PER INSERIMENTO FILE IN MONGODB---------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
      private  BufferedImage leggiFilePicture(InputStream is, String fileName) {
        BufferedImage image = null;
        try ( BufferedReader br = new BufferedReader(new InputStreamReader(is)  )  ) {

            String number = br.readLine();
            String title = br.readLine();
            String text = br.readLine();

            System.out.println(" NUMBER "   +  number);
            System.out.println(" TITLE "   +  title);
            System.out.println(" TEXT "   +  text);

            ImageInputStream iis = ImageIO.createImageInputStream(is);
            image = ImageIO.read(iis);
            String str = fileName+".png";
            ImageIO.write(image, "png", new File(str));
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return image;
    }

    public void DisplayImage(File f, InputStream is, String selection) throws IOException {

        BufferedImage img = null;
        ImageInputStream stream = null;
        ImageInputStream iis = null;
        if (selection.equalsIgnoreCase("is")) {
            iis = ImageIO.createImageInputStream(is);
            img = leggiFilePicture(is, "PROVA"); //ImageIO.read(iis);
        } else {
            img = ImageIO.read(f);
        }

        ImageIcon icon = new ImageIcon(img);
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(200, 300);
        JLabel lbl = new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    

    
     //----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-------------------------------------FINE METODI PER INSERIMENTO FILE IN MONGODB / GRIDFS /---------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    
 //*****************************************************************************
    /***
     * Questo metodo ritorna un DBObject specificato dal int _id passata come
     * parametro. Null altrimenti. <br>
     * Stranamente il nome del campo _idName=_id prende ad un certo punto 
     * il nome campo files_id!! da controlloare, anche se funziona ma da controllare.
     */
     public DBObject findBDBObjectFileByField(String collectionName, int _id) {
        DBCursor dbCursor = getListDocuments_(collectionName);
        String _idName=DatabasesAndCollectionsNames.KEY_ID_NAME;
        DBObject searched = null;
        while (dbCursor.hasNext()) {
            searched = dbCursor.next();
           Object obj = searched.get(_idName);
           //System.out.println("\n\n obj.toString() --->>>>>>>>>>>>>>>>>>>>>>>>" + obj.toString());
           int id=0;
           if(obj instanceof Integer){
                id = Integer.parseInt(obj.toString());
                if (id == _id) {
                    return searched;
                }
           }
        }

        return searched;
    }
    
    //*****************************************************************************
    
    //FILE VIDEO bytes
    public Image riempiImagine(DBObject chunksFile, MultipartFile mfile) {
        System.out.println("  dbObjectChunks.toString() "+ chunksFile.toString());
        
        int fileidFileCurrent = (int) chunksFile.get("files_id");//files_id
        Image img = new Image(mfile);
        img.setFiles_IdChunks(fileidFileCurrent);
        //controllo il db=VIDEO e la collection=VIDEO.files
        List<DBObject> files = this.getListDBObjectGenerics(DatabasesAndCollectionsNames.COLLECTION_FILES_NAME,
                DatabasesAndCollectionsNames.DBVIDEO);
        for (Iterator<DBObject> iterator = files.iterator(); iterator.hasNext();) {
            DBObject next = iterator.next();
             System.out.println(" next.toString() ====== "+ next.toString());
            int _idFileCurrent = (int) next.get("_id");
          //  String str = (String) next.get("filename");
            //if (str.equalsIgnoreCase(img.getFileName())) {
                img.setIdFile(_idFileCurrent);
           // }
        }
        System.out.println(" img.toString() ==========   " + img.toString());
        return img;
    }

    /*public void removeFile(DB db, String collectionName, DBObject queryRemoveFile) {
        GridFS gfsPhoto = new GridFS(db, collectionName);
        if( gfsPhoto == null )
            return ;
           System.out.println("com.mongodb.util.MongodbQueryOperationsUtils.removeFile()"  +   gfsPhoto.toString() );
        gfsPhoto.remove(queryRemoveFile);
    }*/

    //funziona
    public void removeFileChunksAndFile(Image img) {
        DBObject chunks = img.getChunksFiles();
        DBObject metadato = img.getMetadatoFile();
        this.getCollDB(DatabasesAndCollectionsNames.DBVIDEO,
                DatabasesAndCollectionsNames.COLLECTION_CHUNKS_NAME)
                .findAndRemove(chunks);

        this.getCollDB(DatabasesAndCollectionsNames.DBVIDEO,
                DatabasesAndCollectionsNames.COLLECTION_FILES_NAME)
                .findAndRemove(metadato);
    }
       //funziona
    public void removeChunksAndFiles(String files_IdChunks){
        Image img = Image.getSpecificFileFromList(Integer.parseInt(files_IdChunks));
            this.delete(DatabasesAndCollectionsNames.DBVIDEO,
                    DatabasesAndCollectionsNames.COLLECTION_CHUNKS_NAME, img.getQueryFindChunksFile(img));
             this.delete(DatabasesAndCollectionsNames.DBVIDEO,
                    DatabasesAndCollectionsNames.COLLECTION_FILES_NAME, img.getQueryFindMetadatoFile(img));
        Image.listFile.remove(img);
    }
    
    /***
     * Questo metodo rimuove dalla lista locale che dal db corrente sia il metadato file che i vari currentChunksList
 del medesimo file, a partire del (int) _idmetadatiFile passato come parametro.
     * 
     */
    public boolean removeChunksAndFiles_(int _idmetadatiFile) throws Exception {
        
        ChunksFile chunksTmp=null;
        MetadatoFile metadatoFileTmp=null;
        for (MetadatoFile metadatoFile : this.metadatoFileList) {
            for (ChunksFile chunks : this.chunksList) {
                if (_idmetadatiFile == metadatoFile.getId()) {
                    if (metadatoFile.getId() == chunks.getFiles_id()) {
                        metadatoFileTmp = metadatoFile;
                        chunksTmp=chunks;
                    } 
                }
            }
        }
        if( this.metadatoFileList.remove(metadatoFileTmp) )
            if( this.chunksList.remove(chunksTmp))
                System.out.println("\nElementi rimossi dalla lista [ChunksLis e MetadatoList] locale");
       else
                System.out.println("\nElementi Impossibili da rimuovere dalla lista [ChunksLis e MetadatoList] locale");
        //cerco _idmetadatiFile
        DBObject metadatoFileMap = this.findBDBObjectFileByField(DatabasesAndCollectionsNames.COLLECTION_FILES_NAME, _idmetadatiFile);
        if (metadatoFileMap == null) {
            throw new Exception ("Metadato cercato non esiste nel db!! o già eliminato!! ");
        }
       // BasicDBObject queryDeleteMetaFile = new BasicDBObject( (Map)JSON.parse(metadatoFileMap.toString()) );
        MetadatoFile queryDeleteMetaFile = new MetadatoFile((Map)JSON.parse(metadatoFileMap.toString()));
       //*************************************************************************************
        //Cerco nel db tutti i currentChunksList
        List<ChunksFile> currentChunksList = this.getAllChunksFileList(DatabasesAndCollectionsNames.DBVIDEO,
                DatabasesAndCollectionsNames.COLLECTION_CHUNKS_NAME);
        ChunksFile findedChunk = null;
        
        
        //cerco nella lista di currentChunksList l'_idmetadatiFile = file_id del Doc del Chunks
        boolean trovato=false;
        List<DBObject> listChunksParts=new ArrayList<>();
        for (Iterator<ChunksFile> iterator = currentChunksList.iterator(); iterator.hasNext();) {
            ChunksFile next = iterator.next();
            if (next.getFiles_id() == _idmetadatiFile) {
                findedChunk = next;
                //raccolgo tutti i currentChunksList
                listChunksParts.add(next);
            }
        }
        if (findedChunk == null) {
            return false;
        }
        //*************************************************************************************
        System.out.println("\n NUMERO DI CHUNKS/PARTI NELLA LISTA FROM DB=" + listChunksParts.size() );
        System.out.println("\n NUMERO DI CHUNKS/PARTI NELLA LISTA LOCALE =" + chunksList.size() );
        
       
            
       // boolean remove = metadatoFileList.remove(queryDeleteMetaFile);
        //boolean remove_= chunksList.remove(findedChunk);//con
        
        //*************************************************************************************
        boolean isRemoved=true;
        //rimuovo tutte le parti prima suddivise del file precedentemente, cioè currentChunksList nel db.
        for (Iterator<DBObject> iterator = listChunksParts.iterator(); iterator.hasNext() ;) {
            DBObject queryDeleteChunks = iterator.next();
            if( this.getCollDB(DatabasesAndCollectionsNames.DBVIDEO,
                DatabasesAndCollectionsNames.COLLECTION_CHUNKS_NAME)
                .findAndRemove(queryDeleteChunks) ==null ) 
                isRemoved=false;
            //se la lista locale di currentChunksList non è vuota allora rimuovo anche da li.
            
        }
        //eleminiamo i metadati nel db
        if(this.getCollDB(DatabasesAndCollectionsNames.DBVIDEO,
                DatabasesAndCollectionsNames.COLLECTION_FILES_NAME)
                .findAndRemove(queryDeleteMetaFile) == null) isRemoved=false;
        
        //*************************************************************************************
        
        //aggiorno liste locale.
       //chunksList= chunksList;// this.getAllChunksFileList(DatabasesAndCollectionsNames.DBVIDEO,
                //DatabasesAndCollectionsNames.COLLECTION_CHUNKS_NAME);
       
      // metadatoFileList= getAllMetadatoFileList(DatabasesAndCollectionsNames.DBVIDEO,DatabasesAndCollectionsNames.COLLECTION_FILES_NAME);
      // updateLocalChunksAndMetaFileList();
        return isRemoved;
    }
    
    public void updateLocalChunksAndMetaFileList(){
    
       /// if( !(this.chunksList.isEmpty() || this.metadatoFileList.isEmpty()))
       //     return;
           //aggiorno liste locale.
       chunksList= this.getAllChunksFileList(DatabasesAndCollectionsNames.DBVIDEO,
                DatabasesAndCollectionsNames.COLLECTION_CHUNKS_NAME);
       /* for (ChunksFile chunksFile : chunksList) {
            System.out.println("\n\nchunksFile.toString() " + chunksFile.toString());
        }*/
       
       metadatoFileList= getAllMetadatoFileList(DatabasesAndCollectionsNames.DBVIDEO,DatabasesAndCollectionsNames.COLLECTION_FILES_NAME);
         /* for (MetadatoFile metadatoFile : metadatoFileList) {
            System.err.println("\n\n metadatoFile.toString() " + metadatoFile.toString());
        }*/
       
    
    }
    
    
    
    
    
    
    //list of currentChunksList generics
    public  List<DBObject> getListFromDbChunk(Image img) {
        DBObject chunks = img.getChunksFiles();
        return this.getCollDB(DatabasesAndCollectionsNames.DBVIDEO,
                DatabasesAndCollectionsNames.COLLECTION_CHUNKS_NAME)
                .find(chunks).toArray();
    }

     //list of file metadato generics
    public  List<DBObject> getListFromDbFile(Image img) {
        DBObject metadato = img.getMetadatoFile();
        return this.getCollDB(DatabasesAndCollectionsNames.DBVIDEO,
                DatabasesAndCollectionsNames.COLLECTION_FILES_NAME)
                .find(metadato).toArray();
    }
    
    
    
    public void viewAllFiles(DB db, String collectinNameFsFiles) {

        GridFS gfsPhoto = new GridFS(db, collectinNameFsFiles);
        DBCursor cursor = gfsPhoto.getFileList();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }

    }

    public void viewSingleFileGridFDFile(String dbName, String collectinNameFsFiles, DBObject dbObject) {

        try {
            //List<DBObject> fsFiles = this.getListDBObjectGenerics(dbName, collectinNameFsFiles );
            /// List<DBObject> currentChunksList = this.getListDBObjectGenerics(dbName, collectinNameChunks );

            MongoCollection<Document> mgoColl = this.getMongoCollection(dbName, collectinNameFsFiles);
            MongoDatabase mgodb = this.initConnectionMongoDatabase("FILES_PROVA");
            GridFSBucket gridBucket = GridFSBuckets.create(mgodb);
            com.mongodb.client.gridfs.model.GridFSFile gridFSFile = gridBucket.find(eq(dbObject)).first();
            // System.out.println("File Name:- " + gridFSFile.getFilename());
            ///System.out.println("Meta Data:- " + gridFSFile.getMetadata());
            System.out.println("gridFSFile.toString()  :- " + gridFSFile.toString());

        } catch (Exception ex) {
            Logger.getLogger(MongodbQueryOperationsUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

                
  //*****************************************************************************
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    
}


