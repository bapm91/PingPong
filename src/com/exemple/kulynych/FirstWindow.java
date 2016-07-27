package com.exemple.kulynych;

import com.exemple.kulynych.model.Ball;
import com.exemple.kulynych.model.World;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class FirstWindow extends JFrame implements ActionListener {

    // серийный номер класса
    private static final long serialVersionUID = 1L;
    private SwingBallView ballView;
    private Timer moveBallTimer;
    private World world;

    private FirstWindow() {
        Container c = getContentPane(); // клиентская область окна
        c.setLayout(new BorderLayout()); // выбираем компоновщик


        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("File");

        JMenuItem itm = new JMenuItem("New");
        menu.add(itm);
        itm.addActionListener(this);

        itm = new JMenuItem("Open");
        itm.addActionListener(this);
        menu.add(itm);

        itm = new JMenuItem("Close");
        itm.addActionListener(this);
        menu.add(itm);

        // если нужен элемент меню с иконкой
        //itm = new JMenuItem("Close", new ImageIcon("image.gif"));
        //itm = new JMenuItem(new ImageIcon("image.gif"));

        // добавляем разделитель
        menu.add(new JSeparator());

        JMenu submenu = new JMenu("Sub");
        itm = new JMenuItem("Print");
        itm.addActionListener(this);
        submenu.add(itm);

        // Назначаем клавишу: ALT+E
        itm = new JMenuItem("Export");
        itm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                ActionEvent.ALT_MASK));
        itm.addActionListener(this);
        submenu.add(itm);

        menu.add(submenu);
        menubar.add(menu);
        setJMenuBar(menubar);

        // -------------------------------------------
        // настройка окна
        setTitle("Ping - Pong"); // заголовок окна
        // желательные размеры окна
        setPreferredSize(new Dimension(600, 400));
        // завершить приложение при закрытии окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // устанавливаем желательные размеры
        setVisible(true); // отображаем окно

        world = new World(getWidth(), getHeight());

        this.ballView = new SwingBallView(world);
        this.add(ballView);

        moveBallTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.tick(System.currentTimeMillis());
                ballView.repaint();
            }
        });
        moveBallTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }

    // запуск оконного приложения
    public static void main(String args[]) {
        new FirstWindow();
    }
}
