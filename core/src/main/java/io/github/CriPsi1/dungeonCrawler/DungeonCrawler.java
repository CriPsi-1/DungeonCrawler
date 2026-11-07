// for start: .\gradlew.bat lwjgl3:run
package io.github.CriPsi1.dungeonCrawler;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import io.github.CriPsi1.dungeonCrawler.player.Player;
import io.github.CriPsi1.dungeonCrawler.world.GameMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class DungeonCrawler extends ApplicationAdapter {
    private Player player;
    private ShapeRenderer shapeRenderer;
    private GameMap gameMap;
    private GameState gameState = GameState.EXPLORATION;

    int[][] matrix = new int[5][8];
    int tileSize = 48;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        player = new Player();
        gameMap = new GameMap(matrix, tileSize);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        //take player update speed
        float delta = Gdx.graphics.getDeltaTime();
        //Create button for swap mode
        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB))
        {
            if (gameState == GameState.EXPLORATION)
            {
                gameState = GameState.COMBAT;
            }
            else { gameState = GameState.EXPLORATION;}
            System.out.println("Current gameState: " + gameState);
        }
        // check if the player has in exploration or combat mod
        if (gameState == GameState.EXPLORATION) {player.update(delta, gameMap); }
        // create the map and tiles
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < gameMap.getRows(); i++)
        {
            for (int j = 0; j < gameMap.getColumns(); j++)
            {
                int temp = gameMap.getTile(i,j);
                if (temp == 1)
                {
                    shapeRenderer.setColor(0.5f,0.5f,0.5f,1);
                }
                else {
                    shapeRenderer.setColor(0f,0f,0f,1f);
                }
                float x = j * gameMap.getTileSize();
                float y = i * gameMap.getTileSize();
                shapeRenderer.rect(x,y,gameMap.getTileSize(), gameMap.getTileSize());
            }
        }

        // player render
            shapeRenderer.setColor(1f, 0f, 0f, 1f);
            shapeRenderer.rect(player.getX(), player.getY(), player.getSize(), player.getSize());
            shapeRenderer.end();

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
