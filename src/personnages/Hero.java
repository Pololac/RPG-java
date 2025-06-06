package personnages;

import interfaces.PouvoirSpecial;

import java.util.Objects;

public class Hero extends Personnage implements PouvoirSpecial {
    private int pvMax = 100;
    private int mana;
    private boolean potionDisponible;

    public Hero(String nom) {
        super(nom, 10, 20, 5);
        this.mana = 30;
        this.potionDisponible = true;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public boolean isPotionDisponible() {
        return potionDisponible;
    }

    public void setPotionDisponible(boolean potionDisponible) {
        this.potionDisponible = potionDisponible;
    }

    //METHODES
    // TODO
    public void utiliserPouvoir(Personnage cible){

    }

    // Utiliser potion -> Rajouter des points de vie
    public void utiliserPotion(){
        if (!potionDisponible){
            System.out.println("Tu as déjà utilisé ta potion durant ce combat...");
            return;
        }
        this.pv += 30;
        if (this.pv > this.pvMax){
            this.pv = this.pvMax;
            System.out.println("Tu a utilisé une potion et récupérer tous tes PV.");
        }
        System.out.println("Tu utilises une potion et récupères 30 PV. Tu as maintenant " + this.pv + " PV.");
        potionDisponible = false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hero hero = (Hero) o;
        return mana == hero.mana && potionDisponible == hero.potionDisponible;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mana, potionDisponible);
    }

    @Override
    public String toString() {
        return " Tes stats :\n" +
                "- PV : " + pv + "\n" +
                "- Mana : " + mana + "\n" +
                "- Potion disponible : " + (potionDisponible ? "Oui" : "Non") + "\n";
    }
}
