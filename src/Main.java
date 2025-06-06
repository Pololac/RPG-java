import personnages.Ennemi;
import personnages.Hero;
import services.CombatManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        CombatManager CM = new CombatManager();
        int nbrEnnemisVaincus = 0;

        System.out.println("Bienvenue. Rentre le nom de ton personnage : ");
        Hero hero = new Hero(sc.nextLine());

        do {
            System.out.println("Prêt à commencer ta quête " + hero.getNom() + " ?!? ");
            System.out.println("Let's go !");

            while (hero.estVivant()) {
                System.out.println(hero);
                Ennemi ennemi = CM.genererEnnemiAleatoire();
                CM.afficherEnnemi(ennemi);

                //Pour chaque tour
                while (hero.estVivant() && ennemi.estVivant()) {
                    // 1 tour
                    CM.afficherChoix();
                    int choix = sc.nextInt();
                    sc.nextLine();
                    CM.executerChoix(choix, hero, ennemi);
                }

                if (!ennemi.estVivant()) {
                    System.out.println("Bravo ! Tu as vaincu le " + ennemi.getNom());
                    nbrEnnemisVaincus++;
                }
                if (!hero.estVivant()) {
                    System.out.println("Malheureusement, tu as été tué par le " + ennemi.getNom());
                    System.out.println("Tu as tué " + nbrEnnemisVaincus + " ennemis.");
                }
            }

            System.out.println();
            System.out.println("===== FIN DE LA PARTIE ======");
            System.out.println();

            // Proposition de rejouer
            System.out.println("Veux-tu rejouer ? (OUI/NON)");
            String reponse = sc.nextLine().trim().toUpperCase();

            // Sortir
            if (!reponse.equals("OUI")) {
                System.out.println("À bientôt !");
                break;
            }

            // Réinitialiser le héros si nouvelle partie
            System.out.println("Quel est le nom de ton nouveau héros ?");
            hero = new Hero(sc.nextLine());
            nbrEnnemisVaincus = 0;

        } while (true);
    }
}