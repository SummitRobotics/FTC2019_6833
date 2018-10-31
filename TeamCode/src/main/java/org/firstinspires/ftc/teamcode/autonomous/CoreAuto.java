package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.main.Hardware;
import org.firstinspires.ftc.teamcode.main.Action;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class CoreAuto extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();
    Hardware robot = new Hardware();

    void runPath(Action[] path) {

        //for (PVector move : path) {
        for (int i = 0; i < path.length; i++) {

            Action move = path[i];
            telemetry.addData("For #", i);
            telemetry.update();
            if (move.servo == null) {
                encoderMove(0.7, move.turnInches, -1);
                encoderMove(0.7, move.moveInches, 1);
            } else {

                servoMove(move.servo, move.moveInches);
            }
        }
    }

    void liftMast(double speed, double rotations) {

        if (opModeIsActive()) {
            int ticks = (int)(rotations * robot.LIFT_COUNTS_PER_ROT);

            robot.liftMotor.setTargetPosition(robot.liftMotor.getCurrentPosition() + ticks);
            robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

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

            robot.liftMotor.setPower(0);
            robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(200);
        }
    }
    private void encoderMove(double speed, double inches, int mode) {

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            int ticks = (int)(inches * robot.DRIVE_COUNTS_PER_INCH);

            robot.leftDrive.setTargetPosition(robot.leftDrive.getCurrentPosition() + ticks);
            robot.rightDrive.setTargetPosition(robot.rightDrive.getCurrentPosition() + mode * ticks);
            // Turn On RUN_TO_POSITION
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

            sleep(200);
        }
    }

    private void servoMove(Servo servo, double goTo) {
        if (opModeIsActive()) {
            servo.setPosition(goTo);
            sleep(200);
        }
    }
}
