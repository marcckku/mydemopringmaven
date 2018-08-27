/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.my.librery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Marco
 */
public class MyLibrery1 {
   /*
   *
   *
   */
    public static String leggi(String path){
        try {
            FileReader fr = new FileReader(path);
            BufferedReader daBuffer = new BufferedReader(fr);
            String text="";
             while( daBuffer.ready()){//mia versione
                 String lineaTesto = daBuffer.readLine();
                 text +=lineaTesto+(lineaTesto.length() != 0 ? "\n" : ""); 
            }
            System.out.println(text);
            fr.close();
            daBuffer.close(); 
            return text;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
                    ex.printStackTrace();
        }
        return null;
    }
     
     public static void scrivi(String path, String txt, boolean appendi){
        File fonte =null;
        FileOutputStream fos=null;
        PrintWriter pw=null;
        try{
                  fonte = new File(path);
                  fos= new FileOutputStream(fonte,appendi);
                  pw=new PrintWriter(fos);
                  pw.println(txt);
                  
                  pw.flush();
                  pw.close();
                  fos.close();
                  System.out.println("Scrittura sul file avvenuta");
          }catch(FileNotFoundException err){
                  System.out.println(err);
          }
          catch(NullPointerException  epp){//anche senza questo catch funziona
                  System.out.println(epp);
          }
        catch(IOException  io){//anche senza questo catch funziona
                io.printStackTrace();
          }
    }
    
    public static void copiaFile(String oldPathFile, String newPathFile, boolean appendi,String data){
        File fonte =null;
        FileOutputStream fos=null;
        PrintWriter pw=null;
        String text="";
        if(!data.equalsIgnoreCase(""))
           text +=data+"\n";
        try {
            //Straggo dati dal file
            FileReader fr = new FileReader(oldPathFile);
            BufferedReader daBuffer = new BufferedReader(fr);
             text=""+data+"\n";
             while( daBuffer.ready()){//mia versione
                 String lineaTesto = daBuffer.readLine();
                 text +=lineaTesto+(lineaTesto.length() != 0 ? "\n" : ""); 
            }
            //inserisco dati nel nuovo file.
            fonte = new File(newPathFile);
            fos= new FileOutputStream(fonte, appendi);
            pw=new PrintWriter(fos);
            pw.println(text);
            //
            pw.flush();
            pw.close();
            fos.close();
             //
            fr.close();
            daBuffer.close(); 
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
                    ex.printStackTrace();
        }
    
    }
    
    public static ArrayList<String> straiFile(String pathFile){
        ArrayList<String> lista=null;
        ArrayList<String> subStrlist =null;
        try {
                 lista = new ArrayList<>();
                 subStrlist = new ArrayList<>();
                FileReader fr = new FileReader(pathFile);
                BufferedReader daBuffer = new BufferedReader(fr);
                while( daBuffer.ready()){//mia versione
                    String tmp="";
                    String lineaTesto = daBuffer.readLine();
                    for(int k =0; k < lineaTesto.length() ; k++  ){
                         if( !(lineaTesto.charAt(k) >= 30 && lineaTesto.charAt(k) <= 47) && (lineaTesto.charAt(k) != (int)' ') ){
                             int s = contaSpazLineaStr( lineaTesto );
                             tmp = lineaTesto.substring(s); 
                         }//per il football vale questo if per wheater no
                    }
                    if(!tmp.equalsIgnoreCase(""))
                        lista.add( tmp +(tmp.length() != 0  ? '\n' : "")  );
                    
                    tmp="";
                }
                
                fr.close();
                daBuffer.close(); 
                return lista;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
                    ex.printStackTrace();
        }
        return lista;
    }
    
