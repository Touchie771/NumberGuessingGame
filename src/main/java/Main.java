import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            int x = new Random().nextInt(0, 101);
            Scanner scanner = new Scanner(System.in);
            System.out.println("---Number Guessing Game---\n");
            System.out.println("x is a natural number and x < 101\n");
            while (true) {
                System.out.println("Enter your guess: ");
                int guess = scanner.nextInt();
                if (guess < x) { System.out.println("x is greater!"); continue; }
                if (guess > x) { System.out.println("x is smaller!"); continue; }
                System.out.println("You were correct!");
                break;
            }
            System.out.println("Want to play again ? (y/n)");
            // Consume trailing newline
            scanner.nextLine();
            if (!scanner.nextLine().equalsIgnoreCase("y")) break;
        }
    }
}