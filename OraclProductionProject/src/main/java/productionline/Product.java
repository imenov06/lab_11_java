// src/productionline/Product.java
package productionline;

import java.util.Date;
import java.util.ArrayList; // Для printType (Шаг 17)

/**
 * Абстрактный класс, представляющий общий продукт и реализующий интерфейс Item.
 * Предоставляет базовую функциональность, общую для всех элементов на производственной линии.
 * Реализует Comparable для сортировки (Шаг 14).
 */
public abstract class Product implements Item, Comparable<Product> {

    private int serialNumber;         // Серийный номер
    private final String manufacturer;  // Производитель (final, т.к. устанавливается из константы)
    private Date manufacturedOn;      // Дата производства
    private String name;              // Имя продукта

    // Статическая переменная для отслеживания следующего серийного номера
    private static int currentProductionNumber = 1;

    /**
     * Конструктор для класса Product.
     * Инициализирует имя продукта, устанавливает дату производства,
     * присваивает серийный номер и увеличивает счетчик произведенных экземпляров.
     *
     * @param name Имя продукта.
     */
    public Product(String name) {
        this.name = name;
        this.manufacturer = Item.manufacturer; // Устанавливается из константы интерфейса
        this.manufacturedOn = new Date();      // Устанавливается текущая дата и время
        this.serialNumber = currentProductionNumber++; // Присваивается текущий номер и увеличивается для следующего
    }

    // Реализация методов интерфейса Item
    @Override
    public void setProductionNumber(int productionNumber) {
        // Хотя номер присваивается автоматически, интерфейс требует этот метод.
        // Позволяем установить его, потенциально переопределяя автоматическое присвоение, если понадобится позже.
        this.serialNumber = productionNumber;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Date getManufactureDate() {
        // Возвращаем копию, чтобы предотвратить внешнее изменение внутреннего объекта Date
        return new Date(this.manufacturedOn.getTime());
    }

    @Override
    public int getSerialNumber() {
        return serialNumber;
    }

    /**
     * Предоставляет строковое представление основной информации о продукте.
     * Следует указанному формату.
     *
     * @return Форматированная строка с производителем, серийным номером, датой и именем.
     */
    @Override
    public String toString() {
        return "Manufacturer : " + this.manufacturer + "\n" +
                "Serial Number: " + this.serialNumber + "\n" +
                "Date         : " + this.manufacturedOn + "\n" + // Стандартный toString() для Date подходит
                "Name         : " + this.name;
    }

    /**
     * Сравнивает этот Product с другим Product на основе их имен (без учета регистра).
     * Требуется для Шага 14 (сортировка).
     *
     * @param other Product для сравнения.
     * @return отрицательное целое число, ноль или положительное целое число, если имя этого продукта
     *         меньше, равно или больше имени указанного продукта.
     */
    @Override
    public int compareTo(Product other) {
        if (other == null || other.name == null) {
            return 1; // Считаем null меньшими? Или обработать как ошибку? Поставим не-null первыми.
        }
        if (this.name == null) {
            return -1;
        }
        // Сравнение по имени, игнорируя регистр
        return this.name.compareToIgnoreCase(other.name);
    }

    // --- Статический метод для Шага 17 (Бонус) ---

    /**
     * (Бонус Шаг 17)
     * Выводит детали продуктов в коллекции, которые являются экземплярами указанного класса.
     *
     * @param products Коллекция продуктов для итерации.
     * @param type     Объект Class, представляющий тип продукта для вывода (должен наследоваться от Product).
     * @param <T>      Параметр типа, гарантирующий, что это подкласс Product.
     */
    public static <T extends Product> void printType(ArrayList<Product> products, Class<T> type) {
        System.out.println("\n--- Печать только экземпляров " + type.getSimpleName() + " ---");
        int count = 0;
        if (products != null) {
            for (Product p : products) {
                // Используем isInstance для проверки, является ли 'p' экземпляром класса, представленного 'type'
                if (type.isInstance(p)) {
                    System.out.println(p);
                    System.out.println("-----");
                    count++;
                }
            }
        }
        if (count == 0) {
            System.out.println("Экземпляры " + type.getSimpleName() + " не найдены в коллекции.");
        }
        System.out.println("--------------------------------------");
    }
}