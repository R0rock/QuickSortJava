package utilities;

import actions.FillActions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FileUtilities {

    private static String normalizePath(String fileName) {
        return fileName.replaceAll("/", FileSystems.getDefault().getSeparator());
    }

    /**
    * Запись в файл
    * @param info строка для записи
    * @throws IOException io exception
    */
    public static void fileWriting(String info) {
        String filePath = "output.txt";

        Path file = Paths.get(filePath);
        if (Files.exists(file)) {
            System.out.printf("Файл %s найден.\n", file.getFileName());
        } else {
            try {
                Files.createFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.printf("Файл %s НЕ найден, создан новый.\n", file.getFileName());
        }

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file, StandardOpenOption.APPEND)) {
            bufferedWriter.write(info + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> readFile(String fileName, FillActions.Builder<T> builder, Predicate<String[]> check) {
        Path file = Paths.get(normalizePath(fileName));

        if (!Files.exists(file)) {
            System.out.printf("Файл %s не найден, проверьте правильность пути\n", fileName);
            return null;
        }
        List<T> models = new ArrayList<>();

        System.out.printf("Файл %s найден.\n", fileName);
        try (BufferedReader bufferedReader = Files.newBufferedReader(file)) {
            int lineCount = 1;
            int count = 1;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] tmpArr = line.split(";");
                if (tmpArr.length == 3 && check.test(tmpArr)) {
                    models.add(builder.callBuilder(tmpArr, count));
                    count++;
                } else {
                    System.out.printf("Строка %s (под номером %d в файле) некорректна, она будет пропущена.\n",
                            line, lineCount);
                }
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (models.isEmpty()) {
            System.out.println("\nФайл пустой или отсутствует, возврат в предыдущее меню.");
            return null;
        }

        return models;
    }
}
