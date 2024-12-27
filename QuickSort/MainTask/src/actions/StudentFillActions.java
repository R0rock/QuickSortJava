package actions;

import utilities.ManualInputUtilities;
import models.Student;
import utilities.Validate;

import java.util.ArrayList;
import java.util.List;

import static utilities.ManualInputUtilities.*;
import static utilities.FileUtilities.readFile;
import static utilities.RandomUtilities.*;
import static utilities.RandomUtilities.getRandomAverageScore;
import static utilities.Validate.*;

public class StudentFillActions implements FillActions<Student> {
    private static final models.Student.StudentBuilder studentBuilder = new Student.StudentBuilder();

    @Override
    public String getModelName() {
        return "Student";
    }

    /**
     * Метод заполнения массива Student из файла input_Student.txt
     * @return массив List Student
     */
    @Override
    public List<Student> fillByFile() {
        String fileName = "input_Student.txt";
        //TODO add checkers for fields
        return readFile(fileName, (data, _) -> studentBuilder
                .setGroup(data[0])
                .setScore(Double.parseDouble(data[1]))
                .setGradeBookNum(Integer.parseInt(data[2]))
                .build(),
                (String[] data) -> studentGroupValidate(data[0]) &&
                        studentAverageScoreValidate(data[1]) && studentGradeBookValidate(data[2])
        );
    }

    /**
     * Метод заполнения массива Student ручным способом
     * @return массив List Student
     */
    @Override
    public List<Student> fillManual() {
        return ManualInputUtilities.fillManual(getModelName(), (i) -> {
            String group = readString(String.format("%s[%d] - Введите группу студента (0 - отмена): ",
                    getModelName(), i), Validate::studentGroupValidate);
            if (group.equals("0")) return null;
            double averageScore = readDouble(String.format("%s[%d] - Введите средний балл студента (0 - отмена): ",
                    getModelName(), i), Validate::studentAverageScoreValidate);
            if (averageScore == 0) return null;

            int gradeBook = readInt(String.format("%s[%d] - Введите номер зачетной книжки студента (0 - отмена): ",
                    getModelName(), i), Validate::studentGradeBookValidate);
            if (gradeBook == 0) return null;

            return studentBuilder
                    .setGroup(group)
                    .setScore(averageScore)
                    .setGradeBookNum(gradeBook)
                    .build();
        });
    }

    /**
     * Метод заполнения массива Student случайным образом (рандом)
     * @return массив List Student
     */
    @Override
    public List<Student> fillRandom() {
        int arraySize = readInt(String.format("Введите размер массива %s (0 - возврат назад): ", getModelName()),
                Validate::isPositiveInteger);
        if (arraySize == 0) return null;

        List<Student> models = new ArrayList<>(arraySize);

        for (int i = 0; i < arraySize; i++) {
            models.add(studentBuilder
                    .setGroup(getRandomStudentGroup())
                    .setScore(getRandomAverageScore(1, 10))
                    .setGradeBookNum(getRandomNumber(10000, 99999))
                    .build());
        }
        return models;
    }
}
