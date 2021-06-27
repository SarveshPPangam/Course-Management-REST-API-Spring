package com.courseapi.Course.API.services;

import com.courseapi.Course.API.models.Student;
import com.courseapi.Course.API.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> getAll(){
        return studentRepository.findAll((Sort.by(Sort.Direction.ASC, "firstName")));
    }

    public Student findById(int id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()) return student.get();
        else throw new RuntimeException("Student not found for id: "+id);
    }

    public void delete(int id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()) studentRepository.delete(student.get());
        else throw new RuntimeException("Student not found for id: "+id);
    }

    public Student save(Student student){
         return studentRepository.save(student);
    }
}
