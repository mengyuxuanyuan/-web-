package cn.lemon.demo.domain;

import java.io.Serializable;

/**
 * Course 实体类
 */
public class Course implements Serializable {

  private int id;//用户id
  private String name;//课程名
  private int hours;//课时
  private int sid;//开课学院

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getHours() {
    return hours;
  }

  public void setHours(int hours) {
    this.hours = hours;
  }

  public int getSid() {
    return sid;
  }

  public void setSid(int sid) {
    this.sid = sid;
  }

  @Override
  public String toString() {
    return "Course(" + "id=" + id + ",name=" + name + ",hours=" + hours + ",sid=" + sid + ")\n";
  }
}
