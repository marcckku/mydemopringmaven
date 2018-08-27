/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootstrap.model;

import com.mongodb.BasicDBObject;
import java.util.Date;
import java.util.Map;
import javafx.scene.chart.PieChart.Data;
import org.bson.internal.Base64;

/**
 *
 * @author Utente
 */
public class MetadatoFile extends BasicDBObject {   
    
    
    private Integer _id=0;
    private String filename="";
    private String aliases="";
    private Integer chunkSize=null; 
    private Date uploadDate=null;
    private Integer length=0;
    private String contentType="";
    private String md5;

    public String dato="";
    public String formato="";
    
    public MetadatoFile() {
    }

    public MetadatoFile(BasicDBObject newObj) {
        super(newObj);

    }
    
     public MetadatoFile(Map map) {
        super(map);

    }
   
    public MetadatoFile(Integer _id, String filename, String aliases, Integer chunkSize, Date uploadDate, Integer length, String contentType, String md5) {
        super.put("_id",this._id=_id);
        super.put("filename",this.filename=filename);
        super.put("aliases",this.aliases=aliases);
        super.put("chunkSize",this.chunkSize=chunkSize);
        super.put("uploadDate",this.uploadDate=uploadDate);
        super.put("length",this.length=length);
        super.put("contentType",this.contentType=contentType);
        super.put("md5",this.md5=md5);
    }
   
    
     
    
    public Integer getId() {
         this._id=(Integer) super.get("_id");
         return _id;
    }

    public void setId(Integer _id) {
         super.put("_id",this._id=_id);
    }

    public String getFilename() {
        this.filename= super.getString("filename");
        return this.filename;
    }

    public void setFilename(String filename) {
        super.put("filename",this.filename=filename);
    }

    public String getAliases() {
        this.aliases= super.getString("aliases");
        return this.aliases;
    }

    public void setAliases(String aliases) {
        super.put("aliases",this.aliases=aliases);
    }

    public Integer getChunkSize() {
        this.chunkSize= (Integer)super.get("chunkSize");
        return this.chunkSize;
    }

    public void setChunkSize(Integer chunkSize) {
        super.put("chunkSize",this.chunkSize=chunkSize);
    }

    public Date getUploadDate() {
        this.uploadDate= super.getDate("uploadDate");
        return this.uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
       super.put("uploadDate",this.uploadDate=uploadDate);
    }

    public Integer getLength() {
        this.length= (Integer) super.get("length");
        return this.length;
    }

    public void setLength(Integer length) {
        super.put("length",this.length=length);
    }

    public String getContentType() {
        this.contentType= super.getString("contentType");
        return this.contentType;
    }

    public void setContentType(String contentType) {
       super.put("contentType",this.contentType=contentType);
    }

    public String getMd5() {
        this.md5= super.getString("md5");
        return this.md5;
    }

    public void setMd5(String md5) {
        super.put("md5",this.md5=md5);
    }

    
    
    ///
    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getFormato() {
      // this.formato= (String)super.getString("formato"); 
       return this.formato;
    }

    public void setFormato(String formato) {
       // super.put("formato", this.formato=formato);
       this.formato=formato;
    }
    
   
    
    
    @Override
    public String toString() {
        return "\n\ntoString - MetadatoFile -"
                + "\n{" 
                + "_id=" + this.getId() 
                + ", filename= " + this.getFilename() 
                + ", aliases=" + this.getAliases()
                + ", chunkSize=" + this.getChunkSize() 
                + ", uploadDate=" + this.getUploadDate() 
                + ", length=" + this.getLength() 
                + ", contentType=" + this.getContentType() 
                + ", md5=" + this.getMd5() 
                + ", formato = " +getFormato()
                + ", encoding Base64 (length * 3) dato = " + getDato().length()
                + "}\n" ;
    }

    public String toStringJson() {
        return super.toJson();
    }
    
}
