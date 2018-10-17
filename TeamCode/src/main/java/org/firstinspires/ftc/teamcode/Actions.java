
/**
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class Actions {

    private Hardware robot;

    public Actions(Hardware r) {
        robot = r;
    }

    private boolean liftLegsInit(double s, double rotations) {
        int frontGoal = robot.frontLift.getCurrentPosition() + (int)(rotations * robot.liftCountsPerRot);
        int rightGoal = robot.backLift.getCurrentPosition() + (int)(rotations * liftCountsPerRot);
        double speed = s;

        // Turn On RUN_TO_POSITION
        frontLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        return true;
    }
    private boolean liftLegs() {
        leftDrive.setPower(Math.abs(speed));
        rightDrive.setPower(Math.abs(speed));
        return true;
    }

    private void sample() {
        // Write sample code here. Might need to divide into multiple methods.
    }

    private void placeMarker() {
        // Move forward
        // Release team Marker
    }

    private void goToCrater() {
        // Move forward
        // Possibly use gyro to sense crater entrance?
    }
}
**/