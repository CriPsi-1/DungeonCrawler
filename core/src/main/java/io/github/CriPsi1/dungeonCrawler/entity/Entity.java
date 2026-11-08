package io.github.CriPsi1.dungeonCrawler.entity;

import com.sun.org.apache.xml.internal.serializer.EncodingInfo;
import static java.lang.Math.sqrt;

public abstract class Entity{
    protected float x;
    protected float y;
    protected float size;

    protected Entity(float x, float y, float size)
    {
        this.x = x;
        this.y = y;
        this.size = size;
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

}
