package org.example;
import processing.core.*;
import processing.core.PApplet;

public class Ship {
    private static PApplet a = Main.processing;

    float x, y; //represent the position of the ship
    float w = 50, h = 50; //represent the width and height of the ship
    PImage img; //represents the image used to draw the ship

    Ship(float x, float y, PImage img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    //the update method takes a parameter x, which represents the new x-coordinate of the ship
    void update(float x) {
        this.x = x;
    }

    //draw the ship image at its current position (x & y) with the specified width and height (w & h)
    void show() {
        //sets the image alignment mode to the center of the image
        a.imageMode(a.CENTER);
        a.image(img, x, y, w, h);
    }
}
