package org.example;

import processing.core.*;

import java.util.ArrayList;

public class Main extends PApplet {
    public static PApplet processing;
    PImage bg;
    PImage spaceshipImg;
    PImage chickenImg;
    ArrayList<Chicken> chickens;
    ArrayList<Bullet> bullets;
    Ship ship;
    int score = 0;
    float time = 400;
    float changeTime = 0.1F;
    PImage background;

    boolean gameStarted = false;
    boolean gameExited = false;
    String chickenImg2 = "chickenImg2.png";
    String chickenImg3 = "chickenImg3.jpg";

    @Override
    public void setup() {
        processing = this;
        background = loadImage("SpaceBackground.jpg");
        bg = loadImage("ChickenBackground.jpg");
        spaceshipImg = loadImage("spaceship.png");
        chickenImg = loadImage("chickenImg1.png");
        ship = new Ship(width / 2, height - 50, spaceshipImg);
        chickens = new ArrayList<Chicken>();
        bullets = new ArrayList<Bullet>();

    }

    @Override
    public void settings() {
        size(400, 700);
    }

    @Override
    public void draw() {
        if (score > 10 && score <= 20)
            chickenImg = loadImage(chickenImg2);
        else if (score > 20)
            chickenImg = loadImage(chickenImg3);

        if (!gameStarted)
            drawMenu();
        else if (gameExited)
            exit();
        else {
            background(background);
            ship.update(mouseX);
            ship.show();
            if (frameCount % 60 == 0)
                chickens.add(new Chicken(chickenImg));
            chickensStep();
            for (int i = bullets.size() - 1; i >= 0; i--) {
                Bullet bullet = bullets.get(i);
                bullet.update();
                bullet.show();
                if (bullet.offScreen())
                    bullets.remove(i);
            }

            textAlign(LEFT);
            textSize(20);
            fill(255);
            text("Score: " + score, 10, 30);

            time -= changeTime;
            if (time > 75) {
                stroke(0, 255, 0);
                strokeWeight(10);
                line(0, 680, time, 680);
            } else {
                stroke(255, 0, 0);
                strokeWeight(10);
                line(0, 680, time, 680);
            }

            if (score == 50 && time > 0)
                win();
            noStroke();
        }
    }

    private void win() {
        textSize(60);
        textAlign(CENTER, CENTER);
        fill(50, 255, 50);
        text("WIN!", width / 2, height / 2);
        noLoop();
    }

    void drawMenu() {
        background(bg);
        textSize(50);
        textAlign(CENTER, CENTER);
        fill(255, 255, 0);
        text("Chicken Invaders", width / 2, height / 2 - 50);
        textSize(35);
        text("Press Enter to start", width / 2, height / 2 + 50);
        text("Press Shift to exit", width / 2, height / 2 + 100);
    }

    @Override
    public void keyPressed() {
        if (!gameStarted && keyCode == ENTER)
            gameStarted = true;
        else if (keyCode == SHIFT)
            gameExited = true;

    }

    @Override
    public void mousePressed() {
        if (gameStarted)
            bullets.add(new Bullet(ship.x, height - 50));
    }

    void gameOver() {
        textSize(60);
        textAlign(CENTER, CENTER);
        fill(255, 10, 50);
        if (time <= 0)
            text("TIME OVER", width / 2, height / 2 - 100);
        else
            text("GAME OVER", width / 2, height / 2 - 100);
        text("SCORE : " + score, width / 2, height / 2);
        noLoop();
    }

    void chickensStep() {
        for (int i = chickens.size() - 1; i >= 0; i--) {
            Chicken c = chickens.get(i);
            c.update();
            c.show();
            if (c.hits(ship) || time <= 0)
                gameOver();
            for (int j = bullets.size() - 1; j >= 0; j--) {
                Bullet bullet = bullets.get(j);
                if (bullet.hits(c)) {
                    bullets.remove(j);
                    chickens.remove(i);
                    score++;
                }
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("org.example.Main", args);
    }
}