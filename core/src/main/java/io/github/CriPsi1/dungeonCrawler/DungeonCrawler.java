// for start: .\gradlew.bat lwjgl3:run
package io.github.CriPsi1.dungeonCrawler;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import io.github.CriPsi1.dungeonCrawler.combatmanager.CombatManager;
import io.github.CriPsi1.dungeonCrawler.entity.Entity;
import io.github.CriPsi1.dungeonCrawler.player.Player;
import io.github.CriPsi1.dungeonCrawler.world.GameMap;
import io.github.CriPsi1.dungeonCrawler.enemy.Enemy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class DungeonCrawler extends ApplicationAdapter {
    private Player player;
    private ShapeRenderer shapeRenderer;
    private GameMap gameMap;
    private GameState gameState = GameState.EXPLORATION;
    private Enemy enemy;
    private CombatManager combatManager;

    int[][] matrix = new int[5][8];
    int tileSize = 48;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        player = new Player(128f,128f,32f, 120);
        gameMap = new GameMap(matrix, tileSize);
        enemy = new Enemy(96,48,32);
        combatManager = new CombatManager();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        //take player update speed
        float delta = Gdx.graphics.getDeltaTime();
        //Create button for switch mode
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

        // check distance in console
        if (Gdx.input.isKeyJustPressed(Input.Keys.E))
        {
            System.out.println("Distance to enemy: " + player.distanceTo(enemy));
        }

        if (player.distanceTo(enemy) <= 64f && gameState == GameState.EXPLORATION)
        {
            System.out.println("Бой начат");
            List<Entity> participants = new ArrayList<>();
            participants.add(player);
            participants.add(enemy);
            combatManager.startCombat(participants);
            gameState = GameState.COMBAT;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && gameState == GameState.COMBAT)
        {
            combatManager.nextTurn();
            if (combatManager.getCurrentParticipant() == player)
            {
                System.out.println("Player turn");
            }
            else if (combatManager.getCurrentParticipant() == enemy)
            {
                System.out.println("Enemy turn");
            }
        }


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


        // enemy render
        shapeRenderer.setColor(0f,0f,1f,1f);
        shapeRenderer.rect(enemy.getX(), enemy.getY(), enemy.getSize(), enemy.getSize());
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
