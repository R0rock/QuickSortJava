package algorithms;

import comparators.*;
import models.*;
import java.util.*;
import models.Filterable;

public class AdditionalSort {
    public static <T extends Filterable<T>> void sort(List<T> array) {
        List<Integer> evenIndices = new ArrayList<>();
        List<T> evenObjects = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            T object = array.get(i);
            if (object.isEven()) {
                evenIndices.add(i);
                evenObjects.add(object);
            }
        }

        sort(evenObjects, 0, evenObjects.size() - 1);

        for (int i = 0; i < evenIndices.size(); i++) {
            array.set(evenIndices.get(i), evenObjects.get(i));
        }
    }

    private static <T extends Filterable<T>> void sort(List<T> array, int lowIndex, int highIndex) {
        if (lowIndex < highIndex) {
            int partitionIndex = partition(array, lowIndex, highIndex);
            sort(array, lowIndex, partitionIndex - 1);
            sort(array, partitionIndex + 1, highIndex);
        }
    }

    private static <T extends Filterable<T>> int partition(List<T> array, int lowIndex, int highIndex) {
        int pivotIndex = getMedianPivot(array, lowIndex, highIndex);
        T pivot = array.get(pivotIndex);
        swap(array, pivotIndex, highIndex);

        int partitionIndex = lowIndex;
        for (int i = lowIndex; i < highIndex; i++) {
            if (array.get(i).compTo(pivot) < 0) {
                swap(array, i, partitionIndex);
                partitionIndex++;
            }
        }
        swap(array, partitionIndex, highIndex);

        return partitionIndex;
    }

    private static <T> void swap(List<T> array, int index1, int index2) {
        T temp = array.get(index1);
        array.set(index1, array.get(index2));
        array.set(index2, temp);
    }

    private static <T> int getMedianPivot(List<T> array, int lowIndex, int highIndex) {
        int middleIndex = (int)Math.floor((highIndex - lowIndex) / 2.0);
        T firstElement = array.get(lowIndex);
        T lastElement = array.get(highIndex);
        T middleElement = array.get(middleIndex);

        List<T> elements = Arrays.asList(firstElement, lastElement, middleElement);
        elements.sort(getComparatorByClassName(firstElement.getClass().getSimpleName()));

        T medianElement = elements.get(1);
        return array.indexOf(medianElement);
    }

    private static <T> Comparator<T> getComparatorByClassName(String className) {
        return switch (className) {
            case "Bus" -> (Comparator<T>) new BusComparator.ByNumber();
            case "Student" -> (Comparator<T>) new StudentComparator.ByGradeBook();
            case "User" -> (Comparator<T>) new UserComparator.ById();
            default -> throw new IllegalArgumentException("Объект этого класса не поддерживается:" + className);
        };
    }
}
