/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Lenovo
 */
public class Projectile {
    
    private double x;
    private double y;
    private final double xDir;
    private final double yDir;
    private final int speed = 5;
    private final int r = 5;
    private final long age;
    private final long decayTime = (long) (5*Math.pow(10, 9));
    private boolean decayed = false;
    
    public Projectile(double x,double y,double xDir, double yDir){
        this.x = x;
        this.y = y;
        this.xDir = xDir;
        this.yDir = yDir;
        age = System.nanoTime();
    }
    
    private void move(){
        // moves the projectiles until 5s have passed
        if(System.nanoTime()-age<=decayTime){
            this.x=x+xDir*speed;
            this.y=y+yDir*speed;
        }
        else{
            decayed = true;
        }
    }
    
    public boolean checkHit(Point center, int radius){
        boolean aBoolean = false;
        int dx = center.x-(int)x; // delta x
        int dy = center.y-(int)y; // delta y
        int distance = (int)Math.sqrt(dx*dx+dy*dy);
        if(distance<=r+radius)
           aBoolean = true;
        return aBoolean;
    }
    
    public boolean isDecayed(){
        return decayed;
    }
    
    public void draw(Graphics g){
        move();
        g.setColor(Color.black);
        g.fillOval((int)(x-r), (int)(y-r), r, r);
    }
}
