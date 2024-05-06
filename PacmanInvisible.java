package Action;

import java.awt.*;
import java.util.TimerTask;
import java.util.Timer;

public class PacmanInvisible extends FigureState {
    private Pacman pacman;

    public PacmanInvisible(Pacman pacman) {
        this.pacman = pacman;
    }

    @Override
    public void colorState() {
        pacman.setColor(Color.decode("#FDFD96"));
    }

    @Override
    public void State() {
        colorState();
        Timer timer = new Timer();
        int interval = 10000; // Intervalle de temps en millisecondes (1 seconde)
        timer.scheduleAtFixedRate(new TimerTask() {
            int count = 0;
            @Override
            public void run() {
                if (count < 10) { // Changez la couleur pendant 10 secondes
                    if (count % 2 == 0) { // Changez la couleur toutes les 2 secondes (juste un exemple)
                        pacman.setColor(Color.green);
                    } else {
                        pacman.setColor(Color.decode("#FDFD96"));
                    }
                    count++;
                } else {
                    pacman.setColor(Color.green);
                    pacman.setEstVisible(true);
                    pacman.setEstVisibleTimer(0); // Vous pouvez ajuster cette valeur si nécessaire
                    pacman.setFigureState(new PacmanNormal(pacman));
                    pacman.getFigureState().State();
                    timer.cancel(); // Arrêtez le timer après les 10 secondes
                }
            }
        }, 0, interval);
    }
}
