package me.xanecs.kurve;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends BasicGame {
    private Field field;

    public Game(String title) {
        super(title);
        field = new Field();
        field.addSnake(Input.KEY_LEFT, Input.KEY_RIGHT);
        field.addSnake(Input.KEY_A, Input.KEY_D);
        field.addSnake(Input.KEY_J, Input.KEY_L);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        field.update(gameContainer, i);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        field.draw(graphics);
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
