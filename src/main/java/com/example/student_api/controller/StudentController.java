package com.example.student_api.controller;

import com.example.student_api.model.Student;
import com.example.student_api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    // GET /api/students - Get all Students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // GET / api/ students/{id} - Get Students by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable Long id){
        return studentService.getStudentById(id).map(student -> new ResponseEntity<>(student, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    //POST /api/stidents - Create a new Student

    @PostMapping
    public ResponseEntity<Student> createStudent(
            @RequestBody Student student){
        try{
            Student createdStudent = studentService.createStudent(student);
            return new ResponseEntity<>(
                    createdStudent, HttpStatus.CREATED);


        } catch (IllegalStateException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    //hola

// PUT /api/students/{id} - Update Student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student studentDetails){
        try{
            Student updatedStudent = studentService.updateStudent(id, studentDetails);
                return new ResponseEntity<>(
                        updatedStudent, HttpStatus.OK);
            } catch (IllegalStateException e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

//DELETE /api/students/{id} - Delete Student
        @DeleteMapping("/{id}")
                public ResponseEntity<Void> deleteStudent(@PathVariable Long id){

            try{
                studentService.deleteStudent(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (IllegalStateException e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }


    }


//m