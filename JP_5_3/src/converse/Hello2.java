package converse;

// Обратите внимание: оператор import отсутствует

public class Hello2 {

    public static void main(String[] args) {
        // Создаем экземпляр класса MyName, используя полное имя
        greeting.MyName nameObj = new greeting.MyName();

        // Вызываем метод setName, чтобы изменить имя на "Bob"
        nameObj.setName("Bob");

        // Вызываем метод sayHello() для отображения нового имени
        System.out.print("Вызов из класса Hello после изменения имени: ");
        nameObj.sayHello(); // Выведет: Hi Bob
    }
}