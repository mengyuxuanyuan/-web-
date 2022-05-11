package com.example.demo.Dao;

import com.example.demo.pojo.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseDao {
    /*实验二*/
    /**
     * 新增课程
     *
     * @param course
     * @return
     */
    int add(Course course);

    /**
     * 查询所有课程
     * @return
     */
    //@Select("select * from c_course")
    List<Course> findAll();

    /**
     * 修改课程
     * @param course
     * @return
     */
    int update(Course course);

    /**
     * 删除课程
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 根据id查询课程
     * @param id
     * @return
     */
    Course findById(Integer id);

    /**
     * 查询行数
     * @return
     */
    int getRowCount();

    /**
     * 分页查询所有用户
     * @param starRows
     * @return
     */
    List<Course> queryPage(Integer starRows);

}
