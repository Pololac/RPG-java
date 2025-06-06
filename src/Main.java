
import personnages.Hero;
import services.JeuManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        JeuManager jm = new JeuManager(sc);
        List<String> joueurs = new ArrayList<>();

        // Accueil du joueur
        jm.accueillirJoueur();

        do {
            jm.lancerPartie();
        } while (jm.demanderRejouer());

    sc.close();
    }
}