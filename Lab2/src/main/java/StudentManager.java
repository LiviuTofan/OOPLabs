import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;

public class StudentManager {
    private Set<String> usedStudentIds = new HashSet<>();
    private List<Student> students = new ArrayList<>();
    private List<Student> graduatedStudents = new ArrayList<>(); // Declare graduatedStudents list
    private FacultyManager facultyManager;

    public StudentManager(FacultyManager facultyManager) {
        this.facultyManager = facultyManager;
    }

    public void addStudent(Scanner scanner, String studentType) {
        Student student;

        if ("undergraduate".equalsIgnoreCase(studentType)) {
            student = new UndergraduateStudent();
        } else if ("graduate".equalsIgnoreCase(studentType)) {
            student = new GraduateStudent();
        } else {
            System.out.println("Invalid student type. Please choose 'undergraduate' or 'graduate'.");
            return;
        }

        if (facultyManager.getFacultyCount() == 0) {
            System.out.println("Please create at least one faculty first.");
            return;
        }

        student.setStatus("enrolled");

        students.add(student);

        System.out.print("Enter your firstname: ");
        String inputFirstname = scanner.nextLine();
        student.setFirstName(inputFirstname);

        System.out.print("Enter your last name: ");
        String inputLastname = scanner.nextLine();
        student.setLastName(inputLastname);

        System.out.print("Enter your mail: ");
        String inputMail = scanner.nextLine();
        student.setMail(inputMail);

        System.out.print("Write the Date of Birthday: ");
        String inputBday = scanner.nextLine();
        student.setBday(inputBday);

        String studentId;
        do {
            studentId = generateStudentId();
        } while (usedStudentIds.contains(studentId));

        student.setStudentId(studentId);
        usedStudentIds.add(studentId);

        printStudentDetails(student);

        students.add(student);

        System.out.println("Choose a faculty to assign the student to:");
        facultyManager.showFaculties();

        int facultyChoice;
        do {
            System.out.print("Enter the number of the faculty: ");
            facultyChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
        } while (facultyChoice < 1 || facultyChoice > facultyManager.getFacultyCount());

        Faculty selectedFaculty = facultyManager.getFacultyByIndex(facultyChoice - 1);
        selectedFaculty.addStudent(student);

        student.setFaculty(selectedFaculty);
    }

    private void printStudentDetails(Student student) {
        System.out.println();
        System.out.println("Full Name: " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Student ID: " + student.getStudentId());
    }

    private String generateStudentId() {
        String characters = "0123456789";
        StringBuilder studentId = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = (int) (Math.random() * characters.length());
            studentId.append(characters.charAt(index));
        }
        return studentId.toString();
    }

    public int getStudentCount() {
        return students.size();
    }

    public Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null; // Student not found
    }

    public void markStudentAsGraduated(String studentId) {
        Student student = findStudentById(studentId);
        if (student != null) {
            student.setStatus("graduated");
            graduatedStudents.add(student);
            students.remove(student);
        } else {
            System.out.println("No student with the provided ID found.");
        }
    }

    public void displayEnrolledStudents() {
        if (facultyManager.getFacultyCount() == 0) {
            System.out.println("No student was enrolled yet.");
            return;
        }

        System.out.println("Choose a faculty to display enrolled students:");

        facultyManager.showFaculties();
        Scanner scanner = new Scanner(System.in);
        int facultyChoice;

        do {
            System.out.print("Enter the number of the faculty: ");
            facultyChoice = scanner.nextInt();
            scanner.nextLine();
        } while (facultyChoice < 1 || facultyChoice > facultyManager.getFacultyCount());

        Faculty selectedFaculty = facultyManager.getFacultyByIndex(facultyChoice - 1);
        List<Student> enrolledStudents = new ArrayList<>();

        for (Student student : selectedFaculty.getStudents()) {
            if ("enrolled".equalsIgnoreCase(student.getStatus())) {
                enrolledStudents.add(student);
            }
        }

        if (enrolledStudents.isEmpty()) {
            System.out.println("No students are enrolled in " + selectedFaculty.getName());
        } else {
            System.out.println("Enrolled students for " + selectedFaculty.getName() + ":");

            for (Student student : enrolledStudents) {
                System.out.println("Student ID: " + student.getStudentId());
                System.out.println("Full Name: " + student.getFirstName() + " " + student.getLastName());
                System.out.println("Mail: " + student.getMail());
                System.out.println("Date of Birthday: " + student.getBday());
            }
        }
    }
    public List<Student> getGraduatedStudents() {
        return graduatedStudents;
    }
}
