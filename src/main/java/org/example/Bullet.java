package org.example;
import processing.core.PApplet;
public class Bullet {
    private static PApplet a = Main.processing;
    float x, y; //represent the position of the bullet
    float r = 5; //bullet's radius
    float speed = 10; //bullet movement speedz

    Bullet(float x, float y) {
        this.x = x;
        this.y = y;
    }

    //updates the bullet's position by adding its speed to its y coordinate
    void update() {
        y -= speed;
    }

    //displays the bullet as a circle at its current position on the screen
    void show() {
        a.ellipseMode(a.CENTER);
        a.fill(217, 4, 4);
        a.ellipse(x, y, r*2, r*2);
    }

    //Checks if the bullet has left the top of the screen or not
    boolean offScreen() {
        return y+ r < 0;
    }

    boolean hits(Chicken c) {
        float d = a.dist(x, y, c.x, c.y);
        return d < r + c.w/2;
    }
}
