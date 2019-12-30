import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SpaceStationEstablishment {

    public static final char STEPHAN = 'S';
    public static final String COMMAND_UP = "up";
    public static final String COMMAND_DOWN = "down";
    public static final String COMMAND_LEFT = "left";
    public static final String COMMAND_RIGHT = "right";
    public static final String INVALID_INPUT = "Invalid input!";
    public static final char EMPTY_FIELD = '-';
    public static final char BLACK_HOLE = 'O';
    public static final String IS_IN_VOID_MESSAGE = "Bad news, the spaceship went to the void.";
    public static final String IS_COLLECTED_STARPOWER = "Good news! Stephen succeeded in collecting enough star power!";
    public static final String STARPOWER = "Star power collected: ";

    public static void main(String[] args) {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        try {
            int size = Integer.parseInt(reader.readLine());

            char[][] matrix = readMatrix(reader, size);

            int playerRow = getFirstRowSymbol(STEPHAN, matrix);

            int playerCol = getFirstColSymbol(STEPHAN, playerRow, matrix);

            boolean isInVoid = false;

            int power = 0;

            while (power < 50) {

                String direction = reader.readLine();

                int currentPlayerRow = playerRow;
                int currentPlayerCol = playerCol;

                switch (direction) {

                    case COMMAND_UP:
                        currentPlayerRow -= 1;
                        break;

                    case COMMAND_DOWN:
                        currentPlayerRow += 1;
                        break;

                    case COMMAND_LEFT:
                        currentPlayerCol -= 1;
                        break;

                    case COMMAND_RIGHT:
                        currentPlayerCol += 1;
                        break;

                    default:
                        System.out.println(INVALID_INPUT);
                }

                isInVoid = isInGalaxyCheck(currentPlayerRow, currentPlayerCol, matrix.length);

                if (isInVoid) {
                    matrix[playerRow][playerCol] = EMPTY_FIELD;
                    break;
                }

                char currentSymbol = matrix[currentPlayerRow][currentPlayerCol];

                if (currentSymbol == EMPTY_FIELD) {
                    matrix[playerRow][playerCol] = EMPTY_FIELD;
                    matrix[currentPlayerRow][currentPlayerCol] = STEPHAN;
                    playerRow = currentPlayerRow;
                    playerCol = currentPlayerCol;
                } else if (Character.isDigit(currentSymbol)) {
                    matrix[playerRow][playerCol] = EMPTY_FIELD;
                    matrix[currentPlayerRow][currentPlayerCol] = STEPHAN;
                    playerRow = currentPlayerRow;
                    playerCol = currentPlayerCol;
                    power += currentSymbol - 48;
                } else if (currentSymbol == BLACK_HOLE) {
                    matrix[playerRow][playerCol] = EMPTY_FIELD;
                    matrix[currentPlayerRow][currentPlayerCol] = EMPTY_FIELD;
                    playerRow = getFirstRowSymbol(BLACK_HOLE, matrix);
                    playerCol = getFirstColSymbol(BLACK_HOLE, playerRow, matrix);
                    matrix[playerRow][playerCol] = STEPHAN;
                }
            }

            if (isInVoid) {
                System.out.println(IS_IN_VOID_MESSAGE);
            } else {
                System.out.println(IS_COLLECTED_STARPOWER);
            }

            System.out.println(STARPOWER + power);

            printMatrix(matrix);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {

            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }

    private static boolean isInGalaxyCheck(int playerRow, int playerCol, int length) {
        return playerRow < 0 || playerRow >= length || playerCol < 0 || playerCol >= length;
    }

    private static int getFirstColSymbol(char symbol, int playerRow, char[][] matrix) {
        int index = -1;

        for (int col = 0; col < matrix.length; col++) {

            if (matrix[playerRow][col] == symbol) {
                index = col;
                break;
            }
        }

        return index;
    }

    private static int getFirstRowSymbol(char symbol, char[][] matrix) {
        int index = -1;

        int length = matrix.length;

        for (int row = 0; row < length; row++) {

            for (int col = 0; col < length; col++) {

                if (matrix[row][col] == symbol) {
                    index = row;
                    break;
                }
            }

            if (index != -1) {
                break;
            }
        }

        return index;
    }

    private static char[][] readMatrix(BufferedReader reader, int size) {
        char[][] matrix = new char[size][size];

        for (int row = 0; row < size; row++) {

            try {
                char[] colArray = reader.readLine().toCharArray();

                matrix[row] = colArray;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return matrix;
    }
}

