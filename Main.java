/*
 * Name:
 * Ariel Penaloza
 * Estefania Reyes
 * Angie Alvarez
 * Date: 02/19/2026
 * Program: Course Grades Analyzer - reads CSV grade totals and analyzes A percentages.
 */

import java.io.*;
import java.util.*; 

public class Main {
    
    static ArrayList<Course> courses = new ArrayList<>();
    
    public static void main(String[] args) {
        readCoursesFromCSV("courseAndGradesData.csv");

        printSummaryTable();
        highestPercent();
        
        // Print courses to verify
        for (Course course : courses) {
            System.out.println(course);
        }
    }
    
    static void readCoursesFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNum = 0;
            
            while ((line = br.readLine()) != null) {
                lineNum++;

                if(lineNum == 1){
                    continue;
                }
            
                //read line into a String called line, line looks like this in the file ACTG-04A,937,558,347,98,423
                String[] parts = line.split(","); 
                String courseName = parts[0]; 
                ArrayList<Integer> grades = new ArrayList<>();
                // parts[1] = A count, parts[2] = B count, ... parts[5] = F count
                for (int i = 1; i < parts.length; i++) {
                    int count = Integer.parseInt(parts[i].trim());
                    grades.add(count);  // index 0 = A, 1 = B, 2 = C, 3 = D, 4 = F
                }//end for

                Course course = new Course(courseName);
                course.setCourseGrades(grades);
                courses.add(course);

            }//end while

        }catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }//end readCoursesFromCSV
    }

    static void printSummaryTable(){
        for (Course c : courses) {
            ArrayList<Integer> grades = c.getCourseGrades();
            System.out.printf("%-10s %6d %6d %6d %6d %6d %8d %7.2f%%%n", c.getCourseName(), grades.get(0),
            grades.get(1), grades.get(2), grades.get(3), grades.get(4), c.getTotalGrades(), c.getAPercent());// Grades A, B, C, D, F
        }

    }

    static void highestPercent() {
        if (courses.isEmpty()) {
            return;
        }

        Course bestCourse = courses.get(0);

        for(Course c : courses) {
            if(c.getAPercent() > bestCourse.getAPercent()){
                bestCourse = c;
            }
        }
        System.out.println(bestCourse.getCourseName());
        System.out.println(bestCourse.getAPercent());
    }


}//end Main