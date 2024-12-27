package models;

import comparators.BusComparator;

import java.util.Comparator;

public class Bus implements Filterable<Bus> {
    private final Integer number;
    private final String model;
    private final Integer mileage;
    // Чтобы не передавать в методы явным образом компараторы, мы один раз прописываем по какому полю будет любое сравнение элементов данного класса и если захотим в дальнейшем поменять его, то можно поменять лишь данный параметр
    private static Comparator<Bus> comp;

    static {
        comp = BusComparator.FullComparison.getFullComparison();
    }

    private Bus(Integer number, String model, Integer mileage) {
        super();
        this.number = number;
        this.model = model;
        this.mileage = mileage;
    }

    public Integer getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public Integer getMileage() {
        return mileage;
    }

    @Override
    public String toString() {
        return "Bus{" +
                " number='" + number + '\'' +
                ", \tmodel='" + model + '\'' +
                ", \tmileage=" + mileage +
                '}';
    }

    public static Comparator<Bus> getComp() {
        return comp;
    }

    public static void setComp(Comparator<Bus> comp) {
        Bus.comp = comp;
    }

    @Override
    public int compTo(Bus obj) {
        return comp.compare(this, obj);
    }

    @Override
    public boolean isEven() {
        return number % 2 == 0;
    }

    public static class BusBuilder {
        private Integer number;
        private String model;
        private Integer mileage;

        public BusBuilder setNumber(Integer number) {
            this.number = number;
            return this;
        }

        public BusBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public BusBuilder setMileage(Integer mileage) {
            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            return new Bus(number, model, mileage);
        }
    }
}