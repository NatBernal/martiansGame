package com.natalia.game.models;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.natalia.game.pojos.ElementGame;

import com.natalia.game.presenters.ContractPlay;

public class GameModelManager implements ContractPlay.Model {

    private Cannon cannon;
    private List<Martian> martians;
    private List<Bullet> bullets;
    private ContractPlay.Presenter presenter;
    private int deathMartians;
    private int numberMartians;

    private final Object lockListMartians;
    private final Object lockListBullets;

    private int heightBound;
    private int widthBound;

    private boolean isGameRunning;

    public GameModelManager(int widthBound, int heightBound) {
        this.heightBound = heightBound;
        this.widthBound = widthBound;
        initComponents();
        lockListMartians = new Object();
        lockListBullets = new Object();
        cannon = new Cannon(heightBound, widthBound);
    }

    private void initComponents() {
        isGameRunning = true;
        numberMartians = 0;
        deathMartians = 0;
        martians = new ArrayList<>();
        bullets = new ArrayList<>();
    }

    private void startThreadCreateMartians() {
        isGameRunning = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isGameRunning) {
                    try {
                        generateMartian();
                        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    private void generateMartian() {
        Martian martian = new Martian(heightBound, widthBound, this);
        synchronized (lockListMartians) {
            martians.add(martian);
            numberMartians++;
        }
    }

    @Override
    public void setPresenter(ContractPlay.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void start() {
        startThreadCreateMartians();
    }

    @Override
    public void stop() {
        isGameRunning = false;
    }

    @Override
    public List<ElementGame> getMartians() {
        synchronized (lockListMartians) {
            List<ElementGame> martiansList = new ArrayList<>();
            for (ElementGame elementGame : martians) {
                martiansList.add(elementGame);
            }
            return martiansList;
        }
    }

    @Override
    public ElementGame getCannon() {
        return cannon;
    }

    @Override
    public void moveCannonLeft() {
        cannon.left();
    }

    @Override
    public void moveCannonRight() {
        cannon.right();
    }

    @Override
    public void fireCannon() {
        Bullet bullet = new Bullet(cannon.getX() + cannon.getWidth() / 2, cannon.getY() + cannon.getHeight() / 2, this);
        synchronized (lockListBullets) {
            bullets.add(bullet);
        }
        shootThread(bullet);
    }

    private void shootThread(Bullet bullet) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (bullet.isVisible()) {
                    try {
                        Thread.sleep(bullet.getSpeed());
                        bullet.move();
                        checkImpact(bullet);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    private synchronized void checkImpact(Bullet bullet) {
        synchronized (lockListMartians) {
            for (Martian martian : martians) {
                if (martian.isImpacted(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight())) {
                    martian.configureDead();
                    bullet.setImpact();
                    break;
                }
            }
        }
    }

    @Override
    public List<ElementGame> getBullets() {
        List<ElementGame> bulletsList = new ArrayList<>();
        synchronized (lockListBullets) {
            for (ElementGame elementGame : bullets) {
                bulletsList.add(elementGame);
            }
            return bulletsList;
        }
    }

    public GameModelManager getInstance() {
        return this;
    }

    public void removeMartian(Martian martian) {
        synchronized (lockListMartians) {
            if(!martian.isAlive()){
                deathMartians++;
            }
            martians.remove(martian);
        }
    }

    public void removeBullet(Bullet bullet) {
        synchronized (lockListBullets) {
            bullets.remove(bullet);
        }
    }

    @Override
    public int getNumberOfMartians() {
        return numberMartians;
    }

    @Override
    public int getNumberOfEliminatedMartians() {
        return deathMartians;
    }

}
