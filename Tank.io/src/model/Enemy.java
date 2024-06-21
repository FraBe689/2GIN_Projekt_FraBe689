/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author Lenovo
 */
public class Enemy extends Player{
    private final Player player;
    private final int attackRadius = 300;
    private final int minRadius = 20;
    private final Random random = new Random();
    private int pauseTimer;
    private double wanderAngle;
    
    public Enemy(int x, int y, Player player) {
        super(x, y);
        this.player = player;
        color1 = new Color(45,8,10);
        color2 = new Color(204, 41, 54,30);
        maxHealth = 50;
        health = maxHealth;
        pauseTimer = 0;
        wanderAngle = random.nextDouble() * 2 * Math.PI; // Initial random wander angle
    }
    
    @Override
    public void move() {
        // Calculate distance to player
        int deltaX = player.x - x;
        int deltaY = player.y - y;
        double distance = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
        
        // If within attackRadius, move to player
        if(distance < attackRadius && distance>60)
        {
            double angle = Math.atan2(deltaY, deltaX);
            rotateCannon(new Point(player.x, player.y));
            xDir =  Math.cos(angle);
            yDir =  Math.sin(angle);
            shoot();
        }
        //normal movement
        else{
             // When paused timer goes down to generate random stops
            if (pauseTimer > 0) {
                pauseTimer--;
            }

            // Generates random movement direction and changes direction randomly
            else if (random.nextDouble() < 0.01) {
                wanderAngle = random.nextDouble() * 2 * Math.PI; 
                // Calculate movement direction
                xDir = Math.cos(wanderAngle);
                yDir = Math.sin(wanderAngle);
            }
            
            // Checks if the enemy should pause
            else if (random.nextDouble() < 0.01) {
                pauseTimer = 100; 
            }
        }
        super.move();
    }
}
