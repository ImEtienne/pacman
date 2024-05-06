package Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnnemieAction implements ActionListener {
    private Game game;
    private GameView view;

    public EnnemieAction(Game game, GameView view) {
        this.game = game;
        this.view = view;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (PacmanEnnemie p : game.getPacmanEnnemie()){
           mur(p,game.getMap().getMap());
                checkVie(p);

            game.getPacman().collison(p);
            view.repaint();
        }
    }

    public void checkVie(PacmanEnnemie p){

        if(game.getPacman().collison(p)) {
            if(!game.getPacman().isSuperPouvoir())
                game.getPacman().lessVie();
        }
    }

    private void mur(PacmanEnnemie p, EntityType [][] map){

        int newX = p.getX() + p.getDirection().getDx();
        int newY = p.getY() + p.getDirection().getDy();


            // Vérifier si le mouvement est valide (non-blocage)
        while(map[newY / 46][newX / 46] == EntityType.WALL ||// en haut a gauche
                    map[newY / 46][(newX + 34) / 46] == EntityType.WALL ||//en haut a droite
                    map[(newY + 34) / 46][newX / 46] == EntityType.WALL ||//en bas gauche
                    map[(newY + 34) / 46][(newX + 34) / 46] == EntityType.WALL){//en bas droite

            Direction futur_direction = Direction.direction_aleatoire();

            p.setDirection(futur_direction);
            newX = p.getX() + p.getDirection().getDx();
            newY = p.getY() + p.getDirection().getDy();
        }
        p.setX(newX);
        p.setY(newY);
    }
}
