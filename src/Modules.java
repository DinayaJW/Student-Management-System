public class Modules {
    // Variables to store each module marks
    private int module1_marks;
    private int module2_marks;
    private int module3_marks;


    // constructor to initialize module marks
    public Modules(int module1_marks, int module2_marks, int module3_marks) {
        this.module1_marks = module1_marks;
        this.module2_marks = module2_marks;
        this.module3_marks = module3_marks;
    }

    // Getter for module 1 mark
    public int getModule1_marks() {
        return module1_marks;
    }

    // Setter for module 1 mark
    public void setModule1_marks(int module1_marks) {
        this.module1_marks = module1_marks;
    }

    // Getter for module 2 mark
    public int getModule2_marks() {
        return module2_marks;
    }

    // Setter for module 2 mark
    public void setModule2_marks(int module2_marks) {
        this.module2_marks = module2_marks;
    }

    // Getter for module 3 mark
    public int getModule3_marks() {
        return module3_marks;
    }

    // Setter for module 3 mark
    public void setModule3_marks(int module3_marks) {
        this.module3_marks = module3_marks;
    }

    // Method to assign grade based on the average of 3 module marks
    public String getGrade() {
        double average = Student.getAverage();
        if (average >= 80) {
            return "Distinction";
        } else if (average >= 70) {
            return "Merit";
        } else if (average >= 40) {
            return "Pass";
        } else {
            return "Fail";
        }
    }

    @Override
    public String toString() {
        return "Module 1:" + module1_marks + "Module 2:" +  module2_marks + "Module 3:" +  module3_marks;
    }
}


