package Action;


public enum Direction {
    UP(0,-2),DOWN(0,2),  LEFT(-2,0), RIGHT(2,0),VOID(0,0);
    private int dx,dy;
    Direction(int dx, int dy) {
        this.dx=dx;
        this.dy=dy;
    }

    public static Direction direction_aleatoire(){
        switch ((int) (Math.random() * 4)) {
            case 0:
                return UP ;
            case 1:
                return DOWN ;
            case 2:
                return LEFT ;
            case 3:
                return RIGHT ;

        }
        return VOID;
    }
    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
}
