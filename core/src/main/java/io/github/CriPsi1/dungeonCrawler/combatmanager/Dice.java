package io.github.CriPsi1.dungeonCrawler.combatmanager;
import java.util.Random;

public class Dice {
    private static final Random random = new Random();
    public static int roll(int sides)
    {
        if (sides <= 0)
        {
            throw new IllegalArgumentException("The sides has been more then 0");
        }
        int dice = random.nextInt(sides)+1;
        return dice;
    }
}
