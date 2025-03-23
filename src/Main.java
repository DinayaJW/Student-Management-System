import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    static Student[] students = new Student[100]; //Array to store student list
    static int studentsCount = 0;
    static Scanner scan = new Scanner(System.in);

    //Method to display main menu of Student Activity Management System
    public static int mainMenu() {
        System.out.println("Student Activity Management System");
        System.out.println("1. Check Available Seats");
        System.out.println("2. Register Student");
        System.out.println("3. Delete Student ");
        System.out.println("4. Find Student ");
        System.out.println("5. Store Student details into a File");
        System.out.println("6. Load Student Details From the File to the System");
        System.out.println("7. View the list of Students");
        System.out.println("8. Additional controls");
        System.out.print("Enter your choice: ");
        return scan.nextInt();
    }

    //Method to check available seats for students
    public static void checkAvailableSeats() {
            System.out.println((100 - studentsCount )+ " Seats Are Available in the System");
    }

    //Method to register students in the system
    public static void registerStudent() {
        if (studentsCount >= 100){
            // checking if the availability of seats has exceeded the limit
            System.out.println("Exceeded the Limit. Can't be registered");
            return;
        }
        System.out.print("Enter Student ID: ");
        String studentID = scan.next();
        System.out.print("Enter Student Name: ");
        String studentName = scan.next();
        int[] moduleMarks = new int[3];
        for (int i = 0; i < 3; i++) {
            System.out.println("Enter Module " + (i + 1) + " Marks");
            moduleMarks[i] = scan.nextInt();
        }   //creates an object with marks entered
            Modules module = new Modules(moduleMarks[0], moduleMarks[1], moduleMarks[2]);
            students[studentsCount] = new Student(studentID, studentName, module); //creates a student object
            studentsCount++;
            System.out.println("Student Details has registered Successfully");

    }

    // Method to delete students in the system
    public static void deleteStudent() {
        System.out.println("Enter Student ID: ");
        String studentID = scan.next();
            for (int i = 0; i < studentsCount; i++) {
                if (students[i] !=null &&students[i].getStudentID().equals(studentID)) {
                    for(int j =i+1;j<studentsCount;j++){
                        students[j]=students[j+1];
                    }
                    students[--studentsCount]=null;
                    System.out.println("Student Details has deleted Successfully");
                    return;
                }
            }
            System.out.println("Student Not Found");
        }

    //Method to find students registered in the system
    public static Student findStudent (String studentID) {
        for (int i = 0; i < studentsCount; i++)
            if (students[i] != null && students[i].getStudentID().equals(studentID)) {
                return students[i];
            }
        return null;
        }

    //Method to store student details in a file studentDetails.txt
    public static void storeStudent() {
        try {
            FileWriter file = new FileWriter("studentsDetails.txt"); //Generate FileWriter to write details in to the file
            for (int i = 0; i < studentsCount; i++) {
                if (students[i] != null) {
                    file.write(students[i].getStudentID() + " ");
                    file.write(students[i].getStudentName()+" ");
                    Modules module = students[i].getModule();
                    file.write(module.getModule1_marks() + " " + module.getModule2_marks() + " " + module.getModule3_marks() + "\n");
                }
            }

            file.close();
            System.out.println("Details stored successfully");
        }catch(IOException e) {
            System.out.println("Error while writing to the file");
        }
    }

    //Method to load student details from the file studentDetails.txt
    public static void loadStudent() {
        try{
            File file = new File("studentsDetails.txt"); //creates a file object for the file to read  from
            Scanner input  = new Scanner(file);
            studentsCount = 0;
            // Read each lines from the file
            while (input.hasNext()) {
                String studentID = input.next();
                String studentName = input.next();
                int module1_marks = input.nextInt();
                int module2_marks = input.nextInt();
                int module3_marks = input.nextInt();
                Modules module = new Modules(module1_marks, module2_marks, module3_marks);
                students[studentsCount] = new Student(studentID, studentName, module);
                studentsCount++;
            }
            input.close();
            System.out.println("Student Details Loaded Successfully");
        } catch (FileNotFoundException e){
            System.out.println("Error while writing to the file");
        }
    }

    // Method to view students list
    public static void viewStudent() {
        Arrays.sort(students,0,studentsCount, Comparator.comparing(Student::getStudentName));
        for (int i = 0; i < studentsCount; i++) {
            //printing each student details
            System.out.println(students[i].getSummaryDetails());
        }
    }

    //Method to update or add new student's details
    public static void addStudentName() {
        System.out.println("Enter student ID: ");
        String studentID = scan.next();
        System.out.println("Enter student Name: ");
        String studentName = scan.next();
        Student student = findStudent(studentID);
        if (student != null) {
            student.setStudentName(studentName);
            System.out.println("Student Added");
        } else{
            System.out.println("Student Not Found");
        }
    }

    //Method to update or add new student's marks of each module
    public static void addModuleMarks(){
        System.out.println("Enter student ID: ");
        String studentID = scan.next();
        System.out.println("Enter Module 1 Mark: ");
        int module1_marks = scan.nextInt();
        System.out.println("Enter Module 2 Mark: ");
        int module2_marks = scan.nextInt();
        System.out.println("Enter Module 3 Mark: ");
        int module3_marks = scan.nextInt();
        Student student = findStudent(studentID);
        if (students != null){
            student.getModule().setModule1_marks(module1_marks);
            student.getModule().setModule2_marks(module2_marks);
            student.getModule().setModule3_marks(module3_marks);
        } else {
            System.out.println("Student Not Found");
        }
    }

    // //Method to display a summary of the system
    public static void systemSummary(){
        System.out.println("Total Student Count: " + studentsCount);
        //Displaying a counting the students who scored more than 40 in all modules
        long studentsScored40 = Arrays.stream(students,0,studentsCount)
                .filter(m-> m.getModule().getModule1_marks() > 40 && m.getModule().getModule2_marks()>40 && m.getModule().getModule3_marks()>40)
                .count();
        System.out.println("Total students who scored more than 40 marks in all modules"+studentsScored40);
    }

