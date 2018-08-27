/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootstrap.model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSInputFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.internal.Base64;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Utente
 */
public class Image {

    private Integer files_IdChunks;
    private Integer _idFile;
    private String fileName="";
    private byte[] bytesFile;
    private MultipartFile mfile;
    private String encodingBase64="";
    private String formatto="";
    
    private DBObject chunksFiles;
    private DBObject metadatoFile;
    public static List<Image> listFile=new ArrayList<>();
    
    
    public Image(MultipartFile mfile) {
        this.mfile = mfile;
        setAllFields();
    }

    public Image(MultipartFile mfile, DBObject chunksFiles, DBObject metadatoFile) {
        this.mfile = mfile;
        this.chunksFiles = chunksFiles;
        this.metadatoFile = metadatoFile;
        setAllFields();
    }
    
    

    public Image(Integer files_id, MultipartFile mfile) {
        this.files_IdChunks = files_id;
        this.mfile = mfile;
        setAllFields();
    }

    public Image() {
    }

    public Integer getFiles_IdChunks() {
        return files_IdChunks;
    }

    public void setFiles_IdChunks(Integer files_IdChunks) {
        this.files_IdChunks = files_IdChunks;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getBytesFile() {
        return bytesFile;
    }

    public void setBytesFile(byte[] bytesFile) {
        this.bytesFile = bytesFile;
    }

    public MultipartFile getMfile() {
        return mfile;
    }

    public void setMfile(MultipartFile mfile) {
        this.mfile = mfile;
    }

    public String getEncodingBase64() {
        return encodingBase64;
    }

    public void setEncodingBase64(String encodingBase64) {
        this.encodingBase64 = encodingBase64;
    }

    public Integer getIdFile() {
        return _idFile;
    }

    public void setIdFile(Integer _idFile) {
        this._idFile = _idFile;
    }

    public String getFormatto() {
        return formatto;
    }

    public void setFormatto(String formatto) {
        this.formatto = formatto;
    }

    public DBObject getChunksFiles() {
        return chunksFiles;
    }

    public void setChunksFiles(DBObject chunksFiles) {
        this.chunksFiles = chunksFiles;
    }

    public DBObject getMetadatoFile() {
        return metadatoFile;
    }

    public void setMetadatoFile(DBObject metadatoFile) {
        this.metadatoFile = metadatoFile;
    }

    
      private void setAllFields() {
        if (this.mfile == null) {
            return;
        }
        try {
            //***************************************************************
            if(  this.chunksFiles != null   )
                this.files_IdChunks = (Integer) this.chunksFiles.get("files_id");
             if(  this.metadatoFile != null   )
                this._idFile = (Integer) this.metadatoFile.get("_id");
            //***************************************************************
            String[] str = this.mfile.getOriginalFilename().split("\\.");
            //Serparo la stringa esempio=[mp_neve.jpg] in 2 Stringhe= [mp_neve], [jpg], grazie a delimiter [\\.]       
            this.fileName = str[0];//nome file
            this.formatto = str[1];//estensione file
            //String contentType = this.mfile.getContentType();
            this.bytesFile = this.mfile.getBytes();//m bytes from file
            System.out.println("LENGHT ========= "   +   this.bytesFile.length );
            this.encodingBase64 = Base64.encode(this.bytesFile);//file convertito in Str
            //***************************************************************
           
                    
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    //da trasferire questo metodo poi dopo
    public static Image getSpecificFileFromList(int files_IdChunks){
        for (Image image : Image.listFile) {
            if(image.getFiles_IdChunks() == files_IdChunks)
                return image;
        }
        return null;
    }
    
    //da trasferire questo metodo poi dopo
    public BasicDBObject getQueryFindMetadatoFile(Image img) {
       // BasicDBObject queryFind = new BasicDBObject();
        for (Image image : Image.listFile) {
            if (image.equals(img)) {
                return BasicDBObject.parse(image.getMetadatoFile().toString());
              //  queryFind.append("_id", image.getIdFile());
               // queryFind.append("filename", image.getFileName());
            }
        }
        return null;
    }
    
     //da trasferire questo metodo poi dopo
    public BasicDBObject getQueryFindChunksFile(Image img) {
        BasicDBObject queryFind = new BasicDBObject();
        for (Image image : Image.listFile) {
            if (image.equals(img)) {
                 //return BasicDBObject.parse(image.getChunksFiles().toString());
                 System.out.println("\n\nimage.toString() >>>>>>>>>>>>>>>>>>>>>>>>>>>>>  "  +   image.toString()   );
               // queryFind.append("_id",image.);
                queryFind.append("files_id", files_IdChunks);
            }
        }
        return queryFind;
    }
      
      

    @Override
    public String toString() {
         String str=  ""
                 + "\n\nImage{" + ""
                 + "\nfiles_IdChunks=" + files_IdChunks + ","
                 + "\n _idFile=" + _idFile + ","
                 + "\ntitolo=" + fileName + ","
                 + "\nbytesFile=" + bytesFile +  ","
                 + "\nFormatto ="+ this.formatto  +""
                 + "\n\n"
                 + ""+'}';// + ", mfile=" + mfile + ", encodingBase64=" + encodingBase64 +
         return str + "\n" + this.getMetadatoFile().toString() + "\n"
                    + "\n" + this.getChunksFiles().toString()  + "\n";
    }

    

}
