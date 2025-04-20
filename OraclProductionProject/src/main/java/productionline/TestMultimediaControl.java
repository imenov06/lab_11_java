// src/productionline/TestMultimediaControl.java
package productionline;

/**
 * Класс-драйвер для демонстрации полиморфизма с использованием интерфейса MultimediaControl.
 */
public class TestMultimediaControl {

    public static void main(String[] args) {
        // Создаем экземпляры разных классов, реализующих MultimediaControl
        AudioPlayer audioDevice = new AudioPlayer("Generic MP3", "MP3");
        MoviePlayer movieDevice = new MoviePlayer("Generic Video Player",
                new Screen("800x600", 60, 5),
                MonitorType.LCD);

        System.out.println("--- Тестирование Audio Player через интерфейс MultimediaControl ---");
        testDeviceControls(audioDevice);

        System.out.println("\n--- Тестирование Movie Player через интерфейс MultimediaControl ---");
        testDeviceControls(movieDevice);

        // Можно добавить другие устройства, если они реализуют MultimediaControl
        // Пример: StreamingBox streamer = new StreamingBox(...);
        // testDeviceControls(streamer);
    }

    /**
     * Тестирует базовые элементы управления любого устройства, реализующего MultimediaControl.
     * @param device Устройство, реализующее MultimediaControl (AudioPlayer, MoviePlayer и т.д.)
     */
    public static void testDeviceControls(MultimediaControl device) {
        System.out.println("Тестируемое устройство: " + device.getClass().getSimpleName());
        device.play();
        device.stop();
        device.previous();
        device.next();
    }
}