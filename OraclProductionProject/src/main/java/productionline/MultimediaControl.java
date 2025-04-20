// src/productionline/MultimediaControl.java
package productionline;

/**
 * Интерфейс, определяющий базовые методы управления мультимедиа.
 */
public interface MultimediaControl {

    /**
     * Начать воспроизведение.
     */
    void play();

    /**
     * Остановить воспроизведение.
     */
    void stop();

    /**
     * Перейти к предыдущему элементу/треку/главе.
     */
    void previous();

    /**
     * Перейти к следующему элементу/треку/главе.
     */
    void next();
}