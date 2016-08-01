package com.exemple.kulynych;

import com.exemple.kulynych.model.Ball;
import com.exemple.kulynych.model.Wall;
import com.exemple.kulynych.model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FirstWindow extends JFrame implements ActionListener, ComponentListener {

    private static final long serialVersionUID = 1L;

    private SwingView ballView;
    private SwingView wallView;
    private World world;
    private JMenuItem itemBall;
    private JMenuItem itemWall;

    @Override
    public void componentResized(ComponentEvent e) {
        world.setHeight(this.getContentPane().getSize().width);
        world.setWidth(this.getContentPane().getSize().height);
    }

    private FirstWindow() {
        Container c = getContentPane(); // клиентская область окна
        c.setLayout(new BorderLayout()); // выбираем компоновщик

        createWindow();
        createMenu();
        buildWorld();
    }

    private void createWindow() {
        setTitle("Ping - Pong");
        setPreferredSize(new Dimension(600, 400));
        setMinimumSize(new Dimension(600, 400));
        pack(); // устанавливаем желательные размеры
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createMenu() {
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("File");

        itemBall = new JMenuItem("New Ball");
        itemBall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.ALT_MASK));
        menu.add(itemBall);
        itemBall.addActionListener(this);
        itemWall = new JMenuItem("New Wall");
        itemWall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.ALT_MASK));
        menu.add(itemWall);
        itemWall.addActionListener(this);
        menubar.add(menu);
        setJMenuBar(menubar);
    }

    private void buildWorld() {
        world = new World(this.getContentPane().getSize().width,
                this.getContentPane().getSize().height);
        world.wall.add(new Wall(
                world,
                5 + (int) (Math.random() * 60),
                5 + (int) (Math.random() * 60)));
        wallView = new SwingView(world);
        this.add(wallView);

        world.balls.add(new Ball(
                world,
                5 + (int) (Math.random() * 60)));
        ballView = new SwingView(world);
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
        if (e.getSource().equals(itemBall)) {
            world.balls.add(new Ball(
                    world,
                    5 + (int) (Math.random() * 60)));
        }
        if (e.getSource().equals(itemWall)){
            world.wall.add(new Wall(
                    world,
                    5 + (int) (Math.random() * 60),
                    5 + (int) (Math.random() * 60)));
        }
    }

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
