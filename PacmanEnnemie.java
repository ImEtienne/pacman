package Action;

import java.awt.*;

public class PacmanEnnemie extends modelState {
    private Fantome_normale fantome_normale;

    public PacmanEnnemie(int x, int y, Color color) {
        super(x, y, Direction.direction_aleatoire(), color,new Fantome_normale(color,new PacmanEnnemie()));
        this.fantome_normale = new Fantome_normale(color,this);
        setFigureState(fantome_normale);
    }

    public PacmanEnnemie() {}

    public void teleporter() {
        this.setX(5*46);
        this.setY(3*46);
    }

    public Fantome_normale getFantome_normale() {
        return fantome_normale;
    }

    public void setFantome_normale(Fantome_normale fantome_normale) {
        this.fantome_normale = fantome_normale;
    }
}
