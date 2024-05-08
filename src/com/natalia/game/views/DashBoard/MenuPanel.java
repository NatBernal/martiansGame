package com.natalia.game.views.DashBoard;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.natalia.game.utils.GameTimer;

public class MenuPanel extends javax.swing.JPanel {

    private DashBoard dashboard;
    private GameTimer gameTimer;

    private JLabel gameTimeLabel;
    private JLabel martiansLabel;
    private JLabel eliminatedMartiansLabel;

    private int highMenuPanel;

    public MenuPanel(int frameWidth, int frameHeight) {
        this.highMenuPanel = frameHeight/4;
        setBounds(0, 0, frameWidth, highMenuPanel);
        setBackground(Color.lightGray);
        setLabelInitialText();
        configureTextPreferences();
        addLabels();
        gameTimer = new GameTimer();
        gameTimer.startTime();
    }

    private void addLabels(){
        this.setLayout(new GridLayout(1, 3));
        this.add(gameTimeLabel);
        this.add(martiansLabel);
        this.add(eliminatedMartiansLabel);
    }

    private void setLabelInitialText(){
        gameTimeLabel = new JLabel("Time Game: 00:00:00");
        gameTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        martiansLabel = new JLabel("Martians: 0");
        martiansLabel.setHorizontalAlignment(SwingConstants.CENTER);
        eliminatedMartiansLabel = new JLabel("Eliminated martians: 0");
        eliminatedMartiansLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void configureTextPreferences(){
        Font font = new Font("Arial", Font.BOLD, 20);
        gameTimeLabel.setFont(font);
        martiansLabel.setFont(font);
        eliminatedMartiansLabel.setFont(font);
    }

    public void setDashboard(DashBoard dashboard) {
        this.dashboard = dashboard;
    }

    public int getHighMenuPanel() {
        return highMenuPanel;
    }

    public void updateTexts(){
        gameTimeLabel.setText(gameTimer.getFormattedTime());
        martiansLabel.setText("Martians: " + dashboard.getPresenter().getNumberOfMartians());
        eliminatedMartiansLabel.setText("Eliminated martians: " + dashboard.getPresenter().getNumberOfEliminatedMartians());
    }

    public void startThreadMenu(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                        updateTexts();
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        thread.start();
    }

}
