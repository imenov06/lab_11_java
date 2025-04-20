// src/productionline/ScreenSpec.java
package productionline;

/**
 * Интерфейс, определяющий методы, связанные со спецификациями экрана.
 */
public interface ScreenSpec {

    /**
     * Получает разрешение экрана.
     * @return Разрешение в виде строки (например, "1920x1080").
     */
    String getResolution();

    /**
     * Получает частоту обновления экрана в Гц.
     * @return Частота обновления в виде целого числа.
     */
    int getRefreshRate();

    /**
     * Получает время отклика экрана в миллисекундах.
     * @return Время отклика в виде целого числа.
     */
    int getResponseTime();
}