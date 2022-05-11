package cn.lemon.demo.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       // registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "E:/Pictures/img/");
        registry.
                addResourceHandler("/api/file/**").
                addResourceLocations("file:" + "D:/学习课程/大三下/高级web技术/实验/后端源码/实验二/源码/CourseManagement_2/src/main/resources/static/image/");

    }

}