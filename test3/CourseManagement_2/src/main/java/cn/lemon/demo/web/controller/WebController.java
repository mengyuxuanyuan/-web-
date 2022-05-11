package cn.lemon.demo.web.controller;

import cn.lemon.demo.domain.Course;
import cn.lemon.demo.domain.Result;
import cn.lemon.demo.service.impl.CourseServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Instrument;
import javax.validation.constraints.Null;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    int result;
    if(course.getImgName().isEmpty()) {
      Course c1=courseService.findById(course.getId());
      course.setImgName(c1.getImgName());
      result = courseService.update1(course);
    }
    else
      result=courseService.update(course);
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

  /**
   * 文件上传
   * @param picture
   * @param request
   * @return
   */
  @RequestMapping("/upload")
  public Result upload(@RequestParam("picture") MultipartFile picture, HttpServletRequest request) {

    //获取文件在服务器的储存位置
    String path ="D:\\学习课程\\大三下\\高级web技术\\实验\\后端源码\\实验二\\源码\\CourseManagement_2\\src\\main\\resources\\static\\image\\";
    File filePath = new File(path);
    System.out.println("文件的保存路径：" + path);
    if (!filePath.exists() && !filePath.isDirectory()) {
      System.out.println("目录不存在，创建目录:" + filePath);
      filePath.mkdir();
    }

    //获取原始文件名称(包含格式)
    String originalFileName = picture.getOriginalFilename();
    System.out.println("原始文件名称：" + originalFileName);

    //获取文件类型，以最后一个`.`为标识
    String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
    System.out.println("文件类型：" + type);
    //获取文件名称（不包含格式）
    String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));
    String  fileName= UUID.randomUUID().toString();
    fileName += "." + type;
    System.out.println("新文件名称：" + fileName);

    //在指定路径下创建一个文件
    File targetFile = new File(path, fileName);

    //将文件保存到服务器指定位置
    try {
      picture.transferTo(targetFile);
      String newPath="http://localhost:8081/api/file/";
      System.out.println("上传成功");
      System.out.println(new Result(true,newPath+fileName).toString());
      //将文件在服务器的存储路径返回
      return new Result(true,newPath+fileName);
    } catch (IOException e) {
      System.out.println("上传失败");
      e.printStackTrace();
      return new Result(false, "上传失败");
    }
  }

  /**
   * 显示图片
   * getFeedBackPicture.do?picName=
   * @return
   */

  @RequestMapping("/viewPhoto/{photopath}")
  public void getFeedBackPicture(HttpServletResponse response, @PathVariable("photopath")String photopath) throws Exception{
    String realPath="D:\\学习课程\\大三下\\高级web技术\\实验\\后端源码\\实验二\\源码\\CourseManagement_2\\src\\main\\resources\\static\\image\\"+photopath;
    FileInputStream inputStream = new FileInputStream(realPath);
    int i = inputStream.available();
    //byte数组用于存放图片字节数据
    byte[] buff = new byte[i];
    inputStream.read(buff);
    //记得关闭输入流
    inputStream.close();
    //设置发送到客户端的响应内容类型
    response.setContentType("image/*");
    OutputStream out = response.getOutputStream();
    out.write(buff);
    //关闭响应输出流
    out.close();
  }
}