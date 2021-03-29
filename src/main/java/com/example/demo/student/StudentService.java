package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sound.midi.Soundbank;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw  new IllegalStateException("Email is taken");
        }else {
            studentRepository.save(student);
        }
    }

    public void deleteStudent(long id){
        if(!studentRepository.existsById(id)){
            throw new IllegalStateException("Student with ID " + id + " does not exist");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(long id, String name, String email){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isEmpty()) {
            throw new IllegalStateException("Student does not exist");
        }

        if(name != null && name.length() >0 && !name.equals(optionalStudent.get().getName())){
            optionalStudent.get().setName(name);
            System.out.println(optionalStudent.get());
        }
        if(email != null && email.length() >0 && !email.equals(optionalStudent.get().getName())){
            if(studentRepository.findStudentByEmail(email).isPresent()){
                throw  new IllegalStateException("Email is taken");
            }
            optionalStudent.get().setEmail(email);
        }
    }
}
