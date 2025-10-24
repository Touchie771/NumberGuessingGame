import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        dataManager.initialize();
        while (true) {
            int x = new Random().nextInt(0, 101);
            Scanner scanner = new Scanner(System.in);
            System.out.println("---Number Guessing Game---\n");
            System.out.println("x is a natural number and x < 101\n");
            while (true) {
                System.out.println("Enter your guess: ");
                try {
                    int guess = scanner.nextInt();
                    if (guess < x) { System.out.println("x is greater!"); dataManager.incrementLoss(); continue; }
                    if (guess > x) { System.out.println("x is smaller!"); dataManager.incrementLoss(); continue; }
                    System.out.println("You were correct!");
                    dataManager.incrementWin();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number!");
                    scanner.nextLine(); // Consume invalid input
                }
            }
            System.out.println("Want to play again ? (y/n)");
            scanner.nextLine(); // consume leftover newline from nextInt
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("y")) break;
        }
    }
}