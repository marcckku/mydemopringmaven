/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootstrap.controller;

import com.boostrap.singleton.MongodbQueryOperationsUtilsSingleton;
import com.bootstrap.model.ChunksFile;
import com.bootstrap.model.Image;
import com.bootstrap.model.MetadatoFile;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.mongodb.util.DatabasesAndCollectionsNames;
import com.mongodb.util.MongodbQueryOperationsUtils;
import com.my.librery.MyLibrery;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;
import org.bson.internal.Base64;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Utente
 */
@MultipartConfig
@Controller
public class ControllerAllFiles {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ControllerAllFiles.class);
    
    private static final MongodbQueryOperationsUtils mongodbUtils = MongodbQueryOperationsUtilsSingleton.getIstanceMongodbQueryOperationsUtils() ; //new MongodbQueryOperationsUtils();

    //---------------------- upload_video_1 ------------------------------------
    //upload_video_1
    @RequestMapping(value = "/init_upload_video_1", method = RequestMethod.GET)
    // public String upload_video_1(@RequestParam("file") MultipartFile file, ModelMap modelMap){
    public String upload_video_1(Model model) {
        //   model.addAttribute("fileVideo", new FileVideo());
       //  model.addAttribute("fileUploaded", new Image());
         model.addAttribute("fileUploaded",  new MetadatoFile());
         mongodbUtils.updateLocalChunksAndMetaFileList();
         model.addAttribute("listaFiles", mongodbUtils.metadatoFileList);
        return "upload_video_view_1" ;//upload_video_1";
    }

    //2_sign_in
    @RequestMapping(value = "/init_2_sign_in", method = RequestMethod.GET)
    public String _sign_in() {
        return "2_sign_in";
    }

    // 1_upload_file_view, questa view è una vista di esempio.
    @RequestMapping(value = "/init_1_upload_file_view", method = RequestMethod.GET)
    public String _upload_file_view() {
        return "1_upload_file_view";
    }

    //legata con la view upload_video_1.jsp
    //caricaVideoInDb
    /**
     * @RequestMapping(value = "/actionUploadVideo1", method =
     * RequestMethod.GET) public String
     * caricaVideoInDb(@ModelAttribute("fileVideo") @Valid FileVideo fileVideo,
     * BindingResult result, SessionStatus status, ModelMap model) {
     */
    //@RequestMapping(value = "/actionUploadVideo1", method = RequestMethod.POST)
    ///  public String handleUpload(@ModelAttribute("fileVideo") @Valid FileVideo f) {
    //@RequestParam("titolo") String titolo, @RequestParam ("mfile") File mfile // @RequestParam MultipartFile file_  
    // ) throws IOException {
    @RequestMapping(value = "/actionUploadVideo1", method = RequestMethod.POST)
    public String handleUpload(@RequestParam(value = "file") MultipartFile mfile, Model model) throws Exception { // , HttpSession session,   HttpServletRequest request, HttpServletResponse response
        if (mfile.isEmpty() | mfile == null | mfile.getBytes().length == 0) {
            System.err.println("ERRORE MultipartFile = NULL!!");
            throw new Exception("ERRORE FILE NON TROVATO  O NULLO   :::::::::::::::::  ");
        }
        DB db_ =mongodbUtils.initConnectionDB(DatabasesAndCollectionsNames.DBVIDEO);
       // Image ima  = mongodbUtils.insertIntoMongoDBWithMultipartFile(mfile, db_);
        boolean risp =mongodbUtils.saveMultipartFile(mfile, db_);
        logger.info("Server File Location DB name and Collection Name ="
                + db_.toString());
       // if (ima == null) {
         //   return "fail";
      //  }
     //   Image.listFile.add(ima);
        // List<Image> l= new ArrayList<Image>();
     //   model.addAttribute("listImages", Image.listFile);

         // List<MetadatoFile> matadatosList = mongodbUtils.getAllMetadatoFileList(DatabasesAndCollectionsNames.DBVIDEO, DatabasesAndCollectionsNames.COLLECTION_FILES_NAME);
    //    mongodbUtils.viewAllMetadatoFiles(db_);
         // List<ChunksFile> chunks = mongodbUtils.getAllChunksFileList(DatabasesAndCollectionsNames.DBVIDEO, DatabasesAndCollectionsNames.COLLECTION_CHUNKS_NAME);
        if ( !risp )
            return "fail";
        
     //  System.out.println( "\n\n=======mongodbUtils.metadatoFileList.get(0).getDato() ============  "   
             //  +   mongodbUtils.metadatoFileList.get(0).getDato() );
       // List<String> f=new ArrayList<>();
        //f.add(datoBase64);
       //  model.addAttribute("dato_", MongodbQueryOperationsUtils.metadatoFileList.get(0).getDato());
       
       mongodbUtils.updateLocalChunksAndMetaFileList();
       model.addAttribute("listaFiles", mongodbUtils.metadatoFileList);
        return "upload_video_view_1";
    }

    @RequestMapping("/remove/file/{_id}")
    public String removeFile(@PathVariable("_id") int _id, Model model) {
        try {
            //mongodbUtils.removeChunksAndFiles(files_IdChunks);
           if( !mongodbUtils.removeChunksAndFiles_(_id) )
               return "fail";
            mongodbUtils.updateLocalChunksAndMetaFileList();
            model.addAttribute("listImages", mongodbUtils.metadatoFileList);
            return  "redirect:/init_upload_video_1";   //"redirect:/upload_video_view_1";
        } catch (Exception ex) {
            Logger.getLogger(ControllerAllFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "fail";
    }

    
       
    
    
    
    
    @RequestMapping(value = "/actionUpload_video_view_1", method = RequestMethod.GET)
    public String upload_video_view_1() {
        return "upload_video_view_1";
    }

}
