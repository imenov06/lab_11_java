// src/productionline/AudioPlayer.java
package productionline;

/**
 * Конкретный класс, представляющий устройство аудиоплеера.
 * Наследуется от Product и реализует MultimediaControl.
 */
public class AudioPlayer extends Product implements MultimediaControl {

    private String audioSpecification; // Спецификация аудио (например, поддерживаемые форматы)
    private ItemType mediaType;        // Тип носителя (устанавливается как AUDIO)

    /**
     * Конструктор для AudioPlayer.
     *
     * @param name               Имя аудиоплеера (например, "MyPod").
     * @param audioSpecification Аудио спецификация (например, "MP3, WAV").
     */
    public AudioPlayer(String name, String audioSpecification) {
        super(name); // Вызов конструктора Product
        this.audioSpecification = audioSpecification;
        this.mediaType = ItemType.AUDIO; // Установка типа для аудиоплеера
    }

    // Реализация методов MultimediaControl
    @Override
    public void play() {
        System.out.println("Воспроизведение аудио");
    }

    @Override
    public void stop() {
        System.out.println("Остановка аудио");
    }

    @Override
    public void previous() {
        System.out.println("Предыдущий аудио трек");
    }

    @Override
    public void next() {
        System.out.println("Следующий аудио трек");
    }

    /**
     * Предоставляет строковое представление, включающее детали Product
     * и специфичные детали AudioPlayer (аудио спецификация, тип).
     *
     * @return Форматированное строковое представление AudioPlayer.
     */
    @Override
    public String toString() {
        // Вызов toString() суперкласса и добавление специфичных полей
        return super.toString() + "\n" +
                "Audio Spec   : " + this.audioSpecification + "\n" +
                "Type         : " + this.mediaType.getCode() + " (" + this.mediaType + ")"; // Показываем код и имя enum
    }

    // Опционально: Геттеры для специфичных полей, если нужны где-то еще
    public String getAudioSpecification() {
        return audioSpecification;
    }

    public ItemType getMediaType() {
        return mediaType;
    }
}