    public static int contaSpazLineaStr(String str){
        int conta =0;
        for(int i =0; i < str.length(); i++){
            if( str.charAt(i) == ' ' || str.charAt(i)  >= 48 && str.charAt(i) <=57
                || str.charAt(i)  >= 30 && str.charAt(i)  <=47 
                && !(str.charAt(i) >= 65 && str.charAt(i) <=90 &&str.charAt(i)  >= 97 &&str.charAt(i)  <= 122 )
               ){//ultima mod
                conta++;
            }else{ 
              return conta;
            }
        }
       
        return conta;
    }
//***************************************************************
    //Conta il numero di linee che ha il file che terminano con lo \n
    public static int contaLineaFile(String path){
         String buffer = MyLibrery1.leggi(path);
         int i=0;
         int conta=0;
         while(i < buffer.length()){
             if(buffer.charAt(i) == '\n')
               conta++;
             i++;
         }
         return conta;
    }
    
    
    //*Per ogni linea quindi per ogni sottostringa del File creo una lista,
    //di tutte le linee senza simboli e solo dati di interesse.
    public static ArrayList<String> creaListaCampi(String subStr){
        return costruisciCampiDa(subStr);
    }
    
    private static ArrayList<String> costruisciCampiDa(String str){
            String aux="";
            int dim = str.length();
            ArrayList<String> listaCampi =new ArrayList<String>();
            char [] c = new char[dim];
            for(int k=0; k < str.length(); k++){
                  if( !(isSimbol(str.charAt(k)) )){//tolgo i simboli
                       c[k]=str.charAt(k) ;
                  }else{
                      c[k]='\n';
                  }
            }
            for(int i=0;i<c.length;i++){
                if( c[i] != '\n' ){
                  aux +=c[i];
                }else{
                   if(!aux.isEmpty() )
                      listaCampi.add(aux);
                    aux="";
                }
           }
        return listaCampi;
    }
    
  
    public static boolean isSimbol(char c){
        if( c >= 30 && c <= 47 )
           return true;
        return false;        
    } 
    
    public static String dataTime(){
        GregorianCalendar gc = new GregorianCalendar();//qui il tempo del Sistema operativo sottostante aggiornato in ogni istante.
        String data ="data Time : ";
        data += gc.get(Calendar.YEAR)+"/"+gc.get(Calendar.MONTH)+"/"+ gc.get(Calendar.DATE) +"\n";
        return data;
   }
    
   public static String dataCompleta(){
        GregorianCalendar gc = new GregorianCalendar();//da la data del Sistema operativo sottostante aggiornata ogni istante.
        String data=" Data dump :";
        String anno ="";
        anno += "Anno :[" + gc.get(Calendar.YEAR)+"/"+gc.get(Calendar.MONTH)+"/"+gc.get(Calendar.DATE)+ " ] ";
        String tempo = "Ora : [ "+gc.get(Calendar.HOUR)+"/"+gc.get(Calendar.MINUTE)+"/"+ gc.get(Calendar.SECOND)+" ]";
        data  += anno +"---"+ tempo+"\n";
        return data;
   }
   
   public static void visualizza(ArrayList<String> l){
       for(String s : l ){
           System.out.println(l);
       }
   }
   
   
   public static int straiStrPiuLunga(ArrayList<String> l){
       int maxLunghezza = l.get(0).length();
       for(String s : l ){
          if(s.length() > maxLunghezza){
              maxLunghezza = s.length();
          }
       }
       return maxLunghezza;
   }
   
   public static ArrayList<String>  splitFile(String pathFile){
            ArrayList<String> lstRigheFile=null;
         String text="";
        try {
                int k=0;
                FileReader fr = new FileReader(pathFile);
                BufferedReader daBuffer = new BufferedReader(fr);
                int i=1;
                while( daBuffer.ready()){//mia versione
                    String lineaTesto = daBuffer.readLine();
                  text +=lineaTesto+(lineaTesto.length() != 0 ? "\n" : ""); 
                  
                } 
              
                fr.close();
                daBuffer.close(); 
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
                    ex.printStackTrace();
        }
        lstRigheFile = new ArrayList<String>();
          String [] strArr =  text.split(" ");
          for(int j =0; j< strArr.length ; j++){
            if( !strArr[j].equalsIgnoreCase("") ){
                lstRigheFile.add(strArr[j]);
                
            }
         }
         
         return lstRigheFile;
    }
    public static int contaElemFile(String textFile){
             int contaColonne=0;
        String [] strArr =  textFile.split(" ");
         for(int j =0; j< strArr.length ; j++){
           if( !strArr[j].equalsIgnoreCase("") ){
                   contaColonne++;
           }
        }
         return contaColonne;
    }
   
   
}
