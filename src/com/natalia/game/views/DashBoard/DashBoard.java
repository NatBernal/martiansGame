package com.natalia.game.views.DashBoard;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import com.natalia.game.presenters.ContractPlay;
import com.natalia.game.presenters.ContractPlay.Presenter;

public class DashBoard extends JFrame implements ContractPlay.View {

    private ContractPlay.Presenter presenter;

    private MenuPanel menuPanel;
    private GamePanel gamePanel;

    private int heightFrameBound = 600;
    private int widthFrameBound = 900;

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public DashBoard() {
        setLayout(new BorderLayout());
        setBounds(0, 0, widthFrameBound, heightFrameBound);
        setTitle("Martians Game");
        initComponents();
    }

    private void initComponents() {
        menuPanel = new MenuPanel(widthFrameBound, heightFrameBound);
        menuPanel.setDashboard(getInstance());
        gamePanel = new GamePanel(getInstance());
        add(menuPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private DashBoard getInstance() {
        return this;
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void begin() {
        gamePanel.initGameComponents();
        gamePanel.startGame();
        menuPanel.startThreadMenu();
        setVisible(true);
    }

    public Presenter getPresenter() {
        return presenter;
    }

    public int[] getGamePanelDimentions() {
        int gamePanelWidth = widthFrameBound;
        int gamePanelHeight = heightFrameBound - menuPanel.getHighMenuPanel();
        return new int[] { gamePanelWidth, gamePanelHeight };
    }

}
