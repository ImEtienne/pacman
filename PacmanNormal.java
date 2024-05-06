package Action;

import java.awt.*;

public class PacmanNormal extends FigureState{

    private Pacman pacman;

    public PacmanNormal(Pacman pacman) {
        this.pacman = pacman;
    }

    public PacmanNormal() {

    }

    @Override


    public void colorState() {
        pacman.setColor(Color.yellow);
    }

    @Override
    public void State() {
        colorState();
    }
}
