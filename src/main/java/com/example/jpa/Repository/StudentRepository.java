package com.example.jpa.Repository;

import com.example.jpa.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    // Additional query methods can be added here if needed
}
