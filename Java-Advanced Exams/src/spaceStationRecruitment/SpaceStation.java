package spaceStationRecruitment;

import java.util.ArrayList;
import java.util.List;

public class SpaceStation {
    private String name;
    private int capacity;
    private List<Astronaut> data;
    
    public SpaceStation(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getCapacity() {
        return this.capacity;
    }
    
    public int getCount() {
        return this.data.size();
    }
    
    public void add(Astronaut astronaut) {
        if (this.getCount() < this.getCapacity()) {
            this.data.add(astronaut);
        }
    }
    
    public boolean remove(String name) {
        Astronaut astronaut = null;

        for (Astronaut datum : this.data) {

            if (datum.getName().equalsIgnoreCase(name)) {
                astronaut = datum;
                break;
            }
        }

        return this.data.remove(astronaut);
    }

    public Astronaut getOldestAstronaut() {
        return this.data
                .stream()
                .sorted((a, b) -> b.getAge() - a.getAge())
                .findFirst()
                .orElse(null);
    }

    public Astronaut getAstronaut(String name) {
        return this.data
                .stream()
                .filter(a -> a.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public String report() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Astronauts working at Space Station %s:",
                this.getName()));

        sb.append(System.lineSeparator());

        for (Astronaut astronaut : this.data) {
            sb.append(astronaut).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
