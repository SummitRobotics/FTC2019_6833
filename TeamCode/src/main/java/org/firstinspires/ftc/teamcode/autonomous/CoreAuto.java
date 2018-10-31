package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.main.Hardware;
import org.firstinspires.ftc.teamcode.main.Action;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.ArrayList;

// All auto methods and main method to iterate through Action ArrayList
public abstract class CoreAuto extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();
    Hardware robot = new Hardware();
    private int FORWARD = 1, TURN = -1;

    // Method to iterate through Actions.
    void runActions(ArrayList<Action> actions) {

        for (Action move : actions) {

            switch (move.mode) {
                case LIFT: liftMast(0.7, move.value);

                case MOVE: encoderMove(0.7, move.value, FORWARD);

                case TURN: encoderMove(0.7, move.value, TURN);

                case MARKER: servoMove(robot.markerDrop, move.value);
            }

            sleep(200);
        }
    }

    // Method to lift the mast
    void liftMast(double speed, double rotations) {

        // Ensure that the opmode is active
        if (opModeIsActive()) {
            int ticks = (int)(rotations * robot.LIFT_COUNTS_PER_ROT);

            robot.liftMotor.setTargetPosition(robot.liftMotor.getCurrentPosition() + ticks);
            robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Reset time and start motion
            runtime.reset();
            robot.liftMotor.setPower(speed);

            while (((robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) &&
                    runtime.seconds() < 5 && opModeIsActive()) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d", ticks);
                telemetry.addData("Path2",  "Running at %7d",
                        robot.liftMotor.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion
            robot.liftMotor.setPower(0);
            robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    // Method to move the robot (forward or turn based on mode)
    private void encoderMove(double speed, double inches, int mode) {

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            int ticks = (int)(inches * robot.DRIVE_COUNTS_PER_INCH);

            robot.leftDrive.setTargetPosition(robot.leftDrive.getCurrentPosition() + ticks);
            robot.rightDrive.setTargetPosition(robot.rightDrive.getCurrentPosition() + mode * ticks);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.leftDrive.setPower(speed);
            robot.rightDrive.setPower(mode * speed);

            while (((robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) &&
                    runtime.seconds() < 5 && opModeIsActive()) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", ticks,  ticks);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        robot.leftDrive.getCurrentPosition(),
                        robot.rightDrive.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    // Method to move the specified servo
    private void servoMove(Servo servo, double goTo) {
        if (opModeIsActive()) {
            servo.setPosition(goTo);
        }
    }
}
