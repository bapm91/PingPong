package com.exemple.kulynych.model.ball;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SwingControlState extends KeyAdapter implements ControlState {

    private boolean isUp;
    private boolean isDown;
    private boolean isLeft;
    private boolean isRight;
    private boolean isSpace;

    @Override
    public void keyPressed(KeyEvent e) {
        keyHandler(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyHandler(e);
    }

    public void keyHandler(KeyEvent e) {
        switch (e.getID()) {
            case KeyEvent.KEY_PRESSED:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    isUp = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    isDown = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    isLeft = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    isRight = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    isSpace = true;
                }
                break;

            case KeyEvent.KEY_RELEASED:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    isUp = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    isDown = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    isLeft = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    isRight = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    isSpace = false;
                }
                break;
        }
    }

    @Override
    public boolean isUpPressed() {
        return isUp;
    }

    @Override
    public boolean isDownPressed() {
        return isDown;
    }

    @Override
    public boolean isLeftPressed() {
        return isLeft;
    }

    @Override
    public boolean isRightPressed() {
        return isRight;
    }

    @Override
    public boolean isFirePressed() {
        return isSpace;
    }
}