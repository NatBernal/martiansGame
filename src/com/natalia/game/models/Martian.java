package com.natalia.game.models;

import java.util.concurrent.ThreadLocalRandom;

import com.natalia.game.pojos.*;

public class Martian extends ElementGame {

    private boolean appersLeft;
    private int size;
    private boolean alive;
    private boolean visible;
    private int heightBound;
    private int widthBound;

    private GameModelManager gameModelManager;

    private static final int MOVEMENT_FACTOR = 5;
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void configureDead() {
        alive = false;
    }

    public Martian(int heightBound, int widthBound, GameModelManager gameModelManager) {
        super();
        this.heightBound = heightBound;
        this.widthBound = widthBound;
        super.setElementGameType(ElementGameType.MARTIAN);
        this.gameModelManager = gameModelManager;
        configureApper();
        startThreadMartiansMove();
    }

    private void configureApper() {
        appersLeft = ThreadLocalRandom.current().nextBoolean();
        size = ThreadLocalRandom.current().nextInt(60, 100);
        super.setWidth(size);
        super.setHeight(size);
        super.setPadding(0);
        super.setSpeed(ThreadLocalRandom.current().nextInt(20, 200));
        setMartianStartPosition();
        alive = true;
        visible = true;
    }

    private void setMartianStartPosition() {
        int randomY = ThreadLocalRandom.current().nextInt(heightBound / 2);
        int randomX;
        if (appersLeft) {
            randomX = ThreadLocalRandom.current().nextInt(size);
        } else {
            randomX = widthBound - size - ThreadLocalRandom.current().nextInt(size);
        }
        super.setX(randomX);
        super.setY(randomY);
    }

    public void left() {
        super.setX(super.getX() - MOVEMENT_FACTOR);
        if (super.getX() <= 1 || !alive) {
            visible = false;
            gameModelManager.removeMartian(this);
        }
    }

    public void right() {
        super.setX(super.getX() + MOVEMENT_FACTOR);
        if (super.getX() >= widthBound || !alive) {
            visible = false;
            gameModelManager.removeMartian(this);
        }
    }

    public boolean isImpacted(int bulletX, int bulletY, int bulletWidth, int bulletHeight) {
        if (getX() < bulletX + bulletWidth &&
            getX() + getWidth() > bulletX &&
            getY() < bulletY + bulletHeight &&
            getY() + getHeight() > bulletY) {
            return true; 
        }
        return false;
    }

    public boolean isVisible() {
        return visible;
    }

    @Override
    public void move() {
        if (appersLeft) {
            right();
        } else {
            left();
        }
    }

    public void startThreadMartiansMove() {
        this.visible = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (visible) {
                    try {
                        Thread.sleep(getSpeed());
                        move();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

}
