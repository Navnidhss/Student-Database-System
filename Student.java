
import java.util.*;
/* Author: Navnidh Singh Sabharwal
    Student ID: 23298477
 */

// Student class defined with fields
public class Student {
    private int studentID; // ID of student
    private String firstName; // first name of student
    private String familyName; // family name of student
    private Details details; // details object for each student


    // constructor with parameters
    public Student(int pStudentID, String pfirstName, String pfamilyName, Details pDetails) {
        studentID = pStudentID;
        firstName = pfirstName;
        familyName = pfamilyName;
        details = pDetails;
    }

    // getter methods for all Student class fields
    public int getStudentID() { // getter method, returns studentID
        return studentID;
    }

    public String getFirstName() { //getter method, returns firstName
        return firstName;
    }

    public String getFamilyName() { // getter method, returns familyName
        return familyName;
    }

    public Details getDetails() { // getter method, returns details object
        return details;
    }

    // mutator methods for all Student  class fields
    public void setStudentID(int pStudentID) { // mutator method, sets studentID
        studentID = pStudentID;
    }

    public void setFirstName(String pFirstName) { // mutator method, sets firstName
        firstName = pFirstName;
    }

    public void setFamilyName(String pFamilyName) { // mutator method, sets familyName
        familyName = pFamilyName;
    }

    public void setDetails(Details pDetails) { // mutator method, sets details object
        details = pDetails;
    }

    public String toString() { // returns string representation of Student
        return "StudentID: " + studentID +
                ", FirstName: " + firstName +
                ", FamilyName: " + familyName +
                ", Details: " + details;
    }
}


