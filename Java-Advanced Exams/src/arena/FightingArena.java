package arena;

import java.util.ArrayList;
import java.util.List;

public class FightingArena {
    private List<Gladiator> gladiators;
    private String name;

    public FightingArena(String name) {
        this.name = name;
        this.gladiators = new ArrayList<>();
    }

    public void add(Gladiator gladiator) {
        this.gladiators.add(gladiator);
    }

    public void remove(String name) {
        Gladiator gladiator = null;

        for (Gladiator g : gladiators) {
            if (g.getName().equalsIgnoreCase(name)) {
                gladiator = g;
                break;
            }
        }

        this.gladiators.remove(gladiator);
    }

    public Gladiator getGladiatorWithHighestStatPower() {
        Gladiator greatGladiator = null;

        int maxPower = 0;

        for (Gladiator gladiator : this.gladiators) {

            if (gladiator.getStatPower() > maxPower) {
                greatGladiator = gladiator;
                maxPower = gladiator.getStatPower();
            }
        }

        return greatGladiator;
    }

    public Gladiator getGladiatorWithHighestWeaponPower() {
        Gladiator greatGladiator = null;

        int maxPower = 0;

        for (Gladiator gladiator : this.gladiators) {

            if (gladiator.getWeaponPower() > maxPower) {
                greatGladiator = gladiator;
                maxPower = gladiator.getWeaponPower();
            }
        }

        return greatGladiator;
    }

    public Gladiator getGladiatorWithHighestTotalPower() {
        Gladiator greatGladiator = null;

        int maxPower = 0;

        for (Gladiator gladiator : this.gladiators) {

            if (gladiator.getTotalPower() > maxPower) {
                greatGladiator = gladiator;
                maxPower = gladiator.getTotalPower();
            }
        }

        return greatGladiator;
    }

    public int getCount() {
        return this.gladiators.size();
    }

    @Override
    public String toString() {
        return String.format("%s – %d gladiators are participating.",
                this.name,
                this.getCount());
    }
}
