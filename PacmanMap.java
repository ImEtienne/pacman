package Action;

import java.util.Arrays;

import static Action.EntityType.*;

public class PacmanMap {
    private static EntityType[][] map;
    private boolean blockingWall;

    public PacmanMap() {
        map = new EntityType[][] {
                { WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL },
                { WALL, CLAS, WALL, CLAS, CLAS, CLAS, CLAS, CLAS, CLAS, CLAS, CLAS, CLAS, CLAS, WALL, CLAS, WALL },
                { WALL, INVI, WALL, CLAS, WALL, CLAS, WALL, WALL, WALL, WALL, CLAS, WALL, WALL, WALL, SUPR, WALL },
                { WALL, CLAS, CLAS, CLAS, WALL, CLAS, CLAS, CLAS, CLAS, WALL, CLAS, CLAS, CLAS, WALL, CLAS, WALL },
                { WALL, CLAS, WALL, CLAS, WALL, CLAS, WALL, WALL, WALL, WALL, WALL, WALL, CLAS, CLAS, CLAS, WALL },
                { WALL, CLAS, WALL, CLAS, CLAS, CLAS, CLAS, WALL, CLAS, CLAS, CLAS, WALL, CLAS, WALL, WALL, WALL },
                { WALL, WALL, CLAS, CLAS, WALL, WALL, WALL, WALL, CLAS, WALL, CLAS, WALL, WALL, WALL, CLAS, WALL },
                { WALL, CLAS, CLAS, WALL, CLAS, CLAS, CLAS, CLAS, CLAS, CLAS, CLAS, CLAS, CLAS, WALL, CLAS, WALL },
                { CLAS, CLAS, WALL, WALL, CLAS, WALL, CLAS, WALL, WALL, CLAS, WALL, WALL, CLAS, WALL, CLAS, CLAS },
                { WALL, CLAS, CLAS, WALL, CLAS, CLAS, CLAS, CLAS, CLAS, CLAS, CLAS, CLAS, MIXE, CLAS, CLAS, WALL },
                { WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL },
        };
        this.blockingWall = false;
    }
    
   public boolean finJeu() {
       return Arrays.stream(this.getMap())
                .flatMap(Arrays::stream) // Pour chaque tableau interne, aplatissez-le en un flux
                .anyMatch(obj -> obj.equals(CLAS)); // Vérifiez si l'objet recherché est présent
    }

    public EntityType[][] getMap() {
        return  map;
    }

    public static void remplacerparJETONparVIDE(int i, int j){
       map[i][j]= VIDE;
    }

    public void mixlabyrinth(){
        if (blockingWall) {
            map[1][2] = CLAS;
            map[1][13] = CLAS;
            map[5][14] = CLAS;
            map[6][12] = CLAS;
            map[3][4] = CLAS;
            map[7][13] = CLAS;
            map[9][3] = CLAS;
            map[5][7] = CLAS;
            map[3][9] = CLAS;
            map[4][9] = CLAS;
            map[6][1] = CLAS;
        }
    }

    public boolean isBlockingWall() {
        return blockingWall;
    }

    public void setBlockingWall(boolean blockingWall) {
        this.blockingWall = blockingWall;
        mixlabyrinth();
    }
}
