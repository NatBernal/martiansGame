package com.natalia.game.views.DashBoard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.natalia.game.pojos.ElementGame;
import com.natalia.game.utils.GamePropierties;

public class GamePanel extends javax.swing.JPanel {

    private DashBoard dashboard;

    private boolean isGameRunning;
    private actionsEnum playerAction;

    private List<ElementGame> martians;
    private ElementGame cannon;
    private List<ElementGame> bullets;

    GamePropierties gameProperties;

    private BufferedImage cannonImage;
    private BufferedImage martianImage;

    public GamePanel(DashBoard dashboard) {
        setBackground(Color.BLACK);
        setBounds(0, 0, dashboard.getGamePanelDimentions()[0], dashboard.getGamePanelDimentions()[1]);
        this.dashboard = dashboard;
        setFocusable(true);
        requestFocus();
    }

    public void initGameComponents() {
        martians = new ArrayList<>();
        bullets = new ArrayList<>();
        gameProperties = new GamePropierties();
        cannon = dashboard.getPresenter().getCannon();
        try {
            cannonImage = ImageIO.read(new File(gameProperties.getCannonImagePath()));
            martianImage = ImageIO.read(new File(gameProperties.getMartianImagePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        for (ElementGame bullet : bullets) {
            g.fillOval(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
        }
        for (ElementGame martian : martians) {
                Image scaledMartianImage = martianImage.getScaledInstance(martian.getWidth(), martian.getHeight(),
                        Image.SCALE_SMOOTH);
                g.drawImage(scaledMartianImage, martian.getX(), martian.getY(), this);
        }
        Image scaledCannonImage = cannonImage.getScaledInstance(cannon.getWidth(), cannon.getHeight(),
                Image.SCALE_SMOOTH);
        g.drawImage(scaledCannonImage, cannon.getX(), cannon.getY(), this);
    }

    public void startGame() {
        isGameRunning = true;
        addKeyListener(new GameKeyListener());
        threadPaintElements();
    }

    private void threadPaintElements() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                        martians = dashboard.getPresenter().getMartians();
                        cannon = dashboard.getPresenter().getCannon();
                        bullets = dashboard.getPresenter().getBullets();
                    } catch (InterruptedException e) {
                    }
                    repaint();
                }
            }

        });
        thread.start();
    }

    public void setDashboard(DashBoard dashboard) {
        this.dashboard = dashboard;
    }

    private void handlePlayerAction(actionsEnum action) {
        switch (action) {
            case LEFT:
                dashboard.getPresenter().moveCannonLeft();
                break;
            case RIGHT:
                dashboard.getPresenter().moveCannonRight();
                break;
            case SHOOT:
                dashboard.getPresenter().fireCannon();
                break;
            default:
                break;
        }
    }

    private class GameKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            //not used method
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    handlePlayerAction(actionsEnum.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    handlePlayerAction(actionsEnum.RIGHT);
                    break;
                case KeyEvent.VK_SPACE:
                    handlePlayerAction(actionsEnum.SHOOT);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //not used method
        }

    }

}
