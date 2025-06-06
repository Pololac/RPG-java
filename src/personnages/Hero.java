package personnages;

import interfaces.PouvoirSpecial;

import java.util.Objects;

public class Hero extends Personnage implements PouvoirSpecial {
    private int mana;
    private int potion;

    public Hero(String nom, int mana, int potion ) {
        super(nom, 100, 20, 5);
        this.mana = mana;
        this.potion = potion;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getPotion() {
        return potion;
    }

    public void setPotion(int potion) {
        this.potion = potion;
    }

    //METHODES
    // TODO
    public void utiliserPouvoir(Personnage cible){

    }

    // TODO
    public void utiliserPotion(){


    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hero hero = (Hero) o;
        return mana == hero.mana && potion == hero.potion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mana, potion);
    }

    @Override
    public String toString() {
        return "Hero{" +
                "mana=" + mana +
                ", potion=" + potion +
                ", nom='" + nom + '\'' +
                ", pv=" + pv +
                ", attaque=" + attaque +
                ", defense=" + defense +
                '}';
    }
}
