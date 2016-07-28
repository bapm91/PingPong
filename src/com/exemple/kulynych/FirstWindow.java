package com.exemple.kulynych;

import com.exemple.kulynych.model.Ball;
import com.exemple.kulynych.model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FirstWindow extends JFrame implements ActionListener, ComponentListener {

    private static final long serialVersionUID = 1L;

    private SwingBallView ballView;
    private World world;

    @Override
    public void componentResized(ComponentEvent e) {
        world.setHeight(getContentPane().getHeight());
        world.setWidth(getContentPane().getWidth());
    }

    private FirstWindow() {
        Container c = getContentPane(); // клиентская область окна
        c.setLayout(new BorderLayout()); // выбираем компоновщик

        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("File");

        JMenuItem itm = new JMenuItem("New Ball");
        itm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
        menu.add(itm);
        itm.addActionListener(this);
        menubar.add(menu);
        setJMenuBar(menubar);

        setTitle("Ping - Pong");
        setPreferredSize(new Dimension(600, 400));
        setMinimumSize(new Dimension(600, 400));
        pack(); // устанавливаем желательные размеры
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        world = new World(getContentPane().getWidth(), getContentPane().getHeight());
        world.balls.add(new Ball(world, 5 + (int) (Math.random() * 60)));
        ballView = new SwingBallView(world);
        this.add(ballView);

        Timer moveBallTimer = new Timer(20, e -> {
            world.tick(System.currentTimeMillis());
            ballView.repaint();
        });
        moveBallTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        world.balls.add(new Ball(world, 5 + (int) (Math.random() * 60)));
    }

    // запуск оконного приложения
    public static void main(String args[]) {
        new FirstWindow();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
