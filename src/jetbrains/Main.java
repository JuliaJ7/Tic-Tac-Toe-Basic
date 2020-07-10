package jetbrains;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        GameField gameField = new GameField();
        Player player = new Player();
        Scanner scanner = new Scanner(System.in);
        String coordinatesInput = "";

        gameField.printField();

        for (;;) {
            System.out.print("Enter the coordinates: ");
            coordinatesInput = scanner.nextLine();
            String message = validateCoordinates(coordinatesInput, gameField, player);

            if (!message.equals("")) {
                System.out.println(message);
            } else {
                gameField.assignCellValue(player);
                gameField.printField();
                FieldState state = gameField.determineState();

                if (state == FieldState.DRAW || state == FieldState.X_WINS || state == FieldState.O_WINS) {
                    printMessage(state);
                    return;
                }

                player.togglePlayer();
            }
        }
    }

    public static String validateCoordinates(String coordinatesInput, GameField gameField, Player player) {
        String[] coordinatesArray = coordinatesInput.trim().split(" ");

        try {
            player.setX(Integer.parseInt(coordinatesArray[0]));

            if (coordinatesArray.length >= 2) {
                player.setY(Integer.parseInt(coordinatesArray[1]));
            }
        } catch (NumberFormatException e) {
            return "You should enter numbers!";
        }

        if (player.getX() < 1 || player.getX() > 3 || player.getY() < 1 || player.getY() > 3) {
            return "Coordinates should be from 1 to 3!";
        } else if (gameField.isCellOccupied(player)) {
            return "This cell is occupied! Choose another one!";
        }

        return "";
    }

    public static void printMessage(FieldState state) {
        if (state == FieldState.GAME_NOT_FINISHED) {
            System.out.println("Game not finished");
        }

        if (state == FieldState.X_WINS) {
            System.out.println("X wins");
        }

        if (state == FieldState.O_WINS) {
            System.out.println("O wins");
        }

        if (state == FieldState.DRAW) {
            System.out.println("Draw");
        }

        if (state == FieldState.IMPOSSIBLE) {
            System.out.println("Impossible");
        }
    }
}
