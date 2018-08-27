/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boostrap.test;

import java.nio.ByteBuffer;

/**
 *
 * @author Utente
 * http://www.javarticles.com/2018/04/java-nio-bytebuffer.html 
 */
public class TestByteBuffer {
    
    
    
    public static void main(String[] args) {
      /*  ByteBuffer buffer = ByteBuffer.allocate(7);
        
        System.out.println("Buffer limit: " + buffer.limit() + ", capacity: " + buffer.capacity());
        
        put(buffer, (byte) 1);
        put(buffer, (byte) 3);
        put(buffer, (byte) 5);
        put(buffer, (byte) 7);
        put(buffer, (byte) 9);
        put(buffer, (byte) 11);        
        put(buffer, (byte) 13);        
         
        System.out.println("Flip the buffer");
        buffer.flip();
        for (int i = 0; i < buffer.limit() -2; i++) {
            get(buffer);
        }
        System.out.println("Position " + buffer.position() + ", remaining " + buffer.remaining() + ". Compact the buffer");
        buffer.compact();        
        System.out.println("After compact, position " + buffer.position() + ", remaining " + buffer.remaining() + ", limit " + buffer.limit());
 
        System.out.println("Read elements. Start");
        for (int i = 0; i < buffer.limit(); i++) {
            System.out.println("Get[" + i + "]=>" + buffer.get(i));
        }
        System.out.println("End");
        flip(buffer);
        for (int i = 0; i <= buffer.remaining(); i++) {
            get(buffer);
        }
        
        
        */
      
      viewBuf();
    }
     
    private static void put(ByteBuffer buffer, byte b) {
        System.out.println("Put[" + buffer.position() +"]=" + b);
        buffer.put(b);        
        System.out.println("Position " + buffer.position() + ", remaining " + buffer.remaining());
    }
     
    private static void get(ByteBuffer buffer) {
        System.out.println("Get[" + buffer.position() + "]=>" + buffer.get() + ". Remaining " + buffer.remaining());       
    }
     
    private static void flip(ByteBuffer buffer) {
        buffer.flip();
        System.out.println("Flip. Position " + buffer.position() + ", remaining " + buffer.remaining() + ", limit " + buffer.limit());
    }
    
    
    private static void viewBuf(){
         ByteBuffer buffer = ByteBuffer.wrap(new byte[]{
             (byte)1,
             (byte)2,
             (byte)3,
             (byte)4,
             (byte)5
         });
    
         
          System.out.println("Flip the buffer");
       // buffer.flip();
        for (int i = 0; i < buffer.limit() ; i++) {
            get(buffer);
        }
    }
}
