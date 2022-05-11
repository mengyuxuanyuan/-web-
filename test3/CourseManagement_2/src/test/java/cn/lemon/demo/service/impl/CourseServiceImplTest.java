package cn.lemon.demo.service.impl;

import cn.lemon.demo.domain.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CourseServiceImplTest {

  @Resource
  private CourseServiceImpl courseService;

  @Test
  public void add() {
    Course course = new Course();
    course.setName("数据结构");
    course.setHours(50);
    course.setSid(1);
    courseService.add(course);
  }


  @Test
  public void delete() {
    courseService.delete(7);
  }


  @Test
  public void findAll() {
    List<Course> courseList = courseService.findAll();
    for (Course course : courseList) {
      System.out.println(course.toString());
    }
  }


  @Test
  public void findById() {
    Course course = courseService.findById(1);
    System.out.println(course.toString());
  }


  @Test
  public void findByName() {
    List<Course> courseList = courseService.findByName("大学英语");
    for (Course course : courseList) {
      System.out.println(course.toString());
    }
  }


  @Test
  public void getRowCount() {
    int i = courseService.getRowCount();
    System.out.println(i);
  }


  @Test
  public void queryPage() {
    List<Course> courseList = courseService.queryPage(1);
    for (Course course : courseList) {
      System.out.println(course.toString());
    }
  }


  @Test
  public void update() {
    Course course = new Course();
    course.setId(1);
    course.setName("C语⾔程序设计");
    course.setHours(65);
    course.setSid(1);
    courseService.update(course);

    Course course1 = courseService.findById(1);
    System.out.println(course1.toString());
  }
}
