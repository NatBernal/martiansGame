package com.natalia.game.pojos;

import java.awt.Image;

public abstract class ElementGame {
    private int x;
    private int y;
    private int width;
    private int height;
    private int padding;
    private int speed;
    private ElementGameType elementType;
    public Image image = null;

    private ElementGameType elementGameType;
    public ElementGameType getElementGameType() {
        return elementGameType;
    }

    public void setElementGameType(ElementGameType elementGameType) {
        this.elementGameType = elementGameType;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public abstract void move();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ElementGameType getElementType() {
        return elementType;
    }   

}
