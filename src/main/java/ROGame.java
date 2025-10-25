import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class ROGame {

    public static void playGame() {
        DataManager dataManager = new DataManager();
        dataManager.initialize();
        while (true) {
            int x = new Random().nextInt(101);
            Scanner scanner = new Scanner(System.in);
            System.out.println("---Jocul de ghicit numarul---\n");
            System.out.println("x este un numar natural si x < 101\n");
            while (true) {
                System.out.println("Introduceti ghicirea dvs: ");
                try {
                    int guess = scanner.nextInt();
                    if (guess < x) { System.out.println("x este mai mare!"); dataManager.incrementLoss(); continue; }
                    if (guess > x) { System.out.println("x este mai mic!"); dataManager.incrementLoss(); continue; }
                    System.out.println("Ai ghicit!");
                    dataManager.incrementWin();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Introduceti un numar valid!");
                    scanner.nextLine(); // Consume invalid input
                }
            }
            System.out.println("Doriti sa jucati din nou ? (y/n)");
            scanner.nextLine(); // consume leftover newline from nextInt
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("y")) break;
        }
    }
}