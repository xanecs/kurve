package me.xanecs.kurve;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private static final Color[] SNAKE_COLORS = {Color.blue, Color.red, Color.green, Color.pink, Color.orange, Color.cyan};

    private List<Snake> snakes;

    public Field() {
        this.snakes = new ArrayList<>();
    }

    public void addSnake(int leftKey, int rightKey) {
        snakes.add(new Snake(
                SNAKE_COLORS[snakes.size() % SNAKE_COLORS.length],
                new Vector2f(50f + 50f * snakes.size(), 100f), // Replace with random coordinates
                leftKey,
                rightKey
        ));

    }

    public void update(GameContainer gc, int delta) {
        int aliveCount = 0;
        for (Snake s : snakes) {
            s.update(gc, delta);
            for (Snake o : snakes) {
                if (s.isAlive() && s.hits(o)) {
                    s.setAlive(false);
                }
            }
            if (s.isAlive()) aliveCount++;
        }

    }

    public void draw(Graphics g) {
        for (Snake s : snakes) s.draw(g);
    }
}
