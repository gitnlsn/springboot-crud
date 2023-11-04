package com.nlsn.amigoscode.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public java.util.List<Student> getStudents () {
        return this.studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> existingStudent = this.studentRepository.findStudentByEmail(student.getEmail());

        if (existingStudent.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        this.studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean existingStudent = this.studentRepository.existsById(id);

        if (!existingStudent) {
            throw new IllegalStateException("student does not exists");
        }

        this.studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->new IllegalStateException("student dies not exists"))

                if (name != null && !name.isEmpty()) {
                    student.setName(name);
                }

                if (email!=null && !email.isEmpty()) {
                    Optional<Student> existingStudent = studentRepository.findStudentByEmail(email);

                    if (existingStudent.isPresent()) {
                        throw new IllegalStateException("email taken");
                    }

                    student.setEmail(email);
                }
    }
}