// Method to display report of details of students'
public static void detailedReport(){
        // sorting students by their name
        Arrays.sort(students,0,studentsCount, Comparator.comparing(Student::getStudentName));
    System.out.println(("Student ID, Student Name, Module 1 Marks, Module 2 Marks, Module 3 Marks, Total, Average, Grade"));
        for (int i=0; i<studentsCount; i++) {
            System.out.println(students[i].getSummaryDetails());
        }
}

    //Method to select additional controls
    public static void additionalControls() {
        System.out.println("a.Add a student name");
        System.out.println("b.Add module marks(1,2,3)");
        System.out.println("c.System Summary");
        System.out.println("d.Detailed Report of student performance");
        System.out.print("Select an option");
        char option = scan.next().charAt(0);
        switch (option) {
            case 'a':
                addStudentName();
                break;
            case 'b':
                addModuleMarks();
                break;
            case 'c':
                systemSummary();
                break;
            case 'd':
                detailedReport();
                break;
            default:
                System.out.println("Invalid option");

        }

    }


    // Main method of the system
    public static void main(String[] args) {
       int choice;
       do {
           choice  = mainMenu(); //printing main menu and getting user inputs
       switch (choice) {
           case 1:
               checkAvailableSeats();
               break;
           case 2:
               registerStudent();
               break;
           case 3:
               deleteStudent();
               break;
           case 4:
               String studentID = scan.next();
               Student student = findStudent(studentID);
               if (student != null) {
                   System.out.println(student.getSummaryDetails()+ "Student Found");
               } else{
                   System.out.println("Student Not Found");
               }
               break;
           case 5:
                storeStudent();
                break;
           case 6:
               loadStudent();
               break;
           case 7:
               viewStudent();
               break;
           case 8:
               additionalControls();
               break;
           case 0:
               System.out.println("Exiting program. Thank you");
               break;
           default:
               System.out.println("Please enter a valid choice");
               break;

       }
       }while (choice  != 0);
    }
    }



