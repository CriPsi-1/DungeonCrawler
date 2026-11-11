package io.github.CriPsi1.dungeonCrawler.player;
import io.github.CriPsi1.dungeonCrawler.entity.Entity;
import io.github.CriPsi1.dungeonCrawler.world.GameMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Player extends Entity {
private float speed;
    public Player (float x,float y, float size, float speed, int maxHp, int maxActionPoints)
    {
        super(x,y,size,maxHp, maxActionPoints);
        this.speed = speed;
    }

    // update player information
    public void update (float delta, GameMap gameMap)
    {
        //controlling
        float directionX = 0f;
        float directionY = 0f;
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            directionX += 1f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A))
        {
            directionX -= 1f;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W))
        {
            directionY += 1f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S))
        {
            directionY -= 1f;
        }

        // check diagonal direction and fixing it
        if (directionX!= 0 && directionY!=0)
        {
            float diagonalLength = (float) Math.sqrt(2);
            directionX /= diagonalLength;
            directionY /= diagonalLength;
        }

        // predict possition
        float nextX = x + speed * delta * directionX;
        float nextY = y + speed * delta * directionY;

        // update position
        if (gameMap.canMoveTo(nextX,y,size))
        {
            x = nextX;
        }
        if (gameMap.canMoveTo(x, nextY, size))
        {
            y = nextY;
        }
    }
}
