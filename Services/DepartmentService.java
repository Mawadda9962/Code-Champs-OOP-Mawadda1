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
        List<Department> departmentList = new ArrayList<>();
        Boolean continueFlag = true;
        while (continueFlag) {
            //System.out.println("Entering multiple departments");
            departmentList.add(addNewDepartment());
            System.out.println(Constants.INPUT_EXIT_CONTINUE_MESSAGE_DEPARTMENTS);
            if (scanner.nextLine().equalsIgnoreCase("q")) {
                continueFlag = false;
            }
        }
        return departmentList;
    }

    public void updatedeDepartment() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Department name to Update: ");
        UniversityService.university.displayDepartments();

        System.out.println("Enter Department name to update: ");
        String updateDepartment = scanner.nextLine();


        for (Department department : university.getDepartments()) {
            if (department.getName().equalsIgnoreCase(updateDepartment)) {
                System.out.println("Enter new Department Name: ");
                department.setName(scanner.nextLine());
                System.out.println(Constants.DEPARTMENT_UPDATED_SUCCESSFULLY);

            }else{

            System.out.println(Constants.COURSE_NOT_FOUND);
            }
        }

    }


    public void deleteDepartment(List<Department> departmentList){
        Scanner scanner = new Scanner(System.in);

        if (departmentList == null || departmentList.isEmpty()){
            System.out.println("No Departments available to delete");
        }
        System.out.println("Department List");
        UniversityService.university.displayDepartments();

        System.out.println("Enter Department name to delete");
        String deleteDepartment = scanner.nextLine();

        Department departmentToRemove = null;

        for(Department department : departmentList){
            if (department.getName().equalsIgnoreCase(deleteDepartment)){
                departmentToRemove = department;
                System.out.println(Constants.DEPARTMENT_DELETE_SUCCESSFULLY);
            }
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
                System.out.println("Show Departments");
                university.displayDepartments();
            }

            case 4 -> {
                deleteDepartment(university.getDepartments());
            }

            case 5 -> {
                return false;
            }
        }
        return true;
    }

}
