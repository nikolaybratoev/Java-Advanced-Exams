import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Helen {
    public static void main(String[] args) {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        try {
            int energy = Integer.parseInt(reader.readLine());

            int rows = Integer.parseInt(reader.readLine());

            char[][] field = new char[rows][];

            int parisRow = 0;
            int parisCol = 0;

            for (int i = 0; i < rows; i++) {

                String line = reader.readLine();

                field[i] = line.toCharArray();

                if (line.contains("P")) {
                    parisRow = i;
                    parisCol = line.indexOf("P");
                }
            }

            boolean isAbducted = false;

            while (energy > 0 && !isAbducted) {

                energy--;

                String[] direction = reader.readLine().split("\\s+");

                int row = Integer.parseInt(direction[1]);
                int col = Integer.parseInt(direction[2]);

                field[row][col] = 'S';

                if (direction[0].equals("up") && canMove(parisRow - 1, parisCol, field)) {

                    field[parisRow][parisCol] = '-';

                    parisRow--;

                    if (field[parisRow][parisCol] == 'S') {
                        energy -= 2;
                    } else if (field[parisRow][parisCol] == 'H') {
                        isAbducted = true;
                    }

                    field[parisRow][parisCol] = 'P';
                } else if (direction[0].equals("down") && canMove(parisRow + 1, parisCol, field)) {

                    field[parisRow][parisCol] = '-';

                    parisRow++;

                    if (field[parisRow][parisCol] == 'S') {
                        energy -= 2;
                    } else if (field[parisRow][parisCol] == 'H') {
                        isAbducted = true;
                    }

                    field[parisRow][parisCol] = 'P';
                } else if (direction[0].equals("left") && canMove(parisRow, parisCol - 1, field)) {

                    field[parisRow][parisCol] = '-';

                    parisCol--;

                    if (field[parisRow][parisCol] == 'S') {
                        energy -= 2;
                    } else if (field[parisRow][parisCol] == 'H') {
                        isAbducted = true;
                    }

                    field[parisRow][parisCol] = 'P';
                } else if (direction[0].equals("right") && canMove(parisRow, parisCol + 1, field)) {

                    field[parisRow][parisCol] = '-';

                    parisCol++;

                    if (field[parisRow][parisCol] == 'S') {
                        energy -= 2;
                    } else if (field[parisRow][parisCol] == 'H') {
                        isAbducted = true;
                    }

                    field[parisRow][parisCol] = 'P';
                }
            }

            if (isAbducted) {
                field[parisRow][parisCol] = '-';
                System.out.printf("Paris has successfully abducted Helen! Energy left: %d%n", energy);
            } else {
                field[parisRow][parisCol] = 'X';
                System.out.printf("Paris died at %d;%d.%n", parisRow, parisCol);
            }

            printField(field);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private static boolean canMove(int newRow, int newCol, char[][] field) {
        return newRow >= 0 && newRow < field.length && newCol >= 0 && newCol < field[newRow].length;
    }

    private static void printField(char[][] field) {
        for (int row = 0; row < field.length; row++) {

            for (int col = 0; col < field[row].length; col++) {

                System.out.print(field[row][col]);
            }
            System.out.println();
        }
    }
}

