// src/productionline/ScreenDriver.java
package productionline;

/**
 * Класс-драйвер для тестирования функциональности класса Screen.
 */
public class ScreenDriver {

    public static void main(String[] args) {
        System.out.println("--- Создание Screen ---");
        // Используем указанные значения: "600x400", 40, 22
        Screen screen = new Screen("600x400", 40, 22);

        System.out.println(screen); // Вызывает toString()

        System.out.println("\n--- Доступ к отдельным характеристикам ---");
        System.out.println("Разрешение: " + screen.getResolution());
        System.out.println("Частота обновления: " + screen.getRefreshRate());
        System.out.println("Время отклика: " + screen.getResponseTime());
    }
}