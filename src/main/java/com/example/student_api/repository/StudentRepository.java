package com.example.student_api.repository;

import com.example.student_api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

    // Spring Data JPA generates automatically the implementation
    // All of these methods basing in the method name

    Optional<Student> findByEmail(String email);
    List<Student> findByFirstName(String firstName);
    List<Student> findByLastName(String lastName);
    boolean existsByEmail(String email);

}
