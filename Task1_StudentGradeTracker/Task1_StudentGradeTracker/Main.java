import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 0; i < n; i++) {
            System.out.print("Enter name of student " + (i + 1) + ": ");
            String name = sc.nextLine();

            System.out.print("Enter grade of " + name + ": ");
            int grade = sc.nextInt();
            sc.nextLine(); // consume newline

            students.add(new Student(name, grade));
        }

        // Calculate average, highest, lowest
        int sum = 0, highest = Integer.MIN_VALUE, lowest = Integer.MAX_VALUE;
        String topStudent = "", lowStudent = "";

        for (Student s : students) {
            int g = s.getGrade();
            sum += g;

            if (g > highest) {
                highest = g;
                topStudent = s.getName();
            }

            if (g < lowest) {
                lowest = g;
                lowStudent = s.getName();
            }
        }

        double average = (double) sum / students.size();

        // Display results
        System.out.println("\n--- Grade Report ---");
        for (Student s : students) {
            System.out.println(s.getName() + " : " + s.getGrade());
        }
        System.out.println("Average Score: " + average);
        System.out.println("Highest Score: " + highest + " (by " + topStudent + ")");
        System.out.println("Lowest Score: " + lowest + " (by " + lowStudent + ")");
    }
}
