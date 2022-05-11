package cn.lemon.demo.web.controller;

import cn.lemon.demo.domain.Course;
import cn.lemon.demo.service.impl.CourseServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class WebController {
  @Resource
  private CourseServiceImpl courseService;

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public Course add(Course course) {
    return courseService.add(course);
  }

  @RequestMapping("/userList")
  @ResponseBody
  public List<Course> userList() {
    return courseService.findAll();
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public String update(Course course) {
    int result = courseService.update(course);
    if (result >= 1) { return "修改成功";
    } else { return "修改失败"; }
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public Integer delete(Integer id) {
    System.out.println(id);
    int result = courseService.delete(id);
    return result;
  }

  @RequestMapping("/findByName")
  @ResponseBody
  public List<Course> findByName(String name) {
    return courseService.findByName(name);
  }

  /**
   * 分页
   * @return
   */
  @RequestMapping(value="/page")
  @ResponseBody
  public List<Course> page(Integer page){
    int pageNow = page == null ? 1 : page;
    int pageSize = 5;
    int startRows = pageSize*(pageNow-1);
    List<Course> list = courseService.queryPage(startRows);
    return list;
  }

  /**
   * rows
   * @return
   */
  @RequestMapping(value="/rows")
  @ResponseBody
  public int rows(){
    return courseService.getRowCount();
  }
}
