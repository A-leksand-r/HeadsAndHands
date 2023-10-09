package Creatures;

import java.util.Random;

public class Creature implements CreatureImpl{

    private final String name;

    protected final int minDamage;

    private final int maxDamage;

    private final int attack;

    private final int protection;

    private int health;

    private int numberOfHealing = 4;

    public Creature(String name, int minDamage, int maxDamage, int attack, int protection) {
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.attack = attack;
        this.protection = protection;
        this.health = maxHealth;
    }

    public void heal() {
        if (numberOfHealing > 0) {
            health = health + (int) Math.round(0.3 * maxHealth);
            if (health > maxHealth) health = maxHealth;
            numberOfHealing--;
            System.out.println("Использовано исцеление, осталось: " + numberOfHealing + "\n" + this);
        }
        else System.out.println("Исцеления кончились");
    }

    public void makeAHit(Creature creature) {
        if (this == creature) {
            System.out.println("Нельзя ударить самого себя");
        }
        else {
            Random random = new Random();
            int modifierAttack = Math.max(attack - creature.protection + 1, 1);
            if (success(modifierAttack)) {
                int hitDamage = random.nextInt(this.minDamage, this.maxDamage + 1);
                System.out.println(this.name + ": Нанесен удар, " + hitDamage + " урона существу " + creature.name);
                creature.setHealth(creature.getHealth() - hitDamage);
            }
            else System.out.println(this.name + ": Промах по существу " + creature.name);
            System.out.println(creature);
        }
    }

    private boolean success(int modifierAttack) {
        for (int i = 0; i < modifierAttack; i++) {
            if ((int)(Math.random() * 6 + 1) == 5 || (int)(Math.random() * 6 + 1) == 6) return true;
        }
        return false;
    }

    private void death() {
        System.out.println(this.name + " погиб");
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health > 0) this.health = health;
        else {
            this.health = 0;
            death();
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return this.name + ": " + this.health + " hp";
    }
}
