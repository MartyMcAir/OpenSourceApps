package early_classes;

import sweeper.Box;
import sweeper.Coordinate;
import sweeper.Ranges;

import javax.swing.*;
import java.awt.*;

//import sweeper.Box.java;

public class JavaSweeper0 extends JFrame {
    private final int COLS = 15; // 15 столбцов в игре (15 картинок)
    private final int ROWS = 1; // 1 строчка в игре
    private final int IMAGE_SIZE = 50;
    private JPanel panel;

    public static void main(String[] args) {
        // write your code here
        JavaSweeper0 javaSweeper = new JavaSweeper0();
    }

    private JavaSweeper0() {
        Ranges.setSize(COLS, ROWS);
        setImage();
        initPanel();
        initFrame();
    }

    private void initPanel() {
        panel = new JPanel() {   // аноним класс
            @Override   // метод для рисования в панельке
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Box box : Box.values()) {
                    // 0
                    // выводим img панели с координатами 0 0, в this т.е. в текущее окно JFrame'а (JavaSweeper класса)
//                g.drawImage(getImage("bomb"), 0, 0, this);
//                g.drawImage(getImage("num1"), IMAGE_SIZE, 0, this);
//                g.drawLine(0, 0, 500, 300); // линия по диагонали
//                g.drawImage(getImage("num1"), IMAGE_SIZE, IMAGE_SIZE, this);
//                g.drawImage(getImage("num1"), 0, IMAGE_SIZE, this);
                    //  1
//                g.drawImage(getImage(box.name().toLowerCase()), box.ordinal() * IMAGE_SIZE, 0, this);
                    // 2 _ получаем картинки благодяря методу setImage, что заполняет enum Box картинками по порялку
//                    g.drawImage(box.image, box.ordinal() * IMAGE_SIZE, 0, this);

                    // 3
                    Coordinate coordinate = new Coordinate(box.ordinal(), 0);
                    g.drawImage(box.image, coordinate.x * IMAGE_SIZE, coordinate.y, this);
                }
            }
        };

        // размер панели = столбцы на * размер картинок сапера, ..
        panel.setPreferredSize(new Dimension(COLS * IMAGE_SIZE, ROWS * IMAGE_SIZE));
        this.add(panel); // добавляем панель в окно
    }

    private void initFrame() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Java Sweeper"); // имя окна
        this.setVisible(true); // делаем окно видимым _ по умолчанию false
        this.setResizable(false); // запрещаем изменение размера окна
        this.pack(); // для того, что бы размер окна был запакован под панельку
        this.setLocationRelativeTo(null); // по центру, вызывать надо в конце, а иначе будет не совсем по центру
    }

    private void setImage() {
        for (Box box : Box.values())
            box.image = getImage((box.name().toLowerCase()));
    }

    private Image getImage(String name) {
        // обычная загрузка картинки
//        ImageIcon icon = new ImageIcon("res/img/" + name + ".png");
        // когда папка res помечена как ресурс, то можно указывать просто путь без указания res..
        String fileName = "img/" + name + ".png";   // загрузка картинок с ресурса
        // исщет в папке, что помечена как ресурсы..
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return icon.getImage();
    }
}