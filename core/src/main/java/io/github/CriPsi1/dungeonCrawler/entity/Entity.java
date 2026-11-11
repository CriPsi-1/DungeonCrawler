package io.github.CriPsi1.dungeonCrawler.entity;
import static java.lang.Math.sqrt;

public abstract class Entity{
    protected float x;
    protected float y;
    protected float size;
    private int hp;
    private final int maxHp;
    private final int maxActionPoints;
    private int actionPoints;

    protected Entity(float x, float y, float size, int maxHp, int maxActionPoints)
    {
        this.x = x;
        this.y = y;
        this.size = size;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.maxActionPoints = maxActionPoints;
        this.actionPoints = maxActionPoints/2;
    }

    // get entity position
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

    // get entity center position
    public float getCenterX() {
        return x+size/2;
    }

    public float getCenterY() {
        return y+size/2;
    }

    public float distanceTo (Entity other)
    {
        float dX = this.getCenterX() - other.getCenterX();
        float dY = this.getCenterY() - other.getCenterY();
        return (float)sqrt(dX * dX + dY * dY);
    }

    public int getMaxHp()
    {
        return maxHp;
    }

    public int getCurrentHp()
    {
        return hp;
    }

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

    public boolean isAlive ()
    {
        return hp > 0;
    }

    // function for the get current points
    public int getCurrentPoints() {
        return actionPoints;
    }

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
}
