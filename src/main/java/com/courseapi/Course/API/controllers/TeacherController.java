package com.courseapi.Course.API.controllers;

import com.courseapi.Course.API.models.*;
import com.courseapi.Course.API.repositories.CourseRepository;
import com.courseapi.Course.API.services.CourseService;
import com.courseapi.Course.API.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    CourseService courseService;

    @GetMapping("")
    public Set<TeacherDTO> all(){
        Set<TeacherDTO> teacherDTOS = teacherService.getAll().stream().map(teacher -> {
            return new TeacherDTO(teacher);
        }).collect(Collectors.toSet());
        return teacherDTOS;
    }


    @GetMapping("/{id}")
    public TeacherDTO getStudent(@PathVariable int id) {
        return new TeacherDTO(teacherService.findById(id));
    }

    @PostMapping("/add")
    public TeacherDTO add(@RequestBody Teacher teacher){
        Teacher toSave = new Teacher();
        toSave.setFirstName(teacher.getFirstName())
                .setLastName(teacher.getLastName())
                .setCreatedAt(new Timestamp(System.currentTimeMillis()))
                .setAddress(teacher.getAddress())
                .setPhoneNumber(teacher.getPhoneNumber());
        teacherService.save(toSave);
        return new TeacherDTO(toSave);
    }

    @PutMapping("/{id}")
    public RedirectView update(@PathVariable int id, @RequestBody Teacher updatedTeacher){
        Teacher teacher = teacherService.findById(id);
        teacher
                .setFirstName(updatedTeacher.getFirstName())
                .setLastName(updatedTeacher.getLastName())
                .setAddress(updatedTeacher.getAddress())
                .setPhoneNumber(updatedTeacher.getPhoneNumber());
        teacherService.save(teacher);
        return new RedirectView("/api/teachers/" + id);
    }


    @DeleteMapping("/{id}")
    public RedirectView delete(@PathVariable int id){
        Optional<Course> course = courseService.findByTeacherId(id);
        if (course.isPresent()){
            course.get().clearTeacher();
            courseService.save(course.get());
        }
        Teacher teacher = teacherService.findById(id);
        teacher.getCourses().clear();
        teacher.getStudents().clear();
//        delete teacher courses ok
        teacherService.delete(id);
        return new RedirectView("/api/teachers/");
    }

    @GetMapping("/{teacher_id}/addCourse/{course_id}")
    public RedirectView addCourseToTeacher(@PathVariable int teacher_id, @PathVariable int course_id){
        Course course = courseService.findById(course_id);
        Teacher teacher = teacherService.findById(teacher_id);
        course.setTeacher_id(teacher);
        courseService.save(course);
        return new RedirectView("/api/teachers/" + teacher_id);
    }

    @GetMapping("/{teacher_id}/removeCourse/{course_id}")
    public RedirectView removeCourseFromTeacher(@PathVariable int teacher_id, @PathVariable int course_id){
        Course course = courseService.findById(course_id);
        course.clearTeacher();
        courseService.save(course);
        return new RedirectView("/api/teachers/" + teacher_id);
    }
}
