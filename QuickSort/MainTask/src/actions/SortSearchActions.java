package actions;

import enums.SortType;

import java.util.List;
import java.util.Scanner;

public interface SortSearchActions<T> {

    String getModelName();

    String getSortFieldChoice();

    /**
     * Сортировка массива models
     * <br>Если sortType DEFAULT, то это сортировка по умолчанию
     * <br>Если sortType BY_FIELD, то это сортировка по выбираемому полю
     * <br>Если сортировка ADDITIONAL, то это дополнительная сортировка
     * @param sortType тип сортировки
     * @param models массив автобусов
     */
    default void sortAction(SortType sortType, List<T> models) {
        if (sortType == SortType.DEFAULT) {
            defaultSort(models);
            return;
        }

        if (sortType == SortType.ADDITIONAL) {
            additionalSort(models);
            return;
        }

        Scanner actionScan = new Scanner(System.in);
        String inputAction;

        do {
            System.out.print(getSortFieldChoice());
            System.out.print("\n================\nВведите команду: ");
            inputAction = actionScan.nextLine();
            switch (inputAction) {
                case "1":
                    sortByFirstField(models);
                    return;
                case "2":
                    sortBySecondField(models);
                    return;
                case "3":
                    sortByThirdField(models);
                    return;
                case "4":
                    if (sortByFourthField(models)) return;
                    break;
                case "0":
                    System.out.print("\nВозврат в предыдущее меню.\n");
                    return;
                default:
                    System.out.print("\nКоманда не распознана, повторите ввод (0 - возврат в предыдущее меню).\n");
            }
        } while (true);
    }

    void defaultSort(List<T> models);

    void additionalSort(List<T> models);

    void sortByFirstField(List<T> models);

    void sortBySecondField(List<T> models);

    void sortByThirdField(List<T> models);

    boolean sortByFourthField(List<T> models);

    void sort(List<T> models, String msg, boolean isSkipOdd);

    T binarySearch(List<T> models);
}
