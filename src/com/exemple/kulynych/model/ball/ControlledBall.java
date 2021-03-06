package com.exemple.kulynych.model.ball;

import com.exemple.kulynych.model.World;

public class ControlledBall extends Ball {
    private SwingControlState controlState;

    public ControlledBall(World world, int diameter, SwingControlState controlState) {
        super(world, diameter);
        this.controlState = controlState;
    }

    @Override
    public void tick(long time) {
        super.tick(time);

        if (controlState.isUpPressed()) {
            this.getSpeed().y -= 20;
        }
        if (controlState.isDownPressed()) {
            this.getSpeed().y += 20;
        }
        if (controlState.isFirePressed()) {
            this.getSpeed().x += 100;
            this.getSpeed().y += 100;
        }
        if (controlState.isLeftPressed()) {
            this.getSpeed().x -= 20;
        }
        if (controlState.isRightPressed()) {
            this.getSpeed().x += 20;
        }
    }
}
