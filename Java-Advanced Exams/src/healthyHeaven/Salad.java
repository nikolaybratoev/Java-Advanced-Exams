package healthyHeaven;

import java.util.ArrayList;
import java.util.List;

public class Salad {
    private List<Vegetable> products;
    private String name;

    public Salad(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public int getTotalCalories() {
        int totalCalories = 0;

        for (int i = 0; i < products.size(); i++) {
            totalCalories += products.get(i).getCalories();
        }

        return totalCalories;
    }

    public int getProductCount() {
        return this.products.size();
    }

    public void add(Vegetable product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("* Salad %s is %d calories and have %d products:",
                this.getName(),
                this.getTotalCalories(),
                this.getProductCount()).trim());

        sb.append(String.format("%n"));

        products.forEach(product -> sb.append(product.toString().trim()));

        return sb.toString().trim();
    }
}
