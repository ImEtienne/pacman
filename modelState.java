package Action;

import java.awt.*;

public class modelState extends FigureState{

    int x , y ;
    Direction direction ;
    Color color;
    FigureState figureState;

    public modelState(int x, int y, Direction direction, Color color, FigureState figureState) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.color = color;
        this.figureState = figureState;
    }

    public modelState() {

    }

    @Override
    public void colorState() {
         figureState.colorState();
    }

    @Override
    public void State() {
        figureState.State();
    }

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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public FigureState getFigureState() {
        return figureState;
    }

    public void setFigureState(FigureState figureState) {
        this.figureState = figureState;
    }
}
