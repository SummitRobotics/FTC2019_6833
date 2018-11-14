package org.firstinspires.ftc.teamcode.main;

// Class to represent an action (move, turn, etc)
public class Action {
    public double value;
    public enum Mode {
        MOVE, TURN, LIFT, MARKER, WAIT;
    }
    public Mode mode;

    public Action(Mode mode, double value) {
        this.mode = mode;
        this.value = value;
    }
}
