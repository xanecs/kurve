package me.xanecs.kurve;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends BasicGame {
    private Snake redSnake;
    private Snake blueSnake;

    public Game(String title) {
        super(title);
        redSnake = new Snake(Color.red, new Vector2f(100f, 100f), Input.KEY_LEFT, Input.KEY_RIGHT);
        blueSnake = new Snake(Color.blue, new Vector2f(100f, 200f), Input.KEY_A, Input.KEY_D);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        redSnake.update(gameContainer, i);
        blueSnake.update(gameContainer, i);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        redSnake.draw(graphics);
        blueSnake.draw(graphics);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer appGameContainer = new AppGameContainer(new Game("Kurve"));
            appGameContainer.setDisplayMode(1280, 720, false);
            appGameContainer.start();

        } catch(SlickException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
