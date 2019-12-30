import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TrojanInvasion {
    private static final String SPARTANS_WIN = "The Spartans successfully repulsed the Trojan attack.";
    private static final String PLATES_LEFT = "Plates left: ";
    private static final String TROJANS_WIN = "The Trojans successfully destroyed the Spartan defense.";
    private static final String TROJANS_LEFT = "Warriors left: ";
    public static void main(String[] args) {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        try {
            int waves = Integer.parseInt(reader.readLine());

            ArrayDeque<Integer> queue = new ArrayDeque<>();
            ArrayDeque<Integer> stack = new ArrayDeque<>();

            Arrays.stream(reader.readLine().split("\\s+"))
                    .map(Integer::parseInt)
                    .forEach(queue::offer);

            boolean isDefeated = false;

            for (int wave = 1; wave <= waves; wave++) {

                if (queue.isEmpty() && wave % 3 != 0) {
                    isDefeated = true;
                    break;
                }

                Arrays.stream(reader.readLine().split("\\s+"))
                        .map(Integer::parseInt)
                        .forEach(stack::push);

                if (wave % 3 == 0) {
                    int newPlate = Integer.parseInt(reader.readLine());
                    queue.offer(newPlate);
                }

                while (!(queue.isEmpty() || stack.isEmpty())) {

                    int plate = queue.poll();
                    int attacker = stack.pop();

                    if (attacker > plate) {
                        attacker -= plate;
                        stack.addFirst(attacker);
                    } else if (plate > attacker) {
                        plate -= attacker;
                        queue.addFirst(plate);
                    }
                }
            }

            if (!queue.isEmpty() && !isDefeated) {

                System.out.println(SPARTANS_WIN);
                System.out.print(PLATES_LEFT);

                List<String> string = queue
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.toList());

                System.out.println(String.join(", ", string));

            } else {
                System.out.println(TROJANS_WIN);
                System.out.print(TROJANS_LEFT);

                List<String> string = stack
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.toList());

                System.out.println(String.join(", ", string));
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
