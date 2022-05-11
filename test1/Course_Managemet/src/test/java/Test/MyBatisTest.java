package Test;

import com.example.demo.Dao.CourseMapper;
import com.example.demo.Dao.SchoolMapper;
import com.example.demo.pojo.Course;
import com.example.demo.pojo.School;
import com.example.demo.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyBatisTest {
    //1.基于XML的查询
    @Test
    public void findCourseByIdTest01(){
        SqlSession session= MyBatisUtils.getSession();
        Course course=session.selectOne("com.example.demo.pojo."+"Course.findById",2);
        System.out.println(course);
        session.close();
    }
    //1.基于注解的查询
    @Test
    public void findCourseByIdTest02(){
        SqlSession session= MyBatisUtils.getSession();
        CourseMapper  mapper=session.getMapper(CourseMapper.class);
        Course course=mapper.selectCourseById(2);
        System.out.println(course);
        session.close();
    }
    //2.通过学院名查询课程信息
    @Test
    public void findCourseBySchoolTest01(){
        SqlSession session= MyBatisUtils.getSession();
        List<Course> course =session.selectList(
                "com.example.demo.pojo."+"Course.findCourseBySchool","计算机学院");
        System.out.println(course);
        session.close();
    }
    //3.根据id更新课时
    @Test
    public void updateHoursByIdTest01(){
        SqlSession session= MyBatisUtils.getSession();
        Course course=new Course();
        course.setId(4);
        course.setHours(40);
        int rows=session.update(
                "com.example.demo.pojo."+"Course.updateHoursById",course);
        if(rows>0){
            System.out.println("您成功修改"+rows+"条数据！");
        }else{
            System.out.println("执行修改操作失败！");
        }
        session.commit();
        session.close();
    }

    //4.插入课程
    @Test
    public void addCourseTest01(){
        SqlSession session= MyBatisUtils.getSession();
        Course course=new Course();
        course.setName("大数据存储");
        course.setHours(32);
        course.setSid(1);
        int rows=session.insert(
                "com.example.demo.pojo."+"Course.addCourse",course);
        if(rows>0){
            System.out.println("成功插入"+rows+"条数据！");
        }else{
            System.out.println("插入数据失败！");
        }
        System.out.println(course);
        session.commit();
        session.close();
    }

    //5.查询所有学院的课程信息
    @Test
    public void findAllCourseBySchoolTest01(){
        SqlSession session= MyBatisUtils.getSession();
        SchoolMapper schoolMapper=session.getMapper(SchoolMapper.class);
        List<School> schoolList=schoolMapper.selectSchoolByName();
        for (School school:schoolList) {
            System.out.println(school.toString1());
        }
        session.close();
    }
}
