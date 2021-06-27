package com.courseapi.Course.API.services;

import com.courseapi.Course.API.models.Student;
import com.courseapi.Course.API.models.Teacher;
import com.courseapi.Course.API.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    public List<Teacher> getAll(){
        return teacherRepository.findAll((Sort.by(Sort.Direction.ASC, "firstName")));
    }

    public Teacher findById(int id){
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isPresent()) return teacher.get();
        else throw new RuntimeException("Teacher not found for id: "+id);
    }

    public void delete(int id){
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isPresent()) teacherRepository.delete(teacher.get());
        else throw new RuntimeException("Teacher not found for id: "+id);
    }

    public Teacher save(Teacher teacher){
        return teacherRepository.save(teacher);
    }


}
