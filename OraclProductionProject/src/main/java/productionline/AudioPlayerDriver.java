// src/productionline/AudioPlayerDriver.java
package productionline;

/**
 * Класс-драйвер для тестирования функциональности класса AudioPlayer.
 */
public class AudioPlayerDriver {

    public static void main(String[] args) {
        System.out.println("--- Создание Audio Player ---");
        AudioPlayer player1 = new AudioPlayer("MyPod Nano", "MP3/AAC");
        System.out.println(player1); // Вызывает toString()

        System.out.println("\n--- Тестирование управления ---");
        player1.play();
        player1.stop();
        player1.next();
        player1.previous();

        System.out.println("\n--- Создание другого Audio Player ---");
        AudioPlayer player2 = new AudioPlayer("Walkman Pro", "FLAC/WAV");
        System.out.println(player2);
    }
}