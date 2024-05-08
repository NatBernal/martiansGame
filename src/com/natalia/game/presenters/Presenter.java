package com.natalia.game.presenters;

import java.util.List;

import com.natalia.game.models.GameModelManager;
import com.natalia.game.pojos.ElementGame;
import com.natalia.game.presenters.ContractPlay.Model;
import com.natalia.game.presenters.ContractPlay.View;
import com.natalia.game.views.DashBoard.DashBoard;

public class Presenter implements ContractPlay.Presenter {

    private ContractPlay.View view;
    private ContractPlay.Model model;

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void begin() {
        makeMVP();
        view.begin();
    }

    private void makeMVP() {

        DashBoard dashBoard = new DashBoard();
        dashBoard.setPresenter(this);
        setView(dashBoard);

        GameModelManager managerElementModel = new GameModelManager(dashBoard.getGamePanelDimentions()[0], dashBoard.getGamePanelDimentions()[1]);
        managerElementModel.setPresenter(this);
        setModel(managerElementModel);

        managerElementModel.start();
    }

    @Override
    public List<ElementGame> getMartians() {
        return model.getMartians();
    }

    @Override
    public ElementGame getCannon() {
        return model.getCannon();
    }

    @Override
    public void moveCannonLeft() {
        model.moveCannonLeft();
    }

    @Override
    public void moveCannonRight() {
        model.moveCannonRight();
    }

    @Override
    public void fireCannon() {
        model.fireCannon();
    }

    @Override
    public List<ElementGame> getBullets() {
        return model.getBullets();
    }

    @Override
    public int getNumberOfMartians() {
        return model.getNumberOfMartians();
    }

    @Override
    public int getNumberOfEliminatedMartians() {
        return model.getNumberOfEliminatedMartians();
    }
    
}
