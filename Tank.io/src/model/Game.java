/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Game {

    private final Player player;
    private boolean w = false;
    private boolean a = false;
    private boolean s = false;
    private boolean d = false;
    private int playerXDir, playerYDir;
    private boolean isTopBorder = false;
    private boolean isBottomBorder = false;
    private boolean isLeftBorder = false;
    private boolean isRightBorder = false;
    private int score = 0;
    

    private final World world;
    private final int worldWidth = 1920 * 2;
    private final int worldHeight = 1080 * 2;
    private final int worldX;
    private final int worldY;

    private final ArrayList<Player> alEnemies = new ArrayList<>();
    private final int numberOfEnemies = 15;

    private boolean gameOver = false;
    private boolean gameWon = false;

    public Game(Player player, int width, int height) {
        this.player = player;
        worldX = (int) -((worldWidth - width) / 2);
        worldY = (int) -((worldHeight - height) / 2);
        this.world = new World(worldWidth, worldHeight, worldX, worldY);
        for (int i = 0; i < numberOfEnemies; i++) {
            int x = Utils.random(worldWidth, worldX);
            int y = Utils.random(worldHeight, worldY);
            alEnemies.add(new Enemy(x, y, player));
        }
    }

    public void keyPressed(KeyEvent key) {
        int keyCode = key.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_W ->
                w = true;
            case KeyEvent.VK_S ->
                s = true;
            case KeyEvent.VK_A ->
                a = true;
            case KeyEvent.VK_D ->
                d = true;
        }
        updateMovement();
    }

    public void keyReleased(KeyEvent key) {
        int keyCode = key.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_W ->
                w = false;
            case KeyEvent.VK_S ->
                s = false;
            case KeyEvent.VK_A ->
                a = false;
            case KeyEvent.VK_D ->
                d = false;
        }
        updateMovement();
    }

    private void updateMovement() {
        playerXDir = 0;
        playerYDir = 0;

        if (w && !isTopBorder) {
            playerYDir--;
        }

        if (s && !isBottomBorder) {
            playerYDir++;
        }

        if (a && !isLeftBorder) {
            playerXDir--;
        }

        if (d && !isRightBorder) {
            playerXDir++;
        }
    }

    public int getScore() {
        return score;
    }

    public Player getPlayer() {
        return player;
    }

    public void move() {

        for (int i = 0; i < alEnemies.size(); i++) {
            alEnemies.get(i).move();
        }
        for (int i = 0; i < alEnemies.size(); i++) {
            alEnemies.get(i).addXDir(-playerXDir);
            alEnemies.get(i).addYDir(-playerYDir);
        }

        for (int i = 0; i < alEnemies.size(); i++) {
            worldBorderHandler(alEnemies.get(i));
        }
        worldBorderHandler(player);

        world.moveWorld(playerXDir, playerYDir, player.getSpeed());
        playerXDir = 0;
        playerYDir = 0;

    }

    private void worldBorderHandler(Player player) {
        // Checks collision with the left side
        if (player.getTopLeft().x < world.getWorldX()) {
            if (!player.equals(this.player)) {
                // Adjust enemy position to the right side of the border
                player.setX(world.getWorldX() + player.getRadius());
            } else {
                // Adjust player position to the right side of the border
                world.setWorldX(player.getCenter().x - player.getRadius());
                isLeftBorder = true;
            }
        } else if (player.equals(this.player)) {
            isLeftBorder = false;
        }

        // Checks collision with the right side
        if (player.getTopLeft().x + 2 * player.getRadius() > world.getWorldX() + worldWidth) {
            if (!player.equals(this.player)) {
                // Adjust enemy position to the left side of the border
                player.setX(world.getWorldX() + worldWidth - 2 * player.getRadius());
            } else {
                // Adjust player position to the left side of the border
                world.setWorldX(player.getCenter().x + player.getRadius() - worldWidth);
                isRightBorder = true;
            }
        } else if (player.equals(this.player)) {
            isRightBorder = false;
        }

        // Checks collision with the top side
        if (player.getTopLeft().y < world.getWorldY()) {
            if (!player.equals(this.player)) {
                // Adjust enemy position to the bottom side of the border
                player.setY(world.getWorldY() + player.getRadius());
            } else {
                // Adjust player position to the bottom side of the border
                world.setWorldY(player.getCenter().y - player.getRadius());
                isTopBorder = true;
            }
        } else if (player.equals(this.player)) {
            isTopBorder = false;
        }

        // Checks collision with the bottom side
        if (player.getTopLeft().y + 2 * player.getRadius() > world.getWorldY() + worldHeight) {
            if (!player.equals(this.player)) {
                // Adjust enemy position to the top side of the border
                player.setY(world.getWorldY() + worldHeight - 2 * player.getRadius());
            } else {
                // Adjust player position to the top side of the border
                world.setWorldY(player.getCenter().y + player.getRadius() - worldHeight);
                isBottomBorder = true;
            }
        } else if (player.equals(this.player)) {
            isBottomBorder = false;
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void checkHit() {
        if (alEnemies.isEmpty() && !gameOver) {
            gameWon = true;
        }
        if (!gameWon) {
            for (int i = 0; i < alEnemies.size(); i++) {
                player.getCannon().checkHit(alEnemies.get(i));
                alEnemies.get(i).getCannon().checkHit(player);
            }
            for (int i = 0; i < alEnemies.size(); i++) {
                if (alEnemies.get(i).getHealth() <= 0) {
                    alEnemies.remove(i);
                    score++;
                }
            }
            if (player.getHealth() <= 0) {
                gameOver = true;
            }
        }

    }

    public int getNumberOfRemainingPLayers() {
        return alEnemies.size() + 1; // add player
    }

    public void draw(Graphics g) {
        world.draw(g);
        g.drawString("Players remaining: " + getNumberOfRemainingPLayers(), 30, 30);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));
        player.draw(g);
        for (int i = 0; i < alEnemies.size(); i++) {
            alEnemies.get(i).draw(g);
        }
    }
}
