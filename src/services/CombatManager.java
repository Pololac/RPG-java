package services;

import personnages.*;
import java.util.Random;


public class CombatManager {

    // Génération d’un ennemi aléatoire (creerEnnemi())
    public static Ennemi genererEnnemiAleatoire() {
        Random rand = new Random();
        int tirage = rand.nextInt(3) + 1;
        switch (tirage) {
            case 1: return new Gobelin();
            case 2: return new Troll();
            case 3: return new Dragon();
            default: return new Gobelin(); // sécurité
        }
    }

    // Lancer le combat (Pour chaque tour)
        // Afficher l'ennemi
    public static void afficherEnnemi(Ennemi ennemi) {
        System.out.println("===================");
        System.out.println("Un " + ennemi.getNom() + " est face à toi. ");
        System.out.println("===================");
    }

        // Afficher console de choix
    public static void afficherChoix() {
        System.out.println("Que souhaites-tu faire ?");
        System.out.println("1. Attaquer");
        System.out.println("2. Utiliser le mana");
        System.out.println("3. Utiliser une potion de guérison");
        System.out.println("4. Afficher tes paramètres");
        System.out.println("Rentre le numéro de ton choix : ");
    }

        // Appliquer le choix
    public static void executerChoix(int choix, Hero hero, Ennemi ennemi) {
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
                if (ennemi.estVivant()) {
                    ennemi.attaquer(hero);
                }
                break;
            case 4:
                System.out.println(hero);
                break;
            default:
                System.out.println("Choix invalide !");
        }
    }


        // Gestion des tours (qui attaque, quand)


        // Gestion des potions / pouvoirs

        // Affichage des informations (si centralisation des messages console ici)


    // A la fin de chaque tour, vérification de la mort d'un des combattants
        // Si Ennemi est mort -> Victoire -> Passer au tour d'après
        // Si Hero est mort -> Défaite -> Ecrire le nombre d'ennemis vaincus et sauvegarder le score








}
