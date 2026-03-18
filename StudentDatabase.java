import java.util.*;
import java.io.*;
public class StudentDatabase {
    private static Student[] students = new Student[100]; //array to store student objects; outside main method
    private static int count = 0; // to keep track of number of students

    public static void main(String[] args) { // main method
        loadData();
        int option = -1; // initialize option variable
        Scanner sc = new Scanner(System.in); // scanner for input
        do { // do while loop for menu
            // Menu prompt
            System.out.print("""
                    Welcome to the Student Central!
                    1. Add new student
                    2. Edit student
                    3. View all students
                    4. Filter by Course
                    5. Filter by Status
                    6. Highest CWA
                    7. Avg CWA for each Course
                    8. Credit analysis
                    9. Exit
                    10. Save changes""");
            System.out.println();
            System.out.print("Enter your option: ");
            option = sc.nextInt();
            if (option < 1 || option > 10) {
                System.out.println("Invalid option! Please choose between 1-9.");
            }
            switch (option) {
                case 1: // add new student
                    addStudent();
                    break;
                case 2: // edit existing student
                    editStudent();
                    break;
                case 3: // view all students
                    viewAllStudents();
                    break;
                case 4: // filter by course
                    filterByCourse();
                    break;
                case 5: // filter by status
                    filterByStatus();
                    break;
                case 6: // highest CWA
                    highestCWA();
                    break;
                case 7: // average CWA for each course
                    avgCWAForEachCourse();
                    break;
                case 8: // credit analysis for graduation eligibility
                    creditAnalysis();
                    break;
                case 9: // exit program
                    System.out.println("Exiting the program. Thanks for using Student Central!");
                case 10: // write changes to CSV file
                    saveData();


                    break;
                default:
                    System.out.println("Invalid option! Please try again."); // validation for invalid option
            }
        } while (option != 9);
        sc.close();


    }

    // method to load data from CSV file
    private static void loadData() {

        try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                try {
                    // split line by commas and parse data
                    String[] parts = line.split(",");
                    int studentID = Integer.parseInt(parts[0]);
                    String firstName = parts[1];
                    String familyName = parts[2];
                    String courseEnrolled = parts[3];
                    int yearLevel = Integer.parseInt(parts[4]);
                    double cwa = Double.parseDouble(parts[5]);
                    String status = parts[6];
                    int creditsEarned = Integer.parseInt(parts[7]);
                    // calling details and student constructors
                    Details details = new Details(yearLevel, courseEnrolled, cwa, status, creditsEarned);
                    Student student = new Student(studentID, firstName, familyName, details);
                    students[count] = student; // add student to array with count as index
                    count++;
                } catch (Exception e2) {
                    System.out.println("Skipping invalid line: " + line);
                }

            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


    // method to save data to CSV file
    private static void saveData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data.csv"))) {
            bw.write("StudentID,FirstName,FamilyName,CourseEnrolled,YearLevel,CWA,Status,CreditsEarned");
            bw.newLine();
            for (int i = 0; i < count; i++) {
                Student s = students[i]; // get student at index i
                if (s == null || s.getDetails() == null) {
                    continue;
                }
                bw.write(s.getStudentID() + "," + s.getFirstName() + "," + s.getFamilyName() + "," +
                        s.getDetails().getCourseEnrolled() + "," + s.getDetails().getYearlevel() + "," +
                        s.getDetails().getCWA() + "," + s.getDetails().getStatus() + "," +
                        s.getDetails().getCreditsEarned()); // write student data to file
                bw.newLine();
            }
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }


