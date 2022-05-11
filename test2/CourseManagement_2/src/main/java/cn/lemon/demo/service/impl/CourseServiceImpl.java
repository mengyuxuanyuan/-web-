package cn.lemon.demo.service.impl;

import cn.lemon.demo.dao.ICourseDao;
import cn.lemon.demo.domain.Course;
import cn.lemon.demo.service.ICourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

  @Resource
  private ICourseDao iCourseDao;

  @Override
  public Course add(Course course) {
    iCourseDao.add(course);
    return course;
  }


  @Override
  public int delete(int id) {
    return iCourseDao.delete(id);
  }


  @Override
  public List<Course> findAll() {
    return iCourseDao.findAll();
  }


  @Override
  public Course findById(Integer id) {
    return iCourseDao.findById(id);
  }


  @Override
  public List<Course> findByName(String name) {
    return iCourseDao.findByName(name);
  }


  @Override
  public int getRowCount() {
    return iCourseDao.getRowCount();
  }


  @Override
  public List<Course> queryPage(Integer startRows) {
    return iCourseDao.queryPage(startRows);
  }


  @Override
  public int update(Course course) {
    return iCourseDao.update(course);
  }


}
