class Student {
    int rollNo;
    String name;
    Student() {
        rollNo = 0;
        name = "Not Assigned";
    }
    Student(int r, String n) {
        rollNo = r;
        name = n;
    }
    void display() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name   : " + name);

        System.out.println();
    }

    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student(101, "Ayush");
        System.out.println("Student Details using Default Constructor:");
        s1.display();
        System.out.println("Student Details using Parameterized Constructor:");
        s2.display();
    }
}