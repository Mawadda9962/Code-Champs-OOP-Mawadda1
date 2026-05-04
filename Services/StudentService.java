package ObjectOrientedProgramming.OOPDemo.Services;

import ObjectOrientedProgramming.OOPDemo.Entities.Department;
import ObjectOrientedProgramming.OOPDemo.Entities.Student;
import ObjectOrientedProgramming.OOPDemo.Utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class StudentService {

    CourseService courseService = new CourseService();
    DepartmentService departmentService = new DepartmentService();

    public Student addNewStudent(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(" ** Adding new Student **");

        Student student = new Student();
        student.setId(UUID.randomUUID());

        System.out.println("Enter student Name: ");
        String stdName = scanner.nextLine();
        student.setName(stdName);

        System.out.println("Departments List");
        UniversityService.university.displayDepartments();

        System.out.println("Enter Department: ");
        student.setDepartment(departmentService.addNewDepartment());

        student.setCourseList(student.getDepartment().getOfferedCourses());

        return student;
    }

    public List<Student> addNewStudents(){
        Scanner scanner = new Scanner(System.in);
        List<Student> studentsList = new ArrayList<>();

        Boolean continueFlag = true;

        while (continueFlag) {
            studentsList.add(addNewStudent());
            System.out.println(Constants.INPUT_EXIT_CONTINUE_MESSAGE_STUDENTS);

            if (scanner.nextLine().equalsIgnoreCase("q")) {
                continueFlag = false;
            }
        }
        return studentsList;
    }

    public Student updatedeStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Student name to Update: ");
        String name = scanner.nextLine();

        Student foundStudent = null;

        for(Student s : UniversityService.university.getStudentList()){
            if (s.getName() != null && s.getName().equalsIgnoreCase(name)){
                foundStudent = s;
                break;
            }
        }

        if (foundStudent == null){
            System.out.println(Constants.STUDENT_NOT_FOUND);
            return null;
        }

        System.out.println("Enter new Student name: ");
        foundStudent.setName(scanner.nextLine());

        System.out.println(Constants.STUDENT_UPDATED_SUCCESSFULLY);
        return foundStudent;
    }

    public void deleteStudent(List<Student> studentList){
        Scanner scanner = new Scanner(System.in);

        if(studentList == null || studentList.isEmpty()){
            System.out.println("No Student available to delete");
            return;
        }

        System.out.println("Student List");
     UniversityService.university.displayStudents();

        System.out.println("Enter Student name to delete");
        String name = scanner.nextLine();

        Student studentToRemove = null;

        for (Student s : studentList){
            if (s.getName() != null && s.getName().equalsIgnoreCase(name)){
                studentToRemove = s;
                break;
            }
        }

        if (studentToRemove != null) {
            studentList.remove(studentToRemove);
            System.out.println(Constants.STUDENT_DELETED_SUCCESSFULLY);
        } else {
            System.out.println(Constants.STUDENT_NOT_FOUND);
        }
    }

    public void displayStudentByName(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Student Name: ");
        String name = scanner.nextLine();

        Student student = null;

        for (Student s : UniversityService.university.getStudentList()) {
            if (s.getName() != null && s.getName().equalsIgnoreCase(name)) {
                student = s;
                break;
            }
        }

        if (student == null) {
            System.out.println(Constants.STUDENT_NOT_FOUND);
            return;
        }

        System.out.println(UniversityService.university.getName());
        System.out.println(student.getId());
        System.out.println(student.getName());
        System.out.println(student.getDepartment().getName());
    }

    public void displayAllStudents(){
        List<Student> studentList = UniversityService.university.getStudentList();

        if (studentList == null || studentList.isEmpty()){
            System.out.println("No Student Available");
            return;
        }
        System.out.println("All Student");

        for (Student student : studentList){
            System.out.println(UniversityService.university.getName());
            System.out.println(student.getId());

        }
    }


    public Boolean handleStudentMenu(Integer studentOption) {

        switch (studentOption) {

            case 1 -> {
                UniversityService.university.setStudentList(addNewStudents());
            }

            case 2 -> {
                updatedeStudent();
            }

            case 3 -> {
                deleteStudent(UniversityService.university.getStudentList());
            }

            case 4 -> {
                displayStudentByName();
            }

            case 5 -> {
                System.out.println("Show Students");
                UniversityService.university.displayStudents();
            }

            case 6 -> {
                return false;
            }
        }

        return true;
    }
}