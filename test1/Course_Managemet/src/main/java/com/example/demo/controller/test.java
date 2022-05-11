package com.example.demo.controller;

import com.example.demo.pojo.Course;
import com.example.demo.utils.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.IOException;
import java.io.Reader;

public class test {
    @Test
    public void CourseFindByIdTest(){
        SqlSession session= MyBatisUtils.getSession();
        Course course=session.selectOne("com.example.demo.pojo."+"Course.findById",1);
        System.out.println(course.toString());
        session.close();
    }

}
