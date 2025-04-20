package serialDeserial; // Пакет как на слайде 48

import java.io.Serializable; // Необходимо для сериализации

public class Course implements Serializable { // Шаг 1 + реализация Serializable

    // Шаг 4: Добавляем serialVersionUID для контроля версий
    // Крайне рекомендуется объявлять его явно.
    private static final long serialVersionUID = 1L;

    // Шаг 1: Поля класса
    private String name;
    private String type;
    private String courseCode;
    private int passingScore;

    // Шаг 2: Конструктор
    public Course(String name, String type, String courseCode, int passingScore) {
        this.name = name;
        this.type = type;
        this.courseCode = courseCode;
        this.passingScore = passingScore;
        System.out.println("Объект Course создан: " + name); // Для наглядности
    }

    // Шаг 3: Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(int passingScore) {
        this.passingScore = passingScore;
    }

    // Шаг 13: Метод toString() для удобного вывода
    @Override
    public String toString() {
        return "Name           : " + this.name +
                "\nType           : " + this.type +
                "\nCode           : " + this.courseCode +
                "\nPass Score     : " + this.passingScore;
    }
}