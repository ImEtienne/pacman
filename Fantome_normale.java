package Action;

import java.awt.*;

public class Fantome_normale extends FigureState{
    private Color color ;
    private PacmanEnnemie pacmanEnnemie;

    public Fantome_normale(Color color, PacmanEnnemie pacmanEnnemie) {
        this.color = color;
        this.pacmanEnnemie = pacmanEnnemie;
    }


    @Override
    public void colorState() {
        pacmanEnnemie.setColor(color);
    }

    @Override
    public void State() {
        colorState();
    }

    @Override
    public String toString() {
        return "normal";
    }
}
