package actions;

import algorithms.AdditionalSort;
import algorithms.BinarySearch;
import algorithms.QuickSort;
import comparators.BusComparator;
import models.Bus;
import utilities.Validate;

import java.util.Comparator;
import java.util.List;

import static utilities.ManualInputUtilities.readInt;
import static utilities.ManualInputUtilities.readString;
import static utilities.Constants.*;
import static utilities.FileUtilities.fileWriting;

public class BusSortSearchActions implements SortSearchActions<Bus> {

    @Override
    public String getModelName() {
        return "Bus";
    }

    @Override
    public String getSortFieldChoice() {
        return """
                
                Выберите поля для сортировки:
                1) По номеру (by number)
                2) По модели (by model)
                3) По пробегу (by mileage)
                0) Вернуться в предыдущее меню""";
    }

    /**
     * Сортировка автобусов по умолчанию, по пробегу
     * @param models массив автобусов
     */
    @Override
    public void defaultSort(List<Bus> models) {
        Bus.setComp(BusComparator.FullComparison.getFullComparison());
        sort(models, "\nМассив отсортирован по умолчанию", false);
    }

    @Override
    public void additionalSort(List<Bus> models) {
        Bus.setComp(new BusComparator.ByNumber());
        sort(models, "\nМассив отсортирован только по четным значениям номеров (by number).", true);
    }

    @Override
    public void sortByFirstField(List<Bus> models) {
        Bus.setComp(new BusComparator.ByNumber());
        sort(models, "\nМассив отсортирован по номеру (by number).", false);
    }

    @Override
    public void sortBySecondField(List<Bus> models) {
        Bus.setComp(new BusComparator.ByModel());
        sort(models, "\nМассив отсортирован по модели (by model).", false);
    }

    @Override
    public void sortByThirdField(List<Bus> models) {
        Bus.setComp(new BusComparator.ByMileage());
        sort(models, "\nМассив отсортирован по пробегу (by mileage).", false);
    }

    @Override
    public boolean sortByFourthField(List<Bus> models) {
        System.out.print("\nКоманда не распознана, повторите ввод (0 - возврат в предыдущее меню).\n");
        return false;
    }

    @Override
    public void sort(List<Bus> models, String msg, boolean isSkipOdd) {
        StringBuilder infoToFile = new StringBuilder();
        if (!isSkipOdd)
            QuickSort.sort(models);
        else
            AdditionalSort.sort(models);

        for (Bus it : models) {
            infoToFile.append(it).append("\n");
        }
        fileWriting(infoToFile.toString());
        System.out.println(msg);
    }

    @Override
    public Bus binarySearch(List<Bus> models) {
        Comparator<Bus> comp = Bus.getComp();
        Bus lookingBus;
        Bus.BusBuilder busBuilder = new Bus.BusBuilder();

        switch (comp) {
            case BusComparator.ByNumber _ -> {
                int number = readInt("Введите номер автобуса (0 - отмена): ", Validate::isPositiveInteger);
                if (number == 0) return null;
                lookingBus = busBuilder.setNumber(number).setModel(DEFAULT_BUS_MODEL).setMileage(DEFAULT_BUS_MILEAGE).build();
            }
            case BusComparator.ByModel _ -> {
                String model = readString("Введите модель автобуса (0 - отмена): ", _ -> true);
                if (model.equals("0")) return null;
                lookingBus = busBuilder.setNumber(DEFAULT_BUS_NUMBER).setModel(model).setMileage(DEFAULT_BUS_MILEAGE).build();
            }
            case BusComparator.ByMileage _ -> {
                int mileage = readInt("Введите пробег автобуса (0 - отмена): ", Validate::isPositiveInteger);
                if (mileage == 0) return null;
                lookingBus = busBuilder.setNumber(DEFAULT_BUS_NUMBER).setModel(DEFAULT_BUS_MODEL).setMileage(mileage).build();
            }
            case null, default -> {
                int number = readInt("Введите номер автобуса (0 - отмена): ", Validate::isPositiveInteger);
                if (number == 0) return null;
                String model = readString("Введите модель автобуса (0 - отмена): ", _ -> true);
                if (model.equals("0")) return null;
                int mileage = readInt("Введите пробег автобуса (0 - отмена): ", Validate::isPositiveInteger);
                if (mileage == 0) return null;
                lookingBus = busBuilder.setNumber(number).setModel(model).setMileage(mileage).build();
            }
        }
        int index = BinarySearch.search(models, lookingBus);
        if (index == -1) {
            System.out.println("Искомый автобус не найден в массиве.");
            return null;
        }
        Bus bus = models.get(index);
        fileWriting("Found " + bus);
        System.out.print("Автобус найден: ");
        return bus;
    }
}
