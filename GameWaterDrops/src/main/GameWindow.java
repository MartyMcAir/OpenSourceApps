package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GameWindow extends JFrame {
    private static GameWindow gameWindow;
    private static Image imageBackGround, gameOver, drop;
    private static float drop_x_left = 200, drop_y_top = -100, drop_v_speed = 100;
    private static long lastFrameTime;   // время последнего кадра

    // drop1_left - отступ слева, drop1_top - отступ сверху
    private static float drop1_left = -10, drop1_top = -10, drop2_left = 900, drop2_top = -10;
    // для подсчета кол-ва кадров в одну сек..
    private static long frameCounter = 0, executeTime = System.currentTimeMillis();
    static GameField gameField = new GameField(); // игровое поле
    static String pathFolderIs = "";
//        String pathFolderIs = "../";
//        String pathFolderIs = "/source/";

    public GameWindow() throws HeadlessException {
        super();
        // лево ось y, право ось x
        this.setLocation(200, 100); // координаты окна относительно OS в которой запускается
        this.setSize(906, 478);

        this.setResizable(false);   // запрещаем изменение размера экрана
        this.setVisible(true);   // по умолчанию окно не видимое

        // при нахатии на крестик, app закроется
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws IOException {

        imageBackGround = ImageIO.read(GameWindow.class.getResourceAsStream(pathFolderIs + "background.png"));

        // инициализируем капли
        drop = ImageIO.read(GameWindow.class.getResourceAsStream(pathFolderIs + "drop.png"));

        gameOver = ImageIO.read(GameWindow.class.getResourceAsStream(pathFolderIs + "game_over.png"));

        gameWindow = new GameWindow(); // Запуск окна игры
        // ....................................
        lastFrameTime = System.nanoTime();
        // ....................................

        gameWindow.add(gameField); // добавили в окно

        // добавляем слушатель событий мыши, на игровое поле (которое внутри окна)
        gameField.addMouseListener(new MouseListener());
        // ....................................
    }

    public static void onRepaint(Graphics graphics) {
        // ....................................
        long currentTime = System.nanoTime();
        // 0.000_000_001f-перевод в секунды разницы времени
        float deltaTime = (currentTime - lastFrameTime) * 0.000_000_001f;
        lastFrameTime = currentTime; // время предыдущего кадра, текущее время

        // капля летит вниз со скоростью 200 пикселей в секунду
//        drop_test_top = drop_test_top + 200 * deltaTime;  // перемещается в зависимсоти от теущего кадра
        // который вычисляется по разнице (дельте) времени в наносек, переведенные в секунды
//        graphics.drawImage(drop, (int) drop_x_left, (int) drop_test_top, null);
        drop_y_top = drop_y_top + drop_v_speed * deltaTime; // c каждой след, перерисовкой т.е. кажд сек.

        graphics.drawImage(imageBackGround, 0, 0, null); // задний фон панели

        // падающая капля
        graphics.drawImage(drop, (int) (drop_x_left), (int) (drop_y_top), null);

// ....................................
        // при 10ти кадры заметно падают
//        for (int i = 0; i < 3; i++) dropsExperimental(graphics, deltaTime);

        countFrameOnTheSeconds();    // счетчик кадров за 1 сек _ (180 - 350)

        if (drop_y_top > gameWindow.getHeight()) // если "высота капли" больше высоты окна
            graphics.drawImage(gameOver, 280, 120, null); // конец игры
    }

    private static void dropsExperimental(Graphics graphics, float deltaTime) {
        //        graphics.fillOval(10, 10, 200, 100);
//        graphics.drawLine(200, 200, 400, 300);
        Image drop1 = null, drop2 = null;
        try {
            drop1 = ImageIO.read(GameWindow.class.getResourceAsStream(pathFolderIs + "drop.png"));
            drop2 = ImageIO.read(GameWindow.class.getResourceAsStream(pathFolderIs + "drop.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // падает сверху по диагонали, слева на право
        graphics.drawImage(drop1, (int) (drop1_left += 40 * deltaTime), (int) (drop1_top += 20 * deltaTime), null);
        // падает сверху по диагонали, справа на лево
        graphics.drawImage(drop2, (int) (drop2_left -= 40 * deltaTime), (int) (drop2_top += 20 * deltaTime), null);

//        graphics.drawImage(drop1, (int) (drop1_left += (Math.random() * 40) * deltaTime),
//                (int) (drop1_top += (Math.random() * 20) * deltaTime), null);
//        // падает сверху по диагонали, справа на лево
//        graphics.drawImage(drop2, (int) (drop2_left -= (Math.random() * 40) * deltaTime),
//                (int) (drop2_top += (Math.random() * 20) * deltaTime), null);
    }

    private static void countFrameOnTheSeconds() {
        frameCounter++;
        // еси со времени запуска прошла 1 сек.
        if (System.currentTimeMillis() >= (executeTime + 1000)) {
            executeTime = System.currentTimeMillis(); // времени запуска ставим тек время, и так до след секунды
            System.out.println("количество кадров за 1 сек: " + frameCounter);
            frameCounter = 0;   // обнуляем счетчик
        }
    }


    public static class MouseListener extends MouseAdapter {
        private int score;

        @Override
        public void mousePressed(MouseEvent e) {
            // получаем координаты курсора, x - слева отступ, y - сверху отступ
            int x = e.getX(), y = e.getY();

            // зная размеры рисунка капли определяем ее края, относительно текущего положения
            float drop_right = drop_x_left + drop.getWidth(null); // справа
            float drop_bottom = drop_y_top + drop.getHeight(null); // снизу

            // точка клика курсором, если попала в площадь капли
            boolean horizontal = x >= drop_x_left && x <= drop_right;
            boolean vertical = y >= drop_y_top && y <= drop_bottom;
            if (horizontal && vertical) {
                drop_y_top = -100; // кидаем каплю вверх
                // и в случайное место по вертикали
                drop_x_left = (int) (Math.random() * (gameField.getWidth() - drop.getWidth(null)));
                drop_v_speed += 10; // увеличиваем скорость падения
                score++;
                gameWindow.setTitle("You have drops: " + score);
            }
        }
    }
}