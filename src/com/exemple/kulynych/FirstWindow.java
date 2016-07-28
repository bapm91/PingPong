package com.exemple.kulynych;

import com.exemple.kulynych.model.World;

import javax.swing.*;
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
    private JMenuItem itm;

    private FirstWindow() {
        Container c = getContentPane(); // клиентская область окна
        c.setLayout(new BorderLayout()); // выбираем компоновщик


        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("File");

        itm = new JMenuItem("New Ball");
        itm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                ActionEvent.ALT_MASK));
        menu.add(itm);
        itm.addActionListener(this);
        menubar.add(menu);
        setJMenuBar(menubar);

        // -------------------------------------------
        // настройка окна
        setTitle("Ping - Pong"); // заголовок окна
        // желательные размеры окна
        setPreferredSize(new Dimension(600, 400));
        setMinimumSize(new Dimension(600, 400));
        pack(); // устанавливаем желательные размеры
        // завершить приложение при закрытии окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        World world = new World(getWidth(), getHeight());
        SwingBallView ballView = new SwingBallView(world);
        this.add(ballView);
        Timer moveBallTimer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.tick(System.currentTimeMillis());
                ballView.repaint();
            }
        });
        moveBallTimer.start();
    }

    // запуск оконного приложения
    public static void main(String args[]) {
        new FirstWindow();
    }
}
