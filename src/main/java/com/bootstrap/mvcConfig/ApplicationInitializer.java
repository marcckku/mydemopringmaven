/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootstrap.mvcConfig;

/**
 *
 * @author Marco
 * 
 * Config spring 4
 * http://www.technicalkeeda.com/spring-tutorials/spring-4-mvc-static-resource-mapping-example
 * o  anche 
 * http://websystique.com/springmvc/spring-4-mvc-helloworld-tutorial-annotation-javaconfig-full-example/
 * 
 * 
 * 
 * 
 * 
 * @EnableWebMvc è equivalente a -> mvc:annotation-driven in XML. 
 * Abilita il supporto per le classi annotate da Controller che 
 * utilizzano @RequestMapping per associare le richieste in entrata 
 * a un metodo specifico. 
 * @ComponentScan è equivalente al -> context:component-scan base-package="..." 
 * che fornisce dove cercare i bean / classi gestiti a molla.
 * 
 */

import javax.servlet.*;

 
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
 
public class ApplicationInitializer{ /*implements WebApplicationInitializer {
 
    public void onStartup(ServletContext container) throws ServletException {
 
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(ApplicationConfigSpringMvc.class);
        ctx.setServletContext(container);
 
        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
 
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
        
        
        
    }*/
 
}