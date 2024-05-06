package Action;

import java.awt.*;

public class Game {
    private PacmanMap map;
    private Pacman pacman;
    private PacmanEnnemie []pacmanEnnemie;
    private GameView gameView;
    private PacmanMove pacmanMove;
    private int score;
    private int score_total;

    int size = 46;
    public Game(Frame frame) {
        this.map = new PacmanMap();
        this.pacman = new Pacman(this);
        this.score = 0;
        this.score_total = 0;
        this.gameView = new GameView(frame,this);
        this.pacmanMove = new PacmanMove(this,gameView);
        this.pacmanEnnemie= new PacmanEnnemie[]{
                new PacmanEnnemie(5*size, 3*size,Color.cyan),
                new PacmanEnnemie(12*size, 3*size,Color.MAGENTA),
                new PacmanEnnemie(14*size, 7*size,Color.green),
                new PacmanEnnemie(2*size, 7*size,Color.RED),};
    }
   
    public int ajoutescore(int addScore){

        this.score_total +=addScore;           
        return this.score+=addScore;

    }

    public boolean agagner(){
        return (pacman.getVie() > 0 && getScore_total() == 10700);
    }
    public boolean aperdu(){
        return (pacman.getVie() == 0 );
    }

    public int getScore_total() {
        return score_total;
    }

    public void ajoutevie(){

        while(score>=5000  ){

            pacman.addVie();
            score-=5000;
        }


    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public PacmanMove getPacmanMove() {
        return pacmanMove;
    }

    public void setPacmanMove(PacmanMove pacmanMove) {
        this.pacmanMove = pacmanMove;
    }

    public PacmanEnnemie[] getPacmanEnnemie() {
        return pacmanEnnemie;
    }

    public void setPacmanEnnemie(PacmanEnnemie[] pacmanEnnemie) {
        this.pacmanEnnemie = pacmanEnnemie;
    }

    public PacmanMap getMap() {
        return map;
    }

    public void setMap(PacmanMap map) {
        this.map = map;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }



    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }


}
