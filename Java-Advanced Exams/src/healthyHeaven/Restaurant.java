package healthyHeaven;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private List<Salad> data;
    private String name;

    public Restaurant(String name) {
        this.name = name;
        this.data = new ArrayList<>();
    }

    public void add(Salad salad) {
        this.data.add(salad);
    }

    public boolean buy(String name) {
        return this.data.removeIf(salad -> salad.getName().equalsIgnoreCase(name));
    }

    public Salad getHealthiestSalad() {
        int minCalories = Integer.MAX_VALUE;

        Salad salad = null;

        for (int i = 0; i < this.data.size(); i++) {

            if (this.data.get(i).getTotalCalories() < minCalories) {
                minCalories = this.data.get(i).getTotalCalories();
                salad = this.data.get(i);
            }
        }

        return salad;
    }

    public String generateMenu() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s have %d salads:%n",
                this.name,
                this.data.size()).trim());

        sb.append(String.format("%n"));

        this.data.forEach(salad -> sb.append(salad.toString().trim()));

        return sb.toString().trim();
    }
}