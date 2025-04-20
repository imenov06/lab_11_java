// src/productionline/MoviePlayerDriver.java
package productionline;

/**
 * Класс-драйвер для тестирования функциональности класса MoviePlayer.
 */
public class MoviePlayerDriver {

    public static void main(String[] args) {
        System.out.println("--- Создание Movie Player ---");
        // Сначала создаем объект Screen
        Screen highDefScreen = new Screen("1920x1080", 144, 1);
        // Теперь создаем MoviePlayer
        MoviePlayer player = new MoviePlayer("Portable Blu-Ray Player", highDefScreen, MonitorType.LED);

        System.out.println(player); // Вызывает toString() класса MoviePlayer

        System.out.println("\n--- Тестирование управления ---");
        player.play();
        player.stop();
        player.next();
        player.previous();
    }
}