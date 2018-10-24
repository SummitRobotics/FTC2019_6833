package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.hardware.Servo;

// Class to represent a polar vector.
public class PVector {
    // Radius, Theta
    public double r, t;
    public Servo servo;

    public PVector(double r, double t) {

        this.r = r;
        this.t = t;
        this.servo = null;
    }

    public PVector(double r, Servo servo) {

        this.r = r;
        this.t = 0;
        this.servo = servo;
    }
}
