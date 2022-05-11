package com.example.demo.pojo;

import java.util.List;

public class School {
    private int id;//学院id
    private String schoolname;//学院名称
    private List<Course> courseList; //课程信息

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    @Override
    public String toString(){
        return "School("+"id="+id+",schoolname="+schoolname+")";
    }
    public String toString1(){
        return "School("+"id="+id+",schoolname="+schoolname+",courseList={"+courseList+"})";
    }
}
