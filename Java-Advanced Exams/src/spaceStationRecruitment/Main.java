package spaceStationRecruitment;

public class Main {
    public static void main(String[] args) {

        SpaceStation spaceStation = new SpaceStation("Apolo", 10);

        Astronaut astronaut = new Astronaut("Stephen", 40, "Bulgaria");

        System.out.println(astronaut);

        spaceStation.add(astronaut);

        spaceStation.remove("Astronaut name");

        Astronaut secondAstronaut = new Astronaut("Mark", 34, "UK");

        spaceStation.add(secondAstronaut);

        Astronaut oldestAstronaut = spaceStation.getOldestAstronaut();

        Astronaut astronautStephen = spaceStation.getAstronaut("Stephen");

        System.out.println(oldestAstronaut);

        System.out.println(astronautStephen);

        System.out.println(spaceStation.getCount());

        System.out.println(spaceStation.report());

    }
}
