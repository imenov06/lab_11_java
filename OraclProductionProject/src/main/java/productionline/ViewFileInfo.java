// src/productionline/ViewFileInfo.java
package productionline;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.NoSuchFileException; // Специальное исключение для отсутствующего файла

/**
 * Класс-драйвер для чтения и отображения содержимого файла TestResults.txt.
 * Обрабатывает случаи, когда файл может не существовать.
 */
public class ViewFileInfo {

    public static void main(String[] args) {
        // Воссоздаем путь к файлу, та же логика, что и в ProcessFiles
        Path dirPath = Paths.get("LineTests");
        Path filePath = dirPath.resolve("TestResults.txt");

        System.out.println("--- Попытка чтения файла: " + filePath.toAbsolutePath() + " ---");
        System.out.println("--------------------------------------------------");

        // Используем try-with-resources для гарантированного закрытия ридера
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            // Читаем и печатаем каждую строку до конца файла
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("--------------------------------------------------");
            System.out.println("--- Конец содержимого файла ---");

        } catch (NoSuchFileException e) {
            // Обработка конкретного случая, когда файл не существует
            System.err.println("ОШИБКА: Файл не найден.");
            System.err.println("Пожалуйста, сначала запустите TestProductionLine для генерации файла: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            // Обработка других возможных ошибок ввода-вывода при чтении
            System.err.println("ОШИБКА: Произошла ошибка при чтении файла: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // Перехват любых других непредвиденных исключений
            System.err.println("ОШИБКА: Произошла непредвиденная ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}