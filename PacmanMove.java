package Action;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PacmanMove implements KeyListener {
    private GameView gameView;
    private Game game;
    private int size = 45 ;
    private EntityType[][]map;
    private Direction adirection ;
    private final int jeton_blue = 100;
    private final int jeton_violet = 300;
    private final int jeton_orange = 500;
    private final int jeton_vert = 1000;

    public PacmanMove(Game game, GameView gameView) {
        this.gameView = gameView;
        this.game=game;
        this.map = game.getMap().getMap();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                movePacman(Direction.LEFT, Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                movePacman(Direction.RIGHT, Direction.RIGHT);
                break;
            case KeyEvent.VK_UP:
                movePacman(Direction.UP, Direction.UP
                );
                break;
            case KeyEvent.VK_DOWN:
                movePacman(Direction.DOWN, Direction.DOWN);
                break;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (game.getPacman().isEstVivant()) {
                //gameView.setStart(true);
            } else {
                gameView.getFrame().dispose();
                App.main(null);
            }
        }
        gameView.repaint();
    }

    private boolean isValidMove(int newX, int newY, Direction checkDirection) {
        if (checkDirection == Direction.UP) {
            newY -= (newY-5) % size;
            //    System.out.println("up");
            return map[(newY) / size][newX / size] != EntityType.WALL;

        } else if (checkDirection == Direction.DOWN) {
            if ((newY + 38) % size != 0 && map[(newY + 38) / size][(newX) / size] != EntityType.WALL) {
                newY += size - (newY + 38) % size;
            }
            return map[(newY + 34) / size][(newX) / size] != EntityType.WALL;
        } else if (checkDirection == Direction.LEFT) {
            newX -= (newX-5) % size;
            // System.out.println("LEFT");
            return map[newY / size][newX / size] != EntityType.WALL;
        }
        else if (checkDirection == Direction.RIGHT) {
            newX += (newX+5) % size;
            //  System.out.println("RIGHT");
            return map[newY / size][(newX-5) / size] != EntityType.WALL;
        }
        return false; // Si la direction n'est pas reconnue, retourne false (mouvement invalide)
    }

    private boolean murPacman(Pacman p, EntityType[][] map){

        int newX = p.getX() + p.getDirection().getDx();
        int newY = p.getY() + p.getDirection().getDy();


        // Vérifier si le mouvement est valide (non-blocage)
        while(map[newY / 46][newX / 46] == EntityType.WALL ||// en haut a gauche
                map[newY / 46][(newX+40) / 46] == EntityType.WALL ||//en haut a droite
                map[(newY + 40) / 46][newX / 46] == EntityType.WALL ||//en bas gauche
                map[(newY + 40) / 46][(newX + 40) / 46] == EntityType.WALL){//en bas droite

            Direction futur_direction = Direction.direction_aleatoire();

            p.setDirection(futur_direction);
            newX = p.getX() + p.getDirection().getDx();
            newY = p.getY() + p.getDirection().getDy();
            return true;
        }
        p.setX(newX);
        p.setY(newY);
        return false;
    }

    private void movePacman(Direction newDirection, Direction checkDirection) {
        adirection = game.getPacman().getDirection();
        game.getPacman().setDirection(newDirection);
        int newXMove = game.getPacman().getX() + game.getPacman().getDirection().getDx();
        int newYMove = game.getPacman().getY() + game.getPacman().getDirection().getDy();

        if (isValidMove(newXMove, newYMove, checkDirection) && !murPacman(game.getPacman(), map)) {
            game.getPacman().setX(newXMove);
            game.getPacman().setY(newYMove);
            game.ajoutevie();

            EntityType test = map[(newYMove) / size][newXMove / size];
            if (test != EntityType.VIDE) {
                PacmanMap.remplacerparJETONparVIDE(newYMove / size, newXMove / size);
                switch (test) {
                    case CLAS:
                        game.ajoutescore(jeton_blue);
                        break;
                    case INVI:
                        game.getPacman().setSuperPacman(500);
                        game.getPacman().setEstVisible(true);
                        game.getPacman().setColor(Color.decode("#FFFFE0"));
                        game.ajoutescore(jeton_violet);
                        break;
                    case MIXE:
                        game.ajoutescore(jeton_vert);
                        game.getMap().setBlockingWall(true);
                        break;
                    case SUPR:
                        gameView.getTimerEnnemy().setDelay(50);
                        game.getPacman().setSuperPouvoir(true);
                        game.getPacman().setColor(Color.orange);
                        game.ajoutescore(jeton_orange);
                        break;
                }
            }
        } else {
            game.getPacman().setDirection(adirection);
        }
        gameView.repaint();
    }
    
    public void keyReleased(KeyEvent e) {
    }
}