    // method to add new student data to CSV file
    private static void addStudent() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter Student ID: ");
            int studentID = sc.nextInt();
            // validate studentID
            if (studentID <= 0) {
                System.out.println("Invalid Student ID!");
                return;
            }
            sc.nextLine();
            System.out.print("Enter First Name: ");
            String firstName = sc.nextLine();
            // validate firstName
            if (firstName.isEmpty()) {
                System.out.println("First Name cant be empty!");
                return;
            }
            System.out.print("Enter Family Name: ");
            String familyName = sc.nextLine();
            // validate familyName
            if (familyName.isEmpty()) {
                System.out.println("Family Name can't be empty");
                return;
            }
            System.out.print("Enter Course Enrolled: ");
            String courseEnrolled = sc.nextLine();
            // validate courseEnrolled
            if (courseEnrolled.isEmpty()) {
                System.out.println("Course cant be empty");
                return;
            }
            System.out.print("Enter Year Level: ");
            int yearLevel = sc.nextInt();
            // validate yearLevel
            if (yearLevel < 1 || yearLevel > 4) {
                System.out.println("Year Level must be between 1 and 4");
                return;
            }
            System.out.print("Enter CWA: ");
            // validate cwa
            double cwa = sc.nextDouble();
            if (cwa < 0 || cwa > 100) {
                System.out.println("CWA must be between 0 and 100!");
                return;
            }
            sc.nextLine(); // consume newline
            System.out.print("Enter Status (FT/PT): ");
            String status = sc.nextLine();
            // validate status
            if (!status.equals("FT") && !status.equals("PT") && !status.equals("ft") && !status.equals("pt")) {
                System.out.println("Status must be FT or PT");
                return;
            }
            System.out.print("Enter Credits Earned: ");
            int creditsEarned = sc.nextInt();
            // validate creditsEarned
            if (creditsEarned > 400) {
                System.out.println("Credits Earned cannot be more than 400");
                return;
            }

