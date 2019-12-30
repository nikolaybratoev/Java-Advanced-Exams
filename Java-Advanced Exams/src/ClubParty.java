import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class ClubParty {
    public static void main(String[] args) {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        try {
            int capacity = Integer.parseInt(reader.readLine());

            String[] input = reader.readLine().split("\\s+");

            List<Integer> reservations = new ArrayList<>();

            ArrayDeque<String> hallsQueue = new ArrayDeque<>();

            int currentSum = 0;

            for (int i = input.length - 1; i > 0; i--) {

                boolean isNumber = true;

                try {
                    int number = Integer.parseInt(input[i]);
                } catch (NumberFormatException | NullPointerException e) {
                    isNumber = false;
                }

                if (isNumber && hallsQueue.isEmpty()) {
                    continue;
                }

                if (isNumber) {

                    int number = Integer.parseInt(input[i]);

                    if (currentSum + number <= capacity) {
                        currentSum += number;
                        reservations.add(number);
                    }

                    if (currentSum + number > capacity) {

                        String pattern = reservations.toString().replaceAll("[\\[\\]]", "");

                        String hall = hallsQueue.peek();

                        System.out.printf("%s -> %s%n", hall, pattern);

                        reservations.clear();

                        hallsQueue.poll();

                        currentSum = 0;

                        if (number > capacity) {
                            currentSum += number;
                            reservations.add(number);
                        }
                    }
                } else {
                    hallsQueue.offer(input[i]);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}

