package personnages;

public class Ennemi extends Personnage {
    public Ennemi(String nom, int pv, int attaque, int defense) {
        super(nom, pv, attaque, defense);
    }

    @Override
    public String toString() {
        return this.getNom() + " | ❤\uFE0F " + pv + ", \uD83D\uDDE1\uFE0F " + attaque + ", \uD83D\uDEE1\uFE0F " + defense + "\n";
    }
}
