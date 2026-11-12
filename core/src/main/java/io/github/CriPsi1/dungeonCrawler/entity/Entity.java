package io.github.CriPsi1.dungeonCrawler.entity;
import sun.swing.StringUIClientPropertyKey;

import static java.lang.Math.sqrt;

public abstract class Entity{
    protected float x;
    protected float y;
    protected float size;
    private int hp;
    private final int maxHp;
    private final int maxActionPoints;
    private int actionPoints;
    private int defense;
    private int attackBonus;
    private int movementRange;
    private int remainingMovement;

    protected Entity(float x, float y, float size, int maxHp, int maxActionPoints, int attackBonus, int defense,
                     int movementRange)
    {
        this.x = x;
        this.y = y;
        this.size = size;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.maxActionPoints = maxActionPoints;
        this.actionPoints = maxActionPoints/2;
        this.attackBonus = attackBonus;
        this.defense = defense;
        this.movementRange = movementRange;
        this.remainingMovement = movementRange;

    }

    // list of getter function
    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getSize()
    {
        return size;
    }

    public float getCenterX() {
        return x+size/2;
    }

    public float getCenterY() {
        return y+size/2;
    }

    public int getMaxHp()
    {
        return maxHp;
    }

    public int getCurrentHp()
    {
        return hp;
    }

    public int getAttackBonus()
    {
        return attackBonus;
    }

    public int getDefense()
    {
        return defense;
    }

    public int getMovementRange() {
        return movementRange;
    }

    public int getRemainingMovement() {
        return remainingMovement;
    }

    // function for calculation distance to enemy
    public float distanceTo (Entity other)
    {
        float dX = this.getCenterX() - other.getCenterX();
        float dY = this.getCenterY() - other.getCenterY();
        return (float)sqrt(dX * dX + dY * dY);
    }

    // function for dmg take
    public void takeDmg (int dmg)
    {
        if (dmg <= 0)
        {
            return;
        }
        hp -= dmg;

        if (hp < 0)
        {
            hp = 0;
        }
    }

    // check if entity is alive
    public boolean isAlive ()
    {
        return hp > 0;
    }

    // function for the get current points
    public int getCurrentPoints() {
        return actionPoints;
    }

    // function for recover points
    public int recoverPoints(){
        actionPoints = getCurrentPoints() + maxActionPoints/2;
        if (maxActionPoints < actionPoints)
        {
            actionPoints = maxActionPoints;
        }
        return actionPoints;
    }

    // check the posibility to the spend points
    public boolean tryToSpendActionPoints(int cost)
    {
        if (cost < 0 || getCurrentPoints() < cost)
        {
            return false;
        }
        actionPoints -= cost;
        return true;
    }

    // function for restore movement
    public int restoreMovement(){
        return remainingMovement = movementRange;
    }

    public boolean tryToSpendMovement(int cells)
    {
        if(cells < 0 || getRemainingMovement() < cells)
        {
            return false;
        }
        remainingMovement -= cells;
        return true;
    }

    public void placeAtCell(int row, int column, int tileSize)
    {
        this.x = column*tileSize + (tileSize - size)/2;
        this.y = row*tileSize + (tileSize - size)/2;
    }
}
