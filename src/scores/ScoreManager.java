package scores;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ScoreManager {
    private List<ScoreEntry> scores = new ArrayList<>();
    private static final String FICHIER_SCORES = "scores.txt";

    // Enregistrement du score dans fichier txt
    public void enregistrerScore(String nom, int ennemisVaincus) {
        ScoreEntry entry = new ScoreEntry(LocalDateTime.now(), nom, ennemisVaincus);
        scores.add(entry);

        try (FileWriter writer = new FileWriter(FICHIER_SCORES, true)) {
            writer.write(entry.toString() + "\n");
            System.out.println("✅ Score sauvegardé.");
        } catch (IOException e) {
            System.out.println("❌ Erreur lors de l'enregistrement du score.");
        }
    }

    public void afficherLeaderboard() {
        System.out.println("===== LEADERBOARD =====");
        try {
            List<String> lignes = Files.readAllLines(Paths.get(FICHIER_SCORES));
            List<ScoreEntry> scores = lignes.stream()
                    .map(ScoreEntry::fromFormattedString)
                    .filter(Objects::nonNull)
                    .sorted(Comparator.comparingInt(ScoreEntry::getEnnemisVaincus).reversed())
                    .limit(5)
                    .toList();

            for (ScoreEntry entry : scores) {
                System.out.println(entry);
            }

        } catch (IOException e) {
            System.out.println("❌ Impossible de lire les scores.");
        }
        System.out.println("==================");
    }

}
