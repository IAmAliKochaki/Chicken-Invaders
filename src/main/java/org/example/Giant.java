package org.example;

import processing.core.*;

import java.util.ArrayList;

public class Giant extends Chicken {
    private static PApplet a = Main.processing;
    private boolean movingRight = true;
    private boolean movingDown = false;
    public int shotsRemaining = 15;
    private PImage giantImg;

    Giant(PImage img) {
        super(img);
        giantImg = a.loadImage("LoH.png");
        w = 60;
        h = 80;
        speed = 1;
        x = a.random(a.width - w);
        y = -h;
    }

    void update() {
        if (movingRight)
            x += speed;
        else
            x -= speed;

        if (x > a.width - w)
            movingRight = false;
        else if (x < 0)
            movingRight = true;

        if (movingDown)
            y += speed;

        if (y > a.height / 3) {
            movingDown = false;
            speed = 3;
        }
    }

    void show() {
        a.imageMode(a.CENTER);
        a.image(giantImg, x, y, w, h);
    }

    boolean hits(Ship ship) {
        return (y + h / 2 >= ship.y - ship.h / 2 &&
                y - h / 2 <= ship.y + ship.h / 2 &&
                x + w / 2 >= ship.x - ship.w / 2 &&
                x - w / 2 <= ship.x + ship.w / 2);
    }

    boolean hits(Bullet bullet) {
        float d = a.dist(x, y, bullet.x, bullet.y);
        return d < w / 2 + bullet.r;
    }

    void shoot(ArrayList<Bullet> bullets) {
        if (shotsRemaining > 0 && a.frameCount % 60 == 0) {
            bullets.add(new Bullet(x, y));
            shotsRemaining--;
        }
    }

    boolean isDestroyed() {
        return shotsRemaining <= 0;
    }
}