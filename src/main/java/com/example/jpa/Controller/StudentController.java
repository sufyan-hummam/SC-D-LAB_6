package com.example.jpa.Controller;

import com.example.jpa.Entity.Student;
import com.example.jpa.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;

    // Create a new student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepo.save(student);
    }

    // Retrieve all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    // Retrieve a student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return ResponseEntity.ok(student);
    }

    // Update a student by ID
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student studentDetails) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setCourse(studentDetails.getCourse());

        Student updatedStudent = studentRepo.save(student);
        return ResponseEntity.ok(updatedStudent);
    }

    // Delete a student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Integer id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        studentRepo.delete(student);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
