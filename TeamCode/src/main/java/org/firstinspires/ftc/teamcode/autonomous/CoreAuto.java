package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.main.Hardware;
import org.firstinspires.ftc.teamcode.main.PVector;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="mainAuto", group="Linear Opmode")
public class CoreAuto extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();
    Hardware robot = new Hardware();
    public PVector[] path = new PVector[7];

    public void runOpMode() {

        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");    //
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");
        telemetry.update();

        path[0] = new PVector(-3.981292, 47.59374);
        path[1] = new PVector(9.95072, 22.325);
        path[2] = new PVector(0.7, robot.markerDrop);
        path[3] = new PVector(7.088131, 21.58165);
        path[4] = new PVector(4.661365, 37.130745);
        path[5] = new PVector(4.7618604, 20.821053);
        path[6] = new PVector(-9.134702, 28.87537);

        runPath(path);

    }

//    public void encoderLift(double speed, int rotations) {
//
//        int liftTarget = robot.liftArm.getCurrentPosition() + rotations * (int)robot.LIFT_COUNTS_PER_ROT; // 2 needs to be replaced with # of rotations
//
//        robot.liftArm.setTargetPosition(liftTarget);
//        robot.liftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        robot.liftArm.setPower(speed); // test power val
//
//        while (robot.liftArm.isBusy()) {
//
//            // Display it for the driver.
//            telemetry.addData("Path1",  "Running to %7d", liftTarget);
//            telemetry.addData("Path2",  "Running at %7d :%7d",
//                    robot.liftArm.getCurrentPosition());
//            telemetry.update();
//        }
//
//        robot.liftArm.setPower(0);
//
//        robot.liftArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//    }

    public void runPath(PVector[] path) {

        //for (PVector move : path) {
        for (int i = 0; i < path.length; i++) {

            PVector move = path[i];
            telemetry.addData("For #", i);
            telemetry.update();
            if (move.servo == null) {
                encoderMove(0.7, move.t, -1);
                encoderMove(0.7, move.r, 1);
            } else if (move.servo != null) {

                servoMove(move.servo, move.r);
            }
        }
    }

    public void encoderMove(double speed, double inches, int mode) {

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            int ticks = (int)(inches * robot.DRIVE_COUNTS_PER_INCH);

            telemetry.addData("inches", inches);
            telemetry.addData("dcpi", robot.DRIVE_COUNTS_PER_INCH);
            telemetry.addData("ticks", ticks);
            telemetry.update();

            robot.leftDrive.setTargetPosition(robot.leftDrive.getCurrentPosition() + ticks);
            robot.rightDrive.setTargetPosition(robot.rightDrive.getCurrentPosition() +mode * ticks);

            // Turn On RUN_TO_POSITION
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.leftDrive.setPower(speed);
            robot.rightDrive.setPower(mode * speed);

            while (((robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) && runtime.seconds() < 5) {

                // Display it for the driver.
                //telemetry.addData("Path1",  "Running to %7d :%7d", ticks,  ticks);
                //telemetry.addData("Path2",  "Running at %7d :%7d",
                        //robot.leftDrive.getCurrentPosition(),
                        //robot.rightDrive.getCurrentPosition());
                //telemetry.update();
            }

            // Stop all motion;
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void servoMove(Servo servo, double goTo) {
        if (true) {
            servo.setPosition(goTo);
        }
    }
}
