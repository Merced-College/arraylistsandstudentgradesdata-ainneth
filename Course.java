/*
 * Name:
 * Ariel Penaloza
 * Estefania Reyes
 * Angie Alvarez
 * Date: 02/19/2026
 * Program: Course Grades Analyzer - reads CSV grade totals and analyzes A percentages.
 */

import java.util.ArrayList;

public class Course {
    private String courseName;
    private ArrayList<Integer> courseGrades;

    //default constructor
    public Course(String courseName) {
        this.courseName = courseName;
        this.courseGrades = new ArrayList<>();
    }

    //all setters and getters
    public String getCourseName() {  
        return courseName;              //getter
    }

    public void setCourseName(String courseName) { 
        this.courseName = courseName;       //setter
    }

    public ArrayList<Integer> getCourseGrades() { 
        return courseGrades;            //getter
    }

    public void setCourseGrades(ArrayList<Integer> courseGrades) { 
        this.courseGrades = courseGrades;       //setter
    }

    //tostring method
    @Override
    public String toString() {        
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseGrades=" + courseGrades.toString() +
                '}';
    }

    //todo add methods here for calculations
    //sumOF{A+B+C+D+F}*100
    public int getTotalGrades(){
        int total = 0;
        for(int value : courseGrades){
            total += value;
        }
        return total;
    }

    public double getAPercent() {
        int total = getTotalGrades();
        if( total == 0)
            return 0.0;
        
        return(double) courseGrades.get(0) / total * 100.0;
    }

} // end class 