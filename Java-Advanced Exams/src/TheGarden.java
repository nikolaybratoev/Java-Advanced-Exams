import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TheGarden {
    public static void main(String[] args) {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        try {
            int rows = Integer.parseInt(reader.readLine());

            char[][] garden = new char[rows][];

            for (int i = 0; i < garden.length; i++) {

                String line = reader.readLine().replaceAll("\\s+", "");

                garden[i] = new char[line.length()];

                for (int j = 0; j < line.length(); j++) {
                    garden[i][j] = line.charAt(j);
                }
            }

            int carrots = 0;
            int potatoes = 0;
            int lettuce = 0;
            int harmedVegetables = 0;

            String input = reader.readLine();

            while (!input.equalsIgnoreCase("End of Harvest")) {

                String[] tokens = input.split("\\s+");
                String command = tokens[0];

                int row = Integer.parseInt(tokens[1]);
                int col = Integer.parseInt(tokens[2]);

                switch (command) {

                    case "Harvest":
                        if (checkIndex(garden, row, col) && garden[row][col] != ' ') {

                            switch (garden[row][col]) {
                                case 'C':
                                    carrots++;
                                    break;

                                case 'P':
                                    potatoes++;
                                    break;

                                case 'L':
                                    lettuce++;
                                    break;
                            }

                            garden[row][col] = ' ';
                        }
                        break;

                    case "Mole":
                        String direction = tokens[3];

                        if (checkIndex(garden, row, col)) {

                            switch (direction.toLowerCase()) {

                                case "up":
                                    for (int i = row; i >= 0; i -= 2) {

                                        if (checkIndex(garden, i, col) && garden[i][col] != ' ') {
                                            garden[i][col] = ' ';
                                            harmedVegetables++;
                                        }
                                    }
                                    break;

                                case "down":
                                    for (int i = row; i < rows; i += 2) {

                                        if (checkIndex(garden, i, col) && garden[i][col] != ' ') {
                                            garden[i][col] = ' ';
                                            harmedVegetables++;
                                        }
                                    }
                                    break;

                                case "right":
                                    for (int i = col; i < garden[row].length; i += 2) {

                                        if (checkIndex(garden, row, i) && garden[row][i] != ' ') {
                                            garden[row][i] = ' ';
                                            harmedVegetables++;
                                        }
                                    }
                                    break;

                                case "left":
                                    for (int i = col; i >= 0; i -= 2) {

                                        if (checkIndex(garden, row, i) && garden[row][i] != ' ') {
                                            garden[row][i] = ' ';
                                            harmedVegetables++;
                                        }
                                    }
                                    break;
                            }
                        }
                        break;
                }

                input = reader.readLine();

            }

            printGarden(garden);

            System.out.printf("Carrots: %d%nPotatoes: %d%nLettuce: %d%nHarmed vegetables: %d%n",
                    carrots,
                    potatoes,
                    lettuce,
                    harmedVegetables);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void printGarden(char[][] garden) {
        for (int i = 0; i < garden.length; i++) {

            for (int j = 0; j < garden[i].length; j++) {

                if (j == garden[i].length - 1) {
                    System.out.printf("%c", garden[i][j]);
                } else {
                    System.out.printf("%c ", garden[i][j]);
                }
            }

            System.out.println();
        }
    }

    private static boolean checkIndex(char[][] garden, int row, int col) {
        return row < garden.length && row >= 0 && col < garden[row].length && col >= 0;
    }
}
