package io.github.CriPsi1.dungeonCrawler.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Player {

    private float x = 200f;
    private float y = 100f;
    private float size = 48f;
    private float speed = 120f;

    // update player information
    public void update (float delta)
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

        // update position
        x += speed * delta * directionX;
        y += speed * delta * directionY;

        // borderCheck
        if (x < 0 )
        {
            x = 0;
        }

        if (x > Gdx.graphics.getWidth() - size)
        {
             x = Gdx.graphics.getWidth() - size;
        }

        if (y < 0)
        {
            y = 0;
        }

        if (y > Gdx.graphics.getHeight() - size)
        {
            y = Gdx.graphics.getHeight() - size;
        }
    }

    // need to get information
    public float getX ()
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
}
