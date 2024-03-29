package pl.fis.restapi.service;

import org.springframework.stereotype.Service;
import pl.fis.restapi.entity.Student;
import pl.fis.restapi.enums.Subject;
import pl.fis.restapi.exception.StudentNotFoundException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private List<Student> students;

    public StudentService() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        student.setId(students.get(students.size() - 1).getId() + 1);
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudentById(Long id) {
        List<Student> foundStudents = students
                .stream()
                .filter(student -> student.getId().equals(id))
                .collect(Collectors.toList());
        if(foundStudents.size() != 0)
            return foundStudents.get(0);
        else
            throw new StudentNotFoundException(String.format("Student with id %d does not exists", id));
    }

    public void editStudent(Long id, Student toUpdate) {
        List<Student> foundStudents = students
                .stream()
                .filter(student -> student.getId().equals(id))
                .collect(Collectors.toList());
        if(foundStudents.size() == 0)
            throw new StudentNotFoundException(String.format("Student with id %d does not exists", id));
        foundStudents.get(0).setAge(toUpdate.getAge() != null ? toUpdate.getAge() : foundStudents.get(0).getAge());
        foundStudents.get(0).setLastName(toUpdate.getLastName() != null ? toUpdate.getLastName() : foundStudents.get(0).getLastName());
    }

    public void deleteStudent(Long id) {
        List<Student> foundStudents = students
                .stream()
                .filter(student -> student.getId().equals(id))
                .collect(Collectors.toList());
        if(foundStudents.size() != 0)
            students.remove(foundStudents.get(0));
        else
            throw new StudentNotFoundException(String.format("Student with id %d does not exists", id));

    }

    @PostConstruct
    public void addStudents() {
        Student student1 = new Student(1L, "Wiktoria", "Fica", 22, new ArrayList<>(List.of(Subject.BIOLOGY)));
        Student student2 = new Student(2L, "Aleksandra", "Niefica", 25, new ArrayList<>(List.of(Subject.ALGEBRA)));
        Student student3 = new Student(3L, "Ala", "Makota", 21, new ArrayList<>(List.of(Subject.LAW)));
        Student student4 = new Student(4L, "Jan", "Kowalski", 19, new ArrayList<>());
        Student student5 = new Student(5L, "Adam", "Nowak", 23, new ArrayList<>());
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        Student student6 = new Student(6L, "Monika", "Nazwisko", 19, new ArrayList<>(List.of(Subject.ALGEBRA, Subject.BIOLOGY)));
        Student student7 = new Student(7L, "Agnieszka", "Inna", 21, new ArrayList<>(List.of(Subject.LAW, Subject.BIOLOGY)));
        Student student8 = new Student(8L, "Katarzyna", "Student", 22, new ArrayList<>(List.of(Subject.BIOLOGY)));
        Student student9 = new Student(9L, "Marzena", "Uczen", 23, new ArrayList<>(List.of(Subject.ALGEBRA)));
        Student student10 = new Student(10L, "Martyna", "Szkola", 23, new ArrayList<>(List.of(Subject.ALGEBRA, Subject.BIOLOGY, Subject.LAW)));
        students.add(student6);
        students.add(student7);
        students.add(student8);
        students.add(student9);
        students.add(student10);
    }
}
