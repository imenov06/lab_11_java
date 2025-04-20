
package converse;

// Импортируем класс MyName из пакета greeting
import greeting.MyName;

public class Hello {

    public static void main(String[] args) {
        // Создаем экземпляр класса MyName
        MyName nameObj = new MyName();

        // Вызываем метод sayHello() из объекта MyName
        System.out.print("Вызов из класса Hello: ");
        nameObj.sayHello(); // Выведет: Hi Jane
    }
}