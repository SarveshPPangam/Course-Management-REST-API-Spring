package com.courseapi.Course.API.controllers;

import com.courseapi.Course.API.models.Course;
import com.courseapi.Course.API.models.Student;
import com.courseapi.Course.API.models.StudentDTO;
import com.courseapi.Course.API.services.CourseService;
import com.courseapi.Course.API.services.StudentService;
import com.courseapi.Course.API.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    @Autowired
    TeacherService teacherService;

    @GetMapping("")
    public List<StudentDTO> getAll() {
        List<StudentDTO> studentDTOS = studentService.getAll().stream().map(student -> {
            StudentDTO studentDTO = new StudentDTO(student);
            return studentDTO;
        }).collect(Collectors.toList());
        return studentDTOS;
    }

    @GetMapping("/{id}")
    public StudentDTO getStudent(@PathVariable int id) {
        return new StudentDTO(studentService.findById(id));
    }

    @PostMapping("/add")
    public StudentDTO add(@RequestBody Student student) {
        Student toSave = new Student();
        toSave.setFirstName(student.getFirstName())
                .setLastName(student.getLastName())
                .setCreatedAt(new Timestamp(System.currentTimeMillis()))
                .setAddress(student.getAddress())
                .setPhoneNumber(student.getPhoneNumber());
        studentService.save(toSave);
        return new StudentDTO(toSave);
    }

    @PutMapping("/{id}")
    public RedirectView update(@PathVariable int id, @RequestBody Student updatedStudent){
        Student student = studentService.findById(id);
        student
                .setFirstName(updatedStudent.getFirstName())
                .setLastName(updatedStudent.getLastName())
                .setAddress(updatedStudent.getAddress())
                .setPhoneNumber(updatedStudent.getPhoneNumber());
        studentService.save(student);
        return new RedirectView("/api/students/" + id);
    }

    @DeleteMapping("/{id}")
    public RedirectView delete(@PathVariable int id){
        Student student = studentService.findById(id);
        student.getCourses().clear();
        student.getTeachers().stream().forEach(teacher -> {
            teacher.removeStudent(student);
        });
        studentService.delete(id);
        return new RedirectView("/api/students/");
    }

    @GetMapping("/{student_id}/enroll/{course_id}")
    public RedirectView enroll(@PathVariable int student_id, @PathVariable int course_id) {
        Student student = studentService.findById(student_id);
        Course course = courseService.findById(course_id);
        student.enroll(course);
        studentService.save(student);
        teacherService.save(course.getTeacher());
        return new RedirectView("/api/students/" + student_id);
    }

    @GetMapping("/{student_id}/unEnroll/{course_id}")
    public RedirectView unEnroll(@PathVariable int student_id, @PathVariable int course_id) {
        Student student = studentService.findById(student_id);
        Course course = courseService.findById(course_id);
        student.unEnroll(course);
        studentService.save(student);
        teacherService.save(course.getTeacher());
        return new RedirectView("/api/students/" + student_id);
    }
}
