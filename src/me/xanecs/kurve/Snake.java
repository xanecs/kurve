package me.xanecs.kurve;

import org.lwjgl.util.vector.*;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.*;

public class Snake {

    private static final float HEAD_RADIUS = 5.0f;
    private static final Color HEAD_COLOR = Color.yellow;
    private static final Vector2f DEFAULT_SPEED = new Vector2f(0, 0.15f);

    private Polygon tail;
    private Vector2f headPosition;
    private Color color;
    private Circle head;
    private Vector2f speed;
    private int leftKey;
    private int rightKey;

    public Snake(Color color, Vector2f headPosition, int leftKey, int rightKey) {
        this.color = color;
        this.headPosition = headPosition;
        this.head = new Circle(headPosition.getX(), headPosition.getY(), HEAD_RADIUS);
        this.tail = new Polygon();
        tail.setClosed(false);
        this.speed = new Vector2f(DEFAULT_SPEED);
        addPoint();
        this.leftKey = leftKey;
        this.rightKey = rightKey;
    }

    private void addPoint() {
        tail.addPoint(headPosition.getX(), headPosition.getY());
    }

    public void draw(Graphics graphics) {
        graphics.setLineWidth(8.0f);
        graphics.setColor(color);
        graphics.draw(tail);
        graphics.setColor(HEAD_COLOR);
        graphics.fill(head);
    }

    public void update(GameContainer gc, int delta) {
        float phi = 0;
        Input input = gc.getInput();
        if (input.isKeyDown(leftKey)) {
            phi = 0.005f * delta;
        } else if (input.isKeyDown(rightKey)) {
            phi = -0.005f * delta;
        }

        if (phi != 0) {
            Matrix2f rotationMat = new Matrix2f();
            rotationMat.m00 = (float) Math.cos(phi);
            rotationMat.m01 = (float) -Math.sin(phi);
            rotationMat.m10 = (float) Math.sin(phi);
            rotationMat.m11 = (float) Math.cos(phi);
            Matrix2f.transform(rotationMat, speed, speed);
        }

        Vector2f speedCop = new Vector2f(speed);
        speedCop.scale(delta);
        Vector2f.add(headPosition, speedCop, headPosition);
        addPoint();
        head.setCenterX(headPosition.getX());
        head.setCenterY(headPosition.getY());
    }
}
