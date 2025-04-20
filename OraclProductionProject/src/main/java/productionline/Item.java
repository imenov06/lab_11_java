// src/productionline/Item.java
package productionline;

import java.util.Date;

/**
 * Интерфейс, определяющий базовые требования для любого производимого элемента.
 */
public interface Item {

    /**
     * Константа производителя для всех элементов.
     * public static final подразумевается для полей в интерфейсах.
     */
    String manufacturer = "OracleProduction";

    /**
     * Устанавливает производственный номер для элемента.
     * @param productionNumber Уникальный производственный номер.
     */
    void setProductionNumber(int productionNumber);

    /**
     * Устанавливает имя элемента.
     * @param name Имя элемента.
     */
    void setName(String name);

    /**
     * Получает имя элемента.
     * @return Имя элемента.
     */
    String getName();

    /**
     * Получает дату производства элемента.
     * @return Дата производства элемента.
     */
    Date getManufactureDate();

    /**
     * Получает серийный номер элемента.
     * @return Серийный номер.
     */
    int getSerialNumber();
}