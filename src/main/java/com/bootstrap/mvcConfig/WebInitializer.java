/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootstrap.mvcConfig;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author Marco
 * 
 * 
 * Il contenuto di cui sopra è simile al contenuto di web.xml del tutorial 
 * precedente poiché stiamo utilizzando il front-controller DispatherServler, 
 * assegnando il mapping (url-pattern in xml) e invece di fornire il percorso 
 * per il file di configurazione spring (spring-servlet.xml) , qui stiamo 
 * registrando la classe di configurazione. Nel complesso, stiamo facendo la 
 * stessa cosa, solo l'approccio è diverso.
 * 
 * 
 * 
 * 
 * Una cosa da tenere a mente è che le API di configurazione Spring basate su Java come 
 * WebApplicationInitializer dipendono dai contenitori Servlet 3.0. 
 * Assicuratevi di non avere alcun web.xml con dichiarazione di servlet inferiore a 3.0. 
 * Per il nostro caso, abbiamo rimosso il file web.xml dalla nostra applicazione.
 * (Dovremmo togliere il web.xml)
 * 
 */
public class WebInitializer {/* extends AbstractAnnotationConfigDispatcherServletInitializer {
 
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { ApplicationConfigSpringMvc.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
 */
}