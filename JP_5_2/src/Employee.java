import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Класс представляет сотрудника и генерирует для него
 * имя пользователя, email и пароль на основе полного имени.
 */
public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; // <--- ДОБАВИТЬ ЭТУ СТРОКУ
    // --- Поля Экземпляра ---
    private String name;        // Полное имя (Имя Фамилия)
    private String username;    // Имя пользователя (имя.фамилия)
    private String email;       // Email (иФамилия@oracleacademy.Test)
    private String password;    // Сгенерированный пароль

    // --- Конструктор ---

    /**
     * Конструктор по умолчанию. Запрашивает имя пользователя и
     * инициирует генерацию остальных учетных данных.
     */
    public Employee() {
        System.out.println("--- Создание нового сотрудника ---");
        // Используем Scanner для получения имени от пользователя
        Scanner inputScanner = new Scanner(System.in); // Создаем Scanner здесь

        this.name = setName(inputScanner); // Получаем и валидируем имя
        this.username = setUserName(this.name); // Генерируем имя пользователя
        this.email = setEmail(this.name);       // Генерируем email
        this.password = setPassword(this.username); // Генерируем пароль

        // inputScanner.close(); // Закрываем Scanner после использования в конструкторе
        // ВАЖНО: если Scanner нужен где-то еще, управляйте им иначе.
        System.out.println("Учетные данные сотрудника сгенерированы.");
    }

    // --- Методы для получения и валидации имени ---

    /**
     * Запрашивает у пользователя полное имя (имя и фамилия через пробел)
     * и проверяет корректность ввода. Использует переданный Scanner.
     *
     * @param scanner Объект Scanner для чтения ввода.
     * @return Корректно введенное полное имя.
     */
    public String setName(Scanner scanner) {
        String inputName;
        int spaceCount;

        do {
            System.out.print("Введите имя и фамилию сотрудника (через ОДИН пробел): ");
            inputName = scanner.nextLine().trim(); // Читаем и убираем пробелы по краям

            if (inputName.isEmpty()) {
                System.out.println("ОШИБКА: Имя не может быть пустым.");
                spaceCount = -1; // Чтобы цикл продолжился
                continue; // Переход к следующей итерации цикла
            }

            spaceCount = countChars(inputName, ' ');

            if (spaceCount != 1) {
                System.out.println("ОШИБКА: Введите имя и фамилию, разделенные РОВНО ОДНИМ пробелом.");
            } else if (inputName.startsWith(" ") || inputName.endsWith(" ")) {
                // Эта проверка может быть излишней после trim(), но для ясности оставим
                System.out.println("ОШИБКА: Пробел не должен быть в начале или конце.");
                spaceCount = -1; // Считаем невалидным
            }

        } while (spaceCount != 1);

        return inputName;
    }

    /**
     * Приватный вспомогательный метод для подсчета символов в строке.
     *
     * @param str Строка для поиска.
     * @param ch  Символ для подсчета.
     * @return Количество вхождений символа ch в строке str.
     */
    private static int countChars(String str, char ch) {
        int count = 0;
        if (str == null || str.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    // --- Методы для генерации учетных данных ---

    /**
     * Генерирует имя пользователя из полного имени.
     * Формат: имя.фамилия (в нижнем регистре).
     *
     * @param name Полное имя сотрудника.
     * @return Сформатированное имя пользователя.
     */
    public String setUserName(String name) {
        if (name == null || name.indexOf(' ') == -1) {
            return "invalid.user"; // Обработка некорректного входа
        }
        // Заменяем пробел на точку и переводим в нижний регистр
        return name.toLowerCase().replace(' ', '.');
    }

    /**
     * Генерирует адрес электронной почты из полного имени.
     * Формат: первая_буква_имени + фамилия + @oracleacademy.Test
     *
     * @param name Полное имя сотрудника.
     * @return Сформатированный адрес электронной почты.
     */
    public String setEmail(String name) {
        // Проверка на валидность имени (наличие одного пробела важно)
        if (name == null || name.isEmpty() || name.indexOf(' ') <= 0 || name.indexOf(' ') == name.length() - 1) {
            return "invalid.format@oracleacademy.Test";
        }

        int spaceIndex = name.indexOf(' ');
        char firstInitial = name.toLowerCase().charAt(0);
        String lastName = name.substring(spaceIndex + 1).toLowerCase();
        final String EMAIL_SUFFIX = "@oracleacademy.test"; // Используем константу

        return firstInitial + lastName + EMAIL_SUFFIX;
    }

    /**
     * Генерирует сложный 8-значный пароль на основе имени пользователя.
     * Правила:
     * 1. Длина ровно 8 символов (дополняется '*' или обрезается).
     * 2. Гласные (aeiou, регистронезависимо) заменяются на '*'.
     * 3. Первая алфавитная буква пароля делается заглавной.
     *
     * @param username Имя пользователя.
     * @return Сгенерированный пароль.
     */
    public String setPassword(String username) {
        if (username == null || username.isEmpty()) {
            return "InvPwd**"; // Пароль по умолчанию при ошибке
        }

        // Используем StringBuilder для эффективного изменения строки
        StringBuilder passwordBase = new StringBuilder(username);

        // 1. Коррекция длины до 8 символов
        if (passwordBase.length() < 8) {
            while (passwordBase.length() < 8) {
                passwordBase.append('*');
            }
        } else if (passwordBase.length() > 8) {
            passwordBase.setLength(8); // Обрезаем до 8 символов
        }

        // 2. Замена гласных на '*'
        for (int i = 0; i < passwordBase.length(); i++) {
            // Проверяем символ в нижнем регистре для простоты
            char currentCharLower = Character.toLowerCase(passwordBase.charAt(i));
            if (currentCharLower == 'a' || currentCharLower == 'e' || currentCharLower == 'i' ||
                    currentCharLower == 'o' || currentCharLower == 'u') {
                passwordBase.setCharAt(i, '*'); // Заменяем в StringBuilder
            }
        }

        // 3. Первая алфавитная буква - заглавная
        boolean firstLetterCapitalized = false;
        for (int i = 0; i < passwordBase.length(); i++) {
            char currentChar = passwordBase.charAt(i);
            // Проверяем, является ли символ буквой
            if (Character.isLetter(currentChar)) {
                // Преобразуем первую найденную букву в верхний регистр
                passwordBase.setCharAt(i, Character.toUpperCase(currentChar));
                firstLetterCapitalized = true;
                break; // Выходим из цикла после преобразования первой буквы
            }
        }
        // Можно добавить проверку !firstLetterCapitalized и установить
        // какой-нибудь символ по умолчанию, если букв не нашлось,
        // но по заданию это не требуется.

        return passwordBase.toString(); // Преобразуем StringBuilder обратно в String
    }

    // --- Метод для отображения данных ---

    /**
     * Возвращает строковое представление объекта Employee
     * с форматированными деталями.
     *
     * @return Строка с данными сотрудника.
     */
    @Override
    public String toString() {
        // Используем String.format для красивого и выровненного вывода
        // %n - системно-независимый перенос строки
        // %s - плейсхолдер для строки
        return String.format(
                "Employee Details%n" +
                        "------------------------------------%n" + // Добавим разделитель
                        "Name             : %s%n" +
                        "Username         : %s%n" +
                        "Email            : %s%n" +
                        "Initial Password : %s%n" +
                        "------------------------------------",
                this.name, this.username, this.email, this.password
        );
    }
} // --- Конец класса Employee ---