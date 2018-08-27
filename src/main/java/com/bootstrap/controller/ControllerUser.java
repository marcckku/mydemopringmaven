/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootstrap.controller;

import com.bootstrap.model.User_;
import com.bootstrap.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.boostrap.singleton.ConnectionMongodbClient;
import com.mongodb.util.DatabasesAndCollectionsNames;
import com.mongodb.util.MongodbQueryOperationsUtils;
import com.my.librery.MyLibrery;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Utente
 */

@MultipartConfig 
@Controller
public class ControllerUser {
    
    
        //**************inserito il 08/05/2018****************
   /* public static final String DB1 = "dbTest";
    public static final String COLLECTION1 = "coll_test";
    public static final String COLLECTION2 = "coll_video";
    public static final String DBVIDEO = "VIDEO";
    public static Integer id=1;
    */
        
    public static MongoClient mongodbClient = null;
    //versione deprecata usa BasicDBObject
   /** public DB initConnection(String nomeDB){
        ControllerUser.mongodbClient = ConnectionMongodbClient.getInstance();
        return ControllerUser.mongodbClient.getDB(nomeDB);
    }*/
    //versione attuale usa Document
 /**   public MongoDatabase  initConnectionMongoDatabase(String nomeDB){
        ControllerUser.mongodbClient = ConnectionMongodbClient.getInstance();
        return ControllerUser.mongodbClient.getDatabase(nomeDB);
   }
    
    */
    //***************************
    
    
     private static  MongodbQueryOperationsUtils mongodbUtils=null;
     
     

    
        /**
     * ***************************************
     */
    // 1_sign-in 
    @RequestMapping(value = "/1_sign_in", method = RequestMethod.GET)
    public String sign_in1() {
        return "1_sign-in";
    }

    
    
   //***********************************INIT 1-form-register-utente ver Document**************************************************************
    
    /***
     * Tramite URI trovi la pagina
     * Ver Doc
     * 
     * 1-form-register-utente
     */
    @RequestMapping(value = "/contollerUser/init_registra_user", method = RequestMethod.GET)
    public String setupForm(ModelMap model) {
        model.addAttribute("user", new User());
        return "1-form-register-utente"; //viewname
    }
    
    /**
     * 
     * Ver Doc
     * 
     * 1-form-register-utente
     */
    @RequestMapping(value = "/form_register1", method = RequestMethod.POST)
    public String form_reg1() {
        return "1-form-register-utente";
    }
    
    
     //ver Doc
    @RequestMapping(value = "/contollerUserDoc/init_elenco_users", method = RequestMethod.GET)
    public String initElencoOpUser(ModelMap model) {
        model.addAttribute("user", new User());
         List<User> l =  new MongodbQueryOperationsUtils().getListUsers( DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1); 
         if(l != null)
            model.addAttribute("listaDocsUsers", l);
        return "elenco_op_users_mongodbDoc";
    }
    
    
    
