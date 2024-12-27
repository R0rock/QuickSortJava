package utilities;

public class Constants {
    public static final int DEFAULT_BUS_NUMBER = 1;
    public static final int DEFAULT_BUS_MILEAGE = 1;
    public static final String DEFAULT_BUS_MODEL = "1";

    public static final String DEFAULT_STUDENT_GROUP = "A";
    public static final double DEFAULT_STUDENT_SCORE = 1.0;
    public static final int DEFAULT_STUDENT_GRADE_BOOK_NUM = 1;

    public static final int DEFAULT_USER_ID = 1;
    public static final String DEFAULT_USER_NAME = "A";
    public static final String DEFAULT_USER_PASSWORD = "B";
    public static final String DEFAULT_USER_EMAIL = "F";

    public static final int USER_EMAIL_LENGTH = 12;
    public static final int USER_PASSWORD_LENGTH = 8;
    public static final String LETTERS_FOR_PASSWORD = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static final String NUMBERS_FOR_PASSWORD = "1234567890";
    public static final String SPECIAL_FOR_PASSWORD = "!@$^&*";
    public static final String REGEX_FOR_PASSWORD_CHECK = "^(?=.*[A-Z])(?=.*["
            + SPECIAL_FOR_PASSWORD
            + "])(?=.*[0-9])(?=.*[a-z]).{"
            + USER_PASSWORD_LENGTH
            +"}$";
}
