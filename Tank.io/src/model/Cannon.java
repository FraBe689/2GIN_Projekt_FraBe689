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
    private int length = 20;
    private ArrayList<Projectile> alProjectiles = new ArrayList<>();
    
    public Cannon(int x, int y){
        center = new Point(x, y);
    }
    
    public void rotate(int degrees){
        xDir= Math.cos(Math.toRadians(degrees));
        yDir= -Math.sin(Math.toRadians(degrees));
    }
    
    public void setDir(int mouseX, int mouseY){
        double norm = Math.sqrt(Math.pow((mouseX-center.x),2)+Math.pow(mouseY-center.y, 2));
        xDir = (mouseX-center.x)/norm;
        yDir = (mouseY-center.y)/norm;
    }
    
    private double getCannonTipX(){
        return center.getX()+length*xDir;
    }
    
    private double getCannonTipY(){
        return center.getY()+length*yDir;
    }
    
    public void shoot(){
        alProjectiles.add(new Projectile(getCannonTipX(),getCannonTipY(),xDir,yDir));
    }
    
    public boolean checkHit(ArrayList<Player> alEnemies){
        boolean aBoolean = false;
        for(int i = 0; i < alEnemies.size(); i++){
            for(int j = 0; j < alProjectiles.size(); j++) {
                if(alProjectiles.get(j).checkHit(alEnemies.get(i).getTopLeft(), alEnemies.get(i).getRadius(), alEnemies.get(i).getRadius())){
                    aBoolean = true;
                    alProjectiles.remove(j);
                }
            }
        }
        return aBoolean;
    }
    
    public void setCenter(Point center){
        this.center = center;
    }
    
    public void draw(Graphics g){
        for(int i =0; i<alProjectiles.size();i++){
            if(alProjectiles.get(i).isDecayed()){
                alProjectiles.remove(i);
            }
        }
        for(int i =0; i<alProjectiles.size();i++){
            alProjectiles.get(i).draw(g);
        }
        g.setColor(Color.gray);
        g.drawLine((int)center.getX(), (int)center.getY(), (int)getCannonTipX(), (int)getCannonTipY());
    }
}

