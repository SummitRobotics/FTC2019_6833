package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.main.Hardware;
import org.firstinspires.ftc.teamcode.main.PVector;

//733.9539, -733.9539
//2482.1912
//
//695.76056, -695.76056
//1915.5548
//
//-61.434097, 61.434097
//4454.3467

public abstract class CoreAuto extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();
    Hardware robot = new Hardware();

    public void encoderLift(double speed, int degrees) {

        robot.leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        int frontTarget = robot.frontLift.getCurrentPosition() + degrees * (int)robot.LIFT_COUNTS_PER_ROT; // 2 needs to be replaced with # of rotations
        int backTarget = robot.backLift.getCurrentPosition() + degrees * (int)robot.LIFT_COUNTS_PER_ROT; // 2 needs to be replaced with # of rotations

        robot.backLift.setTargetPosition(backTarget);
        robot.frontLift.setTargetPosition(frontTarget);

        robot.backLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.backLift.setPower(speed); // test power val
        robot.frontLift.setPower(speed); // test power val

        while (robot.frontLift.isBusy() && robot.backLift.isBusy()) {

            // Display it for the driver.
            telemetry.addData("Path1",  "Running to %7d :%7d", frontTarget,  backTarget);
            telemetry.addData("Path2",  "Running at %7d :%7d",
                    robot.frontLift.getCurrentPosition(),
                    robot.backLift.getCurrentPosition());
            telemetry.update();
        }

        robot.frontLift.setPower(0);
        robot.backLift.setPower(0);

        robot.frontLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void runPath(PVector[] path) {
        for (PVector move : path) {
            encoderTurn(0.7, move.t);
            encoderForward(0.7, move.r);
        }
    }

    public void encoderForward(double speed, int ticks) {

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            robot.backLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.frontLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.leftDrive.setTargetPosition(ticks);
            robot.rightDrive.setTargetPosition(ticks);

            // Turn On RUN_TO_POSITION
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.leftDrive.setPower(speed);
            robot.rightDrive.setPower(speed);

            while (opModeIsActive() &&
                    (runtime.seconds() < 10) &&
                    (robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) {

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

            //  sleep(250);   // optional pause after each move
        }
    }

    public void encoderTurn(double speed, int ticks) {
        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            robot.backLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.frontLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.leftDrive.setTargetPosition(ticks);
            robot.rightDrive.setTargetPosition(-ticks);

            // Turn On RUN_TO_POSITION
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.leftDrive.setPower(speed);
            robot.rightDrive.setPower(-speed);

            while (opModeIsActive() &&
                    (runtime.seconds() < 10) &&
                    (robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) {

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

            //  sleep(250);   // optional pause after each move
        }
    }
}
