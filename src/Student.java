public class Student {
    // variables for student name , ID and module marks
    private String studentID;
    private String studentName;
    private static Modules module;

    // constructor for student name , ID and module marks
    public Student(String studentID, String studentName, Modules module) {
        this.studentID = studentID;
        this.studentName = studentName;
        Student.module = module;
    }

    // Getter for student ID
    public String getStudentID() {
        return studentID;
    }

    // Setter for student ID
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    // Getter for student name
    public  String getStudentName() {
        return studentName;
    }

    // Getter for marks of modules
    public Modules getModule() {
        return module;
    }

    // setter for student name
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return studentID + " " + studentName + " " + module;
    }

    // Getter for summary details of students
    public String getSummaryDetails() {
        return studentID + ", " + studentName + ", " + module.getModule1_marks() + ", " + module.getModule2_marks() + ", " + module.getModule3_marks() + ", " + getTotal() + ", " + getAverage() + ", " + module.getGrade();
    }

    // Getter for total marks of modules of each student
    public static int getTotal() {
        return module.getModule1_marks() + module.getModule2_marks()+ module.getModule3_marks();
    }

    // Getter for average marks of each student
    public static double getAverage() {
        return getTotal() / 3.0;
    }




}