package com.example.demo.service.impl;

import com.example.demo.pojo.Course;
import com.example.demo.service.impl.CourseServiceImpl;

import com.example.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes =
        DemoApplication.class)
public class CourseServiceImplTest {
    @Resource
    private CourseServiceImpl courseService;

    @Test
    public void findAll(){
        List<Course> courseList=courseService.findAll();
        for(Course course : courseList){
            System.out.println(course.toString());
        }
    }
}
