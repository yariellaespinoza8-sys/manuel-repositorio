package com.example.student_api.service;

import com.example.student_api.model.Student;
import com.example.student_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    // Getting all Students
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    //Getting all Students by ID
    public Optional<Student> getStudentById(Long id){
        return studentRepository.findById(id);
    }

    // To create a new Student
    public Student createStudent(Student student){

        //Verify that the email is not duplicated

        if(studentRepository.existsByEmail(student.getEmail())){
            throw new IllegalStateException("Email already exists");
        }
return studentRepository.save(student);
    }

    // Update Student
    public Student updateStudent(Long id, Student studentDetails){
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Student with id " + id + " not exists"));

        // Update the fields
        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setAge(studentDetails.getAge());

        return studentRepository.save(student);
    }

    //Delete Student

    public void deleteStudent(Long id){

        boolean exists = studentRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Student with id " + id + " not exists");
        }
        studentRepository.deleteById(id);

    }

// Find Student By Email
    public Optional<Student> getStudentByEmail(String email){
        return studentRepository.findByEmail(email);
    }

}
