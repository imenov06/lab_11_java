import java.io.*; // <--- ДОБАВИТЬ для работы с файлами и потоками
import java.util.InputMismatchException; // Можно добавить для обработки ошибок Scanner
import java.util.Scanner; // Уже должен быть, если использовали в Employee

/**
 * Класс-драйвер для демонстрации работы класса Employee.
 * Создает объект Employee, выводит его данные,
 * а также ТЕПЕРЬ тестирует сериализацию и десериализацию.
 */
public class AccountGeneratorApp {

    // Константа для имени файла сериализации
    private static final String SERIALIZATION_FILE = "employee.ser";

    public static void main(String[] args) {
        System.out.println(">>> Запуск приложения Account Generator <<<");

        Employee newEmployee = null;
        try {
            // Создаем экземпляр класса Employee.
            newEmployee = new Employee();

            // Выводим результат работы, используя метод toString() объекта Employee.
            System.out.println("\n>>> Сгенерированные данные сотрудника <<<");
            System.out.println(newEmployee);

            // --- Тестирование Сериализации (Шаг 2b) ---
            System.out.println("\n--- Тестирование Сериализации ---");
            serializeData(newEmployee);

            // --- Тестирование Десериализации (Шаг 2c, 2d) ---
            System.out.println("\n--- Тестирование Десериализации ---");
            Object loadedObject = deSerialize();

            // Проверяем и выводим десериализованный объект
            if (loadedObject instanceof Employee) {
                Employee loadedEmployee = (Employee) loadedObject; // Безопасное приведение типа
                System.out.println("Десериализованный объект Employee:");
                System.out.println(loadedEmployee);

                // Дополнительная проверка (не обязательно, но полезно)
                if (newEmployee.toString().equals(loadedEmployee.toString())) {
                    System.out.println("ПРОВЕРКА: Десериализованный объект идентичен исходному.");
                } else {
                    System.out.println("ПРОВЕРКА: ВНИМАНИЕ! Десериализованный объект ОТЛИЧАЕТСЯ от исходного.");
                }

            } else {
                System.out.println("ОШИБКА: Десериализован объект неизвестного типа: " + loadedObject.getClass().getName());
            }

        } catch (InputMismatchException e) {
            System.err.println("Ошибка ввода данных при создании Employee: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            // Ловим исключения от deSerialize() или возможные от serializeData()
            System.err.println("Ошибка во время сериализации/десериализации: " + e.getMessage());
            e.printStackTrace(); // Показываем полный стектрейс для отладки
        } catch (Exception e) {
            // Ловим другие возможные ошибки при создании Employee
            System.err.println("Произошла непредвиденная ошибка: " + e.getMessage());
            e.printStackTrace();
        }


        System.out.println("\n>>> Приложение завершило работу <<<");
    }

    // --- Метод Сериализации (Шаг 2b) ---
    /**
     * Сериализует переданный объект Employee в файл "employee.ser".
     * @param emp Объект Employee для сериализации.
     * @throws IOException Если произошла ошибка ввода-вывода при записи файла.
     */
    public static void serializeData(Employee emp) throws IOException {
        System.out.println("Попытка сериализации объекта в файл: " + SERIALIZATION_FILE);
        // Используем try-with-resources для автоматического закрытия потоков
        try (FileOutputStream fos = new FileOutputStream(SERIALIZATION_FILE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(emp); // Записываем объект в поток
            System.out.println("Объект Employee успешно сериализован.");

        }
        // IOException пробрасывается дальше, если возникнет
    }

    // --- Метод Десериализации (Шаг 2c, 2d) ---
    /**
     * Десериализует объект из файла "employee.ser".
     * @return Десериализованный объект (как тип Object).
     * @throws IOException Если произошла ошибка ввода-вывода при чтении файла.
     * @throws ClassNotFoundException Если класс сериализованного объекта не найден.
     */
    public static Object deSerialize() throws IOException, ClassNotFoundException {
        System.out.println("Попытка десериализации объекта из файла: " + SERIALIZATION_FILE);
        Object obj = null;
        // Используем try-with-resources для автоматического закрытия потоков
        try (FileInputStream fis = new FileInputStream(SERIALIZATION_FILE);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            obj = ois.readObject(); // Читаем объект из потока
            System.out.println("Объект успешно десериализован.");
        }
        // Исключения IOException и ClassNotFoundException пробрасываются дальше
        return obj;
    }

} // --- Конец класса AccountGeneratorApp ---