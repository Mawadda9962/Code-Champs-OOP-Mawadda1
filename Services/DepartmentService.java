package ObjectOrientedProgramming.OOPDemo.Services;

import ObjectOrientedProgramming.OOPDemo.Entities.Course;
import ObjectOrientedProgramming.OOPDemo.Entities.Department;
import ObjectOrientedProgramming.OOPDemo.Entities.University;
import ObjectOrientedProgramming.OOPDemo.Utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class DepartmentService {

    CourseService courseService = new CourseService();
    University university = new University();

    public List<Department> getDepartments() {
        if (UniversityService.university.getDepartments() == null) {
            UniversityService.university.setDepartments(new ArrayList<>());
        }
        return UniversityService.university.getDepartments();
    }

    public Department addNewDepartment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("** Adding new department **");

        Department department = new Department();
        department.setId(UUID.randomUUID());

        System.out.println("Enter Department Name");
        String deptName = scanner.nextLine();
        department.setName(deptName);

        department.setOfferedCourses(courseService.addNewCourses());

        return department;
    }

    public List<Department> addNewDepartments() {
        Scanner scanner = new Scanner(System.in);
        List<Department> departmentList = getDepartments();
        Boolean continueFlag = true;

        while (continueFlag) {
            departmentList.add(addNewDepartment());
            System.out.println(Constants.INPUT_EXIT_CONTINUE_MESSAGE_DEPARTMENTS);

            if (scanner.nextLine().equalsIgnoreCase("q")) {
                continueFlag = false;
            }
        }
        return departmentList;
    }


    public Department updatedeDepartment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Department name to Update: ");
        String name = scanner.nextLine();

        Department dep = findDepartmentByName(name);

        if (dep == null) {
            System.out.println(Constants.COURSE_NOT_FOUND);
            return null;
        }

        System.out.println("Enter new Department name: ");
        String addNewName = scanner.nextLine();

        Department updateDepartment = new Department();
        updateDepartment.setId(dep.getId());
        updateDepartment.setName(addNewName);
        updateDepartment.setOfferedCourses(courseService.addNewCourses());

        modifyDepartment(name, updateDepartment);
        return updateDepartment;
    }


    public Boolean modifyDepartment(String departmentName, Department updatedDepartment) {

        Department dep = findDepartmentByName(departmentName);

        if (dep != null) {
            dep.setName(updatedDepartment.getName());
            dep.setOfferedCourses(updatedDepartment.getOfferedCourses());
            System.out.println(Constants.DEPARTMENT_UPDATED_SUCCESSFULLY);
            return true;
        }

        System.out.println(Constants.DEPARTMENT_UPDATED_FAILED);
        return false;
    }


    public void deleteDepartment(List<Department> departmentList) {
        Scanner scanner = new Scanner(System.in);

        if (departmentList == null || departmentList.isEmpty()) {
            System.out.println("No Departments available to delete");
            return;
        }

        System.out.println("Department List");
        UniversityService.university.displayDepartments();

        System.out.println("Enter Department name to delete");
        String deleteDepartment = scanner.nextLine();

        Department departmentToRemove = null;

        for (Department department : departmentList) {
            if (department.getName().equalsIgnoreCase(deleteDepartment)) {
                departmentToRemove = department;
            }
        }

        if (departmentToRemove != null) {
            departmentList.remove(departmentToRemove);
            System.out.println(Constants.DEPARTMENT_DELETE_SUCCESSFULLY);
        } else {
            System.out.println(Constants.COURSE_NOT_FOUND);
        }
    }


    public Department findDepartmentByName(String departmentName) {
        for (Department d : getDepartments()) {
            if (d.getName() != null && d.getName().equalsIgnoreCase(departmentName)) {
                return d;
            }
        }
        return null;
    }

    public void displayDepartmentByName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Department Name: ");
        String name = scanner.nextLine();

        Department department = findDepartmentByName(name);

        if (department == null){
            System.out.println(Constants.COURSE_NOT_FOUND);
            return;
        }
        System.out.println(UniversityService.university.getName());
        System.out.println(department.getId());
        System.out.println(department.getName());
    }


    public void displayAllDepartments() {
        List<Department> departmentList = getDepartments();

        if(departmentList == null || departmentList.isEmpty()){
            System.out.println("No departments available");
            return;
        }
        System.out.println("All Departments: ");

        for (Department department : departmentList){
            System.out.println(UniversityService.university.getName());
            System.out.println(department.getId());
            System.out.println(department.getName());
        }

    }
    public Boolean handleDepartmentMenu(Integer departmentOption) {

        switch (departmentOption) {
            case 1 -> {
                university.setDepartments(addNewDepartments());
            }
            case 2 -> {
                updatedeDepartment();
            }
            case 3 -> {
                deleteDepartment(university.getDepartments());
            }
            case 4 -> {
                displayDepartmentByName();

            }
            case 5 -> {
                return false;
            }
        }
        return true;
    }
}