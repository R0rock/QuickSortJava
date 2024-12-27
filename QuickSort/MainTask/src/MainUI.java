import java.util.*;

import actions.*;
import enums.SortType;

public class MainUI {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean isSortUsed = false;

    public static void main(String[] args) {
        String helloText = "Добро пожаловать в приложение демонстрации алгоритма \"Быстрой сортировки\"" +
                " (created by Clowns_team)";

        String baseMenu = """
                
                Выберите один из классов:
                1) Bus (Number, Model, Mileage)
                2) User (Id,Name, Password, e-mail)
                3) Student (Group Number, Average Score, Grade Book Number)
                0) Выход из приложения""";

        String inputMenu;

        System.out.println(helloText);
        do {
            System.out.println(baseMenu);
            System.out.print("================\nВведите команду: ");
            inputMenu = scanner.nextLine();

            switch (inputMenu) {
                case "1":
                    System.out.println("\nВы выбрали класс Bus (Number, Model, Mileage)");
                    modelsMenu(new BusFillActions());
                    break;
                case "2":
                    System.out.println("\nВы выбрали класс User (Name, Password, Mail)");
                    modelsMenu(new UserFillActions());
                    break;
                case "3":
                    System.out.println("\nВы выбрали класс Student (Group Number, Average Score, Grade Book Number)");
                    modelsMenu(new StudentFillActions());
                    break;
                case "0":
                    System.out.println("\nВыход из программы.");
                    break;
                default:
                    System.out.print("\nКоманда не распознана, повторите ввод (0 - для выхода).\n");
            }
        }
        while (!inputMenu.equals("0"));
    }

    private static <T> void modelsMenu(FillActions<T> fillActions) {
        String inputClass;
        List<T> models;
        do {
            System.out.println("\nВыберите способ заполнения массива " + fillActions.getModelName() + ": " +
                    "\n1) Из файла" +
                    "\n2) Рандом" +
                    "\n3) Вручную" +
                    "\n0) Вернуться в главное меню");
            System.out.print("================\nВведите команду: ");
            inputClass = scanner.nextLine();
            SortSearchActions<T> sortSearchActions = (SortSearchActions<T>) getSortSearchActions(fillActions.getModelName());

            switch (inputClass) {
                case "1":
                    System.out.println("\nВы выбрали 1 - \"Из файла\"\n");
                    models = fillActions.fillByFile();
                    sortSearchMenu(sortSearchActions, models);
                    break;
                case "2":
                    System.out.println("\nВы выбрали 2 - \"Рандом\"\n");
                    models = fillActions.fillRandom();
                    sortSearchMenu(sortSearchActions, models);
                    break;
                case "3":
                    System.out.println("\nВы выбрали 3 - \"Вручную\"\n");
                    models = fillActions.fillManual();
                    sortSearchMenu(sortSearchActions, models);
                    break;
                case "0":
                    System.out.println("\nВозврат в предыдущее меню.");
                    break;
                default:
                    System.out.print("\nКоманда не распознана, повторите ввод (0 - возврат в предыдущее меню).\n");
            }
        } while (!inputClass.equals("0"));
    }

    private static <T, R extends SortSearchActions<T>> void sortSearchMenu(R actions, List<T> models) {
        String actionMenu = """
                
                Массив %s (содержит %d записей)
                Выберите действие:
                1) Вывести массив в консоль
                2) Отсортировать массив быстрой сортировкой (поле по умолчанию)
                3) Отсортировать массив быстрой сортировкой по определенному полю
                4) Отсортировать массив по четным полям
                5) Найти элемент (бинарный поиск)
                0) Вернуться в предыдущее меню""";

        if (models == null) return;

        Scanner actionScan = new Scanner(System.in);
        String inputAction;

        do {
            System.out.printf(actionMenu, actions.getModelName(), models.size());
            System.out.print("\n================\nВведите команду: ");
            inputAction = actionScan.nextLine();

            switch (inputAction) {
                case "1":
                    System.out.printf("\nЭлементы в массиве %s:\n", actions.getModelName());
                    models.forEach(System.out::println);
                    break;
                case "2":
                    actions.sortAction(SortType.DEFAULT, models);
                    isSortUsed = true;
                    break;
                case "3":
                    actions.sortAction(SortType.BY_FIELD, models);
                    isSortUsed = true;
                    break;
                case "4":
                    actions.sortAction(SortType.ADDITIONAL, models);
                    isSortUsed = false;
                    break;
                case "5":
                    if (!isSortUsed) {
                        System.out.println("\nСначала отсортируйте массив любой сортировкой, кроме дополнительной");
                        break;
                    }
                    T model = actions.binarySearch(models);
                    if (model != null) System.out.println(model);
                    break;
                case "0":
                    System.out.print("\nВозврат в предыдущее меню.\n");
                    break;
                default:
                    System.out.print("\nКоманда не распознана, повторите ввод (0 - возврат в предыдущее меню).\n");
            }
        } while (!inputAction.equals("0"));
    }

    private static SortSearchActions<?> getSortSearchActions(String modelName) {
        return switch (modelName) {
            case "Bus" -> new BusSortSearchActions();
            case "User" -> new UserSortSearchActions();
            case "Student" -> new StudentSortSearchActions();
            default -> null;
        };
    }
}
