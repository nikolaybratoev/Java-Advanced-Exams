import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class SpaceshipCrafting {

    private static final String ALUMINIUM = "Aluminium";
    private static final String GLASS = "Glass";
    private static final String LITHIUM = "Lithium";
    private static final String CARBON = "Carbon fiber";
    private static final String SPACESHIP_CREATED = "Wohoo! You succeeded in building the spaceship!";
    private static final String SPACESHIP_NOT_CREATED = "Ugh, what a pity! You didn't have enough materials to build the spaceship.";
    private static final String PHYSICAL_ITEMS_LEFT = "Physical items left: ";
    private static final String NONE = "none";
    private static final String LIQUIDS_LEFT = "Liquids left: ";
    private static final int GLASS_VALUE = 25;
    private static final int ALUMINIUM_VALUE = 50;
    private static final int LITHIUM_VALUE = 75;
    private static final int CARBON_VALUE = 100;

    private static void main(String[] args) {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        ArrayDeque<Integer> liquids = new ArrayDeque<>();
        ArrayDeque<Integer> physicals = new ArrayDeque<>();

        try {
            Arrays.stream(reader.readLine().split("\\s+"))
                    .map(Integer::parseInt)
                    .forEach(liquids::offer);

            Arrays.stream(reader.readLine().split("\\s+"))
                    .map(Integer::parseInt)
                    .forEach(physicals::push);

            Map<String, Integer> advancedMaterials = new TreeMap<>();

            advancedMaterials.put(ALUMINIUM, 0);
            advancedMaterials.put(GLASS, 0);
            advancedMaterials.put(LITHIUM, 0);
            advancedMaterials.put(CARBON, 0);

            while (!(liquids.isEmpty() || physicals.isEmpty())) {

                int currentLiquid  = liquids.poll();
                int currentItem = physicals.pop();

                int advanceMaterialSum = currentItem + currentLiquid;

                String advanceMaterial = getAdvancedMaterial(advanceMaterialSum);

                if (advanceMaterial == null) {
                    physicals.push(currentItem + 3);
                } else {
                    int newValue = advancedMaterials.get(advanceMaterial) + 1;
                    advancedMaterials.put(advanceMaterial, newValue);
                }
            }

            if (advancedMaterials.entrySet().stream().allMatch(e -> e.getValue() > 0)) {

                System.out.println(SPACESHIP_CREATED);
            } else {
                System.out.println(SPACESHIP_NOT_CREATED);
            }

            printLiquids(liquids);

            printPhysicalItems(physicals);

            for (Map.Entry<String, Integer> entry : advancedMaterials.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void printPhysicalItems(ArrayDeque<Integer> physicals) {
        String itemsLeft = PHYSICAL_ITEMS_LEFT;

        if (physicals.isEmpty()) {
            itemsLeft += NONE;
        } else {
            itemsLeft += joinStack(physicals);
        }

        System.out.println(itemsLeft);
    }

    private static void printLiquids(ArrayDeque<Integer> liquids) {
        String liquidsLeft = LIQUIDS_LEFT;

        if (liquids.isEmpty()) {
            liquidsLeft += NONE;
        } else {
            liquidsLeft += joinQueue(liquids);
        }

        System.out.println(liquidsLeft);
    }

    private static String joinStack(ArrayDeque<Integer> physicals) {
        StringBuilder sb = new StringBuilder();

        while (!physicals.isEmpty()) {

            sb.append(physicals.pop());

            if (physicals.isEmpty()) {
                break;
            }

            sb.append(", ");
        }

        return sb.toString().trim();
    }

    private static String joinQueue(ArrayDeque<Integer> liquids) {
        StringBuilder sb = new StringBuilder();

        while (!liquids.isEmpty()) {

            sb.append(liquids.poll());

            if (liquids.isEmpty()) {
                break;
            }

            sb.append(", ");
        }

        return sb.toString().trim();
    }

    private static String getAdvancedMaterial(int advanceMaterialSum) {
        String advancedMaterial = null;

        switch (advanceMaterialSum) {

            case GLASS_VALUE:
                advancedMaterial = GLASS;
                break;

            case ALUMINIUM_VALUE:
                advancedMaterial = ALUMINIUM;
                break;

            case LITHIUM_VALUE:
                advancedMaterial = LITHIUM;
                break;

            case CARBON_VALUE:
                advancedMaterial = CARBON;
                break;
        }

        return advancedMaterial;
    }
}

