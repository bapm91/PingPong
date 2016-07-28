package com.exemple.kulynych;

import com.exemple.kulynych.model.Ball;
import com.exemple.kulynych.model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FirstWindow extends JFrame implements ActionListener, ComponentListener {

    private static final long serialVersionUID = 1L;

    private SwingBallView ballView;
    private Timer moveBallTimer;
    private World world;
    private JMenuItem itm;

    @Override
    public void componentResized(ComponentEvent e) {
        world.setHeight(this.getHeight());
        world.setWidth(this.getWidth());
    }

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

        setTitle("Ping - Pong");
        setPreferredSize(new Dimension(600, 400));
        setMinimumSize(new Dimension(600, 400));
        pack(); // устанавливаем желательные размеры
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        world = new World(getWidth(), getHeight());
        world.balls.add(new Ball(world, 30));
        this.add(new SwingBallView(world.balls.get(world.balls.size() - 1)));
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
        world.balls.add(new Ball(world, 30));
        this.add(new SwingBallView(world.balls.get(world.balls.size() - 1)));
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
