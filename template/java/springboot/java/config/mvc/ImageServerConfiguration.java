package com.ctgu.springbootbase.config.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImageServerConfiguration implements WebMvcConfigurer {

    @Value("${uploadPath}")
    private String uploadPath;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	/**
         * @Description: 对文件的路径进行配置,创建一个虚拟路径/upload/** ，即只要在<img src="/upload/img/9aa64b2b-a558-421e-929c-537ff0aecdba.jpg" />便可以直接引用图片
         * 这是图片的物理路径 "file:/+本地图片的地址"
         */
	    registry.addResourceHandler("/upload/**").addResourceLocations(uploadPath);
    }
}