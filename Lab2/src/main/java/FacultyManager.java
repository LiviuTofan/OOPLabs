import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FacultyManager {
    private List<Faculty> faculties = new ArrayList<>();

    public void addFaculty(Scanner scanner) {
        Faculty faculty = new Faculty();

        System.out.print("Enter faculty name: ");
        String facultyName = scanner.nextLine().trim();
        faculty.setName(facultyName);

        System.out.print("Enter faculty abbreviation: ");
        String facultyAbbreviation = scanner.nextLine().trim();
        faculty.setAbbreviation(facultyAbbreviation);

        String studyField = selectStudyField(scanner);

        faculty.setStudyField(studyField);

        faculties.add(faculty);

        System.out.println("A faculty was created successfully.");

    }

    private String selectStudyField(Scanner scanner) {
        String studyField = "";
        while (true) {
            System.out.println("Choose the Study Field:");
            System.out.println("1. Mechanical Engineering");
            System.out.println("2. Software Engineering");
            System.out.println("3. Food Technology");
            System.out.println("4. Urbanism Architecture");
            System.out.println("5. Veterinary Medicine");

            int studyFieldChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (studyFieldChoice) {
                case 1:
                    studyField = "Mechanical Engineering";
                    break;
                case 2:
                    studyField = "Software Engineering";
                    break;
                case 3:
                    studyField = "Food Technology";
                    break;
                case 4:
                    studyField = "Urbanism Architecture";
                    break;
                case 5:
                    studyField = "Veterinary Medicine";
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, 4, or 5");
                    continue;
            }
            break;
        }
        return studyField;
    }

    public void showFaculties() {
        if (faculties.isEmpty()) {
            System.out.println("No faculty is provided.");
        } else {
            System.out.println("List of Faculties:");
            for (int i = 0; i < faculties.size(); i++) {
                Faculty faculty = faculties.get(i);
                System.out.println((i + 1) + ". Faculty Name: " + faculty.getName());
                System.out.println("   Faculty Abbreviation: " + faculty.getAbbreviation());
                System.out.println("   Study Field: " + faculty.getStudyField());
            }
        }
    }

    public Faculty getFacultyByIndex(int index) {
        if (index >= 0 && index < faculties.size()) {
            return faculties.get(index);
        }
        throw new IndexOutOfBoundsException("Invalid faculty index: " + index);
    }

    public int getFacultyCount() {
        return faculties.size();
    }
}