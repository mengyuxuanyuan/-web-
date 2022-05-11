package com.example.demo.service;

import com.example.demo.pojo.Course;

import java.util.List;

public interface CourseService {

    /**
     * 查询所有课程
     * @return
     */
    List<Course> findAll();
}
