package org.example;

import processing.core.*;
public class Chicken {
    private static PApplet a = Main.processing;
    float x, y; //represent the position of the chicken
    float w = 50, h = 50; //represent the width and height of the chicken
    float speed = 2; //chicken movement speed
    PImage img; //represents the image used to draw the chicken

    Chicken(PImage img) {
        this.img = img;
        x = a.random(a.width - w);
        y = -h;
    }

    //updates the chicken's position by adding its speed to its y coordinate
    void update() {
        y += speed;
    }

    //draw the chicken image at its current position (x & y) with the specified width and height (w & h)
    void show() {
        //sets the image alignment mode to the center of the image
        a.imageMode(a.CENTER);
        a.image(img, x, y, w, h);
    }

    //checks if the chicken collides with the ship
    boolean hits(Ship ship) {
        return (y + h/2 >= ship.y - ship.h/2 &&
                y - h/2 <= ship.y + ship.h/2 &&
                x + w/2 >= ship.x - ship.w/2 &&
                x - w/2 <= ship.x + ship.w/2);
    }
}
