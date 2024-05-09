/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 *
 * @author Lenovo
 */
public class Player {
    private int x,y;
    private int xDir,yDir;
    private int radius = 10;
    private boolean w,a,s,d;
    private int speed = 5;
    
    private Cannon cannon;
    
    public Player(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void keyPressed(KeyEvent key){
        if(key.getKeyCode() == KeyEvent.VK_A){
            a = true;
        }
        if(key.getKeyCode() == KeyEvent.VK_D){
            d = true;
        }
        if(key.getKeyCode() == KeyEvent.VK_W){
            w = true;
        }
        if(key.getKeyCode() == KeyEvent.VK_S){
            s = true;
        }
    }
    
    public void keyReleased(KeyEvent key){
        if(key.getKeyCode() == KeyEvent.VK_A){
            a = false;
            System.out.println("a released");
        }
        if(key.getKeyCode() == KeyEvent.VK_D){
            d = false;
            System.out.println("d released");

        }
        if(key.getKeyCode() == KeyEvent.VK_W){
            w = false;
            System.out.println("w released");
        }
        if(key.getKeyCode() == KeyEvent.VK_S){
            s = false;
            System.out.println("s released");
        }
    }
    
    
    public void move(KeyEvent key){
        System.out.println(key);
        double length = (int)Math.sqrt(Math.pow(speed, 2)+Math.pow(speed, 2));
        if(w==true && a==false && s==false && d==false){
            yDir = -speed;
            xDir = 0;
        }
        if(w==false && a==false && s==true && d==false){
            yDir = speed;
            xDir = 0;
        }
        if(w==false && a==true && s==false && d==false){
            yDir = 0;
            xDir = -speed;
            
        }
        if(w==true && a==false && s==false && d==false){
            yDir = 0;
            xDir = speed;
        }
        if(w==true && a==true && s==false && d==false){
            yDir = -(int)(speed/length);
            xDir = -(int)(speed/length);
            
        }
        if(w==true && a==false && s==false && d==true){
            yDir = -(int)(speed/length);
            xDir = (int)(speed/length);
        }
        if(w==false && a==true && s==true && d==false){
            yDir = (int)(speed/length);
            xDir = -(int)(speed/length);
        }
        if(w==false && a==false && s==true && d==true){
            yDir = (int)(speed/length);
            xDir = (int)(speed/length);
        }
        if(w==true && a==false && s==true && d==false){
            yDir = 0;
            xDir = 0;
        }
        if(w==false && a==true && s==false && d==true){
            yDir = 0;
            xDir = 0;
        }
        x = x + xDir;
        y = y + yDir;
            
    }
    
    public void draw(Graphics g){
        g.setColor(Color.blue);
        g.fillOval(x-10, y-10, 2*radius, 2*radius);
    }
}
