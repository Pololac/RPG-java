
import services.JeuManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        JeuManager jm = new JeuManager(sc);

        // Accueil du joueur
        jm.accueillirJoueur();

        do {
            jm.lancerPartie();
        } while (jm.demanderRejouer());

    sc.close();
    }
}