            Details details = new Details(yearLevel, courseEnrolled, cwa, status, creditsEarned);
            Student student = new Student(studentID, firstName, familyName, details);
            students[count] = student;
            count++;
            System.out.println("Student added successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter the correct data type.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    // method to edit existing student data in CSV file
    private static void editStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Student ID to edit: ");
        int studentID = Integer.parseInt(sc.nextLine());
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (students[i].getStudentID() == studentID) {
                found = true;
                System.out.println("Editing Student: " + students[i]);
                System.out.println();
                System.out.println("Leave field blank to keep current info"); // when you want to keep current info
                System.out.print("Enter new First Name (current: " + students[i].getFirstName() + "): "); // dsisplay current first name and prompt for new
                String firstName = sc.nextLine();
                if (!firstName.isEmpty()) { // if input is not empty, update first name
                    students[i].setFirstName(firstName);
                }

                System.out.print("Enter new Family Name (current: " + students[i].getFamilyName() + "): ");
                String familyName = sc.nextLine();
                if (!familyName.isEmpty()) {
                    students[i].setFamilyName(familyName);
                }

                System.out.print("Enter new Course Enrolled (current: " + students[i].getDetails().getCourseEnrolled() + "): ");
                String courseEnrolled = sc.nextLine();
                if (!courseEnrolled.isEmpty()) {
                    students[i].getDetails().setCourseEnrolled(courseEnrolled);
                }

                System.out.print("Enter new Year Level (current: " + students[i].getDetails().getYearlevel() + "): ");
                String yearLevelStr = sc.nextLine();
                if (!yearLevelStr.isEmpty()) {
                    try {
                        int yearLevel = Integer.parseInt(yearLevelStr);
                        if (yearLevel >= 1 && yearLevel <= 4) { // validate year level
                            students[i].getDetails().setYearlevel(yearLevel);
                        } else {
                            System.out.println("Invalid Year Level! Must be between 1 and 4.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Year Level must be a number.");
                    }
                }

                System.out.print("Enter new CWA (current: " + students[i].getDetails().getCWA() + "): ");
                String cwaStr = sc.nextLine();
                if (!cwaStr.trim().isEmpty()) {
                    try {
                        double cwa = Double.parseDouble(cwaStr);
                        if (cwa >= 0 && cwa <= 100) { // validate CWA
                            students[i].getDetails().setCWA(cwa);
                        } else {
                            System.out.println("Invalid CWA! Must be between 0 and 100.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("CWA must be a number.");
                    }
                }

                System.out.print("Enter new Status (current: " + students[i].getDetails().getStatus() + "): ");
                String status = sc.nextLine();
                if (!status.isEmpty()) {
                    if (status.equalsIgnoreCase("FT") || status.equalsIgnoreCase("PT")) {
                        students[i].getDetails().setStatus(status);
                    } else {
                        System.out.println("Status must be FT or PT."); // validate status
                    }
                }

                System.out.print("Enter new Credits Earned (current: " + students[i].getDetails().getCreditsEarned() + "): ");
                String creditsStr = sc.nextLine();
                if (!creditsStr.isEmpty()) {
                    try {
                        int credits = Integer.parseInt(creditsStr);
                        if (credits >= 0 && credits <= 400) { // validate credits earned
                            students[i].getDetails().setCreditsEarned(credits);
                        } else {
                            System.out.println("Credits Earned must be between 0 and 400.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Credits Earned must be a number.");
                    }
                }

                System.out.println("Student updated successfully!");
                return;
            }
        }
        if (!found) {
            System.out.println("Student ID not found.");
        }
    }

    // method to view all student data
    private static void viewAllStudents() {
        if (count == 0) {
            System.out.println("No students found.");
            return;
        }
        for (int i = 0; i < count; i++) { // loop through all students and print their details
            System.out.println(students[i].toString()); // string representation of student used to show all details in one line
        }

    }

    // method to filter students by course
    private static void filterByCourse() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Course to filter by: "); // prompt for course
        String course = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (students[i].getDetails().getCourseEnrolled().equalsIgnoreCase(course)) { // check if course matches, ignoring case for user convenience
                System.out.println(students[i].toString());
                found = true;
            }
        }
        if (!found) { //if no students found for the course
            System.out.println("No students found for course: " + course);
        }
    }

    // method to filter students by status
    private static void filterByStatus() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Status (FT/PT) to filter by: "); // prompt for status
        String status = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (students[i].getDetails().getStatus().equalsIgnoreCase(status)) { // check if status matches, ignoring case
                System.out.println(students[i].toString());
                found = true;
            }
        }
        if (!found) { // if no students found with selected status
            System.out.println("No students found with status: " + status);
        }
    }


    // method to find student with highest CWA
    private static void highestCWA() {
        if (count == 0) { // if no students in database
            System.out.println("No students found.");
            return;
        }
        Student topStudent = null; // variable for student with highest CWA
        double maxCWA = -1;
        for (int i = 0; i < count; i++) {
            Student s = students[i];
            if (s != null && s.getDetails() != null) {
                double cwa = s.getDetails().getCWA();
                if (cwa > maxCWA) { // compare CWA to current max
                    maxCWA = cwa;
                    topStudent = s;
                }
            }
        }
        if (topStudent != null) {
            System.out.println("Student with highest CWA:");
            System.out.println(topStudent.toString()); // print student with highest CWA, string representation used
        } else {
            System.out.println("No valid student data found."); // if no valid student data
        }


    }
    // method to calculate average CWA for each course
    private static void avgCWAForEachCourse() {
        if (count == 0) { // if no students in database
            System.out.println("No students found.");
            return;
        }

        String[] courses = new String[count];
        double[] sums = new double[count];
        int[] countsPerCourse = new int[count];
        int uniqueCourses = 0;

        for (int i = 0; i < count; i++) {
            Student s = students[i];
            if (s == null || s.getDetails() == null) continue; // skips invalid student data
            String course = s.getDetails().getCourseEnrolled();
            if (course == null || course.isEmpty()) course = "Unknown";

            int idx = -1;
            for (int j = 0; j < uniqueCourses; j++) {
                if (courses[j].equalsIgnoreCase(course)) { // check if course already exists, ignoring case
                    idx = j;
                    break;
                }
            }

            if (idx == -1) {
                courses[uniqueCourses] = course; // new course found
                sums[uniqueCourses] = s.getDetails().getCWA();
                countsPerCourse[uniqueCourses] = 1;
                uniqueCourses++;
            } else {
                sums[idx] += s.getDetails().getCWA(); // existing course, update sum and count
                countsPerCourse[idx]++;
            }
        }

        if (uniqueCourses == 0) { // if no valid courses found
            System.out.println("No valid student/course data found.");
            return;
        }

        System.out.println("Average CWA for each course:");
        for (int i = 0; i < uniqueCourses; i++) { // calculate and print average CWA for each course
            double avg = sums[i] / countsPerCourse[i];
            System.out.println(courses[i] + ": " + avg);
        }
    }
    // method for credit analysis
    private static void creditAnalysis() {
        if (count == 0) { // if no students in database
            System.out.println("No students found.");
            return;
        }
        boolean found = false;
        System.out.println("Students eligible for graduation (400+ credits):");
        for (int i = 0; i < count; i++) { // loop through all students
            Student s = students[i];
            if (s != null && s.getDetails() != null) {
                if (s.getDetails().getCreditsEarned() >= 400) {
                    System.out.println(s.toString());
                    found = true;
                }
            }
        }
        if (!found) { // if no students eligible for graduation
            System.out.println("No students eligible for graduation.");
        }
    }




}

