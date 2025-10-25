import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Locale locale = Locale.getDefault();

        switch (locale.getLanguage()) {
            case "ro" -> ROGame.playGame();
            default -> ENGame.playGame();
        }
    }
}