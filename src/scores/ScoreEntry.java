package scores;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScoreEntry {
    private LocalDateTime date;
    private String nom;
    private int ennemisVaincus;

    public ScoreEntry(LocalDateTime date, String nom, int ennemisVaincus) {
        this.date = date;
        this.nom = nom;
        this.ennemisVaincus = ennemisVaincus;
    }

    public int getEnnemisVaincus() {
        return ennemisVaincus;
    }

    public String getNom() {
        return nom;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public static ScoreEntry fromFormattedString(String ligne) {
        try {
            String datePart = ligne.substring(0, 20).trim();
            String nomPart = ligne.substring(20, 35).trim();
            String scorePart = ligne.replaceAll(".*?(\\d+) ennemis vaincus", "$1");

            // Parse format "dd/MM/yyyy à HH'h'mm"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'à' HH'h'mm");
            LocalDateTime date = LocalDateTime.parse(datePart, formatter);

            int score = Integer.parseInt(scorePart);
            return new ScoreEntry(date, nomPart, score);

        } catch (Exception e) {
            System.out.println("❌ Ligne invalide : " + ligne);
            return null;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'à' HH'h'mm");
        String dateFormattee = date.format(formatter);
        return String.format("%-20s %-15s %d ennemis vaincus", dateFormattee, nom, ennemisVaincus);
    }

}
