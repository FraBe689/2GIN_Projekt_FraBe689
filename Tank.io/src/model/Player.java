/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
/**
 *
 * @author Lenovo
 */
public class Player {
    protected int x,y;
    protected int xDir,yDir;
    private int radius = 10;
    protected Color color1 = new Color(62,76,94);
    protected Color color2 = new Color(44,61,85);
    private final int speed = 1;
    private int health = 100;
    private Cannon cannon;
    
    public Player(int x, int y){
        this.x = x;
        this.y = y;
        cannon = new Cannon(x, y);
    }
    
    public int getxDir() {
        return xDir;
    }

    public int getyDir() {
        return yDir;
    }

    public int getSpeed() {
        return speed;
    }

    public Cannon getCannon() {
        return cannon;
    }
    
    public void move(){
        x = x + xDir;
        y = y + yDir;
    }
    
    public void rotateCannon(Point mouse){
        cannon.setDir(mouse.x, mouse.y);
    }
    
    protected void setXDir(int xDir){
        this.xDir = xDir;
    }
    protected void setYDir(int yDir){
        this.yDir = yDir;
    }

    public void shoot(){
        cannon.shoot();
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
        g2d.setStroke(new BasicStroke(2));
        cannon.setCenter(new Point(x,y));
        cannon.draw(g);
        GradientPaint gradient = new GradientPaint(x-radius, y-radius, color1, x+radius, y+radius, color2);
        g2d.setPaint(gradient);
        g2d.fillOval(getTopLeft().x, getTopLeft().y, 2*radius, 2*radius);
        g.setColor(Color.red);
        g.drawRect(x-radius, y+radius, radius*2, 2);
        g.setColor(Color.green);
        g.drawRect(x-radius, y+radius, radius*2*health/100, 2);
        
    }
}
