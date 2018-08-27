/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootstrap.model;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import org.bson.Document;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Utente
 */
public class FileVideo  extends BasicDBObject  implements Serializable{//BasicDBObject // MultipartFile
    
    
    private String titolo;
    
    private File file;
  //  MultipartFile  f;
  //  FileCopyUtils fc;
    
    //private MultipartFile mf;
      
    public FileVideo(String titolo, File file) {
        this.titolo=titolo;
        this.file=file;
        super.put("Titolo video", this.titolo);
        super.put("File",this.file);//JSON.serialize(this.file)
    }

    public FileVideo() {
    }

    public String getTitolo() {
        String tit = super.getString("Titolo");
         if(tit == null)
            tit = this.titolo;
         return tit;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
        super.put("Titolo", this.titolo);
    }

    public File getFile() {
        File f = null;
        if(super.get("File") == null)
          f = this.file;
        else
           f=(File) super.get("File");
        return f;
    }

    public void setFile(File file) {
        this.file = file;
        super.put("File", JSON.serialize(this.file));
    }

    
    
    
    
  
    
    
}
