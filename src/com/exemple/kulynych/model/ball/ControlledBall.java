package com.exemple.kulynych.model.ball;

import com.exemple.kulynych.model.World;

public class ControlledBall extends Ball {

    public ControlledBall(World world, int diameter, ControlState controls) {
        super(world, diameter);
    }

    @Override
    public void tick(long time){

    }
}
//    ControlledBall controlled = new ControlledBall(mySwingControls);
//world.addBall(controlled);