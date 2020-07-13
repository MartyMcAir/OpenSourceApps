import sweeper.Box;
import sweeper.Coordinate;
import sweeper.Game;
import sweeper.Ranges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JavaSweeper extends JFrame {
    private final int COLS = 9; // 15 столбцов в игре (15 картинок)
    private final int ROWS = 9; // 1 строчка в игре
    private final int IMAGE_SIZE = 50;
    private JPanel panel;
    private Game game;
    private final int TOTAL_BOMBS = 3;
    private JLabel infoLabel; // информационное поле внизу игры

    public static void main(String[] args) {
        // write your code here
        JavaSweeper javaSweeper = new JavaSweeper();
    }

    private JavaSweeper() {
//        Ranges.setSize(COLS, ROWS);
        game = new Game(COLS, ROWS, TOTAL_BOMBS);
        game.start();

        setImage();
        initPanel();
        initLabel();
        initFrame();
    }

    private void initLabel() {
        infoLabel = new JLabel(getMessage());
        Font font = new Font("Georgia", Font.BOLD, 18);
        infoLabel.setFont(font);
        this.add(infoLabel, BorderLayout.SOUTH);
    }

    private void initPanel() {
        panel = new JPanel() {   // аноним класс
            @Override   // метод для рисования в панельке
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coordinate coordinate : Ranges.getAllCoordinates()) {
//                    g.drawImage(Box.FLAGGED.image, coordinate.x * IMAGE_SIZE, coordinate.y * IMAGE_SIZE, this);
                    // берем Image, из доступных values по индексу в массиве, а остоок отделения на кол-во ээлементов
                    // необходим что бы, не выходило за границы массива
//                    g.drawImage(Box.values()[(coordinate.x + coordinate.y) % Box.values().length].image,
//                            coordinate.x * IMAGE_SIZE, coordinate.y * IMAGE_SIZE, this);

                    g.drawImage(game.getBox(coordinate).image,
                            coordinate.x * IMAGE_SIZE, coordinate.y * IMAGE_SIZE, this);
                }
            }
        };

//        game.pressLeftButton(new Coordinate(4, 4)); // открываем клетку в центре игрового поля
//        game.pressRightButton(new Coordinate(7, 7)); // помечаем клетку флагом
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coordinate coordinate = new Coordinate(x, y);
                switch (e.getButton()) {
                    case MouseEvent.BUTTON1: // ЛКМ
                        game.pressLeftButton(coordinate);
                        break;
                    case MouseEvent.BUTTON3:
                        game.pressRightButton(coordinate);
                    case MouseEvent.BUTTON2: // говорит что это средняя кнопка, но у меня срабатывает как правая
                        game.start(); // рестарт игры
                        break;
                }
                infoLabel.setText(getMessage());
                panel.repaint(); // при действии мыши перерисовываем нашу панельку
            }
        });

        // размер панели = столбцы на * размер картинок сапера, ..
        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE));
        this.add(panel); // добавляем панель в окно
    }

    private void initFrame() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Java Sweeper"); // имя окна
        this.setResizable(false); // запрещаем изменение размера окна
        this.pack(); // для того, что бы размер окна был запакован под панельку
        // размещаем после упаковки что бы не было, рамок вокруг игрового поля
        this.setVisible(true); // делаем окно видимым _ по умолчанию false
        this.setLocationRelativeTo(null); // по центру, вызывать надо в конце, а иначе будет не совсем по центру
    }

    private void setImage() {
        for (Box box : Box.values())
            box.image = getImage((box.name().toLowerCase()));
        // ставим иконку
        this.setIconImage(getImage("icon"));
    }

    private String getMessage() {
        switch (game.getGameState()) {
            case BOMBED:
                return "Ba-Da-Boom! You Lose!";
            case WINNER:
                return "Congratulations! All bombs have been marked!";
            case PLAYED:   // по умолчанию или PLAYED
            default:
                if (game.getTotalFlags() == 0) return "Welcome!";
                else return "Think twice! Flagged " + game.getTotalFlags() + " of " + game.getTotalBombs() + " bombs.";
        }
    }

    private Image getImage(String name) {
        String fileName = "img/" + name + ".png";   // загрузка картинок с ресурса
        // исщет в папке, что помечена как ресурсы..
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return icon.getImage();
    }
}