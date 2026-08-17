import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    ArrayList<Integer> grades;

    Student(String name) {
        this.name = name;
        grades = new ArrayList<>();
    }

    void addGrade(int grade) {
        grades.add(grade);
    }

    double getAverage() {
        int sum = 0;

        for (int grade : grades) {
            sum += grade;
        }

        return (double) sum / grades.size();
    }

    int getHighest() {
        int highest = grades.get(0);

        for (int grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }

        return highest;
    }

    int getLowest() {
        int lowest = grades.get(0);

        for (int grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }

        return lowest;
    }

    void displayReport() {
        System.out.println("\nStudent Name : " + name);
        System.out.println("Grades       : " + grades);
        System.out.printf("Average      : %.2f%n", getAverage());
        System.out.println("Highest      : " + getHighest());
        System.out.println("Lowest       : " + getLowest());
    }
}

public class StudentGradeTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {

            System.out.print("\nEnter student name: ");
            String name = sc.nextLine();

            Student student = new Student(name);

            System.out.print("Enter number of grades: ");
            int gradeCount = sc.nextInt();

            for (int j = 0; j < gradeCount; j++) {

                int grade;

                do {
                    System.out.print("Enter grade (0-100): ");
                    grade = sc.nextInt();

                    if (grade < 0 || grade > 100) {
                        System.out.println("Invalid grade! Enter between 0 and 100.");
                    }

                } while (grade < 0 || grade > 100);

                student.addGrade(grade);
            }

            sc.nextLine();
            students.add(student);
        }

        System.out.println("\n==================================");
        System.out.println("       STUDENT GRADE REPORT");
        System.out.println("==================================");

        for (Student student : students) {
            student.displayReport();
        }

        sc.close();
    }
}