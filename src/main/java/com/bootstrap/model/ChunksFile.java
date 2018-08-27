/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootstrap.model;

import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFSInputFile;
import java.util.Map;
import org.bson.internal.Base64;
import org.bson.types.ObjectId;

/**
 *
 * @author Utente
 */
public class ChunksFile extends BasicDBObject {
    

    private ObjectId _id;
    private Integer files_id;
    private Integer n;
    private byte [] data;
    private String file;

    public ChunksFile(Map map) {
        super(map);
    }

  
    
    public ChunksFile() {
    }

    
    public ChunksFile(ObjectId _id, Integer files_id, Integer n, byte[] bytesChunksFile) {
        super.put("_id", n);
        super.put("files_id", n);
        super.put("n", n);
        super.put("data", bytesChunksFile);
    }

    public ObjectId getId() {
        return super.getObjectId("_id");
    }

    public void setId(ObjectId _id) {
       super.put("_id", _id);
    }

    public Integer getFiles_id() {
        return super.getInt("files_id");
    }

    public void setFiles_id(Integer files_id) {
        super.put("files_id", files_id );
    }

    public Integer getN() {
         return super.getInt("n");
    }

    public void setN(Integer n) {
       super.put("n", n);
    }

    public byte[] getData() {
       return (byte[])super.get("data");
    }

    public void setData(byte[] data) {
       super.put("data", data);
    }

    //
    public String getFile() {
        return Base64.encode(this.getData());
    }
    //
    public void setFile(String file) {
        this.file = file;
    }

    
    public String base64ConverterData(){
       return Base64.encode(this.getData());
    }
  
    public String toStringJson() {
        return  super.toJson();
    }

    @Override
    public String toString() {
        return "\n\n ChunksFile{" + "_id=" + this.getId() 
                + ", files_id=" + this.getFiles_id() 
                + ", n=" + this.getN() 
                + ", data length =" + this.getData().length 
                + "}\n\n";
    }
    
    
    
    
    
}
