package ObjectOrientedProgramming.OOPDemo.Entities;

import ObjectOrientedProgramming.OOPDemo.Behaviours.DepartmentInterface;
import ObjectOrientedProgramming.OOPDemo.Behaviours.UniversityInterface;
import ObjectOrientedProgramming.OOPDemo.Services.UniversityService;

import java.util.List;

public class University extends ParentEntity implements UniversityInterface {

    private List<Department> departments;

    private String address;

    private List<Student> studentList;

    private List<Teacher> teacherList;

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {

        this.departments = departments;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    @Override
    public Boolean registerStudent(Student student) {
        Boolean status = studentList.add(student);
        return status;
    }


    @Override
    public void chargeFees(Integer studentId, String studentName) {
        System.out.println("Fee Charged");
    }

    @Override
    public Boolean hireTeachers(Teacher teacher) {
        Boolean status = teacherList.add(teacher);
        return status;
    }

    @Override
    public void displayDepartments() {
        System.out.println("University Name" + UniversityService.university.getName());
        for (Department department : UniversityService.university.getDepartments()) {
            System.out.println("Department Id: " + department.getId());
            System.out.println("Department Name: " + department.getName());
            for (Course c : department.getOfferedCourses()) {
                System.out.println("Course Id: " + c.getId());
                System.out.println("Course Name: " + c.getName());
                System.out.println("Course Code: " + c.getCourseCode());
            }
        }
    }
    @Override
    public void displayStudents() {
        System.out.println("University Name " + UniversityService.university.getName());

        if (UniversityService.university.getStudentList() == null || UniversityService.university.getStudentList().isEmpty()) {
            System.out.println("No Students Available");
            return;
        }

        for (Student student : UniversityService.university.getStudentList()) {
            System.out.println("Student Id: " + student.getId());
            System.out.println("Student Name: " + student.getName());

            if (student.getDepartment() != null) {
                System.out.println("Department Name: " + student.getDepartment().getName());
            }

        }
    }


    @Override
    public String toString() {
        return "University{" +
                "departments=" + departments +
                ", address='" + address + '\'' +
                ", studentList=" + studentList +
                ", teacherList=" + teacherList +
                '}';
    }
}
