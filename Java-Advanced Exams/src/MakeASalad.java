import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MakeASalad {

    private static final int TOMATO_CALORIES = 80;
    private static final int CARROT_CALORIES = 136;
    private static final int LETTUCE_CALORIES = 109;
    private static final int POTATO_CALORIES = 215;

    public static void main(String[] args) {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        try {
            ArrayDeque<String> queue = new ArrayDeque<>();
            ArrayDeque<Integer> stack = new ArrayDeque<>();

            Arrays.stream(reader.readLine().split("\\s+"))
                    .forEach(queue::offer);

            Arrays.stream(reader.readLine().split("\\s+"))
                    .map(Integer::parseInt)
                    .forEach(stack::push);

            List<Integer> readySalads = new ArrayList<>();

            int leavedVeg = 0;
            boolean haveVeg = false;

            while (!(queue.isEmpty() || stack.isEmpty())) {

                int currentSalad = stack.peek();

                boolean isReady = false;

                while (currentSalad > 0 || !queue.isEmpty()) {

                    String vegetable = queue.peek().toLowerCase();

                    if ("tomato".equals(vegetable)) {
                        queue.poll();
                        currentSalad -= TOMATO_CALORIES;

                        if (currentSalad <= 0) {
                            int salad = stack.pop();
                            readySalads.add(salad);
                            isReady = true;
                        }

                        if (currentSalad < 0) {
                            leavedVeg += Math.abs(currentSalad);
                        }

                        if (haveVeg && queue.isEmpty()) {
                            currentSalad -= leavedVeg;
                            int salad = stack.pop();
                            readySalads.add(salad);
                            isReady = true;
                        }
                    } else if ("carrot".equals(vegetable)) {
                        queue.poll();
                        currentSalad -= CARROT_CALORIES;

                        if (currentSalad <= 0) {
                            int salad = stack.pop();
                            readySalads.add(salad);
                            isReady = true;
                        }

                        if (currentSalad < 0) {
                            leavedVeg += Math.abs(currentSalad);
                        }

                        if (haveVeg && queue.isEmpty()) {
                            currentSalad -= leavedVeg;
                            int salad = stack.pop();
                            readySalads.add(salad);
                            isReady = true;
                        }
                    } else if ("lettuce".equals(vegetable)) {
                        queue.poll();
                        currentSalad -= LETTUCE_CALORIES;

                        if (currentSalad <= 0) {
                            int salad = stack.pop();
                            readySalads.add(salad);
                            isReady = true;
                        }

                        if (currentSalad < 0) {
                            leavedVeg += Math.abs(currentSalad);
                        }

                        if (haveVeg && queue.isEmpty()) {
                            currentSalad -= leavedVeg;
                            int salad = stack.pop();
                            readySalads.add(salad);
                            isReady = true;
                        }
                    } else if ("potato".equals(vegetable)) {
                        queue.poll();
                        currentSalad -= POTATO_CALORIES;

                        if (currentSalad <= 0) {
                            int salad = stack.pop();
                            readySalads.add(salad);
                            isReady = true;
                        }

                        if (currentSalad < 0) {
                            leavedVeg += Math.abs(currentSalad);
                        }

                        if (haveVeg && queue.isEmpty()) {
                            currentSalad -= leavedVeg;
                            int salad = stack.pop();
                            readySalads.add(salad);
                            isReady = true;
                        }
                    }

                    if (isReady) {
                        break;
                    }

                    if (leavedVeg > 0) {
                        haveVeg = true;
                    }
                }
            }

            if (readySalads.size() > 0) {
                readySalads.forEach(salad -> System.out.print(salad + " "));
                System.out.println();
            }

            if (!stack.isEmpty()) {
                stack.forEach(salad -> System.out.print(salad + " "));
            }

            if (!queue.isEmpty()) {
                queue.forEach(vegetable -> System.out.print(vegetable + " "));
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
