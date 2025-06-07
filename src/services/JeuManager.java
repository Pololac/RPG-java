package services;

import animations.MonstreAnime;
import personnages.Hero;
import scores.ScoreManager;
import java.util.Scanner;
import exceptions.ChoixInvalideException;
import ui.AsciiArt;

public class JeuManager {
    private Hero hero;
    private Scanner scanner;

    private final ScoreManager scoreManager = new ScoreManager();

    public JeuManager(Scanner scanner) {
        this.scanner = scanner;
    }

    // Accueillir le joueur
    public void accueillirJoueur() {
        System.out.println("==== DUNGEONS & DATA ==== ");
        AsciiArt.afficherHero();
        AsciiArt.afficherGobelin();
        AsciiArt.afficherDragon();

        System.out.println("Rentre le nom de ton personnage : ");
        String nom = scanner.nextLine();
        creerJoueur(nom);
        System.out.println("Bienvenue, " + hero.getNom() + " !");
        System.out.println();

        int choix;
        do {
            afficherMenu();
            choix = scanner.nextInt();
            scanner.nextLine(); // Eviter les pb avec sauts de ligne
            try {
                executerChoix(choix);
            } catch (ChoixInvalideException e) {
                System.out.println("❌ Erreur : " + e.getMessage());
            }
        } while (choix != 2);  // La boucle s'exécute tant qu'on ne lance pas la partie
    }

    // Créer le joueur et le rendre accessible aux autres classes
    public void creerJoueur(String nom) {
        this.hero = new Hero(nom);
    }

    public Hero getHero() {
        return this.hero;
    }

    private void afficherMenu() {
        System.out.println("Où veux-tu aller ?");
        System.out.println("1. Découvrir les règles");
        System.out.println("2. Débuter la partie tout de suite");
        System.out.println("3. Voir les scores");
        System.out.println("Rentre le numéro de ton choix : ");
    }

    private void afficherRegles() {
        System.out.println("===== RÈGLES DU JEU =====");
        System.out.println("Tu incarnes un héros affrontant des ennemis au tour par tour.");
        System.out.println("À chaque combat, tu peux attaquer, utiliser un pouvoir (mana) qui te permet d'augmenter la force de ton attaque, ou te soigner à l'aide d'une potion.");
        System.out.println("Ton objectif est de vaincre autant d'ennemis que possible avant de mourir.");
        System.out.println("Bonne chance !");
        System.out.println("=========================");

        try {
            Thread.sleep(10000); // pause de 10s
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    // Appliquer le choix
    public void executerChoix(int choix) throws ChoixInvalideException {
        switch (choix) {
            case 1:
                afficherRegles();
                break;
            case 2:
                break; // partie déjà lancer dans le Main
            case 3:
                scoreManager.afficherLeaderboard();
                break;
            default:
                throw new ChoixInvalideException("Choix invalide. Rentre 1, 2 ou 3.");
        }
    }

    public void lancerPartie() {
        CombatManager cm = new CombatManager(scanner);
        int ennemisVaincus = 0;

        while (hero.estVivant()) {
            System.out.println("\nUn nouveau combat commence !");

            boolean herosToujoursEnVie = cm.lancerCombat(hero);

            if (herosToujoursEnVie) {
                ennemisVaincus++;
                System.out.println("✅ Tu as survécu au combat !");
            } else {
                System.out.println("☠️ Le héros est mort pendant le combat.");
                break;
            }
        }
        System.out.println("🏁 Fin de la partie. Ennemis vaincus : " + ennemisVaincus);
        scoreManager.enregistrerScore(hero.getNom(), ennemisVaincus);

    }

    // Proposition de rejouer
    public boolean demanderRejouer() {
        while (true) {
            System.out.println("Veux-tu rejouer ? (OUI/NON)");
            String reponse = scanner.nextLine().trim().toUpperCase();

            if (reponse.equals("OUI")) {
                System.out.println("Quel est le nom de ton nouveau héros ?");
                String nom = scanner.nextLine();
                hero = new Hero(nom);
                return true;
            } else if (reponse.equals("NON")) {
                System.out.println("À bientôt !");
                System.out.println("==== FIN DE PARTIE ====");
                return false;
            } else {
                System.out.println("❌ Réponse invalide. Merci de répondre par OUI ou NON.");
            }
        }
    }

}
