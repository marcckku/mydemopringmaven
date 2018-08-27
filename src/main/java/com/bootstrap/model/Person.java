/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootstrap.model;

import org.bson.types.ObjectId;

/**
 *
 * @author Utente
 * 
 * final:
 * Si utilizza la parola chiave final in una dichiarazione di metodo, classe o variabile
 * per indicare che esso non può essere sovrascritto dalle sottoclassi. 
 * Significa che diventano (metodi, classi o variabili) definitivi.
 *  La parola chiave java final può essere utilizzata in molti contesti.
 * 
 * Se la classe è final allora diventa come definitiva, non puoi estenderla.
 * 
 * Se si rende qualsiasi metodo come final, non è possibile sovrascriverlo.
 * 
 * Se rendi qualsiasi variabile come finale, non puoi cambiare il valore della variabile finale (sarà costante

* 
* ANCORA DA PROVARE MONGODB POJOS
 */
public final class Person {
    private ObjectId id;
    private String name;
    private int age;
    private Address address;

    public Person() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(final ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }
    
    // Rest of implementation
}

