package com.exemple.kulynych;

import com.exemple.kulynych.model.ball.Ball;
import com.exemple.kulynych.model.Wall;
import com.exemple.kulynych.model.World;
import com.exemple.kulynych.model.ball.ControlledBall;
import com.exemple.kulynych.model.ball.SwingControlState;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class FirstWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private SwingView swingView;
    private World world;
    private SwingControlState state;
    private JMenuItem itemBall;
    private JMenuItem itemControlledBall;
    private JMenuItem itemWall;
    private JMenuItem delWalls;
    private JMenuItem delBalls;
    private JMenuItem clearWorld;

    private FirstWindow() {
        Container c = getContentPane(); // клиентская область окна
        c.setLayout(new BorderLayout()); // выбираем компоновщик
        state = new SwingControlState();
        createMenu();
        createWindow();
        buildWorld();
        this.addKeyListener(state);
    }

    private void createWindow() {
        setTitle("Ping - Pong");
        this.addComponentListener(new ComponentListener() {
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

        itemControlledBall = new JMenuItem("New ControlledBall");
        itemControlledBall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
        menu.add(itemControlledBall);
        itemControlledBall.addActionListener(this);

        itemWall = new JMenuItem("New Wall");
        itemWall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.ALT_MASK));
        menu.add(itemWall);
        itemWall.addActionListener(this);

        clearWorld = new JMenuItem("Clear World");
        clearWorld.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
        menu.add(clearWorld);
        clearWorld.addActionListener(this);

        delWalls = new JMenuItem("Del Wall's");
        menu.add(delWalls);
        delWalls.addActionListener(this);

        delBalls = new JMenuItem("Del Ball's");
        menu.add(delBalls);
        delBalls.addActionListener(this);

        menubar.add(menu);
        setJMenuBar(menubar);
    }

    private void buildWorld() {
        world = new World(
                this.getContentPane().getSize().width,
                this.getContentPane().getSize().height);
        swingView = new SwingView(world);
        this.add(swingView);

        Timer moveBallTimer = new Timer(20, e -> {
            world.tick(System.currentTimeMillis());
            swingView.repaint();
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
        if (e.getSource().equals(itemControlledBall)) {
            world.balls.add(new ControlledBall(
                    world,
                    5 + (int) (Math.random() * 60),
                    state));
        }
        if (e.getSource().equals(itemWall)) {
            world.walls.add(new Wall(
                    world,
                    5 + (int) (Math.random() * 60),
                    5 + (int) (Math.random() * 60)));
        }
        if (e.getSource().equals(clearWorld)) {
            world.balls.clear();
            world.walls.clear();
        }
        if (e.getSource().equals(delBalls)) {
            world.balls.clear();
        }
        if (e.getSource().equals(delWalls)) {
            world.walls.clear();
        }
    }

    public static void main(String args[]) {
        new FirstWindow();
    }
}
