/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongodb.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;

/**
 *
 * @author Utente
 */
public class MemoryUtil {

    private static final int MegaBytes = 10241024;

    public static void main(String[] args) throws IOException {

        long heapFreeSize_freeMemory = Runtime.getRuntime().freeMemory(); //MegaBytes;
        long heapSize_totalMemory = Runtime.getRuntime().totalMemory();//MegaBytes;
        long heapMaxSize_maxMemory = Runtime.getRuntime().maxMemory();//MegaBytes;

        System.out.println("\nJVM heapFreeSize_freeMemory = [ " + heapFreeSize_freeMemory + "] ");

        System.out.println("\nheapSize_totalMemory o dimensione totale dalla heap iniziale dalla JVM=  [ " + heapSize_totalMemory + "]");

        System.out.println("\nheapMaxSize_maxMemory o memoria massima della Heap della JVM = [" + heapMaxSize_maxMemory + "]");

        System.out.println("\nMemoria iniziale è sempre minore[ " + heapSize_totalMemory + " ] <  massima memoria data per la heap" + " [ " + heapMaxSize_maxMemory + " ]");

        ArrayList objects = new ArrayList();
        //array di tutti gli objects presenti in questo momento sulla heap 
        System.out.println("\n Currente Size object in this machine " + objects.size());
        for (int i = 0; i < objects.size(); i++) {
            objects.add("" + 10 * 2710);
        }

        heapFreeSize_freeMemory = Runtime.getRuntime().freeMemory(); //MegaBytes;
        heapSize_totalMemory = Runtime.getRuntime().totalMemory();//MegaBytes;
        heapMaxSize_maxMemory = Runtime.getRuntime().maxMemory();//MegaBytes;

        System.out.println("\nMemoria utilizata nella JVM  = [" + (heapMaxSize_maxMemory - heapFreeSize_freeMemory) + "]");

        System.out.println("\nMemoria ancora libera nella JVM = [ " + heapFreeSize_freeMemory + "]");

        System.out.println("\nDimensione totale della Memoria Heap corrente dalla JVM = [ " + heapSize_totalMemory + "]");

        System.out.println("Memoria massima nella JVM = [ " + heapMaxSize_maxMemory + "]");
        
        
        for (Object object : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
             
        System.out.println("\n\n ManagementFactory.getRuntimeMXBean().getInputArguments() " 
                +  object);
        }
       
        
        
        
      
        /*
         C:\WINDOWS\system32>java -Xms500m -Xmx200m C:\all_wskpace_\MavenBoostrap3Examples\MavenBootstrap3Examples
         non funziona buuu!!
            heap
            java "-Xms256m -Xmx256m" C:\all_wskpace_\MavenBoostrap3Examples\MavenBootstrap3Examples\src\main\java\com\bootstrap\controller
            export JVM_ARGS = " -Xms1024m  -Xmx512m "
            export JVM_ARGS="-Xms1024m -Xmx1024m"
            PermGen 
           export JVM_ARGS = "- Xmx1024m -XX: MaxPermSize = 256m"

         */
 /*
         Output:
            JVM heapFreeSize_freeMemory: 9
            JVM heapSize_totalMemory also equals to initial heap size of JVM : 9
            JVM heapMaxSize_maxMemory also equals to maximum heap size of JVM: 116
            Used Memory in JVM: 81
            heapFreeSize_freeMemory in JVM: 35
            heapSize_totalMemory in JVM shows current size of java heap : 108
            heapMaxSize_maxMemory in JVM: 116
         
        

            JVM heapFreeSize_freeMemory = [ 254741016] 

            heapSize_totalMemory o dimensione totale dalla heap iniziale dalla JVM=  [ 257425408]

            heapMaxSize_maxMemory o memoria massima della Heap della JVM = [3791650816]

            Memoria iniziale è sempre minore[ 257425408 ] <  massima memoria data per la heap [ 3791650816 ]

             Currente Size object in this machine 0

            Memoria utilizata nella JVM  = [3536909800]

            Memoria ancora libera nella JVM = [ 254741016]

            Dimensione totale della Memoria Heap corrente dalla JVM = [ 257425408]
            Memoria massima nella JVM = [ 3791650816]
        
        
         */
    }
}
