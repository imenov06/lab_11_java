// src/productionline/TestProductionLine.java
package productionline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List; // Используем интерфейс List для гибкости
import java.io.IOException; // Для обработки исключений файла (Шаг 21)

/**
 * Основной класс-драйвер для симуляции производственной линии,
 * хранения элементов в коллекции, их сортировки и демонстрации других возможностей.
 */
public class TestProductionLine {

    public static void main(String[] args) {
        // Используем ArrayList, но тип переменной List - хорошая практика
        List<Product> productionRun = new ArrayList<>();

        // Создаем разные продукты
        AudioPlayer ap1 = new AudioPlayer("iPod Mini", "MP3");
        AudioPlayer ap2 = new AudioPlayer("Sony Walkman", "AAC");
        MoviePlayer mp1 = new MoviePlayer("Portable DVD", new Screen("480p", 30, 12), MonitorType.LCD);
        MoviePlayer mp2 = new MoviePlayer("Surface Tablet", new Screen("2736x1824", 120, 2), MonitorType.LED);
        AudioPlayer ap3 = new AudioPlayer("Boombox", "WAV/Cassette");

        // Добавляем в коллекцию (Шаг 15)
        productionRun.add(ap1);
        productionRun.add(ap2);
        productionRun.add(mp1);
        productionRun.add(mp2);
        productionRun.add(ap3);

        System.out.println("--- Начальный производственный запуск (не отсортированный) ---");
        print(productionRun); // Используем метод print из Шага 16

        // Сортируем коллекцию с помощью Collections.sort() - опирается на compareTo в Product (Шаг 14)
        Collections.sort(productionRun);

        System.out.println("\n--- Производственный запуск отсортированный по имени ---");
        print(productionRun); // Печатаем снова после сортировки

        System.out.println("\n==========================================");
        System.out.println(" КОНЕЦ СИМУЛЯЦИИ ПРОИЗВОДСТВА (Шаги 15/16)");
        System.out.println("==========================================");

        // --- Тестирование Бонусного Шага 17 ---
        System.out.println("\n======================================");
        System.out.println("    БОНУСНЫЙ ШАГ 17 - printType");
        System.out.println("======================================");
        // Передаем ArrayList, как требует метод printType в Product
        Product.printType(new ArrayList<>(productionRun), AudioPlayer.class);
        Product.printType(new ArrayList<>(productionRun), MoviePlayer.class);

        // --- Шаг 18 и 19: Информация о сотруднике ---
        System.out.println("\n======================================");
        System.out.println("  ШАГИ 18, 19, 20 - Информация о сотруднике");
        System.out.println("======================================");
        EmployeeInfo emp = new EmployeeInfo(); // Конструктор запросит имя и ID отдела
        System.out.println("\n--- Детали сотрудника ---");
        System.out.println(emp); // Вызовет toString() с кодом и зашифрованным ID отдела

        // --- Шаг 21: Файловый ввод-вывод ---
        System.out.println("\n======================================");
        System.out.println("          ШАГ 21 - Файловый ввод-вывод");
        System.out.println("======================================");
        ProcessFiles pf = new ProcessFiles(); // Создает директорию при необходимости

        try {
            // Записываем продукты, затем информацию о сотруднике (в режиме добавления)
            pf.WriteFile(productionRun); // Передаем List
            pf.WriteFile(emp);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Не удалось записать в файл: " + e.getMessage());
            e.printStackTrace(); // Печать трассировки стека для отладки
        }

        System.out.println("\nПроцесс записи в файл завершен. Проверьте содержимое: " + pf.getFilePath().toAbsolutePath());

        // Закрываем сканнер, связанный с объектом EmployeeInfo, если он еще открыт
        emp.closeScanner();

        System.out.println("\n==========================================");
        System.out.println("     КОНЕЦ ТЕСТОВОГО ЗАПУСКА ЛИНИИ");
        System.out.println("==========================================");
    }

    /**
     * (Шаг 16)
     * Вспомогательный метод для печати деталей всех продуктов в коллекции.
     *
     * @param products Список продуктов для печати.
     */
    public static void print(List<Product> products) {
        if (products == null || products.isEmpty()) {
            System.out.println("Производственный запуск пуст.");
            return;
        }
        for (Product p : products) {
            System.out.println(p); // Опирается на метод toString() каждого продукта
            System.out.println("-----");
        }
    }
}