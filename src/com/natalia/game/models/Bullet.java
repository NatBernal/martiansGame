package com.natalia.game.models;

import com.natalia.game.pojos.ElementGame;
import com.natalia.game.pojos.ElementGameType;

public class Bullet extends ElementGame {

    private boolean impacted;
    private boolean visible;

    private GameModelManager gameModelManager;

    private static final int MOVEMENT_FACTOR = 5;

    public Bullet(int cannonX, int cannonY, GameModelManager gameModelManager) {
        super();
        super.setX(cannonX);
        super.setY(cannonY);
        super.setWidth(10);
        super.setHeight(10);
        super.setSpeed(10);
        this.gameModelManager = gameModelManager;
        super.setElementGameType(ElementGameType.BULLET);
        impacted = false;
        visible = true;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setImpact(){
        impacted = true;
    }

    @Override
    public synchronized void move() {
        super.setY(super.getY() - MOVEMENT_FACTOR);
        if (super.getY() < 0 || impacted == true) {
            visible = false;
            gameModelManager.removeBullet(this);
        }
    }
}