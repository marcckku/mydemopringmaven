/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.my.librery;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.mongodb.util.DatabasesAndCollectionsNames;
import com.mongodb.util.JSON;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import static org.springframework.util.FileCopyUtils.BUFFER_SIZE;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Utente
 */
public class UtilGridFSDBFile {
    
    //********************** https://www.programcreek.com/java-api-examples/?api=com.mongodb.gridfs.GridFSDBFile ********************************
    public static byte[] readFileFromGridFS(GridFSDBFile file) {
        InputStream is = file.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            byte bytes[] = new byte[BUFFER_SIZE];
            int read = -1;

            while ((read = is.read(bytes)) != -1) {
                baos.write(bytes, 0, read);
            }

            return baos.toByteArray();
        } catch (IOException e) {
            System.out.println("com.my.librery.UtilGridFSDBFile.readFileFromGridFS(), " + e.getMessage());
        }
        return null;
    }
     
  
    
    /**
     * @param imageName unique image name
     * @return return an image mapped from the given name, null if one can't be
     * loaded
     */
    public static BufferedImage loadImage(final String imageName, GridFS images ) throws IOException {
        BufferedImage image = null;
        final List<GridFSDBFile> files = images.find(imageName);
        if (files != null && files.size() > 0) {
            final Date uploadDate = files.get(0).getUploadDate();
            final long timeDiff = Math.abs(uploadDate.getTime() - System.currentTimeMillis());
            final long limitTimeDiff = 1000L * 60 * 60 * 24 * 30;
            if (timeDiff > limitTimeDiff) {
                final ObjectId id = (ObjectId) files.get(0).getId();
                images.remove(id);
                return null;
            }
             final InputStream inputStream = files.get(0).getInputStream(); {
                image = ImageIO.read(inputStream);
            }
        }
        return image;
    }

    
    //Project: BLELocalization   File: MongoService.java   View source code
    //HttpServletResponse response =  response.getOutputStream()
    public static boolean readFile(GridFSDBFile gridfs, OutputStream os) throws IOException {
        InputStream is = null;
        try {
            is = gridfs.getInputStream();
            byte data[] = new byte[4096];
            int len = 0;
            while ((len = is.read(data, 0, data.length)) > 0) {
                os.write(data, 0, len);
            }
            return true;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
   
    
    
    /**
     * Updates already existing tei with new (more enriched one, fulltext..).
     */
    public static void updateTei(String newTei, String repositoryDocId, String collection,  DB db) {
        try {
            GridFS gfs = new GridFS(db, collection);
            GridFSDBFile gdf = gfs.findOne(repositoryDocId + ".tei.xml");
            GridFSInputFile gfsNew = gfs.createFile(new ByteArrayInputStream(newTei.getBytes()), true);
            gfsNew.put("uploadDate", gdf.getUploadDate());
            gfsNew.setFilename(gdf.get("repositoryDocId") + ".tei.xml");
            gfsNew.put("repositoryDocId", gdf.get("repositoryDocId"));
            gfsNew.put("documentType", gdf.get("documentType"));
            gfsNew.put("anhalyticsId", gdf.get("anhalyticsId"));
            gfsNew.put("source", gdf.get("source"));

            gfsNew.save();
            gfs.remove(gdf);
        } catch (Exception e) {
           // logger.error(e.getMessage(), e.getCause());
            System.out.println("com.my.librery.UtilGridFSDBFile.updateTei() " +  e.getCause());
        }
    }

    
    
    
    //https://examples.javacodegeeks.com/core-java/io/fileinputstream/read-file-in-byte-array-with-fileinputstream/# 
    public static void l(File file) {
        // File file = new File("inputfile.txt");
        FileInputStream fin = null;
        try {
            // create FileInputStream object
            fin = new FileInputStream(file);
            byte fileContent[] = new byte[(int) file.length()];
            // Reads up to certain bytes of data from this input stream into an array of bytes.
            fin.read(fileContent);
            //create string from byte array
            String s = new String(fileContent);
            System.out.println("File content: " + s);
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading file " + ioe);
        } finally {
            // close the streams using close method
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);

            }
        }
    }

    //https://www.programcreek.com/2009/02/java-convert-a-file-to-byte-array-then-convert-byte-array-to-a-file/
    public static byte[] getBytesFromFile(File file) throws FileNotFoundException {

        // File file = new File("java.pdf");
        byte[] bytes = null;
        FileInputStream fis = new FileInputStream(file);
        //System.out.println(file.exists() + "!!");
        //InputStream in = resource.openStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); //no doubt here is 0
                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        /*
         //below is the different part
        File someFile = new File("java2.pdf");
        FileOutputStream fos = new FileOutputStream(someFile);
        fos.write(bytes);
        fos.flush();
        fos.close();
        */
        return bytes = bos.toByteArray();
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     //********************** https://www.programcreek.com/java-api-examples/?api=com.mongodb.gridfs.GridFSDBFile ********************************
   
    
    
    
    
    
   public MultipartFile saveMultipartFile(MultipartFile mfile, DB db, int currentContattore) throws Exception {
         
         if(mfile == null || db == null)
             throw new Exception("mfile NULLO!! ||  db NULLO||");

        InputStream is = null;
        GridFS gridfs = null;
        GridFSInputFile gfsInputFile = null;
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

                gfsInputFile.setId(currentContattore);
                gfsInputFile.setFilename(fileName);
                int numberNextChunk = gfsInputFile.saveChunks();
                gfsInputFile.setContentType(contentType);
                if (numberNextChunk > 0) {
                    gfsInputFile.save();
                } else {
                    return null;
                }
            } catch (IOException ex) {
                String errore = "\"ERRORE FILE NON TROVATO  O NULLO   ::::::::::::::::: ";
                System.err.println(errore + ex.getMessage());
            } finally {
                try {
                    is.close();
                } catch (IOException ex) {
                    String errore = "ERRORE NEL CHIUDERE LO STREAM!!";
                    ex.printStackTrace();
                    System.err.println(errore + ex.getMessage());
                }
            }
        }
        return mfile;
    }   
    
    
    
    
    
   public static GridFSInputFile saveMultipartFileVer2(MultipartFile mfile, DB db, int currentContattore) throws Exception {
         
         if(mfile == null || db == null)
             throw new Exception("mfile NULLO!! ||  db NULLO||");

        InputStream is = null;
        GridFS gridfs = null;
        GridFSInputFile gfsInputFile = null;
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
               // System.out.println("  _idChunksFile " + _idChunksFile);
                gfsInputFile.setId(currentContattore);//CUSTOM ID Integer
                // gfsInputFile.setId(_idChunksFile); //DEFAULT  ID ObjectId
                gfsInputFile.setFilename(fileName);
                int numberNextChunk = gfsInputFile.saveChunks();
                gfsInputFile.setContentType(contentType);
                if (numberNextChunk > 0) {
                    gfsInputFile.save();
                } else {
                    return null;
                }
            } catch (IOException ex) {
                String errore = "\"ERRORE FILE NON TROVATO  O NULLO   ::::::::::::::::: ";
                System.err.println(errore + ex.getMessage());
            } finally {
                try {
                    is.close();
                } catch (IOException ex) {
                    String errore = "ERRORE NEL CHIUDERE LO STREAM!!";
                    ex.printStackTrace();
                    System.err.println(errore + ex.getMessage());
                }
            }
        }
        
        return gfsInputFile;
    }
    
    
    
    
    
    //prova
    public static int  getLengthSingleImage(DB db, BasicDBObject queryFindFile) {
        try {
            // String newFileName = "c:/DemoImage";
            GridFS gfsPhoto = new GridFS(db, db.getName());
            GridFSDBFile imageForOutput = gfsPhoto.findOne(queryFindFile);
            System.out.println(  "SIZE BYTES INPUTSTREAM FROM FILE"    +  imageForOutput.getInputStream().read());
            
            BasicDBObject newMetadato = new BasicDBObject( imageForOutput.toMap());
            //imageForOutput.getInputStream()
            return Integer.parseInt( ""+imageForOutput.getLength() );
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(UtilGridFSDBFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
   
       /**
     * Questo meotodo ritorna l'oggetto DBCursor (oggetti generici sia metadata
     * o i chunks) il quale contiene tutti gli oggetti della mia collezione
     * passata come parametro. Altrimenti NUll. <br>
     * Se la collection non esiste allora viene creata!!!. Di solito questo
     * metodo si usa dopo che si è già fatta la connection al db.
     * <br>
     * Ver BasicDBObject
     *
     * @param db
     * @param collectionName
     * @return
     */
    public static DBCursor getListDocuments_(DB db, String collectionName) {
        DBCollection collections = db.getCollection(collectionName);
        if (collections == null) {
            return null;
        }
        DBCursor dbCursor = collections.find();
        return dbCursor;
    }
    
    
    /***
     * Questo metodo trova una lista di DBObject, usa al suo interno 
     * la classe GridFS. Null altrimenti.
     */
    public static List<DBObject>  getAllMetadatoFileList(DB db){
       GridFS gridfs = new GridFS (db, db.getName());
       DBCursor dbCursor =gridfs.getFileList();
       List<DBObject> list = new  ArrayList<>();
       while(dbCursor.hasNext()){
         DBObject obj = dbCursor.next();
         list.add(obj);
       }
       return list;
    }
    
    
    
     //prova
    public void up(DBObject queryFind, DB db ){
        try {
            GridFS gridfs = new GridFS(db,db.getName());
            GridFSDBFile gfsFileOut = (GridFSDBFile) gridfs.findOne(queryFind);
            InputStream is = gfsFileOut.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line="";
            while ((line = br.readLine()) != null) {

            }
            
             ByteArrayOutputStream stream = new ByteArrayOutputStream();// = converter.convert(file.getInputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
    }
    
    
    
    // GRIDFS LINK https://howtodoinjava.com/mongodb/java-mongodb-getsave-image-using-gridfs-apis/
    
    //https://github.com/Litarvan/vget  
    
    //https://stackoverflow.com/questions/4032766/how-to-download-videos-from-youtube-on-java
    
    
    //https://www.mkyong.com/java/how-to-encode-a-url-string-or-form-parameter-in-java/
    public static void codificaUrl() {

        try {

            String url = "<![CDATA[ <IMG SRC=\"  javascript:document.vulnerable=true;\"> ]]>";

            String encodedUrl = URLEncoder.encode(url, "UTF-8");

            System.out.println("Encoded URL " + encodedUrl);

            String decodedUrl = URLDecoder.decode(url, "UTF-8");

            System.out.println("Decoded URL " + decodedUrl);

        } catch (UnsupportedEncodingException e) {

            System.err.println(e);

        }
    }

    public static String encodeUrlInUTF8(String url) {
        String encodedUrl = "";
        try {
            encodedUrl = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            java.util.logging.Logger.getLogger(UtilGridFSDBFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encodedUrl;
    }
    
     public static String decodeUrlInUTF8(String url) {
        String decodedUrl = "";
        try {
            decodedUrl = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            java.util.logging.Logger.getLogger(UtilGridFSDBFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return decodedUrl;
    }
    
    
    
    
    public static void main(String [] args){
    
       String ur ="/remove/file/{_id}";
        System.out.println("   decodeUrlInUTF8(ur) = "   +  encodeUrlInUTF8(ur));
        // ris = %2Fremove%2Ffile%2F%7B_id%7D
       //codificaUrl();
    
    }
    
    
     public static ByteBuffer getAllBytesFromChunksList(List<ByteBuffer> allChunksList){
       
        int len=0;
         ByteBuffer mybuf =null;
        for (Iterator<ByteBuffer> bufCurrent = allChunksList.iterator(); bufCurrent.hasNext();) {
            ByteBuffer buf = bufCurrent.next();
            len+=buf.array().length;
        }
         mybuf=ByteBuffer.allocate(len);
        //System.out.println("LENGTH ARRAY BYTES FROM LIST ARRAYS BYTES CHUNKS = \t[ " + len + " ]");     
        int index=0;
         for (Iterator<ByteBuffer> bufCurrent = allChunksList.iterator(); bufCurrent.hasNext();) {
            ByteBuffer buf = bufCurrent.next();
            for (int i=0; i < buf.limit() ; i++) {
                //  System.out.println("Get[" + buf.position() + "]=>" + buf.get() + ". Remaining " + buf.remaining());     
                mybuf.put( buf.array()[i]);
            }
        }
         
        return mybuf;
    }
    
    
    
     
     //*********************
    
    public static byte[] compress(String data) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length());
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        gzip.write(data.getBytes());
        gzip.close();
        byte[] compressed = bos.toByteArray();
        bos.close();
        return compressed;
    }


    public static byte[] compress_(byte [] data) throws IOException {
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        gzip.write(data);
        gzip.close();
        byte[] compressed = bos.toByteArray();
        bos.close();
        return compressed;
    }

    //comprimi http://www.java2s.com/Tutorials/Java/Data_Type_How_to/String/Encode_and_decode_string_Base64.htm
     public static String deflate( byte[] output1) throws UnsupportedEncodingException {
        Deflater compresser = new Deflater();
        compresser.setInput( output1);
        compresser.finish();
        int compressedDataLength = compresser.deflate(output1);
        compresser.end();

        String str = new String(Base64.getEncoder().encode(output1));
        System.out.println("\n\nDeflated String:" + str);
        
        return str;

    }
     
    //decomprimi
    public static byte[] inflater(byte [] compressedInBase64Deflaitated) {
        try {
            // Decompress the bytes
            Inflater decompresser = new Inflater();
            decompresser.setInput(compressedInBase64Deflaitated);
            byte[] result = compressedInBase64Deflaitated;
            int resultLength = decompresser.inflate(compressedInBase64Deflaitated);
            decompresser.end();
            // Decode the bytes into a String
            String outputString = new String(result, 0, resultLength, "UTF-8");
            System.out.println("Inflated String:" + outputString);
            outputString.getBytes();
        } catch (UnsupportedEncodingException ex) {
            java.util.logging.Logger.getLogger(UtilGridFSDBFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataFormatException ex) {
            java.util.logging.Logger.getLogger(UtilGridFSDBFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
     //https://gist.github.com/yfnick/227e0c12957a329ad138
    public static String decompress(byte[] compressed) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
        GZIPInputStream gis = new GZIPInputStream(bis);
        BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        gis.close();
        bis.close();
        return sb.toString();
    }
    
    public static String decompress_(final byte[] compressed) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
        GZIPInputStream gis = new GZIPInputStream(bis);
        byte[] bytes = IOUtils.toByteArray(gis);
        return new String(bytes, "UTF-8");
    }
    
}//end class


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

