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
        //object student class
        Student student = new Student();
        student.setId(UUID.randomUUID());

        System.out.println("Enter student Name: ");
        String stdName = scanner.nextLine();

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


    }







}
