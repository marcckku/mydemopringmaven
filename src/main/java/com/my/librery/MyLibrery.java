/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.my.librery;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Utente
 */
public class MyLibrery {
    
    private static int cont=1; //attenti qui perchè ogni volta che riavvi il server ricomincia da uno.
    

    
        
    
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
    
    
    
    /**
     * Ritorna true se il  Metodo inserisce un file in mongodb. Mongodb deve essere accesso.
     * False altrimenti.
     */
    public static boolean insertIntoMongoDBWithMultipartFile( MultipartFile mfile, DB db){
            
        boolean risp=false;
        InputStream is= null;
        try {
            
            String contentType= mfile.getContentType();
            String fileName = mfile.getOriginalFilename();
            GridFS gridfs = new GridFS( db , db.getName());// db=ref al db usato, invece db.getName() = nome dato alla tua collection esem: VIDEO.chunks o VIDEO.files altrimenti sè vuoto allora rimane soltanto : chunks e files.

            
            // Carichiamo il mfile
            is = mfile.getInputStream();
            gridfs.createFile(is);
            
             System.err.println(" gridfs.toString() " +  gridfs.toString());
            
             System.err.println(" db.toString() " +  db.toString());
             
             System.err.println(" mfile.toString() " +  mfile.toString());
              
              
             
             
            GridFSInputFile gfsInputFile = gridfs.createFile(is);
            gfsInputFile.setId(cont++);
            gfsInputFile.setFilename(fileName);
            int saveChunks = gfsInputFile.saveChunks();
            gfsInputFile.setContentType(contentType);
            if(saveChunks > 0){
                gfsInputFile.save(); 
               risp=true;
            }
           is.close();
        } catch (IOException ex) {  
            System.err.println("ERRORE FILE NON TROVATO  O NULLO   :::::::::::::::::  " + ex.getMessage());
            Logger.getLogger(MyLibrery.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
        return risp;
    }
    
    public static boolean existFileInMongoDb(int id){
        return (id <= 0 ? false : true);
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
    //******************************************************************************************************************************************************************************************************
    //******metodo del controller si chiamava doPost addesso insertFileFromContextServletDoPost************************************************************************************************************************************************************************************************
    public static boolean insertFileFromContextServletDoPost(HttpServletRequest request, HttpServletResponse response, DB db) throws ServletException, java.io.IOException, Exception{

        
        if(request == null || response == null  )
            throw new Exception("Request or Response sono a NULL!!!" );
        
        File file = null;
        String filePath = null;
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
    
    //In caso tu abbia inserito il metodo del form come GET allora si solleva un Exception
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

        throw new ServletException("GET method used with " + getClass().getName() + ": POST method required.");
    }

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

    //****************************************************************************************************************************************
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

    //****************************************************************************************************************************************
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

    //****************************************************************************************************************************************
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

    //****************************************************************************************************************************************
}
