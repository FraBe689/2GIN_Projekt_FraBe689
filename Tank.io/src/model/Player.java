/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
/**
 *
 * @author Lenovo
 */
public class Player {
    protected int x,y;
    protected double xDir,yDir;
    private final int radius = 15;

    protected Color color1 = new Color(8,65,92);
    protected Color color2 = new Color(98,144,200,30);
    
    private final int speed = 5;
    protected int health = 400;
    protected int maxHealth = 400;
    private final Cannon cannon;
    private long lastShot = 0;
    
    public Player(int x, int y){
        this.x = x;
        this.y = y;
        cannon = new Cannon(x, y);
    }
    
    public int getHealth(){
        return health;
    }
    
    public Point getCenter() {
        return new Point(x,y);
    }
    
    public double getxDir() {
        return xDir;
    }

    public double getyDir() {
        return yDir;
    }

    public int getSpeed() {
        return speed;
    }

    public Cannon getCannon() {
        return cannon;
    }
    
    public void move(){
        double hyp = Math.sqrt(xDir*xDir + yDir*yDir);
        if(hyp != 0) {
            x = (int)Math.round(x + xDir/hyp*speed);
            y = (int)Math.round(y + yDir/hyp*speed);
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void rotateCannon(Point mouse){
        cannon.setDir(mouse.x, mouse.y);
    }
    
    protected void addXDir(int xDir){
        this.xDir = this.xDir + xDir;
    }
    protected void addYDir(int yDir){
        this.yDir = this.yDir + yDir;
    }

    public void shoot(){
        if(System.nanoTime()-lastShot > 2*Math.pow(10, 9)){
            lastShot = System.nanoTime();
            cannon.shoot();
        }  
    }
    
    public int getRadius() {
        return radius;
    }
    
    public void loseHealth(){
        health = health -10;
    }
    
    public Point getTopLeft(){
        return new Point(x-radius, y-radius);
    }
    public void draw(Graphics g){
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        
        cannon.setCenter(new Point(x,y));
        cannon.draw(g);
        
        g.setColor(color1);
        g.fillOval(getTopLeft().x, getTopLeft().y, 2*radius, 2*radius);
        
        int gradients = 15;
        int gradientRadius = radius/gradients;
        for (int i = gradients; i > 0; i--) {
            g.setColor(color2);
            g.fillOval(getTopLeft().x+radius-i*gradientRadius, getTopLeft().y+radius-i*gradientRadius, 2*i*gradientRadius, 2*i*gradientRadius);
        }
        
        g.setColor(Color.red);
        g.drawRect(x-radius, y+radius, radius*2, 2);
        g.setColor(Color.green);
        g.drawRect(x-radius, y+radius, radius*2*health/maxHealth, 2);
        
    }
}
