package cn.lemon.demo.dao;

import cn.lemon.demo.domain.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据库访问层，访问数据库
 */
@Mapper
public interface ICourseDao {
  /**
   * 添加用户
   *
   * @param course
   * @return
   */
  int add(Course course);
  /**
   * 删除用户
   *
   * @param id
   * @return
   */
  int delete(int id);
  /**
   * 查询所有用户
   *
   * @return
   */
  List<Course> findAll();
  /**
   * 根据ID查询用户
   *
   * @param id
   * @return
   */
  Course findById(Integer id);
  /**
   * 根据姓名查询所有用户
   *
   * @param name
   * @return
   */
  List<Course> findByName(String name);
  /**
   * 查询行数
   *
   * @return
   */
  int getRowCount();
  /**
   * 分页查询所有用户
   *
   * @param startRows
   * @return
   */
  List<Course> queryPage(Integer startRows);
  /**
   * 修改用户
   *
   * @param course
   * @return
   */
  int update(Course course);
}
