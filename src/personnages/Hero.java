package personnages;

import interfaces.PouvoirSpecial;

import java.util.Objects;
import java.util.Random;

public class Hero extends Personnage implements PouvoirSpecial {
    private int pvMax = 100;
    private int mana;
    private int nbrPotions;

    //TODO
    // Ajouter une progression (XP / montée en niveau) pour que le héros devienne plus fort au fil des combats


    public Hero(String nom) {
        super(nom, 50, 12, 4);
        this.mana = 30;
        this.nbrPotions = 2;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getNbrPotions() {
        return nbrPotions;
    }

    public void setNbrPotions(int nbrPotions) {
        this.nbrPotions = nbrPotions;
    }

    //METHODES
    // Utiliser du Pouvoir : inflige plus de dégats qu'une attaque classique mais coûte du "mana"
    public void utiliserPouvoir(Personnage cible){
        int coutMana = 10;

        if (this.mana < coutMana){
            System.out.println("Désolé, mais tu n'as pas assez de mana...");
            return;
        }
        this.mana -= coutMana;

        // Appliquer un facteur "random" compris entre 1,5 et 2 sur l'attaque pour ajouter imprévisibilité
        Random r = new Random();
        double min = 1.5;
        double max = 2;
        double random = min + (max - min) * r.nextDouble();

        double degatsDouble = (this.attaque * random) - cible.defense;
        int degats = (int) Math.round(degatsDouble);

        if (degats < 0) degats = 0;
        System.out.println(this.nom + " attaque " + cible.nom + " et inflige " + degats + " dégâts !");
        System.out.println("Mana restant : " + this.mana);
        cible.prendreDegats(degats);
    }

    // Utiliser une potion : pour rajouter des points de vie
    public void utiliserPotion(){
        int ajoutPv = 20;
        if (nbrPotions == 0){
            System.out.println("Tu as déjà utilisé toutes tes potions...");
            return;
        }
        int pvAvant = this.pv;
        this.pv += ajoutPv;
        if (this.pv > this.pvMax){
            this.pv = this.pvMax;
            System.out.println("Tu a utilisé une potion et récupéré tous tes PV.");
        }
        int pvGagnes = this.pv - pvAvant;

        System.out.println("Tu a utilisé une potion et récupéré " + pvGagnes + " PV. Tu as maintenant " + this.pv + " PV.");
        nbrPotions--;
        System.out.println("Potions restantes : " + nbrPotions);

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hero hero = (Hero) o;
        return mana == hero.mana && nbrPotions == hero.nbrPotions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mana, nbrPotions);
    }

    @Override
    public String toString() {
        return nom + " | PV " + pv + ", Mana " + mana + ", Potions " + nbrPotions;
    }
}
