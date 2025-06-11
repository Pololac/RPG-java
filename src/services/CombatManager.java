package services;

import exceptions.ManaInsuffisantException;
import exceptions.PotionIndisponibleException;
import personnages.*;
import java.util.Random;
import java.util.Scanner;
import exceptions.ChoixInvalideException;


public class CombatManager {
    private final Scanner scanner;

    public CombatManager(Scanner scanner) {
        this.scanner = scanner;
    }

    // Lancer le combat (Pour chaque tour)
    public boolean lancerCombat(Hero hero) {
        Ennemi ennemi = genererEnnemiAleatoire();

        String emoji = "";
        switch (ennemi.getNom()) {
            case "Troll":
                emoji = "\uD83E\uDDCC"; // 🧌
                break;
            case "Gobelin":
                emoji = "\uD83D\uDD77\uFE0F"; // 🕷️
                break;
            case "Dragon":
                emoji = "\uD83D\uDC09"; // 🐉
                break;
            default:
                emoji = "\u2753"; // ❓ inconnu
        }

        System.out.println("\n " + emoji + " Un " + ennemi.getNom() + " apparaît !");

        // Ajout d'une temporisation
        try {
            Thread.sleep(3000); // pause de 0.5s
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        while (hero.estVivant() && ennemi.estVivant()) {
            // 1 tour
            afficherEtat(hero, ennemi);
            // Ajout d'une temporisation
            try {
                Thread.sleep(1000); // pause de 0.5s
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            afficherChoix();
            int choix = scanner.nextInt();
            scanner.nextLine();
            try {
                executerChoix(choix, hero, ennemi);
            } catch (ChoixInvalideException e) {
                System.out.println("❌ Erreur : " + e.getMessage());
            }

            // Ajout d'une temporisation
            try {
                Thread.sleep(500); // pause de 0.5s
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return hero.estVivant();
    }


    // Génération d’un ennemi aléatoire
    private Ennemi genererEnnemiAleatoire() {
        Random rand = new Random();
        int tirage = rand.nextInt(3) + 1;
        switch (tirage) {
            case 1: return new Gobelin();
            case 2: return new Troll();
            case 3: return new Dragon();
            default: return new Gobelin();
        }
    }

    // Afficher état hero et ennemi
    private void afficherEtat(Hero hero, Ennemi ennemi) {
        System.out.println("\n--- État actuel ---");
        System.out.println(hero);
        System.out.println(ennemi);
    }

    // Afficher console de choix
    public void afficherChoix() {
        System.out.println("Que souhaites-tu faire ?");
        System.out.println("1. ⚔\uFE0F Attaquer");
        System.out.println("2. \uD83D\uDD25 Utiliser le mana");
        System.out.println("3. \uD83E\uDDEA Utiliser une potion de guérison");
        System.out.println("4. Afficher tes paramètres");
        System.out.println("Rentre le numéro de ton choix : ");
    }

        // Appliquer le choix
    public void executerChoix(int choix, Hero hero, Ennemi ennemi) throws ChoixInvalideException {
        try {
            switch (choix) {
                case 1:
                    hero.attaquer(ennemi);
                    if (ennemi.estVivant()) {
                        ennemi.attaquer(hero);
                    }
                    break;
                case 2:
                    hero.utiliserPouvoir(ennemi);
                    if (ennemi.estVivant()) {
                        ennemi.attaquer(hero);
                    }
                    break;
                case 3:
                    hero.utiliserPotion();
                    break;
                case 4:
                    System.out.println(hero);
                    break;
                default:
                    throw new ChoixInvalideException("Choix invalide durant le combat. Rentre l'un des nombres donnés.");
            }
        } catch (ManaInsuffisantException | PotionIndisponibleException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

}
