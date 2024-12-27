package algorithms;

import models.Filterable;

import java.util.List;

/**
 * Класс бинарного поиска
 */
public class BinarySearch {

    /**
     * Поиск элемента с помощью бинарного поиска, на отсортированном массиве
     * @param list коллекция, в которой необходимо осуществить поиск
     * @param target искомый элемент
     * @return индекс элемента в коллекции или -1, если элемент не найден
     * @param <T> тип элемента коллекции
     */
    public static <T extends Filterable<T>> int search(List<T> list, T target) {
        return search(list, target, 0, list.size());
    }

    private static <T extends Filterable<T>> int search(List<T> list, T target, int low, int high) {
        int middle = low  + ((high - low) / 2);

        if (middle < 0 || middle >= list.size() || high < low) {
            return -1;
        }

        int comp = list.get(middle).compTo(target);

        if (comp == 0) {
            return middle;
        } else if (comp > 0) {
            return search(list, target, low, middle - 1);
        } else {
            return search(list, target, middle + 1, high);
        }
    }
}
