package personnages;

import interfaces.PouvoirSpecial;

public class Ennemi extends Personnage {
    public Ennemi(String nom, int pv, int attaque, int defense) {
        super(nom, pv, attaque, defense);
    }

    @Override
    public String toString() {
        return this.getNom() + " | PV " + pv + "\n";
    }
}