      /**
     * *
     * Inserzione di un User, con una sequenza che autoincrementa
     * automaticamente ad ogni inserzione. Utilizo di User che extende Document.
     *
     * Info inserita dalla view 1-form-register-utente via POST.
     * 
     * Ver. Dcoument
     */
    @RequestMapping(value = "/contollerUserDoc/adduser", method = RequestMethod.POST)
    public String processSubmitFormElencoUser(@ModelAttribute("user") @Valid User user, BindingResult result, SessionStatus status, Model model) {

       
        mongodbUtils = new MongodbQueryOperationsUtils();
        // System.out.println("USER DOCUMENTO PASSATO COME PARAMETRO , user_.toJson()" + user.toJson() + "  _id = " +user.get("_id"));
        try {
            // mongodbUtils = new MongodbQueryOperationsUtils();
            //user passato non ha ancora il vla seq corrente
            boolean exist = mongodbUtils.isDocUserInsert(DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1, user);   // mongodbUtils.isDocUserInsert(DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1, user);

            System.out.println("ESSISTE USER DOCUMENT CURRENT ?? = [ " + exist + " ]");
            if (!exist) {
                if (!mongodbUtils.insertDocumenttIntoDB(user, DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1, DatabasesAndCollectionsNames.CURRENT_VAL_PKEY_OBJ_SEQ)) {
                    return "fail";
                }
            }else {
                //aggiorno user_
                mongodbUtils.updateUserDoc(user, DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1);
                mongodbUtils.viewAllDocs(DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1);
        
            }
            //non neccessario passare un user_ aggiornato visto che la lista va a prendere tutti gli users_ presenti aggiornati e non nel db.
            List<User> l = mongodbUtils.getListUsers(DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1);
            model.addAttribute("listaDocsUsers", l);

            return "redirect:/contollerUserDoc/init_elenco_users";
        } catch (Exception ex) {
            System.out.println("com.bootstrap.controller.ControllerUser.processSubmitFormElencoUser() " + ex.getLocalizedMessage());
            Logger.getLogger(ControllerUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "fail";

    }

    // Ver Doc 
    @RequestMapping("/rimuovere/user/{seq}")
    public String removeUser(@PathVariable("seq") int seq, Model model) {
        try {
            mongodbUtils = new MongodbQueryOperationsUtils();
            // mongodbUtils.viewSingleDocument( DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1,  mongodbUtils.findByValFielFromMyListDocument(DB1, COLLECTION1, seq));
            //mongodbUtils.deleteDocument(seq,DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1) ;
            mongodbUtils.deleteDocument_(DatabasesAndCollectionsNames.DB1,
                    DatabasesAndCollectionsNames.COLLECTION1,
                    mongodbUtils.findByValFielFromMyListDocument(DatabasesAndCollectionsNames.DB1,
                            DatabasesAndCollectionsNames.COLLECTION1,
                            seq).getObjectId("_id"));

            List<User> list = mongodbUtils.getListUsers(DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1);
            model.addAttribute("listaDocsUsers", list);
            return "redirect:/contollerUserDoc/init_elenco_users";
        } catch (Exception ex) {
            Logger.getLogger(ControllerUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "fail";
    }

      /***
     * Questo metodo carica soltanto l'obj con il val seq corrente.
     * 
     */
    @RequestMapping("/editare/user/{seq}")
    public String editUser(@PathVariable("seq") int seq, Model model) {

        try {
            mongodbUtils = new MongodbQueryOperationsUtils();
            System.out.println("\n\n\n VAL SEQ DOCUMENTO DA EDITARE = " + seq);

            User findedUserDocEdit = mongodbUtils.findByValFielFromMyListOfDocuments(DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1, seq);
            if (findedUserDocEdit == null) {
                return "fail";
            }
            //carico soltanto il user_ con val seq corrente da editare poi nel browser.
            List<User_> list = mongodbUtils.getListUsers_(DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1);
            System.out.println("\n\n\n DOCUMENT USER TROVATO  user.toString() = " + findedUserDocEdit.toString());
            model.addAttribute("user", findedUserDocEdit);
            model.addAttribute("listaDocsUsers", list);
            return "elenco_op_users_mongodbDoc";
        } catch (Exception ex) {
            Logger.getLogger(ControllerUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "fail";
    }
    
    
    
    
    
    //***********************************END 1-form-register-utente ver Document**************************************************************
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //***********************************INIT 1-form-register-utente_1 && elenco_op_users_mongodb VIEWS ver BasicObject************************
    

    //ver BasicObject
    @RequestMapping(value = "/contollerUser/init_registra_user_", method = RequestMethod.GET)
    public String userFormReg_(ModelMap model) {
        model.addAttribute("regUser_", new User_());
        return "1-form-register-utente_1";//1-form-register-utente_1"; //viewname
    }
    
    
    /**
     * Inserzione di un User_, con una sequenza che autoincrementa
     * automaticamente ad ogni inserzione.
     *
     * Ver BasicDBObject
     */
    @RequestMapping(value = "/contollerUser/adduser_", method = RequestMethod.POST)
    public String processSubmitFormUser_(@ModelAttribute("regUser_") @Valid User_ user_, BindingResult result, SessionStatus status, Model model) {

        mongodbUtils = new MongodbQueryOperationsUtils();
        if (!mongodbUtils.insertBDBOjectIntoDB(user_, DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1, DatabasesAndCollectionsNames.CURRENT_VAL_PKEY_OBJ_SEQ)) {
            return "fail";
        }
        return "redirect:/contollerUser/init_registra_user_";

    }
    
    
    //***********************************INIT elenco_op_users_mongodb***************************************************************
    //ver BasicObject
    @RequestMapping(value = "/contollerUser/init_elenco_users_", method = RequestMethod.GET)
    public String initElencoOpUser_(ModelMap model) {
        model.addAttribute("oneUser_", new User_());
        mongodbUtils = new MongodbQueryOperationsUtils();
        List<User_> l = mongodbUtils.getListUsers_(DatabasesAndCollectionsNames.DB1 , DatabasesAndCollectionsNames.COLLECTION1);
        if(l!=null)
            model.addAttribute("listaUsers_", l);
        return "elenco_op_users_mongodb";//1-form-register-utente_1"; //viewname
    }

   

    /**
     * *
     * Inserzione di un User_, con una sequenza che autoincrementa automaticamente ad ogni inserzione.
     * L'user_ passato come parametro NON ha un val seq quindi è sempre null.
     *
     * Ver BasicDBObject
     */
    @RequestMapping(value = "/contollerUser/add/oneUser_", method = RequestMethod.POST)
    public String insertUsersElenco(@ModelAttribute("oneUser_") @Valid User_ user_, BindingResult result, SessionStatus status, ModelMap model) {

       // System.out.println("USER PASSATO COME PARAMETRO , user_.toJson()" + user_.toJson() + "  _id = " +user_.get("_id"));
        try {
            mongodbUtils = new MongodbQueryOperationsUtils();
            boolean exist = mongodbUtils.isUserInsert(DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1, user_);

              System.out.println("boolean exist user_ " + exist);
            if (!exist) {
                if (!mongodbUtils.insertBDBOjectIntoDB(user_, DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1, DatabasesAndCollectionsNames.CURRENT_VAL_PKEY_OBJ_SEQ)) {
                    return "fail";
                }
            } else {
                //aggiorno user_
                 mongodbUtils.updateUser_(user_, DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1);
            }
            //non neccessario passare un user_ aggiornato visto che la lista va a prendere tutti gli users_ presenti aggiornati e non nel db.
            List<User_> l = mongodbUtils.getListUsers_(DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1);
            model.addAttribute("listaUsers_", l);

            return "redirect:/contollerUser/init_elenco_users_";
        } catch (Exception ex) {
            System.out.println("com.bootstrap.controller.ControllerUser.insertUsersElenco() " + ex.getLocalizedMessage());
            Logger.getLogger(ControllerUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "fail";
    }

    // Ver BasicDBObject
     @RequestMapping("/remove/{seq}")
    public String removeUser_(@PathVariable("seq") int seq, Model model) {
        mongodbUtils = new MongodbQueryOperationsUtils();
        if(!mongodbUtils.deleteBDBOjectIntoDB(seq, DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1) ){
            return "fail";
        }
         List<User_> list = mongodbUtils.getListUsers_(DatabasesAndCollectionsNames.DB1 , DatabasesAndCollectionsNames.COLLECTION1);
        model.addAttribute("listaUsers_", list);
        return "redirect:/contollerUser/init_elenco_users_";
    }

    /***
     * Questo metodo carica soltanto l'obj con il val seq corrente.
     * 
     */
    @RequestMapping("/edit/{seq}")
    public String editUser_(@PathVariable("seq") int seq, Model model) {

        try {
            mongodbUtils = new MongodbQueryOperationsUtils();
            System.out.println("\n\n\ntrovato id o seq  user da editare = " + seq);

            User_ user_Updated = mongodbUtils.findByValFielFromMyListUsers_(DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1, seq);
            if (user_Updated == null) {
                return "fail";
            }
            //carico soltanto il user_ con val seq corrente da editare poi nel browser.
            List<User_> list = mongodbUtils.getListUsers_(DatabasesAndCollectionsNames.DB1, DatabasesAndCollectionsNames.COLLECTION1);
            System.out.println("\n\n\nUSER_ TROVATO  user_.toString() = " + user_Updated.toString());
            model.addAttribute("oneUser_", user_Updated);
            model.addAttribute("listaUsers_", list);
            return "elenco_op_users_mongodb";
        } catch (Exception ex) {
            Logger.getLogger(ControllerUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "fail";
    }
    
    //***********************************END 1-form-register-utente_1 && elenco_op_users_mongodb VIEWS ver BasicObject************************

    
    
    
    
    
    
     //---------------------- upload_video_1 ------------------------------------
    
    
    
    
         /**  if (mdb.getCollection(DatabasesAndCollectionsNames.COLLECTION1) != null) {
                if (mcoll.find().first() != null) {
                    BasicDBObject query = new BasicDBObject("id", "2");

                    FindIterable<Document> iterDoc = mcoll.find(query);
                    int i = 0;
                    // Getting the iterator 
                    Iterator it = iterDoc.iterator();
                    while (it.hasNext()) {
                        System.out.println("\n */////CURRENT OBJECT//////  doc trovato =" + ((User) it.next()).toString());
                  /**      i++;
                    }
                    modelMap.put("user_", mcoll.find().first());
                    modelMap.addAttribute("user_", mcoll.find().first());
                    /// return "1-form-register-utente";
                    return "ok";
                }*/
                 
}
