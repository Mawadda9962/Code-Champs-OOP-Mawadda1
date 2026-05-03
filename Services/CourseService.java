package ObjectOrientedProgramming.OOPDemo.Services;

import ObjectOrientedProgramming.OOPDemo.Entities.Course;
import ObjectOrientedProgramming.OOPDemo.Entities.Student;
import ObjectOrientedProgramming.OOPDemo.Utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CourseService {

    public List<Student> getCourses() {
        if (UniversityService.university.getStudentList() == null) {
            UniversityService.university.setCourseList(new ArrayList<>());
        }
        return UniversityService.university.getStudentList();
    }

    public Course addNewCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("** Adding new Course **");

        Course course = new Course();
        course.setId(UUID.randomUUID());

        System.out.println("Enter course name");
        course.setName(scanner.nextLine());

        System.out.println("Enter course code");
        course.setCourseCode(scanner.nextLine());

        boolean add = getCourses().add(course);

        System.out.println(Constants.COURSE_ADDED_SUCCESSFULLY);

        return course;
    }

    public Course updateCourse() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Course name to Update: ");
        String name = scanner.nextLine();

        Course course = findCourseByName(name);

        if (course == null) {
            System.out.println(Constants.COURSE_NOT_FOUND);
            return null;
        }

        System.out.println("Enter new Course name: ");
        course.setName(scanner.nextLine());

        System.out.println("Enter new Course code: ");
        course.setCourseCode(scanner.nextLine());

        System.out.println(Constants.COURSE_UPDATED_SUCCESSFULLY);

        return course;
    }

    public boolean deleteCourse() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Course name to delete: ");
        String name = scanner.nextLine();

        Course course = findCourseByName(name);

        if (course == null) {
            System.out.println(Constants.COURSE_NOT_FOUND);
            return false;
        }

        boolean removed = getCourses().remove(course);

        System.out.println(removed ?
                Constants.COURSE_DELETED_SUCCESSFULLY :
                Constants.COURSE_DELETED_FAILED);

        return removed;
    }

    public Course findCourseByName(String name) {
        for (Course c : getCourses()) {
            if (c.getName() != null && c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public void displayCourseByName() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Course name: ");
        String name = scanner.nextLine();

        Course course = findCourseByName(name);

        if (course == null) {
            System.out.println(Constants.COURSE_NOT_FOUND);
            return;
        }

        System.out.println("Course Id: " + course.getId());
        System.out.println("Course Name: " + course.getName());
        System.out.println("Course Code: " + course.getCourseCode());
    }

    public void displayAllCourses() {
        System.out.println("All Courses:");

        if (getCourses() == null || getCourses().isEmpty()) {
            System.out.println("No Courses Available");
            return;
        }

        for (Course c : getCourses()) {
            System.out.println("Course Id: " + c.getId());
            System.out.println("Course Name: " + c.getName());
            System.out.println("Course Code: " + c.getCourseCode());
        }
    }

    public boolean handleCourseMenu(int option) {

        switch (option) {

            case 1 -> addNewCourse();

            case 2 -> updateCourse();

            case 3 -> deleteCourse();

            case 4 -> displayCourseByName();

            case 5 -> displayAllCourses();

            case 6 -> {
                return false;
            }

            default -> System.out.println("Invalid option!");
        }

        return true;
    }

    // kept for compatibility with your teacher/department code
    public List<Course> addNewCourses() {
        return addNewCourse() != null ? getCourses() : getCourses();
    }
}