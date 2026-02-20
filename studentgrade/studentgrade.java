import java.util.InputMismatchException;
import java.util.Scanner;

public class studentgrade {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("Enter name: ");
            String n = sc.nextLine();

            System.out.print("Enter marks: ");
            int m = sc.nextInt();

            if (m < 0 || m > 100) {
                throw new IllegalArgumentException("Marks must be between 0 and 100");
            }

            String g;

            if (m >= 90) g = "A";
            else if (m >= 75) g = "B";
            else if (m >= 60) g = "C";
            else if (m >= 40) g = "D";
            else g = "Fail";

            System.out.println(n);
            System.out.println(m);
            System.out.println(g);
        }

        catch (InputMismatchException e) {
            System.out.println("Enter numbers only");
        }

        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        finally {
            System.out.println("Done");
        }
    }
}
