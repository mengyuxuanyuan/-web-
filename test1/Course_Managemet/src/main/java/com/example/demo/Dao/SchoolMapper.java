package com.example.demo.Dao;

import com.example.demo.pojo.Course;
import com.example.demo.pojo.School;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SchoolMapper {
    @Select("select * from s_school")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "schoolname",property = "schoolname"),
            @Result(column = "id",property = "courseList",
                    many = @Many(select = "com.example.demo.Dao.CourseMapper.selectCourseBySid"))
    })
    List<School> selectSchoolByName();
}
