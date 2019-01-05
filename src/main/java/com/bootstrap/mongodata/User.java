/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootstrap.mongodata;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 *
 * @author marco
 * 
 * La annotazione @Document & @Id non sono necessarie già provato!!
 */
@Document
public class User  {

   @Id
    private int id;
    //private long id;
    private String nome;
    private String cog;
    private String pwd;
    private String email;

    /**
     * @param id
     * @param nome
     * @param cog
     * @param pwd
     * @param email
     */
    public User(int id, String nome, String cog, String pwd, String email) {
        this.id = id;
        this.nome = nome;
        this.cog = cog;
        this.pwd = pwd;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCog() {
        return cog;
    }

    public void setCog(String cog) {
        this.cog = cog;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User \n\n[getId()=" + getId() + ", getNome()=" + getNome() + ", getCog()=" + getCog() + ", getPwd()="
                + getPwd() + ", getEmail()=" + getEmail() + "]\n\n";
    }

}
