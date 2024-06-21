/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author FraBe689
 */
public class World {

    private final int worldWidth;
    private final int worldHeight;
    private int worldX;
    private int worldY;

    public World(int worldWidth, int worldHeight, int worldX, int worldY) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public void moveWorld(int xDir, int yDir, int speed) {
        worldX = worldX - xDir * speed;
        worldY = worldY - yDir * speed;
    }

    public void draw(Graphics g) {

        g.setColor(Color.white);
        g.fillRect(worldX, worldY, worldWidth, worldHeight);
        g.setColor(Color.lightGray);
        int gridSize = 20;
        g.setColor(Color.lightGray);
        for (int i = worldX; i <= worldX + worldWidth; i += gridSize) {
            g.drawLine(i, worldY, i, worldY + worldHeight);
        }
        for (int j = worldY; j <= worldY + worldHeight; j += gridSize) {
            g.drawLine(worldX, j, worldX + worldWidth, j);
        }
        g.setColor(Color.red);
        g.drawRect(worldX, worldY, worldWidth, worldHeight);
    }
}
