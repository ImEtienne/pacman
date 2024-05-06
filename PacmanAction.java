package Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacmanAction implements ActionListener{
int size =46 ;
    private Game game;
    private  GameView gameView;
    private Pacman pacman;

    public PacmanAction(Game game, GameView gameView, Pacman pacman) {
        this.game = game;
        this.gameView = gameView;
        this.pacman = pacman;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EntityType[][] map = game.getMap().getMap();
        int pacXMove = pacman.getX() + pacman.getDirection().getDx();
        int pacYMove = pacman.getY() + pacman.getDirection().getDy();

        if(map[pacYMove / 46][pacXMove / 46] == EntityType.WALL ||// en haut a gauche
                map[pacYMove / 46][(pacXMove + 34) / 46] == EntityType.WALL ||//en haut a droite
                map[(pacYMove + 34) / 46][pacXMove / 46] == EntityType.WALL ||//en bas gauche
                map[(pacYMove + 34) / 46][(pacXMove + 34) / 46] == EntityType.WALL){

            pacman.setX(pacXMove);
            pacman.setY(pacYMove);
        }

        gameView.repaint();
    }


}
