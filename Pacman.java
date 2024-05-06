package Action;

import java.awt.*;

public class Pacman extends modelState{
    private int superPacman;
    private int vie;
    private boolean estVivant;
    private boolean estVisible;
    private boolean superPouvoir;
    private boolean retirerVie;
    private long EstVisibleTimer;

    public Pacman(Game game) {
        super(7*36, 9*36, Direction.VOID, Color.YELLOW, new PacmanNormal());
        this.EstVisibleTimer=0;
        this.estVivant = true;
        this.superPouvoir = false;
        this.retirerVie = false;
        this.vie=3;
        setFigureState(new PacmanNormal(this));
        this.estVisible = false;                                //logique si il est visible alors il est true changer la ou il faut
        this.superPacman = 0;
    }

    public int getSuperPacman() {
        return superPacman;
    }

    public void setSuperPacman(int superPacman) {
        this.superPacman = superPacman;
    }

    public long getEstVisibleTimer() {
        return EstVisibleTimer;
    }

    public void setEstVisibleTimer(long estVisibleTimer) {
        EstVisibleTimer = estVisibleTimer;
    }


    public void addVie(){ this.vie ++;}

    public boolean lessVie(){
        this.vie -- ;
        if(vie == 0){
            return true;
        }
    return false;
    }

    public boolean collison(PacmanEnnemie pacmanEnnemie){
        int fantomeX = pacmanEnnemie.getX();
        int fantomeY = pacmanEnnemie.getY();
        int pacmanX = getX();
        int pacmanY = getY();

        int a=670;int b=6;

        if (pacmanX == 4 && pacmanY >= 360 && pacmanY <= 404) {
            setX(a);
        }

        if (pacmanX >= 694 && pacmanY >= 360 && pacmanY <= 404) {
            setX(b);
        }

        boolean collisionCondition = Math.abs(fantomeX - pacmanX) < 46 &&
                                     Math.abs(fantomeY - pacmanY) < 46;


        if(superPouvoir && collisionCondition){
            pacmanEnnemie.setX(5 * 46);
            pacmanEnnemie.setY(3 * 46);
            return true;
        }

        if (!estVisible && collisionCondition){
            setX(7 * 36);
            setY(9 * 36);
            return true;
        }

        if (estVisible && collisionCondition){
        }
        return false;
    }


    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public boolean isEstVivant() {
        return estVivant;
    }

    public void setEstVivant(boolean estVivant) {
        this.estVivant = estVivant;
    }

    public boolean isEstVisible() {
        return estVisible;
    }

    public void setEstVisible(boolean estVisible) {
        this.estVisible = estVisible;
    }

    public boolean isSuperPouvoir() {
        return superPouvoir;
    }

    public void setSuperPouvoir(boolean superPouvoir) {
        this.superPouvoir = superPouvoir;
    }

    public boolean isRetirerVie() {
        return retirerVie;
    }

    public void setRetirerVie(boolean retirerVie) {
        this.retirerVie = retirerVie;
    }
}
