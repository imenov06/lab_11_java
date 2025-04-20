// src/productionline/Screen.java
package productionline;

/**
 * Класс, представляющий физический экран, реализующий ScreenSpec.
 */
public class Screen implements ScreenSpec {

    private String resolution;   // Разрешение
    private int refreshRate;     // Частота обновления
    private int responseTime;    // Время отклика

    /**
     * Конструктор для класса Screen.
     *
     * @param resolution   Разрешение экрана (например, "1920x1080").
     * @param refreshRate  Частота обновления экрана в Гц.
     * @param responseTime Время отклика экрана в мс.
     */
    public Screen(String resolution, int refreshRate, int responseTime) {
        this.resolution = resolution;
        this.refreshRate = refreshRate;
        this.responseTime = responseTime;
    }

    // Реализация методов ScreenSpec
    @Override
    public String getResolution() {
        return resolution;
    }

    @Override
    public int getRefreshRate() {
        return refreshRate;
    }

    @Override
    public int getResponseTime() {
        return responseTime;
    }

    /**
     * Предоставляет строковое представление спецификаций экрана.
     *
     * @return Форматированная строка с разрешением, частотой обновления и временем отклика.
     */
    @Override
    public String toString() {
        // Используем немного другие метки для ясности по сравнению с toString() класса Product
        return "Screen Resolution: " + this.resolution + "\n" +
                "Refresh Rate     : " + this.refreshRate + " Hz\n" +
                "Response Time    : " + this.responseTime + " ms";
    }
}