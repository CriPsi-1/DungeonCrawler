// for start: .\gradlew.bat lwjgl3:run
package io.github.CriPsi1.dungeonCrawler;
import io.github.CriPsi1.dungeonCrawler.player.Player;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class DungeonCrawler extends ApplicationAdapter {
    private Player player;
    private ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        player = new Player();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        float delta = Gdx.graphics.getDeltaTime();
        player.update(delta);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1f,0f,0f,1f);
        shapeRenderer.rect(player.getX(), player.getY(), player.getSize(), player.getSize());
        shapeRenderer.end();

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
