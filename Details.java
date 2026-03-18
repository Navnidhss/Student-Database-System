import java.util.*;
/* author: Navnidh Singh Sabharwal
    Student ID: 23298477
 */
// Details class defined with it's fields
public class Details {
    private int yearlevel;  // year enrolled in 1-4
    private String courseEnrolled; // course enrolled in
    private double cwa; // current weighted average
    private String status; // Fulltime or PartTime status
    private int creditsEarned; // number of credits earned

    // Constructor with parameters
    public Details (int pYearlevel, String pCourseEnrolled, double pCWA, String pStatus, int pCreditsEarned) {
        yearlevel = pYearlevel;
        courseEnrolled = pCourseEnrolled;
        cwa = pCWA;
        status = pStatus;
        creditsEarned = pCreditsEarned;

    }

    // getter methods for all fields of Details class
    public int getYearlevel() { // getter method, returns yearlevel
        return yearlevel; }

    public String getCourseEnrolled() { // getter method, returns courseEnrolled
        return courseEnrolled; }

    public double getCWA() { // getter method, returns cwa
        return cwa; }

    public String getStatus() { // getter method, returns status
        return status; }

    public int getCreditsEarned() { // getter method, returns creditsEarned
        return creditsEarned; }


    // mutator methods for all fields of details class
    public void setYearlevel(int pYearlevel) { //mutator method, sets yearlevel
         yearlevel = pYearlevel;
    }
    public void setCourseEnrolled(String pCourseEnrolled) { //mutator method, sets courseEnrolled
        courseEnrolled = pCourseEnrolled; } //mutator method, sets courseEnrolled

    public void setCWA(double pCWA) { // mutator method, sets cwa
         cwa = pCWA; }

    public void setStatus(String pStatus) { //mutator method, sets status
        status = pStatus; }

    public void setCreditsEarned(int pCreditsEarned) { // mutator method, sets creditsEarned
         creditsEarned = pCreditsEarned; }


    public String toString() {  // returns string representation of Details
        return "yearlevel: " + yearlevel
                + ", courseEnrolled: " + courseEnrolled
                +", cwa: " + cwa
                + ", status: " + status
                + ", creditsEarned: " + creditsEarned; }

}

