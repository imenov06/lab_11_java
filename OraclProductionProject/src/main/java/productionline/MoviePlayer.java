// src/productionline/MoviePlayer.java
package productionline;

/**
 * Конкретный класс, представляющий устройство видеоплеера.
 * Наследуется от Product и реализует MultimediaControl.
 * Содержит Screen и MonitorType.
 */
public class MoviePlayer extends Product implements MultimediaControl {

    private Screen screen;          // Композиция: MoviePlayer ИМЕЕТ Screen
    private MonitorType monitorType; // Тип монитора

    /**
     * Конструктор для MoviePlayer.
     *
     * @param name        Имя видеоплеера.
     * @param screen      Объект Screen, связанный с этим плеером.
     * @param monitorType Тип монитора (LCD или LED).
     */
    public MoviePlayer(String name, Screen screen, MonitorType monitorType) {
        super(name); // Вызов конструктора Product
        this.screen = screen;
        this.monitorType = monitorType;
        // Можно было бы автоматически присвоить ItemType.VISUAL_MOBILE,
        // но пока строго следуем заданию для MoviePlayer (нет поля mediaType)
    }

    // Реализация методов MultimediaControl
    @Override
    public void play() {
        System.out.println("Воспроизведение видео");
    }

    @Override
    public void stop() {
        System.out.println("Остановка видео");
    }

    @Override
    public void previous() {
        System.out.println("Предыдущая глава видео");
    }

    @Override
    public void next() {
        System.out.println("Следующая глава видео");
    }

    /**
     * Предоставляет строковое представление, включающее детали Product,
     * тип монитора и детали экрана.
     *
     * @return Форматированное строковое представление MoviePlayer.
     */
    @Override
    public String toString() {
        return super.toString() + "\n" +      // Получаем детали Product
                "Monitor Type   : " + this.monitorType + "\n" + // Добавляем тип монитора
                this.screen.toString();        // Добавляем детали экрана, вызывая его toString()
    }

    // Опционально: Геттеры для специфичных полей
    public Screen getScreen() {
        return screen;
    }

    public MonitorType getMonitorType() {
        return monitorType;
    }
}