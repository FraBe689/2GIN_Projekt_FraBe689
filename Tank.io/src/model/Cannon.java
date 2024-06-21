/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Cannon {
    
    private Point center;
    private double xDir = 0;
    private double yDir = -1;
    private final int length = 20;
    private final ArrayList<Projectile> alProjectiles = new ArrayList<>();
    
    public Cannon(int x, int y){
        center = new Point(x, y);
    }
    
    // rotates the cannon
    public void rotate(int degrees){
        xDir= Math.cos(Math.toRadians(degrees));
        yDir= -Math.sin(Math.toRadians(degrees));
    }
    
    // changes direction to aim at the mouse
    public void setDir(int mouseX, int mouseY){
        double norm = Math.sqrt(Math.pow((mouseX-center.x),2)+Math.pow(mouseY-center.y, 2));
        xDir = (mouseX-center.x)/norm;
        yDir = (mouseY-center.y)/norm;
    }
    
    // returns the cannon tip
    private double getCannonTipX(){
        return center.getX()+length*xDir;
    }
    private double getCannonTipY(){
        return center.getY()+length*yDir;
    }
    
    // shoots
    public void shoot(){
        alProjectiles.add(new Projectile(getCannonTipX()+5*xDir,getCannonTipY()+5*yDir,xDir,yDir));
    }
    
    // checks if enemies or player has been hit
    public void checkHit(Player player){
        for(int i = 0; i < alProjectiles.size(); i++){
                if(alProjectiles.get(i).checkHit(player.getCenter(), player.getRadius())){
                    player.loseHealth();
                    alProjectiles.remove(i);
                }
            }
    }
    
    // sets the center of the cannon
    public void setCenter(Point center){
        this.center = center;
    }
    
    // draws the cannon and it's projectiles
    public void draw(Graphics g){
        // removes projectiles that have decayed
        for(int i =0; i<alProjectiles.size();i++){
            if(alProjectiles.get(i).isDecayed()){
                alProjectiles.remove(i);
            }
        }
        // draws the remaining projectiles
        for(int i =0; i<alProjectiles.size();i++){
            alProjectiles.get(i).draw(g);
        }
        g.setColor(Color.gray);
        g.drawLine((int)center.getX(), (int)center.getY(), (int)getCannonTipX(), (int)getCannonTipY());
    }
}

