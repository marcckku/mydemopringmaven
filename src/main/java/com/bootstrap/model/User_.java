/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootstrap.model;

import com.mongodb.BasicDBObject;
import java.io.Serializable;
import java.util.Objects;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Utente
 */
public class User_  extends BasicDBObject implements Serializable{

    
    
    private static final long serialVersionUID = 1L;
  
   private Integer seq; 
   
   
   private String name;
   
   @Email
   private String email;

    
    public Integer getSeq() {
       return (Integer) super.get("seq");
    }

    public void setSeq(Integer seq) {
        super.put("seq",this.seq= seq);
       // super.put("seq", this.seq);
    }

    public String getName() {
         return (String)super.get("name");
    }

    public void setName(String name) {
        super.put("name", this.name = name);
    }

    public String getEmail() {
       return (String)super.get("email");
    }

    public void setEmail(String email) {
         super.put("email", this.email = email);
    }

    @Override
    public String toString() {
        return "User{" + "seq=" + seq + ", name=" + name + ", email=" + email + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.seq);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User_ other = (User_) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.seq, other.seq)) {
            return false;
        }
        return true;
    }

  

    
}
