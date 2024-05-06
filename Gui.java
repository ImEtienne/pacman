package Action;

import javax.swing.*;

public class Gui {

    private JFrame frame = new JFrame("Pacouman");
    private boolean agagner = false;

    public Gui() {
        Game game = new Game(frame);
        EntityType[][] terrain = game.getMap().getMap();

        frame.setSize(terrain[0].length * 46, terrain.length * 46 + 75);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(game.getGameView());
        frame.addKeyListener(game.getPacmanMove());
        frame.requestFocusInWindow();

    }

    public boolean affiche_gagner() {
        if (agagner) {
            JOptionPane.showMessageDialog(frame, "Vous avez gagné!", "Félicitations", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        return false;
    }
}

