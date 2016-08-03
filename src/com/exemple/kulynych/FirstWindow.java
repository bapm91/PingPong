package com.exemple.kulynych;

import com.exemple.kulynych.model.Ball;
import com.exemple.kulynych.model.Wall;
import com.exemple.kulynych.model.World;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class FirstWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private SwingView ballView;
    private SwingView wallView;
    private World world;
    private JMenuItem itemBall;
    private JMenuItem itemWall;

    private FirstWindow() {
        Container c = getContentPane(); // клиентская область окна
        c.setLayout(new BorderLayout()); // выбираем компоновщик

        createMenu();
        createWindow();
        buildWorld();
    }

    private void createWindow() {
        setTitle("Ping - Pong");
        this.addComponentListener(new ComponentListener(){
            public void componentResized(ComponentEvent e) {
                world.setHeight(getContentPane().getSize().height);
                world.setWidth(getContentPane().getSize().width);
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
        });
        setPreferredSize(new Dimension(600, 400));
        setMinimumSize(new Dimension(600, 400));
        pack();
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
}
