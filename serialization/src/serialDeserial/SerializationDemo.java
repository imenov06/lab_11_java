package serialDeserial;
import java.io.*; // Для потоков ввода/вывода и исключений
import java.nio.file.Path;
import java.nio.file.Paths;

public class SerializationDemo {

    // Шаг 5: Основной метод для тестирования
    public static void main(String[] args) {
        System.out.println("--- Начало теста сериализации ---");

        // Создаем объект Course
        Course course = new Course("Java Programming", "Oracle", "JP", 60);

        // Шаг 12: Определяем путь к файлу для сохранения/загрузки
        // Используем относительный путь для удобства запуска в разных средах
        Path path = Paths.get("course_details.ser");
        // Если нужно использовать путь как в примере, убедитесь, что папка существует:
        // Path path = Paths.get("C:/JavaProgramming/details.ser");

        // Шаг 12: Сериализуем объект
        System.out.println("\nПопытка сериализации объекта Course...");
        serializeData(course, path);

        // Шаг 12: Десериализуем объект
        System.out.println("\nПопытка десериализации объекта Course...");
        Course savedCourse = deSerializeData(path);

        // Шаг 12 (проверка) и Шаг 14 (вывод): Отображаем десериализованные данные
        System.out.println("\n--- Результат десериализации ---");
        if (savedCourse != null) {
            displayData(savedCourse);
        } else {
            System.out.println("Не удалось десериализовать объект.");
        }

        System.out.println("\n--- Тест сериализации завершен ---");
    }

    // Шаг 6 & 7 & 8: Метод для сериализации данных
    static void serializeData(Course course, Path path) {
        // Используем try-with-resources для автоматического закрытия потоков
        try (FileOutputStream fileOut = new FileOutputStream(path.toString());
             ObjectOutputStream objOut = new ObjectOutputStream(fileOut))
        {
            // Записываем объект в поток
            objOut.writeObject(course);
            System.out.println("Успешно: Сериализованные данные сохранены в " + path.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("ОШИБКА при сериализации: " + e.getMessage());
            e.printStackTrace(); // Печатаем стектрейс для детальной информации
        }
    }

    // Шаг 6 & 9 & 10: Метод для десериализации данных
    static Course deSerializeData(Path path) {
        Course course = null;
        // Используем try-with-resources
        try (FileInputStream fileIn = new FileInputStream(path.toString());
             ObjectInputStream objIn = new ObjectInputStream(fileIn))
        {
            // Читаем объект и приводим к типу Course
            course = (Course) objIn.readObject();
            System.out.println("Успешно: Объект десериализован из " + path.toAbsolutePath());

        } catch (FileNotFoundException e) {
            System.err.println("ОШИБКА при десериализации: Файл не найден - " + path.toAbsolutePath());
        } catch (ClassNotFoundException e) {
            // Эта ошибка возникнет, если класс Course не найден при десериализации
            System.err.println("ОШИБКА при десериализации: Класс Course не найден.");
            // e.printStackTrace();
        } catch (IOException e) {
            System.err.println("ОШИБКА при десериализации (IO): " + e.getMessage());
            e.printStackTrace();
        }
        return course; // Возвращаем десериализованный объект или null в случае ошибки
    }

    // Шаг 6 & 11 & 14: Метод для отображения данных объекта Course
    public static void displayData(Course course) {
        System.out.println("--- Deserialized Course Details ---");
        // Используем метод toString() класса Course (как на слайде 59)
        System.out.println(course);
    }
}