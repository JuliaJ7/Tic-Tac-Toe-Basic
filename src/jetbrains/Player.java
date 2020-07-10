package jetbrains;

public class Player {
    private int x;
    private int y;
    private char playerType;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getPlayerType() {
        return playerType;
    }

    public Player() {
        x = -1;
        y = -1;
        playerType = 'X';
    }

    public void togglePlayer() {
        playerType = playerType == 'X' ? 'O' : 'X';
    }
}
