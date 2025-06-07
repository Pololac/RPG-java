package personnages;

import java.util.Objects;
import java.util.Random;

public abstract class Personnage {
    protected String nom;
    protected int pv;
    protected int attaque;
    protected int defense;

    protected Personnage(String nom, int pv, int attaque, int defense) {
        this.nom = nom;
        this.pv = pv;
        this.attaque = attaque;
        this.defense = defense;
    }

    // GETTERS & SETTERS
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getAttaque() {
        return attaque;
    }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }


    //  METHODES
    // Attaquer = infliger des dégats à la cible
    public void attaquer(Personnage cible) {
        // Appliquer un facteur "random" compris entre 0,8 et 1,2 sur l'attaque pour limiter calculs prévisibles
        Random r = new Random();
        double min = 0.8;
        double max = 1.2;
        double random = min + (max - min) * r.nextDouble();

        double degatsDouble = (this.attaque * random) - cible.defense;
        int degats = (int) Math.round(degatsDouble);

        if (degats < 0) degats = 0;
        System.out.println("> " + this.nom + " attaque " + cible.nom + " ! Il inflige " + degats + " dégâts !");
        cible.prendreDegats(degats);
    }

    // Infliger les dégats
    public void prendreDegats(int degats) {
        this.pv -= degats;
        if (this.pv < 0) this.pv = 0;
    }

    // Voir si l'individu est vivant par rapport à ses pv
    public boolean estVivant() {
        return pv > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Personnage that = (Personnage) o;
        return pv == that.pv && attaque == that.attaque && defense == that.defense && Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, pv, attaque, defense);
    }

    @Override
    public String toString() {
        return "Personnage{" +
                "nom='" + nom + '\'' +
                ", pv=" + pv +
                ", attaque=" + attaque +
                ", defense=" + defense +
                '}';
    }
}
