package personnages;

import exceptions.ManaInsuffisantException;
import exceptions.PotionIndisponibleException;
import interfaces.PouvoirSpecial;

import java.util.Objects;
import java.util.Random;

public class Hero extends Personnage implements PouvoirSpecial {
    private static final int PV_MAX = 70;
    public static final int AJOUT_PV_POTION = 20;

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
    public void utiliserPouvoir(Personnage cible) throws ManaInsuffisantException {
        int coutMana = 10;

        if (this.mana < coutMana){
            throw new ManaInsuffisantException("Tu n'as pas assez de mana \uD83D\uDD25 pour utiliser ton pouvoir !");
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
        System.out.println(this.nom + " utilise son pouvoir \uD83D\uDD25 et inflige " + degats + " dégâts au " + cible.nom + ".");
        System.out.println("\uD83D\uDD25 Mana restant : " + this.mana);
        cible.prendreDegats(degats);
    }

    // Utiliser une potion : pour rajouter des points de vie
    public void utiliserPotion() throws PotionIndisponibleException {
        if (nbrPotions == 0){
            throw new PotionIndisponibleException("Potion indisponible !");
        }
        int pvAvant = this.pv;
        this.pv += AJOUT_PV_POTION;
        if (this.pv > this.PV_MAX){
            this.pv = this.PV_MAX;
            System.out.println("Tu a utilisé une potion \uD83E\uDDEA et récupéré tous tes PV.");
        }
        int pvGagnes = this.pv - pvAvant;

        System.out.println("Tu a utilisé une potion \uD83E\uDDEA et récupéré " + pvGagnes + " PV. Tu as maintenant " + this.pv + " PV.");
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
        return nom + " | ❤\uFE0F " + pv + ", \uD83D\uDDE1\uFE0F " + attaque + ", \uD83D\uDEE1\uFE0F " + defense + " |  \uD83D\uDD25 " + mana + ", \uD83E\uDDEA " + nbrPotions;
    }
}
