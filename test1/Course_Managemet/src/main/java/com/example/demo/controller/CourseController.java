package com.example.demo.controller;

import com.example.demo.pojo.Course;
import com.example.demo.service.impl.CourseServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CourseController {
    @Resource
    private CourseServiceImpl courseService;

    @RequestMapping("/courseList")
    @ResponseBody
    public List<Course> userList(){
        return courseService.findAll();
    }
}