public class Pakuri {
    private String species;
    private int attack, defense, speed;

    public Pakuri(String species) {
        this.species = species;
        this.attack = (species.length() * 7) + 9;
        this.defense = (species.length() * 5) + 17;
        this.speed = (species.length() * 6) + 13;
    }

    public String getSpecies() {
        return species;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setAttack(int newAttack) {
        attack = newAttack;
    }

    public void evolve() {
        attack = attack * 2;
        defense = defense * 4;
        speed = speed * 3;
    }
}
