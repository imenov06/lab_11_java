// src/productionline/InteractiveProduction.java
package productionline;

import java.util.*; // Для Scanner, ArrayList, List, Map, Set, InputMismatchException

/**
 * Интерактивное консольное приложение для симуляции добавления продуктов
 * на производственную линию, отображения коллекции и показа базовой статистики.
 */
public class InteractiveProduction {

    // Используем List для гибкости, ArrayList для реализации
    private static List<Product> productCollection = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in); // Единый сканер для приложения

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("===== Добро пожаловать на Интерактивную Производственную Линию =====");

        while (running) {
            printMenu(); // Показываем меню
            int choice = getUserChoice(); // Получаем выбор пользователя

            switch (choice) {
                case 1:
                    addAudioPlayer(); // Добавить аудиоплеер
                    break;
                case 2:
                    addMoviePlayer(); // Добавить видеоплеер
                    break;
                case 3:
                    displayCollection(); // Показать коллекцию
                    break;
                case 4:
                    displayStatistics(); // Показать статистику
                    break;
                case 5:
                    running = false; // Выход
                    System.out.println("Выход из Интерактивной Производственной Линии. До свидания!");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, введите число от 1 до 5.");
            }
            System.out.println(); // Добавляем пустую строку для читаемости
        }

        scanner.close(); // Закрываем сканер при выходе из приложения
    }

    /** Печатает опции главного меню в консоль. */
    private static void printMenu() {
        System.out.println("-------------------- МЕНЮ --------------------");
        System.out.println("1. Добавить новый(е) Аудиоплеер(ы)");
        System.out.println("2. Добавить новый(е) Видеоплеер(ы)");
        System.out.println("3. Показать производственную коллекцию");
        System.out.println("4. Показать производственную статистику");
        System.out.println("5. Выход");
        System.out.println("----------------------------------------------");
        System.out.print("Введите ваш выбор: ");
    }

    /** Получает и валидирует выбор пользователя в меню. */
    private static int getUserChoice() {
        int choice = -1;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Неверный ввод. Пожалуйста, введите число.");
            // Потребляем неверный ввод
        } finally{
            scanner.nextLine(); // ВСЕГДА потребляем символ новой строки после nextInt() или ошибки
        }
        return choice;
    }

    /** Получает ввод количества от пользователя. */
    private static int getQuantity() {
        int quantity = 0;
        while (quantity <= 0) {
            System.out.print("Введите количество для производства (должно быть > 0): ");
            try {
                quantity = scanner.nextInt();
                if (quantity <= 0) {
                    System.out.println("Количество должно быть положительным.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите целое число.");
            } finally {
                scanner.nextLine(); // Потребляем символ новой строки
            }
        }
        return quantity;
    }

    /** Обрабатывает добавление новых Аудиоплееров. */
    private static void addAudioPlayer() {
        System.out.println("\n--- Добавить Аудиоплеер ---");
        System.out.print("Введите имя плеера: ");
        String name = scanner.nextLine();
        System.out.print("Введите аудио спецификацию (например, MP3, AAC): ");
        String spec = scanner.nextLine();
        int quantity = getQuantity(); // Получаем количество

        for (int i = 0; i < quantity; i++) {
            AudioPlayer ap = new AudioPlayer(name, spec);
            productCollection.add(ap);
            System.out.println("Добавлено: " + ap.getName() + " (SN: " + ap.getSerialNumber() + ")");
        }
        System.out.println(quantity + " Аудиоплеер(ов) успешно добавлено.");
    }

    /** Обрабатывает добавление новых Видеоплееров. */
    private static void addMoviePlayer() {
        System.out.println("\n--- Добавить Видеоплеер ---");
        System.out.print("Введите имя плеера: ");
        String name = scanner.nextLine();

        // Получаем детали экрана
        System.out.print("Введите разрешение экрана (например, 1920x1080): ");
        String resolution = scanner.nextLine();
        int refreshRate = getIntegerInput("Введите частоту обновления экрана (Гц): ");
        int responseTime = getIntegerInput("Введите время отклика экрана (мс): ");
        Screen screen = new Screen(resolution, refreshRate, responseTime);

        // Получаем тип монитора
        MonitorType monitorType = getMonitorTypeInput();

        int quantity = getQuantity(); // Получаем количество

        for (int i = 0; i < quantity; i++) {
            // Создаем новый объект Screen для каждого плеера, если они должны быть уникальны
            // Screen playerScreen = new Screen(resolution, refreshRate, responseTime);
            // MoviePlayer mp = new MoviePlayer(name, playerScreen, monitorType);
            // ИЛИ используем тот же объект screen, если характеристики идентичны
            MoviePlayer mp = new MoviePlayer(name, screen, monitorType);
            productCollection.add(mp);
            System.out.println("Добавлено: " + mp.getName() + " (SN: " + mp.getSerialNumber() + ")");
        }
        System.out.println(quantity + " Видеоплеер(ов) успешно добавлено.");
    }

    /** Вспомогательный метод для безопасного получения целочисленного ввода. */
    private static int getIntegerInput(String prompt) {
        int value = -1;
        while (value < 0) { // Базовая валидация (неотрицательное число), можно изменить
            System.out.print(prompt);
            try {
                value = scanner.nextInt();
                if (value < 0) System.out.println("Пожалуйста, введите неотрицательное число.");
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите целое число.");
            } finally {
                scanner.nextLine(); // Потребляем символ новой строки
            }
        }
        return value;
    }

    /** Вспомогательный метод для безопасного получения ввода MonitorType. */
    private static MonitorType getMonitorTypeInput() {
        MonitorType type = null;
        while (type == null) {
            System.out.print("Введите тип монитора (LCD или LED): ");
            String input = scanner.nextLine().toUpperCase(); // Приводим к верхнему регистру для сравнения
            try {
                type = MonitorType.valueOf(input); // Пытаемся получить значение enum
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный тип. Пожалуйста, введите LCD или LED.");
            }
        }
        return type;
    }

    /** Отображает все продукты, находящиеся в данный момент в коллекции. */
    private static void displayCollection() {
        System.out.println("\n--- Текущая производственная коллекция ---");
        if (productCollection.isEmpty()) {
            System.out.println("Коллекция в данный момент пуста.");
        } else {
            // Используем метод print из TestProductionLine, если он доступен и статичен,
            // или просто перебираем коллекцию здесь
            // TestProductionLine.print(productCollection); // Если метод print статичен и публичен
            for (Product p : productCollection) {
                System.out.println(p);
                System.out.println("-----");
            }
            System.out.println("Всего элементов в коллекции: " + productCollection.size());
        }
        System.out.println("------------------------------------");
    }

    /** Рассчитывает и отображает производственную статистику. */
    private static void displayStatistics() {
        System.out.println("\n--- Производственная статистика ---");
        if (productCollection.isEmpty()) {
            System.out.println("Продукты еще не производились.");
            System.out.println("---------------------------");
            return;
        }

        int totalItems = productCollection.size(); // Общее количество
        Set<String> uniqueProductNames = new HashSet<>(); // Множество для уникальных имен
        Map<String, Integer> productTypeCounts = new HashMap<>(); // Карта для подсчета по типам

        for (Product p : productCollection) {
            uniqueProductNames.add(p.getName()); // Добавляем имя в множество (дубликаты игнорируются)
            String typeName = p.getClass().getSimpleName(); // Получаем имя класса ("AudioPlayer", "MoviePlayer")
            // Увеличиваем счетчик для этого типа или инициализируем его 1
            productTypeCounts.put(typeName, productTypeCounts.getOrDefault(typeName, 0) + 1);
        }

        System.out.println("Всего произведено элементов: " + totalItems);
        System.out.println("\nКоличество по типам продуктов:");
        for(Map.Entry<String, Integer> entry : productTypeCounts.entrySet()){
            System.out.println("- " + entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\nКоличество уникальных наименований продуктов: " + uniqueProductNames.size());
        System.out.println("Уникальные наименования: " + uniqueProductNames);

        System.out.println("---------------------------");
    }
}