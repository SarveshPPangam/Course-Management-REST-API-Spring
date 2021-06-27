package com.courseapi.Course.API.controllers;

import com.courseapi.Course.API.models.Course;
import com.courseapi.Course.API.models.CourseDTO;
import com.courseapi.Course.API.models.CourseInfo;
import com.courseapi.Course.API.models.Teacher;
import com.courseapi.Course.API.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("")
    public Set<CourseDTO> getAll(){
        Set<CourseDTO> courseDTOS = courseService.getAll().stream().map(course -> {
            return new CourseDTO(course);
        }).collect(Collectors.toSet());
        return courseDTOS;
    }

    @GetMapping("/{id}")
    public CourseDTO get(@PathVariable int id){
        return new CourseDTO(courseService.findById(id));
    }

    @PostMapping("/add")
    public CourseDTO addCourse(@RequestBody Course course){
        Course toSave = new Course();
        toSave.setName(course.getName());
        toSave.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return new CourseDTO(courseService.save(toSave));
    }

    @PutMapping("/{id}")
    public CourseDTO update(@PathVariable int id, @RequestBody Course updatedCourse){
        Course course = courseService.findById(id);
        course.setName(updatedCourse.getName());
        return new CourseDTO(courseService.save(course));
    }

    @DeleteMapping("/{id}")
    public RedirectView delete(@PathVariable int id){
        courseService.deleteById(id);
        return new RedirectView("/api/courses/");
    }
}
