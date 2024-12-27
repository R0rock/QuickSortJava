package utilities;

import enums.BusModelEnum;
import enums.DomainUserEnum;
import enums.MailUserEnum;
import enums.UserNameEnum;

import static utilities.Constants.*;

/**
 * Утилитарный класс с разными функциями
 */
public final class RandomUtilities {

    /**
     * Генератор случайных чисел в заданном диапазоне
     * @param min нижняя граница
     * @param max верхняя граница
     * @return случайное сгенерированное число
     */
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Генератор номера автобуса для класса Bus в формате ББЧЧЧБ, где Б - латинская заглавная буква, Ч - число в dec
     * @return строка - номер автобуса
     */
    @Deprecated
    public static String getRandomBusNumber() {
        return (char) getRandomNumber('A', 'Z')+
                ""+
                getRandomNumber(100, 999)+
                (char) getRandomNumber('A', 'Z')+
                (char) getRandomNumber('A', 'Z');
    }

    /**
     * Генератор случайного выбора значения из перечислений
     * @param enumName имя класса-перечисления
     * @return случайное значение из enum
     */
    public static String getRandomFromEnum(String enumName) {
        return switch (enumName) {
            case "Bus" -> BusModelEnum.values()[getRandomNumber(0, BusModelEnum.values().length)].toString();
            case "User" -> UserNameEnum.values()[getRandomNumber(0, UserNameEnum.values().length)].toString();
            case "Mail" -> MailUserEnum.values()[getRandomNumber(0, MailUserEnum.values().length)].toString();
            case "Domain" -> DomainUserEnum.values()[getRandomNumber(0, UserNameEnum.values().length)].toString();
            default -> "";
        };
    }

    /**
     * Генерирует пароль для класса User, отвечающий сложности т.е. обязательное наличие прописной, заглавной латинских
     * букв, цифры, специального символа
     * @return сгенерированный пароль
     */
    public static String getRandomUserPassword() {
        StringBuilder password;
        do {
            password = new StringBuilder();
            for (int i = 0; i < USER_PASSWORD_LENGTH; i++) {
                password.append((LETTERS_FOR_PASSWORD+NUMBERS_FOR_PASSWORD+SPECIAL_FOR_PASSWORD)
                        .charAt(getRandomNumber(1, (LETTERS_FOR_PASSWORD+NUMBERS_FOR_PASSWORD+SPECIAL_FOR_PASSWORD).length())));
            }
        } while (!password.toString().matches(REGEX_FOR_PASSWORD_CHECK));
        return password.toString();
    }

    /**
     * Генерирует почту для класса User, с символом @, имени домена почты и домена верхнего уровня
     * @return сгенерированную почту
     */
    public static String getRandomUserMail() {
        StringBuilder mail = new StringBuilder();

        for (int i = 0; i < USER_EMAIL_LENGTH; i++) {
            mail.append((char) getRandomNumber('a', 'z'));
        }

        return mail
                .append("@")
                .append(getRandomFromEnum("Mail"))
                .append(".")
                .append(getRandomFromEnum("Domain"))
                .toString();
    }

    /**
     * Метод для генерации номера группы для класса Student
     * @return строку - номер группы в формате Б-ЧЧЧ, где Б - заглавная латинская буква, Ч - положительное число
     */
    public static String getRandomStudentGroup() {
        return (char) getRandomNumber('A', 'Z') + "-" + getRandomNumber(100, 999);
    }

    /**
     * Генератор случайных чисел для среднего балла Student
     * @param min нижняя граница
     * @param max верхняя граница
     * @return double с точностью до 2х знаков после занятой
     */
    public static double getRandomAverageScore(double min, double max) {
        return (double) Math.round(((Math.random() * (max - min)) + min) * 100) / 100;
    }

}
