package Action;

import javax.swing.*;
import java.awt.*;

public class GameView extends JComponent {
    private Game game;
    private Frame frame;
    private boolean start;
    public int size= 46;
    private int delayFantome;
    private Timer timerEnnemy;
   
    public GameView(Frame frame, Game game) {
        super();
        setStart(false);
        setOpaque(true);
        this.game=game;
        this.frame = frame;
        this.timerEnnemy = new Timer(25, new EnnemieAction(game, this));
    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public int getDelayFantome() {
        return delayFantome;
    }

    public void setDelayFantome(int delayFantome) {
        this.delayFantome = delayFantome;
    }

    public void setStart(boolean b) {
        this.start = b;
    }

    private boolean isStart() {
    return start;}

    public Timer getTimerEnnemy() {
        return timerEnnemy;
    }

    public void setTimerEnnemy(Timer timerEnnemy) {
        this.timerEnnemy = timerEnnemy;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //dessin du terrain
        g.setColor(Color.black);
        if(!isStart()){
            timerEnnemy.start();
            if(game.aperdu()){
                ScreenLose(g);
            }else{
                if (game.agagner()) {
                    screenWin(g);
                } else {
                    drawTerrain(g);
                    drawPacman(g);
                    drawVie(g);
                    drawFantome(g);
                }
            }
        }
    }


    // Terrain
    private void drawTerrain(Graphics g) {
        g.fill3DRect(0, 0, game.getMap().getMap()[0].length * size, game.getMap().getMap().length * size, start);

        int x;
        int y = 0;

        for (EntityType[] i : game.getMap().getMap()) {
            x = 0;
            for (EntityType j : i) {
                switch (j) {
                    case WALL:
                        g.setColor(Color.GRAY); // Utilisez la couleur grise
                        g.fillRect(x, y, size, size);
                        break;
                    case CLAS:
                        g.setColor(Color.blue);
                        g.fillOval(x + 12, y + 12, size - 14 * 2, size - 14 * 2);
                        break;
                    case INVI:
                        g.setColor(Color.magenta);
                        g.fillOval(x + 12, y + 12, size - 14 * 2, size - 14 * 2);
                        break;
                    case SUPR:
                        g.setColor(Color.orange);
                        g.fillOval(x + 12, y + 12, size - 14 * 2, size - 14 * 2);
                        break;
                    case MIXE:
                        g.setColor(Color.green);
                        g.fillOval(x + 12, y + 12, size - 14 * 2, size - 14 * 2);
                        break;
                    case VIDE:
                        g.setColor(Color.black);
                        g.fillOval(x + 12, y + 12, size - 14 * 2, size - 14 * 2);
                        break;
                }
                x += size;
            }
            y += size;
        }
    }

    private void drawVie(Graphics g) {
        int size = 12;  // Taille du cœur
        int x = 125;    // Coordonnée x de départ
        int y = 520;    // Coordonnée y du cœur et du texte "Vie :"
        int spacing = 10;  // Espacement entre les cœurs
        g.setFont(new Font("Arial", Font.BOLD, 24)); // Définir la police et la taille
        for (int i = 0; i < game.getPacman().getVie()+7; i++) {
            // Dessiner le cœur
            if(i<game.getPacman().getVie()){
                g.setColor(Color.RED );
            }else{
                g.setColor(Color.BLACK );
            }
            g.fillArc(x, y - size / 2, size, size, 0, 180);
            g.fillArc(x - size / 2, y - size / 2, size, size, 0, 180);
            int[] triangleX = {x - size / 2, x + size, x};
            int[] triangleY = {y, y, y + size};
            g.fillPolygon(triangleX, triangleY, 3);
            x += size + spacing;
        }

        // Dessiner le texte "Vie :"
        g.setColor(Color.black);
        g.drawString("Vie :", 65, y + size );

        // Dessiner le score
        String scoreText = "Score : " + game.getScore_total();
        int scoreX = 75; // Coordonnée x du score
        int scoreY = 480; // Coordonnée y du score
        g.drawString(scoreText, scoreX, scoreY);
    }

    private void drawPacman(Graphics g) {
        //System.out.println("avant if - valeur getSuperpacman : " + game.getPacman().getSuperPacman());
        //System.out.println( "avant visible" + game.getPacman().isEstVisible());

        if (game.getPacman().isEstVisible() || game.getPacman().isSuperPouvoir()) {
            if (game.getPacman().isSuperPouvoir()) {
                for (PacmanEnnemie ennemie : game.getPacmanEnnemie()) {
                    ennemie.setColor(Color.blue);
                }
            }

            if (game.getPacman().getSuperPacman() > 1) {
                game.getPacman().setSuperPacman(game.getPacman().getSuperPacman() - 1);
            } else if (game.getPacman().getSuperPacman() == 1) {
                game.getPacman().setEstVisible(false);
                game.getPacman().setColor(Color.yellow);
            }
        }
        g.setColor(game.getPacman().getColor());
        g.fillRect(game.getPacman().getX(), game.getPacman().getY(), size - 5, size - 5);/*-5*2*/
    }
     
    private void drawFantome(Graphics g) {
        for(PacmanEnnemie ennemi: game.getPacmanEnnemie()){
            g.setColor(ennemi.getColor());
            g.fillRect(ennemi.getX(), ennemi.getY(), size-5*2, size-5*2);
        }
   }

   private void screenWin(Graphics g){
       this.timerEnnemy.stop();
       g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
       g.setColor(Color.GREEN);
       g.setFont(new Font("Century Gothic", Font.PLAIN, 40));
       g.drawString("Félicitiations, tu as gagné !", 200, 250);
   }

    private void ScreenLose(Graphics g) {
        this.timerEnnemy.stop();
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
        g.setColor(Color.RED);
        g.setFont(new Font("Helvetica", Font.PLAIN, 40));
        g.drawString("partie perdu !", 250, 250);
    }
}



