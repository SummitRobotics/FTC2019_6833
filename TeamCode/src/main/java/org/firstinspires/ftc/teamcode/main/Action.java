package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.hardware.Servo;

// Class to represent a polar vector.
public class Action {
    // Radius, Theta
    public double moveInches, turnInches;
    public Servo servo;

    public Action(double rotation, double angle) {

        this.moveInches = rotation;
        this.turnInches = angle;
        this.servo = null;
    }

    public Action(double rotation, Servo servo) {

        this.moveInches = rotation;
        this.turnInches = 0;
        this.servo = servo;
    }
}
