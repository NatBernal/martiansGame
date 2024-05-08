package com.natalia.game.models;


import com.natalia.game.pojos.ElementGame;
import com.natalia.game.pojos.ElementGameType;

public class Cannon extends ElementGame {

    private int height;
    private int width;
    private int heightBound;
    private int widthBound;
    private boolean isMovingLeft;

    private static final int MOVEMENT_FACTOR = 5;


    public Cannon(int heightBound, int widthBound) {
        super();
        this.heightBound = heightBound;
        this.widthBound = widthBound;
        super.setElementGameType(ElementGameType.CANNON);
        configureApper();
    }

    private void configureApper() {
        height = 100;
        width = 100;
        super.setWidth(width);
        super.setHeight(height);
        super.setPadding(height / 2);
        super.setSpeed(100);
        setCannonStartPosition();
    }

    private void setCannonStartPosition() {
        int y = heightBound - super.getPadding();
        int x = widthBound / 2 - width / 2;
        super.setX(x);
        super.setY(y);
    }

    public void left() {
        boolean outOfBounds = super.getX() <= 0;
        if (outOfBounds == false) {
            super.setX(super.getX() - MOVEMENT_FACTOR);
            isMovingLeft = true;
        }
    }

    public void right() {
        boolean outOfBounds = super.getX() >= widthBound - width;
        if (outOfBounds == false) {
            super.setX(super.getX() + MOVEMENT_FACTOR);
            isMovingLeft = false;
        }
    }

    @Override
    public void move() {
        if (isMovingLeft) {
            right();
        } else {
            left();
        }
    }

}