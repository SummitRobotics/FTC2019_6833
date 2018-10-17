package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class ServoToggle {

    Servo servo;
    double[] poses = new double[2];
    boolean lastState;
    int flip = 0;

    public ServoToggle(Servo servo, double pos1, double pos2) {

        poses[0] = pos1;
        poses[1] = pos2;

        this.servo = servo;
        this.lastState = false;
    }

    public void setServoState(boolean currentState) {

        servo.setPosition(poses[this.flop(currentState)]);
    }

    private int flop(boolean currentState) {

        if (currentState && !lastState) {

            flip = 1 - flip;
        }

        lastState = currentState;

        return flip;
    }
}
