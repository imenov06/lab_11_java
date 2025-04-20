// src/productionline/ItemType.java
package productionline;

/**
 * Перечисление (enum), представляющее различные типы производимых элементов.
 */
public enum ItemType {
    AUDIO("AU"),            // Аудио
    VISUAL("VI"),           // Видео
    AUDIO_MOBILE("AM"),     // Аудио мобильное
    VISUAL_MOBILE("VM");    // Видео мобильное

    private final String code; // Код типа

    /**
     * Конструктор для констант перечисления ItemType.
     * @param code Код, связанный с типом элемента.
     */
    ItemType(String code) {
        this.code = code;
    }

    /**
     * Получает код, связанный с типом элемента.
     * @return Код типа элемента (например, "AU", "VI").
     */
    public String getCode() {
        return code;
    }
}