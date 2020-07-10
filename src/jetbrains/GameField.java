package jetbrains;

public class GameField {
    public static final int DIMENSION = 3;
    public static final char EMPTY_CELL = '_';

    private char[][] field;

    public GameField() {
        this.field = new char[][]{
                {EMPTY_CELL, EMPTY_CELL, EMPTY_CELL},
                {EMPTY_CELL, EMPTY_CELL, EMPTY_CELL},
                {EMPTY_CELL, EMPTY_CELL, EMPTY_CELL}
        };
    }

    public void printField() {
        System.out.println("---------");
        for (int i = 0; i < DIMENSION; i++) {
            System.out.print("| ");
            for (int j = 0; j < DIMENSION; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public boolean isCellOccupied(Player player) {
        int x = player.getX();
        int y = player.getY();

        if (field[3 - y][x - 1] != EMPTY_CELL) {
            return true;
        }

        return false;
    }

    public void assignCellValue(Player player) {
        int i = 3 - player.getY();
        int j = player.getX() - 1;

        this.field[i][j] = player.getPlayerType();
    }

    public FieldState determineState() {

        if (hasWinCondition('X') && hasWinCondition('O')) {
            return FieldState.IMPOSSIBLE;
        }

        if (Math.abs(characterCount('X') - characterCount('O')) >= 2) {
            return FieldState.IMPOSSIBLE;
        }

        if (hasWinCondition('X')) {
            return FieldState.X_WINS;
        }

        if (hasWinCondition('O')) {
            return FieldState.O_WINS;
        }

        if (hasEmptyCells()) {
            return FieldState.GAME_NOT_FINISHED;
        } else {
            return FieldState.DRAW;
        }
    }

    private boolean hasWinCondition(char character) {
        for (int i = 0; i < DIMENSION; i++) {
            if (field[i][0] == field[i][1] && field[i][1] == field[i][2] && field[i][0] == character) {
                return true;
            }
        }

        for (int i = 0; i < DIMENSION; i++) {
            if (field[0][i] == field[1][i] && field[1][i] == field[2][i] && field[0][i] == character) {
                return true;
            }
        }

        if (field[0][0] == field[1][1] && field[1][1] == field[2][2] && field[0][0] == character) {
            return true;
        }

        if (field[0][2] == field[1][1] && field[1][1] == field[2][0] && field[0][2] == character) {
            return true;
        }

        return false;
    }

    private int characterCount(char character) {
        int count = 0;

        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if (field[i][j] == character) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean hasEmptyCells() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if (field[i][j] == EMPTY_CELL) {
                    return true;
                }
            }
        }

        return false;
    }
}
