import Creatures.Creature;
import Creatures.Enum.Commands;
import Creatures.Enum.TypeOfCreatures;
import Creatures.TypeOfCreatures.Monster;
import Creatures.TypeOfCreatures.Player;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Creature monster = new Monster("Монстр", 10, 30, 5, 3);
        Creature player = new Player("Игрок", 20, 40, 4, 4);
        System.out.println("Команды действий:\n " + "   " + Commands.heal + "\n    " + Commands.makeAHit);
        System.out.println("Создание первого юнита, введите характеристики монстра через запятую: ");
        String[] parameters = in.nextLine().split(", ");
        Creature unit1 = createCreature(TypeOfCreatures.valueOf(parameters[0]), parameters[1], Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]), Integer.parseInt(parameters[4]), Integer.parseInt(parameters[5]));
        System.out.println("Создание второго юнита, введите характеристики монстра через запятую: ");
        parameters = in.nextLine().split(", ");
        Creature unit2 = createCreature(TypeOfCreatures.valueOf(parameters[0]), parameters[1], Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]), Integer.parseInt(parameters[4]), Integer.parseInt(parameters[5]));
        System.out.println("Начало игры");
        startGame(unit1, unit2);
    }

    public static Creature createCreature(TypeOfCreatures typeOfCreatures, String name, int minDamage, int maxDamage, int attack, int protection) {
        return switch (typeOfCreatures) {
            case MONSTER -> new Monster(name, minDamage, maxDamage, attack, protection);
            case PLAYER -> new Player(name, minDamage, maxDamage, attack, protection);
        };
    }

    public static void startGame(Creature monster, Creature player) {
        Random random = new Random();
        Scanner in = new Scanner(System.in);
        while (monster.getHealth() != 0 && player.getHealth() != 0) {
            if (random.nextInt(1, 3) == 1) {
                System.out.println("Ходит " + monster.getName());
                if (random.nextInt(1, 3) == 1) monster.heal();
                else monster.makeAHit(player);
            } else {
                System.out.println("Ходит " + player.getName());
                System.out.print("Выбор действия:");
                switch (Commands.valueOf(in.nextLine())) {
                    case heal -> player.heal();
                    case makeAHit -> player.makeAHit(monster);
                }
            }
        }
    }
}