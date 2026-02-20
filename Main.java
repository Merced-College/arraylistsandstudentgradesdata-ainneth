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
        searchCourse();

        // Print courses to verify
        for (Course course : courses) {
            System.out.println(course);
        }
    }
    
     //Step 4: Read CSV & build ArrayList
     
    static void readCoursesFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNum = 0;
            
            while ((line = br.readLine()) != null) {
                lineNum++;

                if(lineNum <= 2){
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

    //Step 4: Print a Summary Table
    
    static void printSummaryTable(){
        System.out.printf("%-10s %6s %6s %6s %6s %6s %8s %8s%n", 
             "Course", "A", "B", "C", "D", "F", "Total", "A%");
        for (Course c : courses) {
            ArrayList<Integer> grades = c.getCourseGrades();
            System.out.printf("%-10s %6d %6d %6d %6d %6d %8d %7.2f%%%n", c.getCourseName(), grades.get(0),
            grades.get(1), grades.get(2), grades.get(3), grades.get(4), c.getTotalGrades(), c.getAPercent());// Grades A, B, C, D, F
        }

    }

    //Step 5 Best Course
    static void highestPercent() {
        if (courses.isEmpty()) {
            return;
        }

        Course bestCourse = courses.get(0);
        double bestPercent = bestCourse.getAPercent();

        for(Course c : courses) {
            double percent = c.getAPercent();
            if(percent > bestPercent){
                bestPercent = percent;
                bestCourse = c;
            }
        }
        System.out.println("Best Course: " + bestCourse.getCourseName());
        System.out.println("A%: " + bestCourse.getAPercent());
        System.out.println("Total: " + bestCourse.getTotalGrades());
        ArrayList<Integer> grades = bestCourse.getCourseGrades();
        System.out.printf("A:%d B:%d C:%d D:%d F:%d\n", grades.get(0), grades.get(1), grades.get(2), grades.get(3), grades.get(4));

        
    }
     // percents

    //Step 6 Best Course

    static void searchCourse(){
        try (Scanner scnr = new Scanner(System.in)) {
             while (true) {
                System.out.print("Enter course name (or 'quit' to exit): ");
                String searchName = scnr.nextLine();

                if (searchName.equalsIgnoreCase("quit")) {
                    break;
                }

                boolean find = false;

                for(Course c : courses){
                    if(c.getCourseName().equalsIgnoreCase(searchName)){
                        ArrayList<Integer> grades = c.getCourseGrades();
                        System.out.println("A: " + grades.get(0));
                        System.out.println("B: " + grades.get(1));
                        System.out.println("C: " + grades.get(2));
                        System.out.println("D: " + grades.get(3));
                        System.out.println("F: " + grades.get(4));
                        System.out.println("Total: " + c.getTotalGrades());
                        System.out.printf("A%%: %.2f%%\n", c.getAPercent());
                        find = true;
                    break;
                    }
                }
                if(!find){
                    System.out.println("No course" + searchName);
                }

             }
        }
    }
}//end Main