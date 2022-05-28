package com.project.noticeSaver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**/
        //No need registry for load resource files
        /**/
        /*registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");

        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("/static/");*/

        /*registry
                .addResourceHandler("/styles/**")
                .addResourceLocations("/resources/static/bootstrap-5.0.2/css");*/

    }
}
