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
    private double xDir;
    private double yDir;
    private int speed = 5;
    private int r = 5;
    private long age;
    private boolean decayed = false;
    
    public Projectile(double x,double y,double xDir, double yDir){
        this.x = x;
        this.y = y;
        this.xDir = xDir;
        this.yDir = yDir;
        age = System.nanoTime();
    }
    
    private void move(int width, int height){
        
        while(System.nanoTime()-age<=5*Math.pow(10, 9)){
            this.x=x+xDir*speed;
            this.y=y+yDir*speed;
        }
        decayed = true;
    }
    
    public void draw(Graphics g, int width, int height){
        move(width, height);
        
        g.setColor(Color.white);
        g.fillOval((int)(x-r), (int)(y-r), r, r);
    }
    
    public boolean checkHit(Point topLeft, int width, int height){
        boolean aBoolean = false;
        if(!((topLeft.y+height<y-r)||(topLeft.x>x+r)||(topLeft.y>y+r)||(topLeft.x+width<x)))
           aBoolean = true;
        return aBoolean;
    }
}
