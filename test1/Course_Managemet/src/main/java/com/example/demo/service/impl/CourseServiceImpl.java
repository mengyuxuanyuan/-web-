package com.example.demo.service.impl;

import com.example.demo.Dao.CourseDao;
import com.example.demo.Dao.CourseMapper;
import com.example.demo.pojo.Course;
import com.example.demo.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseDao courseDao;

    @Override
    public List<Course> findAll(){
        return courseDao.findAll();
    }
}
