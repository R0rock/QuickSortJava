package utilities;

import static utilities.Constants.*;

/**
 * Класс для валидации данных
 */
public class Validate {
    /**
     * Валидация объекта Bus, поле номер (Number)
     * целое положительное число от 2 до 5 знаков
     * @param data строка для проверки
     * @return true or false
     */
    public static boolean busNumberValidate(String data) {
        return isPositiveInteger(data) && data.length() >= 2 && data.length() <= 5;
    }

    /**
     * Валидация объекта Bus, поле модель (Model)
     * строка из заглавных или строчных латинских букв, от 3 до 10 символов
     * @param data строка для проверки
     * @return true or false
     */
    public static boolean busModelValidate(String data) {
        return data.matches("^[A-Za-z]{3,10}$");
    }

    /**
     * Валидация объекта Bus, поле пробег (Mileage)
     * целое положительное число от 2 до 8 знаков
     * @param data строка для проверки
     * @return true or false
     */
    public static boolean busMileageValidate(String data) {
        return data.matches("^[0-9]{2,8}$");
    }

    /**
     * Валидация объекта User, поле имя (Name)
     * строка состоящая из латинских букв от 2 до 10 символов
     * @param data строка для проверки
     * @return true or false
     */
    public static boolean userNameValidate(String data) {
        return data.matches("^[A-Za-z]{2,10}$");
    }

    /**
     * Валидация строки на целое, положительное число
     * @param data строка для проверки
     * @return true or false
     */
    public static boolean isPositiveInteger(String data) {
        try {
            return Integer.parseInt(data) > 0;
        } catch (NumberFormatException _) {
            return false;
        }
    }

    /**
     * Валидация строки на положительный double
     * @param data строка для проверки
     * @return true or false
     */
    public static boolean isPositiveDouble(String data) {
        try {
            return Double.parseDouble(data) > 0;
        } catch (NumberFormatException _) {
            return false;
        }
    }

    /**
     * Валидация объекта User, поле пароль (Password)
     * длина 8 символов, обязательное наличие
     * заглавной, прописной латинских букв, цифры, спец.символа !@$^&*
     * @param data строка для проверки
     * @return true or false
     */
    public static boolean userPasswordValidate(String data) {
        return data.matches(REGEX_FOR_PASSWORD_CHECK);
    }

    /**
     * Валидация объекта User, поле электронная почта (EMail)
     * имя_почты@домен_почты.верхний_домен где:
     * имя_почты - прописные латинские буквы и цифры от 2 до 20 символов
     * домен_почты - прописные латинские буквы и цифры от 2 до 10 символов
     * верхний_домен - латинские прописные буквы от 2 до 4 символов
     * пример - example@maildomain.com
     * @param data строка для проверки
     * @return true or false
     */
    public static boolean userEMailValidate(String data) {
        return data.matches("^[a-z]{2,20}+@[a-z]{2,10}+.[a-z]{2,4}$");
    }

    /**
     * Валидация объекта Student, поля номера группы (Group)
     * вида X-NNN, где
     * X - любая заглавная латинская буква
     * NNN - 3х значное число (001 допускается)
     * @param data строка для проверки
     * @return true or false
     */
    public static boolean studentGroupValidate(String data) {
        return data.matches("^[A-Z]{1}+-[0-9]{3}$");
    }

    /**
     * Валидация объекта Student, поля среднего балла (AverageScore)
     * положительное число, целое или дробное (максимальный балл 10.0)
     * @param data строка для проверки
     * @return true or false
     */
    public static boolean studentAverageScoreValidate(String data) {
        return isPositiveDouble(data) && Double.parseDouble(data) > 0 && Double.parseDouble(data) <= 10;
    }

    /**
     * Валидация объекта Student, поля зачетной книжки (GradeBook)
     * положительное число от 3 до 6 символов
     * @param data строка для проверки
     * @return true or false
     */
    public static boolean studentGradeBookValidate(String data) {
        return data.matches("^[0-9]{3,6}$");
    }
}