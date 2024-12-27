package comparators;

import models.Bus;

import java.util.Comparator;

public class BusComparator {

    // только для сортировки
    public static class FullComparison {
        public static Comparator<Bus> getFullComparison() {
            return new ByNumber().thenComparing(new ByModel()).thenComparing(new ByMileage());
        }
    }

    // для сортировок и бин поиска
    public static class ByNumber implements Comparator<Bus> {

        @Override
        public int compare(Bus bus1, Bus bus2) {
            return bus1.getNumber().compareTo(bus2.getNumber());
        }
    }

    // для сортировок и бин поиска
    public static class ByModel implements Comparator<Bus> {

        @Override
        public int compare(Bus bus1, Bus bus2) {
            return bus1.getModel().compareTo(bus2.getModel());
        }
    }

    // для сортировок и бин поиска
    public static class ByMileage implements Comparator<Bus> {

        @Override
        public int compare(Bus bus1, Bus bus2) {
            return bus1.getMileage().compareTo(bus2.getMileage());
        }
    }
}
