package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.hardware.Servo;

// Class to represent a polar vector.
public class Action {
    // Radius, Theta
    public double value;
    public int mode;
    public static final int MOVE = 0, TURN = 1, LIFT = 2, MARKER = 3;

    public Action(int mode, double value) {
        this.mode = mode;
        this.value = value;
    }
}
