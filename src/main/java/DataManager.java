import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;

public class DataManager {

    private static final File dataFile = new File("data.yml");
    private int wins = 0;
    private int losses = 0;

    public void initialize() {
        if (!dataFile.exists()) {
            try {
                if (!dataFile.createNewFile()){
                    System.out.println("Error: could not create data file");
                }
            } catch (IOException e) {
                System.out.println("Error: could not create data file");
            }
            // Write initial contents
            save();
            return;
        }
        // If file already exists, attempt to load values
        load();
    }

    public void incrementWin() {
        this.wins++;
        save();
    }

    public void incrementLoss() {
        this.losses++;
        save();
    }

    public double getRatio() {
        if (losses == 0) {
            return wins == 0 ? 0.0 : Double.POSITIVE_INFINITY;
        }
        return (double) wins / (double) losses;
    }

    private void load() {
        try {
            List<String> lines = Files.readAllLines(dataFile.toPath());
            for (String line : lines) {
                String[] parts = line.split(":", 2);
                if (parts.length != 2) continue;
                String key = parts[0].trim().toLowerCase();
                String value = parts[1].trim();
                switch (key) {
                    case "wins":
                        wins = parseIntSafe(value);
                        break;
                    case "losses":
                        losses = parseIntSafe(value);
                        break;
                    default:
                        // ignore unknown keys like ratio, which is derived
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Warning: failed to read data file, starting with defaults.");
            wins = 0;
            losses = 0;
        }
        // Ensure file reflects current state (including ratio)
        save();
    }

    private int parseIntSafe(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void save() {
        try (PrintWriter pw = new PrintWriter(dataFile)) {
            pw.println("wins: " + wins);
            pw.println("losses: " + losses);
            double ratio = getRatio();
            String ratioStr;
            if (Double.isInfinite(ratio)) {
                ratioStr = "Infinity";
            } else {
                ratioStr = String.format("%.2f", ratio);
            }
            pw.println("wins and losses ratio: " + ratioStr);
        } catch (IOException e) {
            System.out.println("Error: could not save data file");
        }
    }
}