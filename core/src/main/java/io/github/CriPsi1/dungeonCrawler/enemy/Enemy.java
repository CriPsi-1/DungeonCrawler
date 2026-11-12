package io.github.CriPsi1.dungeonCrawler.enemy;

import io.github.CriPsi1.dungeonCrawler.entity.Entity;


public class Enemy extends Entity {
    public Enemy (float x, float y, float size, int maxHp, int maxActionPoints, int attackBonus,
                  int defense, int movementRange)
    {
        super (x,y,size, maxHp, maxActionPoints, attackBonus, defense, movementRange);
    }
}
