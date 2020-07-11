package main;

import javax.swing.*;
import java.awt.*;

// рисовать т.е. onRapaint мы можем на панелях, которые представлены классом Jpanel
public class GameField extends JPanel {
    // когда каакой-то графичиский компоненте вырисовывается в Jpanel
    // то внутри него вызывается метод paintComponent, который принимает параметром класс Graphics
    // т.е. сам класс с помощью которого он и рисуется
    // __ Так вот связи с этим задача, динамечиски заменить поведение рисования на наш метод

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // отрисуем панельку
        GameWindow.onRepaint(g); // а после наш метод

        // для того что бы окно, мгновенно перерисовывалось, каждый раз показывая каплю
        repaint();  // что бы было понятно на каком она месте сейчас..
    }
}
