package com.example.demo.Dao;

import com.example.demo.pojo.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import sun.misc.InnocuousThread;

import java.util.List;


public interface CourseMapper {
    @Select("select * from c_course where id = #{id}")
    Course selectCourseById(int id);

    @Select("select * from c_course where sid=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "hours",property = "hours")
    })
    List<Course> selectCourseBySid(int sid);


}
