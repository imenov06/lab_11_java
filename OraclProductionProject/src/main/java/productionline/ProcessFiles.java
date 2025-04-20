// src/productionline/ProcessFiles.java
package productionline;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List; // Используем List вместо ArrayList для гибкости

/**
 * Обрабатывает запись результатов тестов (Продукты и EmployeeInfo) в файл.
 * Создает необходимые директории и файловые структуры. Дописывает данные в файл.
 */
public class ProcessFiles {

    private Path p;  // Путь к директории (например, ./LineTests относительно корня проекта)
    private Path p2; // Имя файла (TestResults.txt)
    private Path p3; // Полный путь к файлу

    /**
     * Конструктор инициализирует пути к файлам и гарантирует существование директории.
     */
    public ProcessFiles() {
        // Используем относительный путь для лучшей переносимости
        p = Paths.get("LineTests"); // Директория относительно места запуска
        p2 = Paths.get("TestResults.txt");
        p3 = p.resolve(p2); // Объединяем путь к директории и имя файла -> LineTests/TestResults.txt

        CreateDirectory(); // Гарантируем существование директории при создании объекта
    }

    /**
     * Создает директорию, указанную путем 'p', если она еще не существует.
     */
    private void CreateDirectory() {
        try {
            if (Files.notExists(p)) {
                Files.createDirectories(p);
                System.out.println("Директория создана: " + p.toAbsolutePath());
            } else {
                // System.out.println("Директория уже существует: " + p.toAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("ОШИБКА: Не удалось создать директорию '" + p.toAbsolutePath() + "'. " + e);
        }
    }

    /**
     * Записывает детали списка продуктов в выходной файл. Дописывает данные.
     *
     * @param products Список объектов Product для записи.
     * @throws IOException Если возникает ошибка ввода-вывода во время записи.
     */
    public void WriteFile(List<Product> products) throws IOException {
        // Используем try-with-resources для автоматического закрытия FileWriter и BufferedWriter
        try (FileWriter fw = new FileWriter(p3.toString(), true); // true = режим добавления (append)
             BufferedWriter writer = new BufferedWriter(fw)) {

            writer.write("======== Производственный запуск ========");
            writer.newLine();
            if (products != null && !products.isEmpty()) {
                for (Product prod : products) {
                    writer.write(prod.toString()); // Записываем детали продукта
                    writer.newLine();
                    writer.write("-----");          // Добавляем разделитель
                    writer.newLine();
                }
            } else {
                writer.write("Продуктов в этом запуске нет.");
                writer.newLine();
            }
            writer.write("====== Конец производственного запуска ======");
            writer.newLine();
            writer.newLine(); // Добавляем пустую строку перед следующей записью
            System.out.println("Данные продуктов успешно добавлены в " + p3.getFileName());

        } // Файлы автоматически закрываются здесь, даже если возникли исключения
    }

    /**
     * Записывает детали объекта EmployeeInfo в выходной файл. Дописывает данные.
     *
     * @param emp Объект EmployeeInfo для записи.
     * @throws IOException Если возникает ошибка ввода-вывода во время записи.
     */
    public void WriteFile(EmployeeInfo emp) throws IOException {
        try (FileWriter fw = new FileWriter(p3.toString(), true); // true = режим добавления
             BufferedWriter writer = new BufferedWriter(fw)) {

            writer.write("======== Информация о сотруднике ========");
            writer.newLine();
            if (emp != null) {
                writer.write(emp.toString()); // Записываем детали сотрудника
                writer.newLine();
            } else {
                writer.write("Информация о сотруднике не предоставлена.");
                writer.newLine();
            }
            writer.write("====== Конец информации о сотруднике ======");
            writer.newLine();
            writer.newLine(); // Добавляем пустую строку
            System.out.println("Данные сотрудника успешно добавлены в " + p3.getFileName());

        } // Файлы автоматически закрываются здесь
    }

    /**
     * Геттер для пути к файлу, если он нужен другим классам (например, для просмотра).
     * @return Полный путь к файлу результатов.
     */
    public Path getFilePath() {
        return p3;
    }
}