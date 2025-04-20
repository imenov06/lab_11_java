// src/productionline/EmployeeInfo.java
package productionline;

import java.util.Scanner;
import java.util.regex.Matcher; // Для валидации
import java.util.regex.Pattern; // Для валидации

/**
 * Класс для хранения информации о сотруднике в целях аудита.
 * Собирает имя, генерирует код сотрудника, собирает ID отдела
 * и валидирует формат ID отдела. Кодирует ID отдела реверсированием.
 */
public class EmployeeInfo {

    private StringBuilder name;     // Имя (используем StringBuilder по заданию)
    private String code;            // Код сотрудника
    private Scanner scanner;        // Сканер для ввода

    // Поля, добавленные для Шага 19
    private String deptId;          // ID отдела (зашифрованный)
    private Pattern p;              // Шаблон для валидации ID отдела

    // Поле для Шага 20 (используется в логике Шага 19)
    private final String defaultDeptId = "None01"; // ID отдела по умолчанию

    /**
     * Конструктор для EmployeeInfo. Запрашивает у пользователя имя и ID отдела,
     * валидирует их и генерирует необходимые коды/ID.
     */
    public EmployeeInfo() {
        scanner = new Scanner(System.in);
        // Компилируем шаблон для ID отдела: Заглавная буква, 3 строчные, 2 цифры
        p = Pattern.compile("[A-Z][a-z]{3}\\d{2}");

        setName();      // Получаем имя и генерируем код
        setDeptId();    // Получаем, валидируем и устанавливаем (зашифрованный) ID отдела

        // Сканер НЕ закрывается здесь, т.к. может понадобиться в вызывающем коде.
        // Вызывающий код (например, TestProductionLine) должен вызвать closeScanner().
    }

    /**
     * Получает имя сотрудника.
     * @return StringBuilder, содержащий имя сотрудника.
     */
    public StringBuilder getName() {
        return name;
    }

    /**
     * Получает сгенерированный код сотрудника.
     * @return Строка кода сотрудника (например, "gwest" или "jsmith").
     */
    public String getCode() {
        return code;
    }

    /**
     * Внутренний метод для обработки ввода имени пользователя
     * и установки полей 'name' и 'code'.
     */
    private void setName() {
        String fullName = inputName(); // Получаем ввод с помощью вспомогательного метода
        StringBuilder sbName = new StringBuilder(fullName);

        if (checkName(sbName)) {
            this.name = sbName; // Сохраняем валидное имя
            createEmployeeCode(this.name); // Генерируем код
        } else {
            // Обработка неверного формата имени (нет пробела)
            System.out.println("Введен неверный формат имени (требуется имя и фамилия, разделенные пробелом).");
            this.name = new StringBuilder("Некорректное Имя"); // Указываем, что имя не установлено корректно
            this.code = "guest"; // Устанавливаем код по умолчанию "guest"
        }
    }

    /**
     * Запрашивает у пользователя ввод полного имени и возвращает его.
     * @return Строка имени, введенная пользователем.
     */
    private String inputName() {
        System.out.print("Пожалуйста, введите ваше имя и фамилию: ");
        return scanner.nextLine();
    }

    /**
     * Проверяет, содержит ли предоставленное имя пробел, подразумевая имя и фамилию.
     * @param name StringBuilder с именем для проверки.
     * @return true, если имя содержит пробел, иначе false.
     */
    private boolean checkName(StringBuilder name) {
        if (name == null) {
            return false;
        }
        // Проверяем, содержит ли строковое представление StringBuilder пробел (после удаления крайних пробелов)
        return name.toString().trim().contains(" ");
    }

    /**
     * Создает код сотрудника из валидированного имени.
     * Код: первая буква имени + полная фамилия, все в нижнем регистре.
     * Устанавливает поле 'code'.
     * @param name StringBuilder с валидированным полным именем (должно содержать пробел).
     */
    private void createEmployeeCode(StringBuilder name) {
        String nameStr = name.toString().trim(); // Используем строку без крайних пробелов
        int spaceIndex = nameStr.indexOf(' ');

        // Уже проверили наличие пробела в checkName, но двойная проверка безопасна
        if (spaceIndex > 0 && spaceIndex < nameStr.length() - 1) {
            String firstNameInitial = nameStr.substring(0, 1);
            String lastName = nameStr.substring(spaceIndex + 1);
            // Удалить возможные пробелы внутри самой фамилии, если нужно? Предполагаем простые случаи.
            this.code = (firstNameInitial + lastName).toLowerCase();
        } else {
            // Запасной вариант, хотя checkName должен предотвратить этот путь при правильном использовании
            this.code = "guest";
        }
    }

    // --- Методы, добавленные/измененные для Шага 19 и 20 ---

    /**
     * Получает ID отдела (это будет зашифрованный/реверсированный ID).
     * @return Строка ID отдела.
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * Внутренний метод для получения ввода ID отдела, его валидации,
     * ШИФРОВАНИЯ (реверсирования) если валиден, и установки поля 'deptId'.
     */
    private void setDeptId() {
        String id = getId(); // Получаем "сырой" ввод
        if (validId(id)) {
            // Если валиден, РЕВЕРСИРУЕМ его перед присвоением (Шаг 20)
            this.deptId = reverseString(id);
        } else {
            System.out.println("Неверный формат ID отдела. Присваивается значение по умолчанию.");
            // Не реверсируем значение по умолчанию
            this.deptId = defaultDeptId;
        }
    }

    /**
     * Запрашивает у пользователя ввод ID отдела.
     * @return Строка ID отдела, введенная пользователем.
     */
    private String getId() {
        System.out.print("Пожалуйста, введите ID отдела (например, Prod12): ");
        return scanner.nextLine();
    }

    /**
     * Валидирует данный ID по скомпилированному шаблону.
     * Шаблон: Одна заглавная буква, три строчные буквы, две цифры.
     * @param id Строка ID отдела для валидации.
     * @return true, если ID соответствует шаблону, иначе false.
     */
    private boolean validId(String id) {
        if (id == null) {
            return false;
        }
        Matcher m = p.matcher(id);
        return m.matches();
    }

    /**
     * Предоставляет строковое представление, включающее код сотрудника и ЗАШИФРОВАННЫЙ ID отдела.
     * Переопределяет стандартный метод toString.
     *
     * @return Форматированная строка с кодом сотрудника и зашифрованным ID отдела.
     */
    @Override
    public String toString() {
        // Метка отражает, что ID зашифрован
        String deptLabel = "Encoded Department ID";

        return "Employee Code        : " + (code != null ? code : "N/A") + "\n" +
                deptLabel + " : " + (deptId != null ? deptId : "N/A");
    }

    /**
     * Метод для закрытия сканера. Должен быть вызван из основного приложения,
     * когда ввод больше не требуется.
     */
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
            // System.out.println("Сканер закрыт из EmployeeInfo."); // Можно раскомментировать для отладки
        }
    }

    // --- Метод для Шага 20 ---
    /**
     * (Шаг 20) Реверсирует входную строку рекурсивно.
     * Базовый случай: null или строка длиной <= 1 возвращает саму себя.
     * Рекурсивный шаг: reverse(подстрока(1)) + символ(0).
     *
     * @param id Строка для реверсирования.
     * @return Реверсированная строка.
     */
    public String reverseString(String id) {
        if (id == null || id.length() <= 1) {
            return id;
        }
        // Рекурсивный вызов: реверсируем остаток строки + добавляем первый символ
        return reverseString(id.substring(1)) + id.charAt(0);
    }
}