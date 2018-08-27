/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootstrap.mvcConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author Marco
 */

@Configuration
@ComponentScan(basePackages = "com.bootstrap")
@EnableWebMvc
public class ApplicationConfigSpringMvc extends WebMvcConfigurerAdapter {

    /*
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/pagine/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry  //.addResourceHandler("/assets/**").addResourceLocations("/assets/");
           .addResourceHandler("/resources/**")
           .addResourceLocations("/resources/"); 
    }*/

    
    
    
    
    
    
    /* //CONFIG PER UPLOAD FILE MA NON FUNZIONA INSERZIONE 16/05/2018
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getCommonsMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(20971520 * 10);   // 20MB
        multipartResolver.setMaxInMemorySize(1048576 * 100);  // 1MB
        return multipartResolver;
    }

*/

}
