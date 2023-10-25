import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MenuManager {
    private StudentManager studentManager;
    private FacultyManager facultyManager;

    public MenuManager() {
        this.facultyManager = new FacultyManager();
        this.studentManager = new StudentManager(facultyManager);
    }
    public void runMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMainMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    runFacultyOperations(scanner);
                    break;
                case 2:
                    runGeneralOperations(scanner);
                    break;
                case 3:
                    exitProgram(scanner);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }
    private void printMainMenu() {
        System.out.println("Choose from provided operations provided by TUM:");
        System.out.println("1. Faculty operations");
        System.out.println("2. General operations");
        System.out.println("3. Exit");
    }

    private void runFacultyOperations(Scanner scanner) {
        while (true) {
            printFacultyMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    studentManager.addStudent(scanner, "undergraduate");
                    break;
                case 2:
                    markStudentAsGraduated(scanner);
                    break;
                case 3:
                    studentManager.displayEnrolledStudents();
                    break;
                case 4:
                    displayGraduatedStudents();
                    break;
                case 5:
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
            System.out.println();
        }
    }

    private void printFacultyMenu() {
        System.out.println("\nFaculty Operations:");
        System.out.println("1. Create and assign a student to a faculty.");
        System.out.println("2. Graduate a student from a faculty.");
        System.out.println("3. Display current enrolled students.");
        System.out.println("4. Display graduates.");
        System.out.println("5. Return to main menu.");
    }

    private void runGeneralOperations(Scanner scanner) {
        while (true) {
            printGeneralMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    facultyManager.addFaculty(scanner);
                    break;
                case 2:
                    facultyManager.showFaculties();
                    break;
                case 3:
                    findStudentById(scanner);
                    break;
                case 4:
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }

    private void printGeneralMenu() {
        System.out.println("\nGeneral Operations:");
        System.out.println("1. Create a new faculty.");
        System.out.println("2. Display University faculties.");
        System.out.println("3. Find student by unique ID.");
        System.out.println("4. Return to main menu.");
    }

    private int getUserChoice(Scanner scanner) {
        int choice;
        do {
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
        } while (choice < 1 || choice > 5);
        return choice;
    }


    private void markStudentAsGraduated(Scanner scanner) {
        if (studentManager.getStudentCount() == 0) {
            System.out.println("Please first enroll a student.");
            return;
        }

        System.out.print("Enter the student's unique ID to mark as graduated: ");
        String studentId = scanner.nextLine();

        Student foundStudent = studentManager.findStudentById(studentId);

        if (foundStudent != null) {
            studentManager.markStudentAsGraduated(studentId);
            System.out.println("Student with ID " + studentId + " has been marked as graduated.");
        } else {
            System.out.println("No student with the provided ID found.");
        }
    }

    private void findStudentById(Scanner scanner) {
        if (studentManager.getStudentCount() == 0) {
            System.out.println("No students are enrolled yet.");
            return;
        }

        System.out.print("Enter the student's unique ID: ");
        String studentId = scanner.nextLine();

        Student foundStudent = studentManager.findStudentById(studentId);

        if (foundStudent != null) {
            System.out.println("Found Student:");
            System.out.println("Full Name: " + foundStudent.getFirstName() + " " + foundStudent.getLastName());
            System.out.println("Mail: " + foundStudent.getMail());
            System.out.println("Date of Birthday: " + foundStudent.getBday());
            Faculty faculty = foundStudent.getFaculty();
            if (faculty != null) {
                System.out.println("Faculty: " + faculty.getName());
            } else {
                System.out.println("Faculty: Not assigned to any faculty.");
            }
        } else {
            System.out.println("No student with the provided ID found.");
        }
    }
    private void displayGraduatedStudents() {
        List<Student> graduatedStudents = studentManager.getGraduatedStudents();

        if (graduatedStudents.isEmpty()) {
            System.out.println("No student was graduated yet.");
        } else {
            System.out.println("Graduated Students:");
            for (Student student : graduatedStudents) {
                System.out.println("Full Name: " + student.getFirstName() + " " + student.getLastName());
                System.out.println("Mail: " + student.getMail());
                System.out.println("Date of Birthday: " + student.getBday());
                Faculty faculty = student.getFaculty();
                if (faculty != null) {
                    System.out.println("Faculty: " + faculty.getName());
                } else {
                    System.out.println("Faculty: Not assigned to any faculty.");
                }
                System.out.println();
            }
        }
    }
    private void exitProgram(Scanner scanner) {
        System.out.println("Goodbye!");
        scanner.close();
        System.exit(0);

    }
}