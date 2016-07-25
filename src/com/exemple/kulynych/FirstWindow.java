package com.exemple.kulynych;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
/**
 * @author DarkRaha
 *
 */
public class FirstWindow extends JFrame {

    // серийный номер класса
    private static final long serialVersionUID = 1L;

    public FirstWindow() {
        Container c = getContentPane(); // клиентская область окна
        c.setLayout(new BorderLayout()); // выбираем компоновщик

        // добавляем какие-нибудь дочерние элементы
        //MyComponent child = new MyComponent();
        /*MyPanel child= new MyPanel();
        c.add(child);
*/
        // -------------------------------------------
        // настройка окна
        setTitle("Example window"); // заголовок окна
        // желательные размеры окна
        setPreferredSize(new Dimension(640, 480));
        // завершить приложение при закрытии окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // устанавливаем желательные размеры
        setVisible(true); // отображаем окно
    }

    // запуск оконного приложения
    public static void main(String args[]) {
        new FirstWindow();
    }
}
