package org.example.versusjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Main extends Application {

    // --- Поля Экземпляра ---

    // Счетчики голосов
    private int cat1Score = 0;
    private int cat2Score = 0;

    // Названия команд
    private String cat1 = "Java Team";
    private String cat2 = "SQL Team";

    // Компоненты GUI (объявлены здесь, чтобы быть доступными в методах)
    private Label score1Lbl;
    private Label score2Lbl;
    private Button cat1Btn;
    private Button cat2Btn;
    private Button showBtn;
    private Button resetBtn;
    // Ссылки на ImageView для управления (хотя в этом коде они напрямую не меняются после создания)
    private ImageView jImgView;
    private ImageView sqlImgView;

    // Неизменяемая ширина для кнопок
    private final double BTN_WIDTH = 120; // Сделаем чуть шире для текста

    @Override
    public void start(Stage primaryStage) {
        try {
            // --- Загрузка Изображений ---
            // Важно: Папка 'img' должна быть Source Folder,
            // а пути начинаются с '/', если они от корня classpath.
            Image jImage = null;
            Image sqlImage = null;
            try {
                jImage = new Image(getClass().getResourceAsStream("/org/example/versusjfx/images/Datagirl.png"));
                sqlImage = new Image(getClass().getResourceAsStream("/org/example/versusjfx/images/Java_Duke.png"));
                if (jImage == null || sqlImage == null) {
                    System.err.println("Ошибка: Не удалось загрузить одно или оба изображения.");
                    System.err.println("Убедитесь, что папка 'img' является Source Folder и содержит Java_Duke.png и Datagirl.png");
                    // Можно либо выйти, либо продолжить без картинок
                }
            } catch (Exception e) {
                System.err.println("Исключение при загрузке изображений: " + e.getMessage());
                e.printStackTrace();
            }


            // --- Создание Компонентов ---

            // 1. Метки и Кнопки для "Java Team" (Категория 1)
            Label cat1Lbl = new Label(cat1); // Локальная переменная, т.к. текст не меняется
            cat1Lbl.setLayoutX(110);
            cat1Lbl.setLayoutY(10);
            cat1Lbl.setTextFill(Color.RED);

            // Метка для счета (инициализируется в поле класса)
            score1Lbl = new Label(String.valueOf(cat1Score)); // Используем поле класса
            score1Lbl.setLayoutX(130);
            score1Lbl.setLayoutY(40);
            score1Lbl.setTextFill(Color.RED);
            score1Lbl.setVisible(false); // Скрыта до показа результатов

            // Кнопка для голосования (инициализируется в поле класса)
            cat1Btn = new Button("Vote " + cat1);
            cat1Btn.setLayoutX(90);
            cat1Btn.setLayoutY(80);
            cat1Btn.setPrefWidth(BTN_WIDTH);

            // Изображение Java Duke
            jImgView = (jImage != null) ? new ImageView(jImage) : new ImageView(); // Используем поле класса
            jImgView.setX(10);  // Позиция X
            jImgView.setY(30);  // Позиция Y
            // Масштабирование, если нужно
            jImgView.setFitHeight(50);
            jImgView.setFitWidth(50);
            jImgView.setPreserveRatio(true);

            // 2. Метки и Кнопки для "SQL Team" (Категория 2)
            Label cat2Lbl = new Label(cat2); // Локальная
            cat2Lbl.setLayoutX(230);
            cat2Lbl.setLayoutY(10);
            cat2Lbl.setTextFill(Color.BLUE);

            score2Lbl = new Label(String.valueOf(cat2Score)); // Поле класса
            score2Lbl.setLayoutX(250);
            score2Lbl.setLayoutY(40);
            score2Lbl.setTextFill(Color.BLUE);
            score2Lbl.setVisible(false); // Скрыта

            cat2Btn = new Button("Vote " + cat2);
            cat2Btn.setLayoutX(210);
            cat2Btn.setLayoutY(80);
            cat2Btn.setPrefWidth(BTN_WIDTH);

            // Изображение SQL (Datagirl)
            sqlImgView = (sqlImage != null) ? new ImageView(sqlImage) : new ImageView(); // Поле класса
            sqlImgView.setX(320); // Правее кнопки
            sqlImgView.setY(30);
            sqlImgView.setFitHeight(50);
            sqlImgView.setFitWidth(50);
            sqlImgView.setPreserveRatio(true);


            // 3. Кнопки Управления (Show Votes, Reset Votes)
            showBtn = new Button("Show Votes");
            showBtn.setLayoutX(90); // Позиция под кнопками голосования
            showBtn.setLayoutY(120);
            showBtn.setPrefWidth(BTN_WIDTH);

            resetBtn = new Button("Reset Votes");
            resetBtn.setLayoutX(210);
            resetBtn.setLayoutY(120);
            resetBtn.setPrefWidth(BTN_WIDTH);
            resetBtn.setDisable(true); // Изначально неактивна

            // --- Настройка Обработчиков Событий ---

            // Лямбда для кнопки "Vote Java Team"
            cat1Btn.setOnAction((ActionEvent event) -> {
                cat1Score++;
                // Обновление метки убрано отсюда (шаг 8.a.v)
                // score1Lbl.setText(String.valueOf(cat1Score));
            });

            // Лямбда для кнопки "Vote SQL Team"
            cat2Btn.setOnAction((ActionEvent event) -> {
                cat2Score++;
                // Обновление метки убрано отсюда
                // score2Lbl.setText(String.valueOf(cat2Score));
            });

            // Лямбда для кнопки "Show Votes"
            showBtn.setOnAction((ActionEvent event) -> {
                showVotes(); // Показать/Обновить счетчики
                // Блокируем кнопки голосования и "Show", разблокируем "Reset"
                setBtnUse(true, true, true, false);
            });

            // Лямбда для кнопки "Reset Votes"
            resetBtn.setOnAction((ActionEvent event) -> {
                resetVotes(); // Сбросить счет и скрыть метки
                // Разблокируем кнопки голосования и "Show", блокируем "Reset"
                setBtnUse(false, false, false, true);
            });

            // --- Сборка Сцены ---

            // Используем Group для свободного размещения по X/Y
            Group root = new Group();
            // Добавляем ВСЕ видимые компоненты в контейнер
            root.getChildren().addAll(cat1Lbl, score1Lbl, cat1Btn, jImgView,
                    cat2Lbl, score2Lbl, cat2Btn, sqlImgView,
                    showBtn, resetBtn);

            // Создаем сцену, указывая корневой узел и размеры окна
            // Размеры окна подберем, чтобы все влезало (примерно 450x200)
            Scene scene = new Scene(root, 450, 200);

            // --- Настройка и Отображение Окна (Stage) ---
            primaryStage.setTitle("Voting Panel!"); // Заголовок окна
            // Установка минимальных размеров (не обязательно, но полезно)
            // primaryStage.setMinWidth(scene.getWidth());
            // primaryStage.setMinHeight(scene.getHeight());
            primaryStage.setScene(scene); // Устанавливаем сцену в окно
            primaryStage.show(); // Показываем окно

            // Устанавливаем начальное состояние кнопок
            setBtnUse(false, false, false, true); // Голосовать можно, Показать можно, Сбросить нельзя

        } catch(Exception e) {
            e.printStackTrace(); // Печатаем ошибку, если что-то пошло не так
        }
    }

    // --- Вспомогательные Методы ---

    /**
     * Обновляет текст меток счета и делает их видимыми.
     */
    private void showVotes() {
        score1Lbl.setText(String.valueOf(cat1Score));
        score2Lbl.setText(String.valueOf(cat2Score));
        score1Lbl.setVisible(true);
        score2Lbl.setVisible(true);
    }

    /**
     * Сбрасывает счетчики голосов в 0 и скрывает метки счета.
     */
    private void resetVotes() {
        cat1Score = 0;
        cat2Score = 0;
        // Обновлять текст не обязательно, т.к. метки скрываются
        // score1Lbl.setText("0");
        // score2Lbl.setText("0");
        score1Lbl.setVisible(false);
        score2Lbl.setVisible(false);
    }

    /**
     * Управляет состоянием активности (enabled/disabled) кнопок.
     * @param cat1Disable true, чтобы заблокировать кнопку cat1Btn
     * @param cat2Disable true, чтобы заблокировать кнопку cat2Btn
     * @param showDisable true, чтобы заблокировать кнопку showBtn
     * @param resetDisable true, чтобы заблокировать кнопку resetBtn
     */
    private void setBtnUse(boolean cat1Disable, boolean cat2Disable, boolean showDisable, boolean resetDisable) {
        cat1Btn.setDisable(cat1Disable);
        cat2Btn.setDisable(cat2Disable);
        showBtn.setDisable(showDisable);
        resetBtn.setDisable(resetDisable);
    }

    // --- Точка Входа ---
    /**
     * Главный метод, запускающий JavaFX приложение.
     * @param args Аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        launch(args); // Стандартный метод для старта JavaFX приложения
    }
}