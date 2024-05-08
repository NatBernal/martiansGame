package com.natalia.game.presenters;

import java.util.List;

import com.natalia.game.pojos.ElementGame;

public interface ContractPlay {
    public interface Model {
        public void setPresenter(Presenter presenter);
        public void start();
        public void stop();
        public List<ElementGame> getMartians();
        public ElementGame getCannon();
        public void moveCannonLeft();
        public void moveCannonRight();
        public void fireCannon();
        public List<ElementGame> getBullets();
        public int getNumberOfMartians();
        public int getNumberOfEliminatedMartians();
    }

    public interface View {
        public void setPresenter(Presenter presenter);
        public void begin();
    }

    public interface Presenter {
        public void setModel(Model model);
        public void setView(View view);
        public void begin();
        public List<ElementGame> getMartians();
        public ElementGame getCannon();
        public void moveCannonLeft();
        public void moveCannonRight();
        public void fireCannon();
        public List<ElementGame> getBullets();
        public int getNumberOfMartians();
        public int getNumberOfEliminatedMartians();
    }

}
